/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package secParser;

import secParser.processors.SignedPartsElementsProcessor;
import secParser.processors.EncryptedPartsElementsProcessor;
import secParser.processors.AsymmetricBindingProcessor;
import secParser.processors.SymmetricBindingProcessor;
import secParser.processors.Wss10Processor;
import secParser.processors.Wss11Processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import org.apache.ws.policy.All;
import org.apache.ws.policy.Policy;
import org.apache.ws.policy.Assertion;
import org.apache.ws.policy.PrimitiveAssertion;
import org.apache.ws.policy.ExactlyOne;
import org.apache.ws.policy.util.PolicyReader;
import org.apache.ws.policy.util.PolicyFactory;

public class WSSPolicyProcessor {

	FileInputStream fis = null;

	PolicyReader prdr = null;

	Policy merged = null;

	SecurityPolicyToken topLevel = new SecurityPolicyToken("_TopLevel_",
			SecurityPolicyToken.COMPLEX_TOKEN, null);

	SecurityProcessorContext secProcessorContext = null;

	public static void main(String[] args) throws Exception {

		WSSPolicyProcessor processor = new WSSPolicyProcessor();
		if (!processor.setup()) {
			return;
		}
		String[] files = new String[1];
		files = new String[2];
		files[0] = "policy/examples/wsse/resources/SecurityPolicyBindings.xml";
		files[1] = "policy/examples/wsse/resources/SecurityPolicyMsg.xml";
		processor.go(files);
	}

	boolean setup() throws NoSuchMethodException {
		prdr = PolicyFactory.getPolicyReader(PolicyFactory.OM_POLICY_READER);

		/*
		 * Initialize the top level security policy token.
		 */
		SecurityPolicyToken spt = null;

		SignedPartsElementsProcessor spep = new SignedPartsElementsProcessor();
		spt = SecurityPolicy.signedParts.copy();
		spt.setProcessTokenMethod(spep);
		topLevel.setChildToken(spt);

		spt = SecurityPolicy.signedElements.copy();
		spt.setProcessTokenMethod(spep);
		topLevel.setChildToken(spt);

		EncryptedPartsElementsProcessor epep = new EncryptedPartsElementsProcessor();
		spt = SecurityPolicy.encryptedParts.copy();
		spt.setProcessTokenMethod(epep);
		topLevel.setChildToken(spt);

		spt = SecurityPolicy.encryptedElements.copy();
		spt.setProcessTokenMethod(epep);
		topLevel.setChildToken(spt);

		spt = SecurityPolicy.asymmetricBinding.copy();
		spt.setProcessTokenMethod(new AsymmetricBindingProcessor());
		topLevel.setChildToken(spt);

		spt = SecurityPolicy.symmetricBinding.copy();
		spt.setProcessTokenMethod(new SymmetricBindingProcessor());
		topLevel.setChildToken(spt);

		spt = SecurityPolicy.wss10.copy();
		spt.setProcessTokenMethod(new Wss10Processor());
		topLevel.setChildToken(spt);

		spt = SecurityPolicy.wss11.copy();
		spt.setProcessTokenMethod(new Wss11Processor());
		topLevel.setChildToken(spt);
        
        /*
         * Now get the initial PolicyEngineData, initialize it and put it onto
         * the PED stack.
         */
        PolicyEngineData ped = new PolicyEngineData();
        ped.initializeWithDefaults();
        
		/*
		 * Now get a context and push the top level token onto the token stack.
		 * The top level token is a special token that acts as anchor to start
		 * parsing.
		 */
		secProcessorContext = new SecurityProcessorContext();
		secProcessorContext.pushSecurityToken(topLevel);
        secProcessorContext.pushPolicyEngineData(ped);

		return true;
	}

