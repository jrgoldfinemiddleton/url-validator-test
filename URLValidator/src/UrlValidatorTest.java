/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import junit.framework.TestCase;





/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   
	   String[] validUrls = {
			   "http://www.amazon.com",
			   "ftp://www.amazon.com",
			   "file://www.amazon.com",
			   "www.amazon.com",
			   "amazon.com"
	   };
	   
	   String[] invalidUrls = {
			   ".com",
			   "com"
	   };
	   
	   System.out.println("TESTING VALID URLS\n");
	   
	   for (int i = 0; i != validUrls.length; ++i) {
		   String testUrl = validUrls[i];
		   
		   if (!urlVal.isValid(testUrl)) {
			   System.out.println(testUrl);
		   }
	   }
	   
	   System.out.println("\nTESTING INVALID URLS\n");
	   
	   for (int i = 0; i != invalidUrls.length; ++i) {
		   String testUrl = invalidUrls[i];
		   
		   if (urlVal.isValid(testUrl)) {
			   System.out.println(testUrl);
		   }
	   }
   }
   
   
   public void testYourFirstPartition()
   {
	   
   }
   
   public void testYourSecondPartition(){
	   
   }
   
   
   public void testIsValid()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   
	   final String[] validSchemes = {
			   
	   };
	   
	   final String[] validAuthorities = {
			   
	   };
	   
	   final String[] validPorts = {
			   
	   };
	   
	   final String[] validPaths = {
			   
	   };
	   
	   final String[] validQueries = {
			   
	   };
	   
	   final String[] invalidSchemes = {
			   
	   };
	   
	   final String[] invalidAuthorities = {
			   
	   };
	   
	   final String[] invalidPorts = {
			   
	   };
	   
	   final String[] invalidPaths = {
			   
	   };
	   
	   final String[] invalidQueries = {
			   
	   };	   
	   
	   final String[][] schemeSets = {
			   invalidSchemes,
			   validSchemes
	   };
	   
	   final String[][] authoritySets = {
			   invalidAuthorities,
			   validAuthorities
	   };
	   
	   final String[][] portSets = {
			   invalidPorts,
			   validPorts
	   };
	   
	   final String[][] pathSets = {
			   invalidPaths,
			   validPaths
	   };
	   
	   final String[][] querySets = {
			   invalidQueries,
			   validQueries
	   };
	   
	   for (int p1 = 0; p1 != 2; ++p1) {
		   for (int q1 = 0; q1 != schemeSets[p1].length; ++q1) {
			   for (int p2 = 0; p2 != 2; ++p2) {
				   for (int q2 = 0; q2 != authoritySets[p2].length; ++q2) {
					   for (int p3 = 0; p3 != 2; ++p3) {
						   for (int q3 = 0; q3 != portSets[p3].length; ++q3) {
							   for (int p4 = 0; p4 != 2; ++p4) {
								   for (int q4 = 0; q4 != pathSets[p4].length; ++q4) {
									   for (int p5 = 0; p5 != 2; ++p5) {
										   for (int q5 = 0; q5 != querySets[p5].length; ++q5) {
											   String testUrl = schemeSets[p1][q1]
													   + authoritySets[p2][q2]
													   + portSets[p3][q3]
													   + pathSets[p4][q4]
													   + querySets[p5][q5];
											   boolean expected = (q1 & q2 & q3 & q4 & q5) == 1;
											   boolean actual = urlVal.isValid(testUrl);
											   if (expected != actual) {
												   System.out.println(testUrl + ": " + actual);
											   }
										   }
									   }
								   }
							   }
						   }
					   }
				   }
			   }
		   }
	   }
   }
   
   public void testAnyOtherUnitTest()
   {
	   
   }
   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */
   

}
