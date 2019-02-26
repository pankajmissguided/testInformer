package com.phishd.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CasesRequiringActionPage {
	
WebDriver driver;
	
	public CasesRequiringActionPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver =driver;
	}
	
	//Page Objects
	By casesRequiringActionTab = By.xpath("//a[@href='/report_cases/cases/requiring_action']");
	By panelTitleAfter = By.xpath("//h3[@class='panel-title']");
	
	By casesReqActionSearchTitle = By.xpath("//h3[@class='panel-title']");
	
	By noOfCasesBefore = By.xpath("//*[@id='case_management_active_cases']/b");
	By noOfCasesAfter =By.xpath("//span[@class='badge bg-phishd-primary-yellow phishd-dark-blue']") ;

	By totalNumberOfCasesRequiredAction = By.xpath("//b[@class='badge bg-phishd-primary-yellow phishd-dark-blue']");
	By totalNumberAfterSearch = By.xpath("//span[@class='badge bg-phishd-primary-yellow phishd-dark-blue']");
	//Search by Reported BY
	By reportedBySearchBox = By.xpath("//input[@placeholder='Reported by...']");
	By filterBtn = By.xpath("//input[@value='Filter']");
	By searchResult = By.xpath("//tbody//tr[1]//td[3]//p[2]//a[1]");
	By viewCase = By.xpath("//tbody//tr[1]//td[7]//a[1]");
	By reportedByUser = By.xpath("//a[contains(text(),'pankaj.kumar@mwrinfosecurity.com')]");
	By resetBtn = By.xpath("//input[@value='Reset']");
	By reportedAt = By.xpath("//input[@name='filter[Case][reported_at]']");
	By reportedFrom = By.xpath("//input[@placeholder='From name / email...']");
	By resetMsg = By.xpath("//p[contains(text(),'Your filters have been reset')]");
	By showAllCases = By.xpath("//a[@class='btn btn-default btn-xs margin-right-5']");
	
	//Subject
	By subjectBy = By.xpath("//input[@placeholder='Subject...']");
	
	//Mark Selected Cases Tab in Cases requiring Action Page
	By markSelectedCasesTab = By.xpath("//span[@id='mark_selected']");
	By errorMarkSelectedTab = By.xpath("//h4[@id='error_message']");
	By errormsg = By.xpath("//h4[@id='error_message']");
	By errorCancelTab = By.xpath("//div[@id='modal-error']//button[@type='button'][contains(text(),'Cancel')]");
	By casereqCountleft = By.xpath("//b[@class='badge bg-phishd-primary-yellow phishd-dark-blue']");
	By casereqCountright = By.xpath("//span[@class='badge bg-phishd-primary-yellow phishd-dark-blue']");
	
	//Assign To
	By chooseUser = By.xpath("//span[@class='filter-option pull-left'][contains(text(),'Choose user...')]");
	By assignToBtn= By.xpath("//input[@value='Assign']");
	By caret = By.xpath("//button[@title='Choose user...']//span[@class='caret']");
	By unAssigned = By.xpath("//div[contains(text(),'Unassigned')]");
	//Methods 
public void ClickOnCasesRecognitionTab() {
	driver.findElement(casesRequiringActionTab).click();
}

public WebElement  getRequiringActioCasesBefore() {
return driver.findElement(noOfCasesBefore);
}
public WebElement  getRequiringActioCasesafter() {
return driver.findElement(noOfCasesAfter);
}

public WebElement IsCasesReqActionSearchResult() { 
    return driver.findElement(casesReqActionSearchTitle);
    }
public String getPanelTotalCases() {
	return driver.findElement(totalNumberAfterSearch).getText();
}
public String getPanelTitle() {
	return driver.findElement(casesReqActionSearchTitle).getText();
}
	public String getTittle(){
		 return driver.getTitle();
		 
	}

//public String ActualTitle() {
	//return driver.getTitle();
