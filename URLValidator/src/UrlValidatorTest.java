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
	   
	   // Test of isValidQuery() function after observing unexpected behavior in
	   // isValid() function with valid query strings.
	   System.out.println("\nTESTING isValidQuery() FUNCTION\n");
	   String[] valid_test_queries = {"?name=test_name", "?", "?a=1&b=2&c=3"};
	   String[] invalid_test_queries = {"name?test", "&&&apples&bananas"};
	   boolean expected;
	   boolean observed;
	   String test_query;
	   
	   // Test valid query strings
	   for(int i = 0; i < valid_test_queries.length; i++){
		   test_query = valid_test_queries[i];
		   expected = true;
		   observed = urlVal.isValidQuery(test_query);
		   System.out.println("expected = " + expected + "\t\tobserved = " + observed + ":\t" + test_query);
	   }
	   
	   // Test invalid query strings
	   for(int i = 0; i < invalid_test_queries.length; i++){
		   test_query = invalid_test_queries[i];
		   expected = false;
		   observed = urlVal.isValidQuery(valid_test_queries[i]);
		   System.out.println("expected = " + expected + "\tobserved = " + observed + ":\t" + test_query);
	   }
	   
	   System.out.println("\n");
	   
	   
	   System.out.println("\nTESTING PORTS\n");
	   String[] validPorts = {"", ":80", ":22", ":65535", ":1024",":2",":1000"};
	   String testUrl;
	   String validUrl = "http://www.google.com";
	   
	   // Test valid ports
	   for(int i = 0; i < validPorts.length; i++){
		   expected = true;
		   testUrl = validUrl + validPorts[i];
		   observed = urlVal.isValid(testUrl);
		   System.out.println("expected = " + expected + "\t\tobserved = " + observed + ":\t" + testUrl);
	   }
	   
	   // Test invalid ports
	   String[] invalidPorts = {":70000", ":65536",	":-1", ":3a7", ":1023"};
	   for(int i = 0; i < invalidPorts.length; i++){
		   expected = false;
		   testUrl = validUrl + invalidPorts[i];
		   observed = urlVal.isValid(testUrl);
		   System.out.println("expected = " + expected + "\tobserved = " + observed + ":\t" + testUrl);
	   }
	   
	   System.out.println("\n");
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
			   "http://",
                           "https://",
                           "ftp://"
	   };
	   
	   final String[] validAuthorities = {
                           "localhost",  // This might need to be part of a manual test
			   "www.google.com",
                           "intel.com",
                           "centaur.dream.mystery.village.org",
                           "new.life",
                           "britain.co.uk",
                           "cape-verde.cv",
                           "192.168.0.1",
                           "127.0.0.1",
                           "0.0.0.0",
                           "255.255.255.255",
                           "45.54.45.54"
	   };
	   
	   final String[] validPorts = {
			   "",
			   ":80",
			   ":22",
			   ":65535",
			   ":1024",
			   ":2",
			   ":1000",
			   ":999"
	   };
	   
	   final String[] validPaths = {
			   "",
                           "/",
                           "/src/UrlValidatorTest.java",
                           "/src/word",
                           "/index.html",
                           "/txt/run/word",
                           "/435tewrgwer34_ewfwge",
                           "/erer-erer/erer-erer.mp3"
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
			   "a://b://",
                           "/",
                           "http:",
                           "ftp",
                           "https:/",
                           "http:\\\\"
	   };
	   
	   final String[] invalidAuthorities = {
			   "test..com",
                           "this.is%in.valid",
                           "",
                           ".org",
                           "google.com.",
                           "1.2",
                           "256.1.1.1",
                           "-1.0.0.1",
                           "0.-1.0.0",
                           ".1.8.7.5",
                           "1.8.7.5.",
                           "50.49.48.47.46",
                           "55-55-55-55",
                           "swollmoney",
                           "mys.tryl"
	   };
	   
	   final String[] invalidPorts = {
			   ":70000",		// Above max range
			   ":65536",		// Above max range
			   ":-1",		// Negative number, below min range
			   ":1023"		// Below min range
	   };
	   
	   final String[] invalidPaths = {
			   "/te?st",
			   "%ds%c",
			   "{path}/{path2}",
			   "/this^character/foo"
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
