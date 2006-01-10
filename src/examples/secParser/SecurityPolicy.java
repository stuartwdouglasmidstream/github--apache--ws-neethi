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
package examples.secParser;

import java.util.ArrayList;

public class SecurityPolicy {

	SecurityPolicyToken signedParts = new SecurityPolicyToken("SignedParts",
			SecurityPolicyToken.COMPLEX_TOKEN, null);

	SecurityPolicyToken header = new SecurityPolicyToken("Header",
			SecurityPolicyToken.SIMPLE_TOKEN, new String[] { "Name",
					"Namespace" });

	SecurityPolicyToken body = new SecurityPolicyToken("Body",
			SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken signedElements = new SecurityPolicyToken(
			"SignedElements", SecurityPolicyToken.COMPLEX_TOKEN,
			new String[] { "XPathVersion" });

	SecurityPolicyToken xPath = new SecurityPolicyToken(
			"XPath",
			SecurityPolicyToken.SIMPLE_TOKEN | SecurityPolicyToken.WITH_CONTENT,
			null);

	SecurityPolicyToken encryptedParts = new SecurityPolicyToken(
			"EncryptedParts", SecurityPolicyToken.COMPLEX_TOKEN, null);

	SecurityPolicyToken encryptedElements = new SecurityPolicyToken(
			"EncryptedElements", SecurityPolicyToken.COMPLEX_TOKEN,
			new String[] { "XPathVersion" });

	SecurityPolicyToken requiredElements = new SecurityPolicyToken(
			"RequiredElements", SecurityPolicyToken.COMPLEX_TOKEN,
			new String[] { "XPathVersion" });

	SecurityPolicyToken usernameToken = new SecurityPolicyToken(
			"UsernameToken", SecurityPolicyToken.COMPLEX_TOKEN,
			new String[] { "IncludeToken" });

	SecurityPolicyToken wssUsernameToken10 = new SecurityPolicyToken(
			"WssUsernameToken10", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken wssUsernameToken11 = new SecurityPolicyToken(
			"WssUsernameToken11", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken issuedToken = new SecurityPolicyToken("IssuedToken",
			SecurityPolicyToken.COMPLEX_TOKEN, new String[] { "IncludeToken" });

	SecurityPolicyToken issuer = new SecurityPolicyToken(
			"Issuer",
			SecurityPolicyToken.SIMPLE_TOKEN | SecurityPolicyToken.WITH_CONTENT,
			null);

	SecurityPolicyToken requestSecurityTokenTemplate = new SecurityPolicyToken(
			"RequestSecurityTokenTemplate", SecurityPolicyToken.COMPLEX_TOKEN
					| SecurityPolicyToken.WITH_CONTENT,
			new String[] { "TrustVersion" });

	SecurityPolicyToken requireDerivedKeys = new SecurityPolicyToken(
			"RequireDerivedKeys", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken requireExternalReference = new SecurityPolicyToken(
			"RequireExternalReference", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken requireInternalReference = new SecurityPolicyToken(
			"RequireInternalReference", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken x509Token = new SecurityPolicyToken("X509Token",
			SecurityPolicyToken.COMPLEX_TOKEN, new String[] { "IncludeToken" });

	SecurityPolicyToken requireKeyIdentifierReference = new SecurityPolicyToken(
			"RequireKeyIdentifierReference", SecurityPolicyToken.SIMPLE_TOKEN,
			null);

	SecurityPolicyToken requireIssuerSerialReference = new SecurityPolicyToken(
			"RequireIssuerSerialReference", SecurityPolicyToken.SIMPLE_TOKEN,
			null);

	SecurityPolicyToken requireEmbeddedTokenReference = new SecurityPolicyToken(
			"RequiredEmbeddedTokenReference", SecurityPolicyToken.SIMPLE_TOKEN,
			null);

	SecurityPolicyToken requireThumbprintReference = new SecurityPolicyToken(
			"RequireThumbprintReference", SecurityPolicyToken.SIMPLE_TOKEN,
			null);

	SecurityPolicyToken wssX509V1Token10 = new SecurityPolicyToken(
			"WssX509V1Token10", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken wssX509V3Token10 = new SecurityPolicyToken(
			"WssX509V3Token10", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken wssX509Pkcs7Token10 = new SecurityPolicyToken(
			"WssX509Pkcs7Token10", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken wssX509PkiPathV1Token10 = new SecurityPolicyToken(
			"WssX509PkiPathV1Token10", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken wssX509V1Token11 = new SecurityPolicyToken(
			"WssX509V1Token11", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken wssX509V3Token11 = new SecurityPolicyToken(
			"WssX509V3Token11", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken wssX509Pkcs7Token11 = new SecurityPolicyToken(
			"WssX509Pkcs7Token11", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken wssX509PkiPathV1Token11 = new SecurityPolicyToken(
			"WssX509PkiPathV1Token11", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken kerberosToken = new SecurityPolicyToken(
			"KerberosToken", SecurityPolicyToken.COMPLEX_TOKEN,
			new String[] { "IncludeToken" });

	// requireDerivedKeys already defined for issuedToken
	// requireKeyIdentifierReference already defined for x509Token
	SecurityPolicyToken wssKerberosV5ApReqToken11 = new SecurityPolicyToken(
			"WssKerberosV5ApReqToken11", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken wssGssKerberosV5ApReqToken11 = new SecurityPolicyToken(
			"WssGssKerberosV5ApReqToken11", SecurityPolicyToken.SIMPLE_TOKEN,
			null);

	SecurityPolicyToken spnegoContextToken = new SecurityPolicyToken(
			"SpnegoContextToken", SecurityPolicyToken.COMPLEX_TOKEN,
			new String[] { "IncludeToken" });

	// issuer already defined for issuedToken
	// requireDerivedKeys already defined for issuedToken

	SecurityPolicyToken securityContextToken = new SecurityPolicyToken(
			"SecurityContextToken", SecurityPolicyToken.COMPLEX_TOKEN,
			new String[] { "IncludeToken" });

	// requireDerivedKeys already defined for issuedToken
	SecurityPolicyToken requireExternalUriReference = new SecurityPolicyToken(
			"RequireExternalUriReference", SecurityPolicyToken.SIMPLE_TOKEN,
			null);

	SecurityPolicyToken sc10SecurityContextToken = new SecurityPolicyToken(
			"SC10SecurityContextToken", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken secureConversationToken = new SecurityPolicyToken(
			"SecureConversationToken", SecurityPolicyToken.COMPLEX_TOKEN,
			new String[] { "IncludeToken" });

	// issuer already defined for issuedToken
	// requireDerivedKeys already defined for issuedToken
	// requireExternalUriReference is already defined for SecurityContextToken
	// sc10SecurityContextToken is already defined for SecurityContextToken
	SecurityPolicyToken bootstrapPolicy = new SecurityPolicyToken(
			"BootstrapPolicy", SecurityPolicyToken.COMPLEX_TOKEN, null);

	SecurityPolicyToken samlToken = new SecurityPolicyToken("SamlToken",
			SecurityPolicyToken.COMPLEX_TOKEN, new String[] { "IncludeToken" });

	// requireDerivedKeys already defined for issuedToken
	// requireKeyIdentifierReference already defined for x509Token
	SecurityPolicyToken wssSamlV10Token10 = new SecurityPolicyToken(
			"WssSamlV10Token10", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken wssSamlV11Token10 = new SecurityPolicyToken(
			"WssSamlV11Token10", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken wssSamlV10Token11 = new SecurityPolicyToken(
			"WssSamlV10Token11", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken wssSamlV11Token11 = new SecurityPolicyToken(
			"WssSamlV11Token11", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken wssSamlV20Token11 = new SecurityPolicyToken(
			"WssSamlV20Token11", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken relToken = new SecurityPolicyToken("RelToken",
			SecurityPolicyToken.COMPLEX_TOKEN, new String[] { "IncludeToken" });

	// requireDerivedKeys already defined for issuedToken
	// requireKeyIdentifierReference already defined for x509Token
	SecurityPolicyToken wssRelV10Token10 = new SecurityPolicyToken(
			"WssRelV10Token10", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken wssRelV20Token10 = new SecurityPolicyToken(
			"WssRelV20Token10", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken wssRelV10Token11 = new SecurityPolicyToken(
			"WssRelV10Token11", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken wssRelV20Token11 = new SecurityPolicyToken(
			"WssRelV20Token11", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken httpsToken = new SecurityPolicyToken("RelToken",
			SecurityPolicyToken.COMPLEX_TOKEN,
			new String[] { "RequireClientCertificate" });

	SecurityPolicyToken algorithmSuite = new SecurityPolicyToken("RelToken",
			SecurityPolicyToken.COMPLEX_TOKEN, null);

	SecurityPolicyToken basic256 = new SecurityPolicyToken("Basic256",
			SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken basic192 = new SecurityPolicyToken("Basic192",
			SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken basic128 = new SecurityPolicyToken("Basic128",
			SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken tripleDes = new SecurityPolicyToken("TripleDes",
			SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken basic256Rsa15 = new SecurityPolicyToken(
			"Basic256Rsa15", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken basic192Rsa15 = new SecurityPolicyToken(
			"Basic192Rsa15", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken basic128Rsa15 = new SecurityPolicyToken(
			"Basic128Rsa15", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken tripleDesRsa15 = new SecurityPolicyToken(
			"TripleDesRsa15", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken basic256Sha256 = new SecurityPolicyToken(
			"Basic256Sha256", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken basic192Sha256 = new SecurityPolicyToken(
			"Basic192Sha256", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken basic128Sha256 = new SecurityPolicyToken(
			"Basic128Sha256", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken tripleDesSha256 = new SecurityPolicyToken(
			"TripleDesSha256", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken basic256Sha256Rsa15 = new SecurityPolicyToken(
			"Basic256Sha256Rsa15", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken basic192Sha256Rsa15 = new SecurityPolicyToken(
			"Basic192Sha256Rsa15", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken basic128Sha256Rsa15 = new SecurityPolicyToken(
			"Basic128Sha256Rsa15", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken tripleDesSha256Rsa15 = new SecurityPolicyToken(
			"TripleDesSha256Rsa15", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken inclusiveC14N = new SecurityPolicyToken(
			"InclusiveC14N", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken soapNormalization10 = new SecurityPolicyToken(
			"SoapNormalization10", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken strTransform10 = new SecurityPolicyToken(
			"StrTransform10", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken xPath10 = new SecurityPolicyToken("XPath10",
			SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken xPathFilter20 = new SecurityPolicyToken(
			"XPathFilter20", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken layout = new SecurityPolicyToken("Layout",
			SecurityPolicyToken.COMPLEX_TOKEN, null);

	SecurityPolicyToken strict = new SecurityPolicyToken("Strict",
			SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken lax = new SecurityPolicyToken("Lax",
			SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken laxTsFirst = new SecurityPolicyToken("LaxTsFirst",
			SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken laxTsLast = new SecurityPolicyToken("LaxTsLast",
			SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken transportBinding = new SecurityPolicyToken(
			"TransportBinding", SecurityPolicyToken.COMPLEX_TOKEN, null);

	SecurityPolicyToken transportToken = new SecurityPolicyToken(
			"TransportToken", SecurityPolicyToken.COMPLEX_TOKEN, null);

	// algorithmSuite and layout see above
	SecurityPolicyToken includeTimestamp = new SecurityPolicyToken(
			"IncludeTimestamp", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken symmetricBinding = new SecurityPolicyToken(
			"SymmetricBinding", SecurityPolicyToken.COMPLEX_TOKEN, null);

	SecurityPolicyToken encryptionToken = new SecurityPolicyToken(
			"EncryptionToken", SecurityPolicyToken.COMPLEX_TOKEN, null);

	SecurityPolicyToken signatureToken = new SecurityPolicyToken(
			"SignatureToken", SecurityPolicyToken.COMPLEX_TOKEN, null);

	SecurityPolicyToken protectionToken = new SecurityPolicyToken(
			"ProtectionToken", SecurityPolicyToken.COMPLEX_TOKEN, null);

	// algorithmSuite and layout see above
	// includeTimestamp already defined for transport binding
	SecurityPolicyToken encryptBeforeSigning = new SecurityPolicyToken(
			"EncryptBeforeSigning", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken encryptSignature = new SecurityPolicyToken(
			"EncryptSignature", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken protectTokens = new SecurityPolicyToken(
			"ProtectTokens", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken onlySignEntireHeadersAndBody = new SecurityPolicyToken(
			"OnlySignEntireHeadersAndBody", SecurityPolicyToken.SIMPLE_TOKEN,
			null);

	SecurityPolicyToken asymmetricBinding = new SecurityPolicyToken(
			"AsymmetricBinding", SecurityPolicyToken.COMPLEX_TOKEN, null);

	SecurityPolicyToken initiatorToken = new SecurityPolicyToken(
			"InitiatorToken", SecurityPolicyToken.COMPLEX_TOKEN, null);

	SecurityPolicyToken receipientToken = new SecurityPolicyToken(
			"ReceipientToken", SecurityPolicyToken.COMPLEX_TOKEN, null);

	// all other tokens for asymmetric already defined above

	SecurityPolicyToken supportingTokens = new SecurityPolicyToken(
			"SupportingTokens", SecurityPolicyToken.COMPLEX_TOKEN, null);

	SecurityPolicyToken signedSupportingTokens = new SecurityPolicyToken(
			"SignedSupportingTokens", SecurityPolicyToken.COMPLEX_TOKEN, null);

	SecurityPolicyToken endorsingSupportingTokens = new SecurityPolicyToken(
			"EndorsingSupportingTokens", SecurityPolicyToken.COMPLEX_TOKEN,
			null);

	SecurityPolicyToken signedEndorsingSupportingTokens = new SecurityPolicyToken(
			"SignedEndorsingSupportingTokens",
			SecurityPolicyToken.COMPLEX_TOKEN, null);

	SecurityPolicyToken wss10 = new SecurityPolicyToken("wss10",
			SecurityPolicyToken.COMPLEX_TOKEN, null);

	SecurityPolicyToken mustSupportRefKeyIdentifier = new SecurityPolicyToken(
			"MustSupportRefKeyIdentifier", SecurityPolicyToken.SIMPLE_TOKEN,
			null);

	SecurityPolicyToken mustSupportRefIssuerSerial = new SecurityPolicyToken(
			"MustSupportRefIssuerSerial", SecurityPolicyToken.SIMPLE_TOKEN,
			null);

	SecurityPolicyToken mustSupportRefExternalUri = new SecurityPolicyToken(
			"MustSupportRefExternalUri", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken mustSupportRefEmbeddedToken = new SecurityPolicyToken(
			"MustSupportRefEmbeddedToken", SecurityPolicyToken.SIMPLE_TOKEN,
			null);

	SecurityPolicyToken wss11 = new SecurityPolicyToken("wss11",
			SecurityPolicyToken.COMPLEX_TOKEN, null);

	// all from wss10
	SecurityPolicyToken mustSupportRefKeyThumbprint = new SecurityPolicyToken(
			"mustSupportRefKeyThumbprint", SecurityPolicyToken.SIMPLE_TOKEN,
			null);

	SecurityPolicyToken mustSupportRefKeyEncryptedKey = new SecurityPolicyToken(
			"mustSupportRefKeyEncryptedKey", SecurityPolicyToken.SIMPLE_TOKEN,
			null);

	SecurityPolicyToken requireSignatureConfirmation = new SecurityPolicyToken(
			"requireSignatureConfirmation", SecurityPolicyToken.SIMPLE_TOKEN,
			null);

	SecurityPolicyToken trust10 = new SecurityPolicyToken("trust10",
			SecurityPolicyToken.COMPLEX_TOKEN, null);

	SecurityPolicyToken mustSupportClientChallenge = new SecurityPolicyToken(
			"mustSupportClientChallenge", SecurityPolicyToken.SIMPLE_TOKEN,
			null);

	SecurityPolicyToken mustSupportServerChallenge = new SecurityPolicyToken(
			"mustSupportServerChallenge", SecurityPolicyToken.SIMPLE_TOKEN,
			null);

	SecurityPolicyToken requireClientEntropy = new SecurityPolicyToken(
			"requireClientEntropy", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken requireServerEntropy = new SecurityPolicyToken(
			"requireServerEntropy", SecurityPolicyToken.SIMPLE_TOKEN, null);

	SecurityPolicyToken mustSupportIssuedTokens = new SecurityPolicyToken(
			"mustSupportIssuedTokens", SecurityPolicyToken.SIMPLE_TOKEN, null);

	String includeNever = "http://schemas.xmlsoap.org/ws/2005/07/securitypolicy/IncludeToken/Never";

	String includeOnce = "http://schemas.xmlsoap.org/ws/2005/07/securitypolicy/IncludeToken/Once";

	String includeAlways = "http://schemas.xmlsoap.org/ws/2005/07/securitypolicy/IncludeToken/Always";

}
