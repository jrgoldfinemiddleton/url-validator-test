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
			   "amazon.com",
			   "https://courses.ecampus.oregonstate.edu/index.php?video=cs372/38.mp4"
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
			   "",
			   "http://"
	   };
	   
	   final String[] validAuthorities = {
			   "www.google.com"
	   };
	   
	   final String[] validPorts = {
			   "",
			   ":80",
			   ":22",
			   ":65535",
			   ":1024"
	   };
	   
	   final String[] validPaths = {
			   ""
	   };
	   
	   final String[] validQueries = {
			   // These all cause every url they're in to evaluate to invalid.
			   // I think these should probably evaluate to true in the url validator, even if
			   // they're not actually valid parameters for the given url. My understanding is
			   // that isValid() should just check that the structure of the url is correct, not
			   // whether it's a currently active/valid site.
			   "?user=test_name",	// Single param
			   "?a=1&b=2&c=3",		// Multiple params
			   "?"					// Empty params
	   };
	   
	   final String[] invalidSchemes = {
			   "a://b://"
	   };
	   
	   final String[] invalidAuthorities = {
			   ""
	   };
	   
	   final String[] invalidPorts = {
			   ":70000",		// Above max range
			   ":65536",		// Above max range
			   ":-1",		// Negative number, below min range
			   ":1023"		// Below min range
	   };
	   
	   final String[] invalidPaths = {
			   "/te?st"
	   };
	   
	   final String[] invalidQueries = {
			   "&id=567?234&",								// Begins with '&' character, must begin query with '?'
			   "name=test_name",							// No '?' character to begin the query
			   "?user=test_user?password=test_password",	// (2) '?' characters in query
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
	   
	   /* invalid schemes then valid */
	   for (int p1 = 0; p1 != 2; ++p1) {
		   for (int q1 = 0; q1 != schemeSets[p1].length; ++q1) {
			   
			   /* invalid authorities then valid */
			   for (int p2 = 0; p2 != 2; ++p2) {
				   for (int q2 = 0; q2 != authoritySets[p2].length; ++q2) {
					   
					   /* invalid ports then valid */
					   for (int p3 = 0; p3 != 2; ++p3) {
						   for (int q3 = 0; q3 != portSets[p3].length; ++q3) {
							   
							   /* invalid paths then valid */
							   for (int p4 = 0; p4 != 2; ++p4) {
								   for (int q4 = 0; q4 != pathSets[p4].length; ++q4) {
									   
									   /* invalid queries then valid */
									   for (int p5 = 0; p5 != 2; ++p5) {
										   for (int q5 = 0; q5 != querySets[p5].length; ++q5) {
											   
											   /* make a combo of parts */
											   String testUrl = schemeSets[p1][q1]
													   + authoritySets[p2][q2]
													   + portSets[p3][q3]
													   + pathSets[p4][q4]
													   + querySets[p5][q5];
											   
											   /* if any part is invalid, whole URL should be invalid */
											   boolean expected = (p1 & p2 & p3 & p4 & p5) == 1;
											   
											   /* print unexpected results only */
											   boolean actual = urlVal.isValid(testUrl);
											   if (expected != actual) {
												   System.out.println(actual + ":\t" + testUrl);
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
