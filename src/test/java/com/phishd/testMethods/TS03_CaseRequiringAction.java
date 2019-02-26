package com.phishd.testMethods;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.commons.codec.language.DaitchMokotoffSoundex;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.phishd.pageObjects.CasesRequiringActionPage;
import com.phishd.pageObjects.DashBoardPage;
import com.phishd.pageObjects.LiveReportingPage;
import com.phishd.pageObjects.LoginPage;
import com.phishd.testBase.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class TS03_CaseRequiringAction extends TestBase {

	LoginPage loginPage; // page objects which we had defined in test script
	DashBoardPage dashBoardPage; // page objects which we had defined in test script
	CasesRequiringActionPage casesRequiringActionPage;

	public TS03_CaseRequiringAction() {
		super();

	}

	@DataProvider(name = "searchData")
	public String[][] getTestData() {
		String[][] testRecords = getData("testData.xlsx", "searchData");
		return testRecords;
	}

	@DataProvider(name = "requiringCases")
	public String[][] requiringCases() {
		String[][] testRecords = getData("testData.xlsx", "requiringCases");
		return testRecords;
	}

	@DataProvider(name = "reporterSearch")
	public String[][] reporterSearch() {
		String[][] testRecords = getData("testData.xlsx", "reporterSearch");
		return testRecords;
	}

	@DataProvider(name = "clientName")
	public String[][] getClientData() {
		String[][] testRecords = getData("testData.xlsx", "clientName");
		return testRecords;
	}
	
	@DataProvider(name = "InvalidReportedBy")
	public String[][] InvalidReportedBy() {
		String[][] testRecords = getData("testData.xlsx", "InvalidReportedBy");
		return testRecords;
	}
	
	@DataProvider(name = "ReportedAt")
	public String[][] ReportedAt() {
		String[][] testRecords = getData("testData.xlsx", "ReportedAt");
		return testRecords;
	}
	
	@DataProvider(name = "Subject")
	public String[][] Subject() {
		String[][] testRecords = getData("testData.xlsx", "Subject");
		return testRecords;
	}
	

@DataProvider(name = "InvalidSubject")
	public String[][] InvalidSubject() {
		String[][] testRecords = getData("testData.xlsx", "InvalidSubject");
		return testRecords;
	}

	@BeforeSuite
	public void setup() {
		try {
			initialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReports.html");
		test = report.startTest("TS01_LoginTest");
		loginPage = new LoginPage(driver);
		dashBoardPage = new DashBoardPage(driver);
		dashBoardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		casesRequiringActionPage = new CasesRequiringActionPage(driver);
	}

	@Test(alwaysRun = true, priority = 0, enabled = true, dataProvider = "clientName")
	public void VerifyClientFromDropDown(String clientName, String runMode) {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {
			test.log(LogStatus.PASS, "****TS03-01 Validate Successfully Login to Informer Analytics Application****");
			test.log(LogStatus.PASS, "****TS03-02 Validate Current Client Selection from Drop down****" + clientName);

			dashBoardPage.setClientName(clientName);

			test.log(LogStatus.PASS, "Validating Client Name" + dashBoardPage.isClientNameDisplayed().getText());
			Assert.assertEquals(dashBoardPage.isClientNameDisplayed().getText(), clientName);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "failed to select current client from the drop down list");
		}
	}

	@Test(enabled = true, priority = 1)
	public void verifyNumberOfRequiringActionCases() throws InterruptedException {

		try {
			test.log(LogStatus.PASS,
					"****TS03-03 Validating Number of Cases Requiring Action match before and AFter Click****");
			test.log(LogStatus.PASS, "User click on Cases Requiring Action Tab in Left Menu");
			casesRequiringActionPage.ClickOnCasesRecognitionTab();
			System.out.println("Successfully click on Cases requiring action");
			Thread.sleep(2500);
			test.log(LogStatus.PASS, "Validating the title of the page in Cases Requiring Action");
			Assert.assertEquals(casesRequiringActionPage.getTittle(), "Report Cases - Requiring Action |");
			test.log(LogStatus.PASS, "Validating No. of cases match before and after click In Cases Requiring Action");
			assertTrue(casesRequiringActionPage.isCaseReqActionCountSame());
			test.log(LogStatus.PASS, "Validating Show All Cases Tab displayed and enabled in Cases Requiring Action");
			casesRequiringActionPage.clickshowAllCases();
			test.log(LogStatus.PASS, "Validating Show All Cases Tab after clicking open Report Case Page");
			Assert.assertEquals(casesRequiringActionPage.getTittle(), "Report Cases |");

			test.log(LogStatus.PASS, "Verify Clicking on Cases Requiring Action tab that brings back ");
			casesRequiringActionPage.ClickOnCasesRecognitionTab();
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Number of Requision cases are not matching");
			// TODO: handle exception
		}
	}

	@Test(enabled = true, priority = 2, dataProvider = "reporterSearch")
	public void verifySearchReportedBy(String username, String runMode) throws InterruptedException {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		Thread.sleep(2000);
		try {
			test.log(LogStatus.PASS, "****TS03-04 Validating Valid Reported By Search Result****");
			test.log(LogStatus.PASS, "User click on Cases Requiring Action Tab in Left Menu");
			casesRequiringActionPage.ClickOnCasesRecognitionTab();
			test.log(LogStatus.PASS, "Enter valid Reported By Email");
			casesRequiringActionPage.enterReporteredBy(username);
			test.log(LogStatus.PASS, "Successfully clicking on Filter Button");
			casesRequiringActionPage.clickFilterBtn();
			Thread.sleep(2500);
			test.log(LogStatus.PASS, "Successfully validating message after search result");
			Assert.assertEquals(casesRequiringActionPage.searchResultReportedBy().getText(), username);
			test.log(LogStatus.PASS, "clicking on View case button");
			casesRequiringActionPage.clickViewCaseBtn();
			Thread.sleep(1500);
			test.log(LogStatus.PASS, "Validating username in View case page");
			Assert.assertEquals(casesRequiringActionPage.userReportedBy().getText(), username);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate search result By Reported By Email");
			// TODO: handle exception
		}
	}
	@Test(enabled = true, priority = 3, dataProvider = "InvalidReportedBy")
	public void verifyInvalidSearchReportedBy(String InvalidReportedBy, String runMode) throws InterruptedException {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {
			test.log(LogStatus.PASS, "****TS03-05 Validating Invalid Search By ReportedBy Email ****");
			test.log(LogStatus.PASS, "User click on Cases Requiring Action Tab in Left Menu");
			casesRequiringActionPage.ClickOnCasesRecognitionTab();
			test.log(LogStatus.PASS, "Enter valid username ");
			casesRequiringActionPage.enterReporteredBy(InvalidReportedBy);
			test.log(LogStatus.PASS, "Successfully clicking on Filter Button");
			casesRequiringActionPage.clickFilterBtn();
			System.out.println("Successfully clicking on filter button");
			test.log(LogStatus.PASS, "Successfully validating with invalid search to get Count = 0");
		    assertTrue(casesRequiringActionPage.isCaseReqActionCount());
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Invalid Search Reported By Email");
			// TODO: handle exception
		}
	}
	
	@Test(enabled = true, priority = 4, dataProvider = "ReportedAt")
	public void verifySearchByRepotedAt(String ReportedAt, String runMode) throws InterruptedException {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		Thread.sleep(2000);
		try {
			test.log(LogStatus.PASS, "****TS03-05 Validating Search By Reported At Date ****");
			test.log(LogStatus.PASS, "User click on Cases Requiring Action Tab in Left Menu");
			casesRequiringActionPage.ClickOnCasesRecognitionTab();
			test.log(LogStatus.PASS, "Enter valid From to Date ");
			casesRequiringActionPage.enterReporteredBy(ReportedAt);
			System.out.println("Successfully entering date Reported By");
			test.log(LogStatus.PASS, "Successfully clicking on Filter Button");
			casesRequiringActionPage.clickFilterBtn();
			Thread.sleep(9000);
			System.out.println("Successfully getting data after search");
			test.log(LogStatus.PASS, "Successfully validating Search result");
		    assertTrue(casesRequiringActionPage.isCaseReportedAtCount());
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Invalid Search Reported By Email");
			// TODO: handle exception
		}
	}
	@Test(enabled = true, priority = 5, dataProvider = "reporterSearch")
	public void verifyValidSearchReportedFrom(String username, String runMode) throws InterruptedException {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		Thread.sleep(2000);
		try {
			test.log(LogStatus.PASS, "****TS03-06 Validating Valid Reported From Search Result****");
			test.log(LogStatus.PASS, "User click on Cases Requiring Action Tab in Left Menu");
			casesRequiringActionPage.ClickOnCasesRecognitionTab();
			test.log(LogStatus.PASS, "Enter valid Reported By Email");
			casesRequiringActionPage.enterReporteredFrom(username);
			test.log(LogStatus.PASS, "Successfully clicking on Filter Button");
			casesRequiringActionPage.clickFilterBtn();
			Thread.sleep(2500);
			test.log(LogStatus.PASS, "Successfully validating message after search result");
			Assert.assertEquals(casesRequiringActionPage.searchResultReportedBy().getText(), username);
			test.log(LogStatus.PASS, "clicking on reset button to clear text field");
			casesRequiringActionPage.clickResetBtn();
			Thread.sleep(1500);
			test.log(LogStatus.PASS, "Validating username in View case page");
			Assert.assertEquals(casesRequiringActionPage.userReportedBy().getText(), username);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate search result Reported From Email");
			// TODO: handle exception
		}
	}
	@Test(enabled = true, priority = 6, dataProvider = "InvalidReportedBy")
	public void verifyInvalidSearchReportedFrom(String InvalidReportedBy, String runMode) throws InterruptedException {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {
			test.log(LogStatus.PASS, "****TS03-07 Validating Invalid Search  ReportedFrom Email ****");
			test.log(LogStatus.PASS, "User click on Cases Requiring Action Tab in Left Menu");
			casesRequiringActionPage.ClickOnCasesRecognitionTab();
			test.log(LogStatus.PASS, "Enter valid username ");
			casesRequiringActionPage.enterReporteredFrom(InvalidReportedBy);
			test.log(LogStatus.PASS, "Successfully clicking on Filter Button");
			casesRequiringActionPage.clickFilterBtn();
			System.out.println("Successfully clicking on filter button");
			test.log(LogStatus.PASS, "Successfully validating with invalid search to get Count = 0");
		    assertTrue(casesRequiringActionPage.isCaseReqActionCount());
		    test.log(LogStatus.PASS, "Successfully clicking on Reset tab");
			casesRequiringActionPage.clickResetBtn();
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Invalid Search Reported From Email");
			// TODO: handle exception
		}
	}
	@Test(enabled = true, priority = 7, dataProvider = "Subject")
	public void verifyValidSubject(String Subject, String runMode) throws InterruptedException {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		Thread.sleep(2000);
		try {
			test.log(LogStatus.PASS, "****TS03-08 Validating Valid Reported From Search Result****");
			test.log(LogStatus.PASS, "User click on Cases Requiring Action Tab in Left Menu");
			casesRequiringActionPage.ClickOnCasesRecognitionTab();
			test.log(LogStatus.PASS, "Enter valid subject for search");
			casesRequiringActionPage.enterSubjectBy(Subject);
			test.log(LogStatus.PASS, "Successfully clicking on Filter Button");
			casesRequiringActionPage.clickFilterBtn();
			Thread.sleep(2500);
			test.log(LogStatus.PASS, "Successfully get some data after search by Subject");
			assertTrue(casesRequiringActionPage.isCaseSubjectByCount());
			System.out.println("Successfully got some data after search By subject");
			test.log(LogStatus.PASS, "Successfully clicking on Reset tab and clear data from text field");
			casesRequiringActionPage.clickResetBtn();
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate search result Reported From Email");
			// TODO: handle exception
		}
	}//InvalidSubject
	@Test(enabled = true, priority = 8, dataProvider = "InvalidSubject")
	public void verifyInValidSubject(String InvalidSubject, String runMode) throws InterruptedException {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		Thread.sleep(2000);
		try {
			test.log(LogStatus.PASS, "****TS03-09 Validating InValid Subject Search Result****");
			test.log(LogStatus.PASS, "User click on Cases Requiring Action Tab in Left Menu");
			casesRequiringActionPage.ClickOnCasesRecognitionTab();
			test.log(LogStatus.PASS, "Enter Invalid Subject for search - " + InvalidSubject);
			casesRequiringActionPage.enterSubjectBy(InvalidSubject);
			test.log(LogStatus.PASS, "Successfully clicking on Filter Button");
			casesRequiringActionPage.clickFilterBtn();
			Thread.sleep(2500);
			test.log(LogStatus.PASS, "Successfully get some data after search by Subject");
			assertTrue(casesRequiringActionPage.isCaseInvalidSubjectByCount());
			System.out.println("Successfully got some data after search By subject");
			test.log(LogStatus.PASS, "Successfully clicking on Reset tab and clear data from text field");
			casesRequiringActionPage.clickResetBtn();
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate search result Reported From Email");
			// TODO: handle exception
		}
	}
	@Test(enabled = true, priority = 9,dataProvider = "reporterSearch")
	public void verifyResetBtn(String username,String runMode) throws InterruptedException {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {
			test.log(LogStatus.PASS, "****TS03-10 Validating Reset Button after entering text in search field****");
			test.log(LogStatus.PASS, "User click on Cases Requiring Action Tab in Left Menu");
			casesRequiringActionPage.ClickOnCasesRecognitionTab();
			test.log(LogStatus.PASS, "Entering text into Reported By Text field ");
			casesRequiringActionPage.enterReporteredBy(username);
			Thread.sleep(1500);
			test.log(LogStatus.PASS, "Successfully clicking on Reset tab");
			casesRequiringActionPage.clickResetBtn();
			Thread.sleep(2500);
			test.log(LogStatus.PASS, "Validating Reset tab message");
			Assert.assertEquals(casesRequiringActionPage.verifyRestMsg().getText(), "Your filters have been reset");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Reset tab Button");
		}
	}

	@Test(enabled = true, priority = 10)
	public void verifyMarkSelectedCasesTab() throws InterruptedException {
		try {
			test.log(LogStatus.PASS, "****TS03-11 Validating Mark Selected tab on Cases requiring page****");
			test.log(LogStatus.PASS, "User click on Cases Requiring Action Tab in Left Menu");
			casesRequiringActionPage.ClickOnCasesRecognitionTab();
			test.log(LogStatus.PASS, "Successfully validating Mark Selected tab is available & enable and able to click the tab");
			casesRequiringActionPage.clickMarkSelectedCasesTab();
			Thread.sleep(1500);
			test.log(LogStatus.PASS, "Validating no cases being selected for Marks Selected cases Tab");
			Assert.assertEquals(casesRequiringActionPage.verifyErrorMsg().getText(), "No Cases Have Been Selected");
			casesRequiringActionPage.clickCancelTab();
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed on clicking Marks selected tab on Cases Requiring Action Page");
		}
	}
	@Test(enabled = true, priority = 11)
	public void verifyAssignedUsers() throws InterruptedException {
		
		try {
			//test.log(LogStatus.PASS, "Successfully able to search by Client Name");
			//dashBoardPage.setClientName(clientName);
			test.log(LogStatus.PASS, "****TS03-12 Validating Mark Selected tab on Cases requiring page****");
			test.log(LogStatus.PASS, "User click on Cases Requiring Action Tab in Left Menu");
			casesRequiringActionPage.ClickOnCasesRecognitionTab();
			test.log(LogStatus.PASS, "clicking on View case button");
			casesRequiringActionPage.clickViewCaseBtn();
			Thread.sleep(6500);
			System.out.println("Verify Cases are unassigned");
			test.log(LogStatus.PASS, "Validating assigned Cases to users ");
			casesRequiringActionPage.selectchooseUser();  //verifyUnassigned
			
			Assert.assertEquals(casesRequiringActionPage.verifyUnassigned().getText(), "Unassigned");
			System.out.println("Verify Cases are unassigned");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "unable to assigned users from dropdown list");
			// TODO: handle exception
		}
	}
	
	@AfterSuite
	public void closeBrowser() throws InterruptedException {
		
		test.log(LogStatus.PASS, "****TS03-13 Validating Logout from Application and closing Browser Successfully****");
		test.log(LogStatus.PASS, "User click on Cases Requiring Action Tab in Left Menu");
		dashBoardPage.logOutTest();
		Thread.sleep(1500);
		test.log(LogStatus.PASS, "Verify the Logout successfull - " + loginPage.getLogOutSuccessMsg());
		test.log(LogStatus.PASS, "Closing browser Successfully");
		driver.close();
		test.log(LogStatus.PASS, "After completion of running all test");
		report.endTest(test);
		test.log(LogStatus.PASS, "Flushing all the previous reports saved in cashing");
		report.flush();
	}

}