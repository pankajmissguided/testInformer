package com.phishd.testMethods;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.commons.codec.language.DaitchMokotoffSoundex;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.phishd.pageObjects.DashBoardPage;
import com.phishd.pageObjects.LoginPage;
import com.phishd.testBase.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class TS02_ClientSelection extends TestBase {

	LoginPage loginPage; // page objects which we had defined in test script
	DashBoardPage dashBoardPage; // page objects which we had defined in test script

	public TS02_ClientSelection() {
		super();
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
		test = report.startTest("TS02_ClientSelection");
		loginPage = new LoginPage(driver);
		dashBoardPage = new DashBoardPage(driver);
		dashBoardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
			}
	
	@Test(alwaysRun=true,priority=0,enabled = true,dataProvider ="clientName")
	public void VerifyClientFromDropDown(String clientName,String runMode) {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {
			test.log(LogStatus.PASS, "*******TS02_Validate Current Client Selection from Drop down********");
			test.log(LogStatus.PASS,
					"***********TC001_Verify Selecting client from Client List drop down In Informer Main Page*************");
			test.log(LogStatus.PASS, "User click on Client Drop down and set the Client Name - "+clientName);
			dashBoardPage.setClientName(clientName);
			Thread.sleep(3500);
			test.log(LogStatus.PASS, "I Verified Client name displayed under useraccount - "
					+ dashBoardPage.isClientNameDisplayed().getText());
			Assert.assertEquals(dashBoardPage.isClientNameDisplayed().getText(), clientName);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "failed to select current client from the drop down list");
		}
	}
	
	// Validating the links on Dashboard Main Page
	@Test(priority = 1,enabled = true,dataProvider ="reporterSearch")
	public void VerifyReporterSearch(String username,String runMode) throws InterruptedException {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {
			test.log(LogStatus.PASS,"Validating DashBoard Link on Main Page of Informer Dashboard");
			test.log(LogStatus.PASS, "***********TC002_VerifyReporterSearch*************");
			test.log(LogStatus.PASS,"Clicking on DashBoard link to refresh Dashboard Main Page");
			dashBoardPage.clickDashBoard();
			Thread.sleep(2500);
			test.log(LogStatus.PASS, "Clicking on Reporter Search");
			dashBoardPage.clickReporterSearch();
			Thread.sleep(1500);
			test.log(LogStatus.PASS, "Enter username - "+ username);
			dashBoardPage.enterReporterSearch(username);
			test.log(LogStatus.PASS, "Clicking on SearchButton");
			dashBoardPage.clickSearchBtn();
			Thread.sleep(2000);
			test.log(LogStatus.PASS, "Validating reporter Search Name after search");
			Assert.assertEquals(dashBoardPage.IsReporterSearchNameDisplayed().getText(), username);
			test.log(LogStatus.PASS, "Click on Dashboard link to upload the main Dashboard page");
			dashBoardPage.clickDashBoard();
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "failed to select current client from the drop down list");
		} finally {
			getScreenShot("VerifyReporterSearch");
		}
	}

	@Test(priority=2,enabled = true,dataProvider="ipaddress")
	public void VerifyIpSearch(String ipaddress,String title,String runMode) throws InterruptedException {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {
		test.log(LogStatus.PASS, "***********TC003_Verify IPSearch Result on Main Dash Board*************");
        test.log(LogStatus.PASS,"Clicking on Dashboard link on Main page");
		dashBoardPage.clickDashBoard();
		test.log(LogStatus.PASS, "Enter an IP Search Menu under Data Search" + ipaddress);
		dashBoardPage.clickIPSearch();
		Thread.sleep(1500);
		test.log(LogStatus.PASS,"Entering IP Address for search IP Address Picking IP Address from Excel Sheet" + ipaddress);
		dashBoardPage.enterIpSearch(ipaddress);
		Thread.sleep(4500);
		test.log(LogStatus.PASS, "Clicking on SearchButton");
		dashBoardPage.clickIPSearchBtn();
		//test.log(LogStatus.PASS,"Printing success of IP search on main Page");
		//System.out.println("click on search button");
		Thread.sleep(4500);
		test.log(LogStatus.PASS, "Validating title of the page");
		Assert.assertEquals(dashBoardPage.getTitle(), title);
		test.log(LogStatus.PASS, "Validating IP Search Address in search result");
		Assert.assertEquals(dashBoardPage.IsIpAddressDisplayed().getText(), ipaddress);
		Thread.sleep(3500);
		System.out.println("Title of the Page - "+ title);
		test.log(LogStatus.PASS, "Clicking on DashBoard Page to get on the main page of Informer DashBoard");
		dashBoardPage.clickDashBoard();
			} catch (Exception e) {
		test.log(LogStatus.FAIL, "failed to search the IP address");
			}
	}

	@Test(priority=3,enabled = true,dataProvider="domain")
	public void VerifyLinkDomainSearch(String domain, String title, String runMode) throws InterruptedException {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {
		test.log(LogStatus.PASS, "***********TC004_Verify Link Domain Search Result on Main Dash Board*************");
        test.log(LogStatus.PASS,"Clicking on Dashboard link on Main page");
		dashBoardPage.clickDashBoard();
		test.log(LogStatus.PASS, "Clicking on Link Domain Search link in the under Data Search Menu");
		dashBoardPage.clickLinkDomainSearch();
		test.log(LogStatus.PASS, "Enter Domain Name as :"+ domain);
		dashBoardPage.enterLinkDomainSearch(domain);
		test.log(LogStatus.PASS, "Clicking on SearchButton");
		dashBoardPage.clickLinkDomainSearchBtn();
		Thread.sleep(5500);
		test.log(LogStatus.PASS, "Validating Link Domain Search Name "+ domain);
		assertTrue(dashBoardPage.getDomainSearchResults(domain));
		// dashBoardPage.getTitle();
		test.log(LogStatus.PASS, "Validating title of the search result page of 'Link Domain Search'");
		Assert.assertEquals(dashBoardPage.getTitle(), title);
		test.log(LogStatus.PASS, "validating after Clicking on DashBoard link to get on the main page of Informer DashBoard");
//		String expectedTitle = "Link Domain Lookup | ";
//		String actualTitle = dashBoardPage.getTitle();
//		if (actualTitle.equals(expectedTitle)) {
// Assert.assertTrue(dashBoardPage.IsLinkDomainAddressDisplayed().contains("HSBC"));
			dashBoardPage.clickDashBoard();
			}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "failed to search LinkDomain from the report");
		}
	}
	@Test(priority=4,enabled = true,dataProvider="attachmentSearch")
	public void VerifyAttachmentSearch(String searchBy,String runMode) throws InterruptedException {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {
		test.log(LogStatus.PASS, "***********TC005_Verify attachment Search from report Result Search by .pdf *************");
		test.log(LogStatus.PASS, "Click on Dash Board link to refresh Dashboard main Page");
		dashBoardPage.clickDashBoard();
		test.log(LogStatus.PASS, "Clicking on AttachmentSearch Search Menu under Data Search");
		dashBoardPage.clickAttachmentSearch();
		test.log(LogStatus.PASS, "enter attachment search into  Search text area" + searchBy);
		dashBoardPage.enterAttachmentSearch(searchBy);
		test.log(LogStatus.PASS, "Clicking on SearchButton");
		dashBoardPage.clickAttachmentSearchBtn();
		Thread.sleep(5500);
		test.log(LogStatus.PASS, "Validating Attachment Search result "+ searchBy);
		assertTrue(dashBoardPage.getAttachmentSearchResults(searchBy));
		//test.log(LogStatus.PASS, "Validating title of the search result page of attahment search page ");
		//Assert.assertEquals(dashBoardPage.getTitle(),title);#
		test.log(LogStatus.PASS,"Validating DashBoard Link After search result in Attachment Search Page");
		dashBoardPage.clickDashBoard();
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "failed to search Attachment from the report");
		}
	}
	
	
	@DataProvider(name = "reporterSearch")
	public String[][] getreporterSearch() {
		String[][] testRecords = getData("testData.xlsx", "reporterSearch");
		return testRecords;
	}
	@DataProvider(name = "userDetails")
	public String[][] getuserData() {
		String[][] testRecords = getData("testData.xlsx", "searchData");
		return testRecords;
	}
	@DataProvider(name = "clientName")
	public String[][] getClientData() {
		String[][] testRecords = getData("testData.xlsx", "clientName");
		return testRecords;
	}
	@DataProvider(name = "domain")
	public String[][] getDomain() {
		String[][] testRecords = getData("testData.xlsx", "domain");
		return testRecords;
	}
	@DataProvider(name = "ipaddress")
	public String[][] getipaddress() {
		String[][] testRecords = getData("testData.xlsx", "ipaddress");
		return testRecords;
	}
	@DataProvider(name = "attachmentSearch")
	public String[][] getattachment() {
		String[][] testRecords = getData("testData.xlsx", "attachmentSearch");
		return testRecords;
	}
	
	@Test(priority = 5,enabled = true)
	public void VerifyInvalidReporterSearch() throws InterruptedException {
	
		try {
			test.log(LogStatus.PASS,"Validating Invalid reporter Search");
			test.log(LogStatus.PASS, "***********TC006_VerifyInvalidReporterSearch*************");
			test.log(LogStatus.PASS,"Clicking on DashBoard link to refresh Dashboard Main Page");
			dashBoardPage.clickDashBoard();
			Thread.sleep(2500);
			test.log(LogStatus.PASS, "Clicking on Reporter Search");
			dashBoardPage.clickReporterSearch();
			Thread.sleep(1500);
			test.log(LogStatus.PASS, "Enter Invalid reporter name ");
			dashBoardPage.enterReporterSearch("fhgghfgh");
			test.log(LogStatus.PASS, "Clicking on SearchButton");
			dashBoardPage.clickSearchBtn();
			Thread.sleep(2000);
			test.log(LogStatus.PASS, "Validating Error message after search");
			Assert.assertEquals(dashBoardPage.getAProblemErrorMsg(),"A problem occurred...");
			test.log(LogStatus.PASS, "Click on Dashboard link to upload the main Dashboard page");
			dashBoardPage.clickDashBoard();
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "failed to validate invalid search with error message on search result page");
		}	}
	@Test(priority = 6,enabled = true)
	public void InvalidDomainSearch() throws InterruptedException {
	
		try {
			test.log(LogStatus.PASS,"Validating Invalid Domain Search");
			test.log(LogStatus.PASS, "***********TC007_VerifyInvalidReporterSearch*************");
			test.log(LogStatus.PASS,"Clicking on DashBoard link to refresh Dashboard Main Page");
			dashBoardPage.clickDashBoard();
			Thread.sleep(2500);
			test.log(LogStatus.PASS, "Clicking on Link Domain Search link in the under Data Search Menu");
			dashBoardPage.clickLinkDomainSearch();
			test.log(LogStatus.PASS, "Enter invalid  Domain Name ");
			dashBoardPage.enterLinkDomainSearch("fhgghfgh");
			test.log(LogStatus.PASS, "Clicking on SearchButton");
			dashBoardPage.clickLinkDomainSearchBtn();
			Thread.sleep(5500);
			test.log(LogStatus.PASS, "Validating No page found error message ");
			Assert.assertEquals(dashBoardPage.getNoResultsFoundMsg(),"No results found");
			// dashBoardPage.getTitle();
			test.log(LogStatus.PASS, "Validating title of the search result page of 'Link Domain Search'");
			Assert.assertEquals(dashBoardPage.getTitle(),"Link Domain Lookup |");
			test.log(LogStatus.PASS, "Click on Dashboard link to upload the main Dashboard page");
			dashBoardPage.clickDashBoard();
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "failed to validate invalid search with error message on search result page");
		}	}
	
	@Test(priority=7,enabled = true)
	public void VerifyInvalidIPSearch() throws InterruptedException {
		try {
		test.log(LogStatus.PASS, "***********TC008_Verify invalid  IPSearch Result on Main Dash Board*************");
        test.log(LogStatus.PASS,"Clicking on Dashboard link on Main page");
		dashBoardPage.clickDashBoard();
		test.log(LogStatus.PASS, "Click on IP Search Menu under Data Search" );
		dashBoardPage.clickIPSearch();
		Thread.sleep(1500);
		test.log(LogStatus.PASS,"Entering IP Address for search IP Address Picking IP Address from Excel Sheet" );
		Thread.sleep(4500);
		test.log(LogStatus.PASS, "Clicking on SearchButton");
		dashBoardPage.clickIPSearchBtn();
		//test.log(LogStatus.PASS,"Printing success of IP search on main Page");
		//System.out.println("click on search button");
		Thread.sleep(4500);
		test.log(LogStatus.PASS, "Validating title of the page");
		Assert.assertEquals(dashBoardPage.getTitle(),"IP Address Lookup |");
		test.log(LogStatus.PASS, "Validating Error msg after invalid IP search");
		Assert.assertEquals(dashBoardPage.getAProblemErrorMsg(),"A problem occurred...");
		test.log(LogStatus.PASS, "Clicking on DashBoard Page to get on the main page of Informer DashBoard");
		dashBoardPage.clickDashBoard();
			} catch (Exception e) {
		test.log(LogStatus.FAIL, "failed to validate invalid IP search");
			}
	}
	@Test(priority=8,enabled = true)
	public void VerifyInvalidAttachSearch() throws InterruptedException {
	
		try {
		test.log(LogStatus.PASS, "***********TC009_Verify nvalid attachment Search from report  *************");
		test.log(LogStatus.PASS, "Click on Dash Board link to refresh Dashboard main Page");
		dashBoardPage.clickDashBoard();
		test.log(LogStatus.PASS, "Clicking on AttachmentSearch Search Menu under Data Search");
		dashBoardPage.clickAttachmentSearch();
		test.log(LogStatus.PASS, "enter attachment search into  Search text area" );
		dashBoardPage.enterAttachmentSearch(".asdf");
		test.log(LogStatus.PASS, "Clicking on SearchButton");
		dashBoardPage.clickAttachmentSearchBtn();
		Thread.sleep(5500);
		test.log(LogStatus.PASS, "Validating Attachment Search result ");
		Assert.assertEquals(dashBoardPage.getNoResultsFoundMsg(),"No results found");
		test.log(LogStatus.PASS, "Validating title of Invalid attahment search page ");
		Assert.assertEquals(dashBoardPage.getTitle(),"Attachment Lookup |");
		test.log(LogStatus.PASS,"Validating DashBoard Link After search result in Attachment Search Page");
		dashBoardPage.clickDashBoard();
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "failed to search Attachment from the report");
		}
	}
	
	@AfterSuite
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(2500);

//  	   Assert.assertEquals(loginPage.getLogOutSuccessMsg(), "You have been logged out successfully");
		dashBoardPage.logOutTest();
		Thread.sleep(1500);
		test.log(LogStatus.PASS, "Verify the Logout successfull - " + loginPage.getLogOutSuccessMsg());
		test.log(LogStatus.PASS, "Closing browser Successfully");
		driver.close();
		test.log(LogStatus.PASS, "After completion of running all test");
		report.endTest(test);
		test.log(LogStatus.PASS,"Flushing all the previous reports saved in cashing");
		report.flush();
	}

}