	void go(String[] args) {

		merged = null;
		for (int i = 0; i < args.length; i++) {
			try {
				System.out.println(new File("").getAbsolutePath());
				fis = new FileInputStream(args[i]);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Policy newPolicy = prdr.readPolicy(fis);
			newPolicy = (Policy) newPolicy.normalize();

			if (merged == null) {
				merged = newPolicy;
			} else {
				merged = (Policy) merged.merge(newPolicy);
			}
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (processPolicy(merged)) {
			System.out.println("Security Policy sucessfully parsed");
		} else {
			System.out.println("Security Policy not sucessfully parsed");
		}
	}

	/**
	 * This method takes a normalized policy object, processes it and returns
	 * true if all assertion can be fulfilled.
	 * 
	 * Each policy must be nromalized accordig to the WS Policy framework
	 * specification. Therefore a policy has one child (wsp:ExactlyOne) that is
	 * an ExactlyOne. This child may contain one or more other terms
	 * (alternatives). To match the policy one of these terms (alternatives)
	 * must match. If none of the contained terms match this policy cannot be
	 * enforced.
	 * 
	 * @param policy
	 *            The policy to process
	 * @return True if this policy can be enforced by the policy enforcement
	 *         implmentation
	 */
	public boolean processPolicy(Policy policy) {

		if (!policy.isNormalized()) {
			throw new RuntimeException("Policy is not in normalized format");
		}

		ExactlyOne xor = (ExactlyOne) policy.getTerms()
				.get(0);
		List listOfPolicyAlternatives = xor.getTerms();

		boolean success = false;
		int numberOfAlternatives = listOfPolicyAlternatives.size();

		for (int i = 0; !success && i < numberOfAlternatives; i++) {
			All aPolicyAlternative = (All) listOfPolicyAlternatives
					.get(i);

			List listOfAssertions = aPolicyAlternative.getTerms();

			Iterator iterator = listOfAssertions.iterator();
			/*
			 * Loop over all assertions in this alternative. If all assertions
			 * can be fulfilled then we choose this alternative and signal a
			 * success.
			 */
			boolean all = true;
			while (all && iterator.hasNext()) {
				Assertion assertion = (Assertion) iterator.next();

				/*
				 * At this point we expect PrimitiveAssertions only.
				 */
				if (!(assertion instanceof PrimitiveAssertion)) {
					System.out.println("Got a unexpected assertion type: "
							+ assertion.getClass().getName());
					continue;
				}
				/*
				 * We need to pick only the primitive assertions which contain a
				 * WSSecurityPolicy policy assertion. For that we'll check the
				 * namespace of the primitive assertion
				 */
				PrimitiveAssertion pa = (PrimitiveAssertion) assertion;
				if (!(pa.getName().getNamespaceURI()
						.equals("http://schemas.xmlsoap.org/ws/2005/07/securitypolicy"))) {
					System.out.println("Got a unexpected assertion: "
							+ pa.getName().getLocalPart());
					continue;
				}
				all = processPrimitiveAssertion((PrimitiveAssertion) assertion);
			}
			/*
			 * copy the status of assertion processing. If all is true then this
			 * alternative is "success"ful
			 */
			success = all;
		}
		return success;
	}

	boolean processPrimitiveAssertion(PrimitiveAssertion pa) {
		boolean commit = true;

		commit = startPolicyTransaction(pa);

		List terms = pa.getTerms();
		if (commit && terms.size() > 0) {
			for (int i = 0; commit && i < terms.size(); i++) {
				Assertion assertion = (Assertion) pa.getTerms().get(i);
				if (assertion instanceof Policy) {
					commit = processPolicy((Policy) assertion);
				} else if (assertion instanceof PrimitiveAssertion) {
					commit = processPrimitiveAssertion((PrimitiveAssertion) assertion);
				}
			}
		}
		if (commit) {
			commitPolicyTransaction(pa);
		} else {
			abortPolicyTransaction(pa);
		}
		return commit;
	}

	public boolean startPolicyTransaction(PrimitiveAssertion pa) {

		String tokenName = pa.getName().getLocalPart();

		SecurityPolicyToken spt = null;

		/*
		 * Get the current security token from the context and check if the
		 * current token supports/contains this assertion as token. If yes set
		 * this token as current token (push onto stack), set the assertion into
		 * context and call the processing method for this token.
		 */
		SecurityPolicyToken currentToken = secProcessorContext
				.readCurrentSecurityToken();
		if (currentToken == null) {
			System.out
					.println("Internal error on token stack - No current token");
			System.exit(1);
		}
		spt = currentToken.getChildToken(tokenName);
		secProcessorContext.pushSecurityToken(spt);
		secProcessorContext.setAssertion(pa);
		secProcessorContext.setAction(SecurityProcessorContext.START);
        
        /*
         * Get the current state of the PolicyEngineData, make a copy of it
         * and push the copy onto the PED stack. The token method works on this
         * copy, adding its data.
         */
        PolicyEngineData ped = secProcessorContext.readCurrentPolicyEngineData();
        ped = ped.copy();
        secProcessorContext.pushPolicyEngineData(ped);
		if (spt == null) {
			System.out
					.println("Security token: '" + tokenName
							+ "' unknown in context of '"
							+ currentToken.getTokenName());
			return false;
		}
		boolean ret = false;

		try {
			ret = spt.invokeProcessTokenMethod(secProcessorContext);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			secProcessorContext.setAction(SecurityProcessorContext.NONE);
		}
		return ret;
	}

	public void abortPolicyTransaction(PrimitiveAssertion pa) {
		SecurityPolicyToken currentToken = secProcessorContext
				.readCurrentSecurityToken();
		if (currentToken == null) {
			secProcessorContext.popSecurityToken();
			System.out.println("Abort transaction because of unknown token: '"
					+ pa.getName().getLocalPart() + "'");
			return;
		}
		secProcessorContext.setAssertion(pa);
		secProcessorContext.setAction(SecurityProcessorContext.ABORT);
		try {
			currentToken.invokeProcessTokenMethod(secProcessorContext);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			secProcessorContext.setAction(SecurityProcessorContext.NONE);
			secProcessorContext.popSecurityToken();
            secProcessorContext.popPolicyEngineData();

		}
	}

	public void commitPolicyTransaction(PrimitiveAssertion pa) {
		SecurityPolicyToken currentToken = secProcessorContext
				.readCurrentSecurityToken();
		if (currentToken == null) {
			System.out
					.println("Internal error on token stack - Commiting an unknown token: "
							+ pa.getName().getLocalPart() + "'");
			System.exit(1);
		}
		secProcessorContext.setAssertion(pa);
		secProcessorContext.setAction(SecurityProcessorContext.COMMIT);
		try {
			currentToken.invokeProcessTokenMethod(secProcessorContext);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			secProcessorContext.setAction(SecurityProcessorContext.NONE);
            secProcessorContext.popSecurityToken();
            secProcessorContext.commitPolicyEngineData();
		}
	}
}