//}
public void enterReporteredBy(String  reporterEmail) {
	driver.findElement(reportedBySearchBox).clear();
	driver.findElement(reportedBySearchBox).sendKeys(reporterEmail);
}
public void enterReporteredFrom(String  InvalidReportedBy) {
	driver.findElement(reportedFrom).clear();
	driver.findElement(reportedFrom).sendKeys(InvalidReportedBy);
}
public void enterSubjectBy(String  Subject) {
	driver.findElement(subjectBy).clear();
	driver.findElement(subjectBy).sendKeys(Subject);
}

public void clickFilterBtn() {
	driver.findElement(filterBtn).click();
}
public WebElement searchResultReportedBy() {
	return driver.findElement(searchResult);
}
public void clickViewCaseBtn() {
	driver.findElement(viewCase).click();
}
public WebElement  userReportedBy() {
	return driver.findElement(reportedByUser);
}
public void clickResetBtn() {
	driver.findElement(reportedBySearchBox).clear();
	driver.findElement(reportedAt).clear();
    driver.findElement(reportedFrom).clear();
	driver.findElement(resetBtn).click();
}
public WebElement verifyRestMsg() {
	return driver.findElement(resetMsg);
}
public void clickMarkSelectedCasesTab() {
	driver.findElement(markSelectedCasesTab).isEnabled();
	driver.findElement(markSelectedCasesTab).isDisplayed();
	driver.findElement(markSelectedCasesTab).click();
}
public void clickshowAllCases() {
	driver.findElement(showAllCases).isEnabled();
	driver.findElement(showAllCases).isDisplayed();
	driver.findElement(showAllCases).click();
}
//showAllCases

public WebElement verifyErrorMsg() {
	return driver.findElement(errormsg);
}
public void selectchooseUser() {
	Select user = new Select(driver.findElement(By.xpath("chooseUser")));
	user.selectByIndex(4);

	driver.findElement(caret).click();
	}
	public void clickAssignBtn() {
		driver.findElement(assignToBtn).isEnabled();
		driver.findElement(assignToBtn).isDisplayed();
		driver.findElement(assignToBtn).click();	
	}

	public WebElement verifyUnassigned() { 
	    return driver.findElement(casesReqActionSearchTitle);
	    }

public void clickCancelTab() {
	driver.findElement(errorCancelTab).isDisplayed();
    driver.findElement(errorCancelTab).isDisplayed();
    driver.findElement(errorCancelTab).click();
}

public boolean isCaseReqActionCountSame(){
	WebElement leftcount = driver.findElement(casereqCountleft);
	WebElement rightcount = driver.findElement(casereqCountright);
	String lefttext = leftcount.getText();
	String righttext = rightcount.getText();
	
	System.out.println("Left Count - "+ lefttext + " - right count "+ righttext);
	int leftcnt = Integer.parseInt(lefttext);
	int rightcnt = Integer.parseInt(righttext);
	if (leftcnt == rightcnt) {
		return true;
	}
	return false;
}

public boolean isCaseReqActionCount(){
	
	WebElement rightcount = driver.findElement(casereqCountright);
	
	String righttext = rightcount.getText();
	
	System.out.println(" - right count "+ righttext);
	
	int rightcnt = Integer.parseInt(righttext);
	if (rightcnt == 0) {
		return true;
	}
	return false;
}
public boolean isCaseReportedAtCount(){
	
	WebElement rightcount = driver.findElement(casereqCountright);
	
	String righttext = rightcount.getText();
	
	System.out.println(" - right count "+ righttext);
	
	int rightcnt = Integer.parseInt(righttext);
	if (rightcnt == 0) {
		return false;
	}
	return true;
}
public boolean isCaseSubjectByCount(){
	
	WebElement rightcount = driver.findElement(casereqCountright);
	
	String righttext = rightcount.getText();
	
	System.out.println(" - right count "+ righttext);
	
	int rightcnt = Integer.parseInt(righttext);
	if (rightcnt != 0) {
		return true;
	}
	return false;
}//isCaseInvalidSubjectByCount
public boolean isCaseInvalidSubjectByCount(){
	
	WebElement rightcount = driver.findElement(casereqCountright);
	
	String righttext = rightcount.getText();
	
	System.out.println(" - right count "+ righttext);
	
	int rightcnt = Integer.parseInt(righttext);
	if (rightcnt == 0) {
		return true;
	}
	return false;
}
}
