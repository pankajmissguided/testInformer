package com.phishd.testMethods;

import java.io.IOException;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.phishd.pageObjects.CasesRequiringActionPage;
import com.phishd.pageObjects.DashBoardPage;
import com.phishd.pageObjects.LoginPage;
import com.phishd.testBase.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class TS01_LoginTest extends TestBase {

	LoginPage loginPage;
	DashBoardPage dashBoardPage;
	CasesRequiringActionPage casereqPage;

	public TS01_LoginTest() {
		super();
	}

	@DataProvider(name = "searchData")
	public String[][] getuserData() {
		String[][] testRecords = getData("testData.xlsx", "searchData");
		return testRecords;
	}

	@DataProvider(name = "EmptyPassword")
	public String[][] EmptyPassword() {
		String[][] testRecords = getData("testData.xlsx", "EmptyPassword");
		return testRecords;
	}

	@DataProvider(name = "EmptyUsername")
	public String[][] EmptyUsername() {
		String[][] testRecords = getData("testData.xlsx", "EmptyUsername");
		return testRecords;
	}

	@DataProvider(name = "invalidData")
	public String[][] invalidData() {
		String[][] testRecords = getData("testData.xlsx", "invalidData");
		return testRecords;
	}

	@BeforeSuite
	public void setUp() throws InterruptedException {
		try {
			initialize();
			report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReports.html");
			test = report.startTest("TS01_LoginTest");
			loginPage = new LoginPage(driver);
			dashBoardPage = new DashBoardPage(driver);
			casereqPage = new CasesRequiringActionPage(driver);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// This Scenario is when some user click Login button
	// without entering UserName/password
	@Test(enabled = true, priority = 1)
	public void verifyLoginWithEmptyCredentials() throws InterruptedException {
		try {
			test.log(LogStatus.PASS, "*******TS01_Login Test********");
			test.log(LogStatus.PASS,
					"***************TC001_ Verify the Login page with and  without credentials****************");
			test.log(LogStatus.PASS, "Do not enter username");
			loginPage.enterUserName(" ");
			test.log(LogStatus.PASS, "Do not enter password");
			loginPage.enterPassword("");
			test.log(LogStatus.PASS, "Clicking on the Login Button");
			loginPage.clickLoginbutton();
			Thread.sleep(2000);
			test.log(LogStatus.PASS, "Validating Message There was a Problem"); // Assertions
			Assert.assertEquals(loginPage.getTherepblmerrormsg(), "There was a problem...");
			Thread.sleep(1000);
			test.log(LogStatus.PASS, "The Email Field is Required message captured");
			Assert.assertEquals(loginPage.getUsernameerror(), "The Email address field is required.");
			Thread.sleep(1000);
			test.log(LogStatus.PASS, "Verifying Passsword Required message appearing in Text msg");
			Assert.assertEquals(loginPage.getPassworderror().getText(), "The Password field is required.");
			loginPage.refreshPage();
		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, "fail to validate login page without credentials");
		}
		}
	@Test(enabled = true, priority = 2, dataProvider = "EmptyPassword")
	public void verifyLoginWithEmptyPasswordCredentials(String username, String runMode) throws InterruptedException {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {
		test.log(LogStatus.PASS, "***************TC002 Verify Login page with no password****************");
		// LoginPage loginPage = new LoginPage(driver);
		test.log(LogStatus.PASS, "Entering Email Id to the login page ");
		loginPage.enterUserName(username);
		test.log(LogStatus.PASS,	"Clicking on to the Login Button ");
		loginPage.clickLoginbutton();
		Thread.sleep(2000);
		test.log(LogStatus.PASS, "Verify the problem error messgae is appearing correctly in the Login Page");
		Assert.assertEquals(loginPage.getTherepblmerrormsg(), "There was a problem..."); // Assertions
		Thread.sleep(1000);
		test.log(LogStatus.PASS, "Verifying second msg captured successfully in Login Page");
		Assert.assertEquals(loginPage.getPasserrormsg(), "The Password field is required.");
		test.log(LogStatus.PASS, "Verifying after refreshing the page it stays on Login Page with Error message");
		loginPage.refreshPage();// Assertions
		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, "fail to validate login page without password Credentials");
		}
		}

	// This Scenario is about entering Password
	// And no EmailAddress Username
	@Test(enabled = true, priority = 3, dataProvider = "EmptyUsername")
	public void verifyLoginWithEmptyEmailCredentials(String password, String runMode) throws InterruptedException {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {
		test.log(LogStatus.PASS, "***************TC003_ Verify the Login page with no Username ****************");
		// LoginPage loginPage = new LoginPage(driver);
		test.log(LogStatus.PASS, "Entering correct password to the login Page ");
		loginPage.enterPassword(password);
		test.log(LogStatus.PASS,"Clicking on Login Button without passing Username to the Login Page");
		loginPage.clickLoginbutton();
		Thread.sleep(2000);
		test.log(LogStatus.PASS, "Verifying error messgae is appearing correctly in the Login Page");
		Assert.assertEquals(loginPage.getTherepblmerrormsg(), "There was a problem..."); // Assertions
		Thread.sleep(1000);
		test.log(LogStatus.PASS, "Verifying the other error messgae is appearing correctly in the Login Page");
		Assert.assertEquals(loginPage.getPasserrormsg(), "The Email address field is required.");
		test.log(LogStatus.PASS, "Verifying after refreshing the page it stays on Login Page with Error message");
		loginPage.refreshPage();// Assertions
	} catch (Exception e) {
		// TODO: handle exception
		test.log(LogStatus.FAIL, "fail to validate login page without credentials");
	}
	}
	// This Scenario is about when Some one enter correct UserName
	// And invalid password field
	@Test(enabled = true, priority = 4, dataProvider = "invalidData")
	public void verifyLoginWithInvalidPassCredentials(String username, String password, String runMode)
			throws InterruptedException {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {
			test.log(LogStatus.PASS,
					"***************TC004_ Verify the Login page with InValid credentials****************");
			// LoginPage loginPage = new LoginPage(driver);
			test.log(LogStatus.PASS, "Entering username to the login page");
			loginPage.enterUserName(username); // Enter valid user name
			test.log(LogStatus.PASS, "Entering invalid password to the login page");
			loginPage.enterPassword(password); // Enter Invalid Password
			test.log(LogStatus.PASS, "Clicking to the login Button in Login page");
			loginPage.clickLoginbutton();
			Thread.sleep(2000);
			test.log(LogStatus.PASS, "Validating Error message in the login Page");
			Assert.assertEquals(loginPage.getAProblemErrorMsg(), "A problem..."); // Assertions
			Thread.sleep(1000);
			test.log(LogStatus.PASS, "Validating Error message in Login Page");
			Assert.assertEquals(loginPage.getInCorrectUserPassMsg().getText(), "Incorrect username or password");
			loginPage.refreshPage();// Assertions

		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, "fail to validate login page with InValid credentials");
		}
	}
	@Test(enabled = true, priority = 5, dataProvider = "searchData")
	public void verifyLoginWithValidCredentials(String username, String password, String runMode)
			throws InterruptedException {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {
			test.log(LogStatus.PASS, "***************TC005_ Verify Login with Valid credentials****************");
			test.log(LogStatus.PASS, "Enter valid  Username to the login page ");
			loginPage.enterUserName(username); // Enter Valid Username
			test.log(LogStatus.PASS, "Enter valid  Password to the login page");
			loginPage.enterPassword(password);
			test.log(LogStatus.PASS, "Clicking on to the LoginButton in login Page");
			dashBoardPage = loginPage.clickLoginbutton();
			test.log(LogStatus.PASS, "Login Successfully");
			System.out.println("Login Successfully in Informer DashBoard");
			test.log(LogStatus.PASS, "Title of the Page");
			Assert.assertEquals(dashBoardPage.getTitle(), "Informer Analytics |");
		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, "fail to login page with Valid credentials");
		}
	}

	@Test(enabled = true, priority = 6)
	public void logOutTest() throws InterruptedException {
		try {
			test.log(LogStatus.PASS, "***************TC006_ Verify logout from Informer Dashboard****************");
			test.log(LogStatus.PASS, "Click on Settings Icon");
			dashBoardPage.clickSettingIcon();
			Thread.sleep(2500);
			test.log(LogStatus.PASS, "Click on Logout button");
			loginPage = dashBoardPage.clickLogoutButton("Log Out");
			test.log(LogStatus.PASS, "Successfully logout from Informer Dashboard");
			System.out.println("Successfully logout from Informer Dashboard Application");
			test.log(LogStatus.PASS, "Verify the Logout successfull - " + loginPage.getLogOutSuccessMsg());
			Assert.assertEquals(loginPage.getLogOutSuccessMsg(), "You have been logged out successfully");
		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, "fail to logout from Informer Dashboard");
		}
	}

	@AfterSuite
	public void closeBrowser() {
		driver.close();
		report.endTest(test);
		report.flush();
	}

}
