# Selenium + Java

# Steps for the setup

1) Download Eclipse IDE.
2) Clone the gitHub project.

# Number of test cases : 3 
1) First Test case : verify the number of email fields and after select the second field and enter the wrong and correct email id. Used testNG assertion for validation.
2) Second Test case : click on the feature button. verify bydefault ‘Recognition’ tab is selected.If this is passed , now select the "feature" tab and verify the feature tab is active or not.
3) Third test case : click on the contact us button, now send " slack" to intercom and verify the suggestion.if there is no suggestion, testNG assertion is there.

# Retry Logic : 
1) Implemented testNG retry logic. whenever any test case failed , the retry logic will start and retry the same test.

# Running the tests : 
1) Use the testNG.xml file for running the tests available under root directory.
