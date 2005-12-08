/*
 * Copyright 2001-2004 The Apache Software Foundation.
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

package org.apache.ws.policy.util;

/**
 * @author Sanka Samaranayake (sanka@apache.org)
 */
public class PolicyFactory {
	private static PolicyFactory singleton = null;
	
	private PolicyFactory() {
	}
	
	public static PolicyFactory getInstance() {
		if (singleton == null) {
			singleton = new PolicyFactory();
		}
		return singleton;
	}
	
	public PolicyWriter getPolicyWriter() {
		return new PolicyWriter();
	}
	
	public PolicyReader getPolicyReader() {
		return new PolicyReader();
	}
	
	public PolicyReaderDOM getPolicyReaderDOM() {
		return new PolicyReaderDOM();
	}

}
