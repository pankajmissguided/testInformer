package com.phishd.testMethods;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.phishd.pageObjects.CasesPage;
import com.phishd.pageObjects.CasesRequiringActionPage;
import com.phishd.pageObjects.DashBoardPage;
import com.phishd.pageObjects.LoginPage;
import com.phishd.testBase.TestBase;
import com.relevantcodes.extentreports.ExtentReports;

public class TS05_VerifyAllCases extends TestBase {
	LoginPage loginPage;
	DashBoardPage dashBoardPage;
	CasesRequiringActionPage casereqPage;
	CasesPage casePage;

	public TS05_VerifyAllCases() {
		// TODO Auto-generated constructor stub
		super();
	}

	@BeforeSuite
	public void setup() throws InterruptedException {
		try {
			initialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		report = new ExtentReports(System.getProperty("user.dir")
				+ "\\ExtentReports.html");
		test = report.startTest("TS01_LoginTest");
		loginPage = new LoginPage(driver);
		dashBoardPage = new DashBoardPage(driver);
		dashBoardPage = loginPage.login(prop.getProperty("username"),
				prop.getProperty("password"));
		dashBoardPage.setClientName(prop.getProperty("clientName"));

	}

	@Test
	public void TC01_VerifyAllCases() throws InterruptedException {
		try {
			Thread.sleep(6000);
		      System.out.println("Started Test Method");
			dashBoardPage.clickONLeftSideMenu("Ca");
			Thread.sleep(2000);
			casePage = new CasesPage(driver);
			casePage.clickOnCaseLink("ALL CASES");
			Assert.assertEquals(casePage.getTile(),"Report Cases | phishd - Informer Analysis Platform");
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
	
}
