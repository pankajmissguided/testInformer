package com.phishd.testMethods;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.phishd.pageObjects.DashBoardPage;
import com.phishd.pageObjects.LiveReportingPage;
import com.phishd.pageObjects.LoginPage;
import com.phishd.testBase.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class TS04_LiveReporting extends TestBase {

	LoginPage loginPage; // page objects which we had defined in test script
	DashBoardPage dashBoardPage; // page objects which we had defined in test script
	LiveReportingPage liveReportingPage;

	public TS04_LiveReporting() {
		super();
	}

	@DataProvider(name = "Viewcase")
	public String[][] getviewCasetab() {
		String[][] testRecords = getData("testData.xlsx", "Viewcase");
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
		test = report.startTest("TS04_LiveReportingTest");
		loginPage = new LoginPage(driver);
		dashBoardPage = new DashBoardPage(driver);
		dashBoardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		liveReportingPage = new LiveReportingPage(driver);
	}

	@Test(dataProvider = "clientName", priority = 1, enabled = true)
	public void VerifyClientFromDropDown(String clientName, String runMode) {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {
			Thread.sleep(2500);
			test.log(LogStatus.PASS,
					"*****TS04--01***Verify Successfully **********");
			test.log(LogStatus.PASS,
					"*****TS04--02***Verify clicked on Live Repoting tab and page open successfully**********");
			test.log(LogStatus.PASS, "User click on Client Drop down and set the Client Name - " + clientName);
			Thread.sleep(3500);
			test.log(LogStatus.PASS, "Selecting Client Name from the drop Down list");
			dashBoardPage.setClientName(clientName);
			Thread.sleep(3500);
			test.log(LogStatus.PASS, "I Verified Client name displayed under useraccount - "
					+ dashBoardPage.isClientNameDisplayed().getText());
			Assert.assertEquals(dashBoardPage.isClientNameDisplayed().getText(), clientName);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "failed to select Clients from Dropdown list");
		}
	}

	@Test(priority = 2, enabled = true)
	public void verifyLiveReportingPage() throws InterruptedException {

		try {
			test.log(LogStatus.PASS, " TS04-03 Validating Live reporting tab Link on the DashBoard page");
			test.log(LogStatus.PASS, "Successfully click on the Live Reporting tab Link");		
			liveReportingPage.clickLiveReporting();
			test.log(LogStatus.PASS, "To get the title of Live Reporting  page");
			Assert.assertEquals(dashBoardPage.getTitle(), "Live Reports |");
			test.log(LogStatus.PASS, "Validate the Live Reporting Page Contentand Panel Title");
			Assert.assertEquals(liveReportingPage.getPanelTitle(), "Live Reports");
			test.log(LogStatus.PASS, "Validting contents appearing in Live Reporting Page");
			Assert.assertEquals(liveReportingPage.getviewCasebutton(), "View Case");

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Live Reporting Tab link appear on Dashboard Page");
			// TODO: handle exception
		}
	}

	@Test(priority = 3, enabled = true)
	public void verifyOverviewOfReport() throws InterruptedException {
		try {
			test.log(LogStatus.PASS, " TS04-04 Validating Overview of the reported cases");
			test.log(LogStatus.PASS, " Successfully click on the Live Reporting tab Link");
			liveReportingPage.clickLiveReporting();
			test.log(LogStatus.PASS, "Click on View Case Button in Live Report Page");
			liveReportingPage.clickViewCaseBtn();
			Thread.sleep(3500);
			test.log(LogStatus.PASS, "validating Overview Tab is enable and successfully clicking on Overview Tab");
			liveReportingPage.clickOverviewTab();
			test.log(LogStatus.PASS, "Validating Reported By in Overview of the Reports in Live Reporting");
			Assert.assertEquals(liveReportingPage.getReportedTitle(), "Reported"); // getMessagetab
			test.log(LogStatus.PASS, "Validating Message in Overview of the Reports in Live Reporting");
			Assert.assertEquals(liveReportingPage.getMessagetab(), "Message");
		}
	catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed in validating Overview in LiveRporting ");
			// TODO: handle exception
		}
	}
	

	@Test(priority = 4, enabled = true)
	public void verifyRulesReported() throws InterruptedException {
		try {
			test.log(LogStatus.PASS, " TS04-05 Validating Rules of the reported cases");
			test.log(LogStatus.PASS, "Click on Live Report tab Link");
			liveReportingPage.clickLiveReporting();
			test.log(LogStatus.PASS, "Successfully clicking on viewcase button ");
			liveReportingPage.clickViewCaseBtn();
			Thread.sleep(3500);
			test.log(LogStatus.PASS, "validating rules tab is enabled and clicking");
			liveReportingPage.clickRulesTab();
			test.log(LogStatus.PASS, "validating Triggered rules under Rules Panel Tab in View case");
			Assert.assertEquals(liveReportingPage.getTriggeredRulePanel(), "Triggered Rules");
			test.log(LogStatus.PASS, "validating Triggered Rule under rules tab");
			Assert.assertEquals(liveReportingPage.getTriggeredRule(), "TRIGGERED RULE");
			test.log(LogStatus.PASS, "validating Weight under Rules tab");
			Assert.assertEquals(liveReportingPage.getWeight(), "WEIGHT");
			test.log(LogStatus.PASS, "validating Rule Reason under Rules tab");
			Assert.assertEquals(liveReportingPage.getRuleReason(), "REASON");

			// Assert.assertEquals()
		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, "Failed to validate rules in reported case under live reporting");
			
		}
	}

	@Test(priority = 5, enabled = true)
	public void verifyCoreHeaders() throws InterruptedException {
		try {
			test.log(LogStatus.PASS, " TS04-06 Validating CoreHeaders on the reported cases");
			test.log(LogStatus.PASS, "Successfully Clicking on Live Report Page");
			liveReportingPage.clickLiveReporting();
			test.log(LogStatus.PASS, "Successfully clicking on viewcase button ");
			liveReportingPage.clickViewCaseBtn();
			Thread.sleep(3500);
			test.log(LogStatus.PASS, "validating CoreHeader tab is enabled and clickable on Report Page");
			liveReportingPage.clickCoreHeaders();
			test.log(LogStatus.PASS, "validating Message Core Header panel under Core header tab");
			Assert.assertEquals(liveReportingPage.getMessageCoreHeader(), "Message Core Headers");
			test.log(LogStatus.PASS, "validating Message Core Header panel under Core header tab");
			Assert.assertEquals(liveReportingPage.getMessageHeader(), "MESSAGE HEADER");
			test.log(LogStatus.PASS, "validating value under Core header tab");
			Assert.assertEquals(liveReportingPage.getMessageHeader(), "VALUE");

			System.out.println("now we need to validte CoreHeaders appearing in reported email");
			// Assert.assertEquals()
		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, "Failed to validate Core Headers in Reported case page");
			
		}
	    }

	@Test(priority = 6, enabled = true)
	public void verifyHeaders() throws InterruptedException {
		try {
			test.log(LogStatus.PASS, " TS04-07 Validating Headers tab Link on the reported cases");
			test.log(LogStatus.PASS, "Click on Live Report tab");
			liveReportingPage.clickLiveReporting();
			test.log(LogStatus.PASS, "Click on View Case Button in Live Report tab");
			liveReportingPage.clickViewCaseBtn();
			Thread.sleep(3500);
			test.log(LogStatus.PASS, "validating Headers tab Link is enabled and clickable on Report Page");
			liveReportingPage.clickHeaders();
			test.log(LogStatus.PASS, "validating Header panel under header tab");
			Assert.assertEquals(liveReportingPage.getPanelMessageHeaderTitle(), "Message Headers");
			test.log(LogStatus.PASS, "validating Message Header panel under header tab");
			Assert.assertEquals(liveReportingPage.getMessageCoreHeader(), "Message Header");

			test.log(LogStatus.PASS, "validating value under header tab");
			Assert.assertEquals(liveReportingPage.getValue(), "VALUE");

			System.out.println("now we need to validte CoreHeaders appearing in reported email");
			// Assert.assertEquals()
		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, "Failed to validate Headers in Reported Case page");
			
		}
	    }

	@Test(priority = 7, enabled = true)
	public void verifyIPAddress() throws InterruptedException {
		try {
			test.log(LogStatus.PASS, " TS04-08 Validating IPAddress tab Link on the reported cases");
			test.log(LogStatus.PASS, "Click on Live Report tab");
			liveReportingPage.clickLiveReporting();
			test.log(LogStatus.PASS, "Click on View Case Button in Live Report tab");
			liveReportingPage.clickViewCaseBtn();
			Thread.sleep(3500);
			test.log(LogStatus.PASS, "validating IPAddress Link is enabled and clickable on Report Page");
			liveReportingPage.clickIpAddress();

			test.log(LogStatus.PASS, "validating IP Address panel under IP Address tab");
			Assert.assertEquals(liveReportingPage.getPanelIPAddressTitle(), "IP Addresses");

			test.log(LogStatus.PASS, "validating IPAddress under panel under IP Address tab");
			Assert.assertEquals(liveReportingPage.getIPAddress(), "IP Address");

			test.log(LogStatus.PASS, "validating Types under panel under IP Address tab");
			Assert.assertEquals(liveReportingPage.getType(), "Type");

			//System.out.println("now we need to validate CoreHeaders appearing in reported email");
			// Assert.assertEquals()
		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, "Failed to report IP Address page");
			
		}
	    }	
	@Test(priority = 8, enabled = true)
	public void verifyMessageBody() throws InterruptedException {
		try {
			test.log(LogStatus.PASS, " TS04-09 Validating Message Body tab Link on the reported cases");
			test.log(LogStatus.PASS, "Click on Live Report tab");
			liveReportingPage.clickLiveReporting();
			test.log(LogStatus.PASS, "Click on View Case Button in Live Report tab");
			liveReportingPage.clickViewCaseBtn();
			Thread.sleep(3500);
			test.log(LogStatus.PASS, "validating Message Body tab Link is enabled and clickable on Report Page");
			liveReportingPage.clickMessageBody();

			test.log(LogStatus.PASS, "validating Message BodyPanel under Message Body tab");
			Assert.assertEquals(liveReportingPage.getMessageBodyPanelTitle(), "Message Body");

			test.log(LogStatus.PASS, "validating Text Plain under  MessageBody panel under MessageBody tab");
			Assert.assertEquals(liveReportingPage.getTextPlain(), "text/plain");

			test.log(LogStatus.PASS, "validating Application RTF under Message Header tab");
			Assert.assertEquals(liveReportingPage.getApplicationRtf(), "application/rtf");

			//System.out.println("now we need to validte CoreHeaders appearing in reported email");
			// Assert.assertEquals()
		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, "Failed to validate Message body in reported Case");
			
		}
	    }
	
	@Test(priority = 9, enabled = true)
	public void verifyLinksDetected() throws InterruptedException {
		try {
			test.log(LogStatus.PASS, " TS04-10 Validating Links Dtetected on the reported cases under Live Reporting");
			test.log(LogStatus.PASS, "Click on Live Report tab");
			liveReportingPage.clickLiveReporting();
			test.log(LogStatus.PASS, "Click on View Case Button in Live Report tab");
			liveReportingPage.clickViewCaseBtn();
			Thread.sleep(3500);
			test.log(LogStatus.PASS, "validating Links is enabled and clickable on Report Page");
			liveReportingPage.clickLinks();

			test.log(LogStatus.PASS, "validating Links in panel under Links Detected tab");
			Assert.assertEquals(liveReportingPage.getLinksDetectedPanelTitle(), "Message Body");

			// test.log(LogStatus.PASS, "validating Message Core Header panel under Core
			// header tab");
			// Assert.assertEquals(liveReportingPage.verifyExportLinks(),"Export Links");

			test.log(LogStatus.PASS, "validating Link Text in  panel under Links detected tab");
			Assert.assertEquals(liveReportingPage.getLinkText(), "Link");

			test.log(LogStatus.PASS, "validating Attributes under Links Detected tab");
			Assert.assertEquals(liveReportingPage.getAttributes(), "Attributes");
			
			test.log(LogStatus.PASS, "validating Unique Domains detected under Links detected tab");
			Assert.assertEquals(liveReportingPage.getDetectedUniqueDomains(), "Detected Unique Domains");


			//System.out.println("now we need to validte CoreHeaders appearing in reported email");
			// Assert.assertEquals()
		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, "Failed to validate any unique domains in  Live Reporting page ");
			
		}
     	}
	
	@Test(priority = 10, enabled = true)
	public void verifyAttachment() throws InterruptedException {
		try {
			test.log(LogStatus.PASS, " TS04-11 Validating Attachments Detected on the reported cases under Live Reporting");
			test.log(LogStatus.PASS, "Click on Live Report tab");
			liveReportingPage.clickLiveReporting();
			test.log(LogStatus.PASS, "Click on View Case Button in Live Report tab");
			liveReportingPage.clickViewCaseBtn();
			Thread.sleep(3500);
			test.log(LogStatus.PASS, "validating Attachment tab is enabled and clickable on Report Page");
			liveReportingPage.verifyAttachments();

			test.log(LogStatus.PASS, "validating Attachment panel under Attachment tab");
			Assert.assertEquals(liveReportingPage.getAttachmentPanelTitle(), "Detected Attachments");

			// test.log(LogStatus.PASS, "validating Message Core Header panel under Core
			// header tab");
			// Assert.assertEquals(liveReportingPage.verifyExportLinks(),"Export Links");

			test.log(LogStatus.PASS, "validating Name  under Attachment tab");
			Assert.assertEquals(liveReportingPage.getName(), "Name");

			test.log(LogStatus.PASS, "validating Mime type under Attachment tab in reported case");
			Assert.assertEquals(liveReportingPage.getMimeType(), "Mime Type");
			
			test.log(LogStatus.PASS, "validating Category under Core Attachment tab");
			Assert.assertEquals(liveReportingPage.getCategory(), "Category");
			
			test.log(LogStatus.PASS, "validating ByMime type inder Attachmnet tab ");
			Assert.assertEquals(liveReportingPage.getByMimeType(), "By Mime Type");


			//System.out.println("now we need to validte CoreHeaders appearing in reported email");
			// Assert.assertEquals()
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Attachmnet in Live Reporting ");
			// TODO: handle exception
		}
	    }

	@Test(priority = 11, enabled = true)
	public void verifyDownloadMessage() throws InterruptedException {
		try {
			test.log(LogStatus.PASS, " TS04-12 Validating Download Message on the reported cases under Live Reporting");
			test.log(LogStatus.PASS, "Click on Live Report tab");
			liveReportingPage.clickLiveReporting();
			test.log(LogStatus.PASS, "Click on View Case Button in Live Report tab");
			liveReportingPage.clickViewCaseBtn();
			Thread.sleep(3500);
			test.log(LogStatus.PASS, "validating Download Message tab is enabled and clickable on Report Page");
			liveReportingPage.verifyDownloadMessage();
			Thread.sleep(5500);

			test.log(LogStatus.PASS, "validating Download Outlook message tab under Download Message tab");
			Assert.assertEquals(liveReportingPage.getDownLoadOutlookMsg(), "Download Outlook .msg");

	// Assert.assertEquals()
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to validate Download Message tab in live reporting page");
			// TODO: handle exception
		}
     	}
	@DataProvider(name = "clientName")
	public String[][] getClientData() {
		String[][] testRecords = getData("testData.xlsx", "clientName");
		return testRecords;
	}

	@AfterSuite
	public void CloseBrowser() throws InterruptedException {

//	  	   Assert.assertEquals(loginPage.getLogOutSuccessMsg(), "You have been logged out successfully");
		test.log(LogStatus.PASS, " TS04-13 Validating Logout to the Informer Dashboard");
		dashBoardPage.logOutTest();

		test.log(LogStatus.PASS, "Verify the Logout successfull - " + loginPage.getLogOutSuccessMsg());
		test.log(LogStatus.PASS, "Closing browser Successfully");
		driver.close();
		test.log(LogStatus.PASS, "After completion of running all test");
		report.endTest(test);
		test.log(LogStatus.PASS, "Flushing all the previous reports saved in cashing");
		report.flush();
	}

   }