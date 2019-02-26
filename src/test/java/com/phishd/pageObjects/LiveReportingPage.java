package com.phishd.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LiveReportingPage  {
WebDriver driver;
	
	public LiveReportingPage (WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver =driver;
	}
	//Page Objects

	By LiveReportingtab = By.xpath("//a[@id='case_management_live']");
	By ViewCaseBttn = By.xpath("//tbody[@id='live_tbody']/tr[1]/td[7]/a[1]");
	By ReportedByEmail = By.xpath("//a[contains(text(),'pankaj.kumar@mwrinfosecurity.com')]");
	By RulesTab= By.xpath("//a[contains(text(),'Rules')]");
	By OverviewTab = By.xpath("//a[contains(text(),'Overview')]");
	By CoreHeaders = By.xpath("//a[contains(text(),'Core Headers')]");
	By PanelTitel = By.xpath("//h3[@class='panel-title']");
	By ReportedBy = By.xpath("//tbody[@id='live_tbody']/tr[1]/td[3]/p[1]/a[1]");
    By UserName = By.xpath("//*[@id='tab_content_overview']/div/div[1]/div/div[2]/div[2]/div");
    By Reported = By.xpath("//h5[contains(text(),'Reported')]");
    By Message = By.xpath("//h5[contains(text(),'Message')]");
	By TriggeredRulePanel =  By.xpath("//h5[@class='block-title margin-bottom-0']");
	
	By TreiggeredRules = By.xpath("//th[contains(text(),'Triggered Rule')]");
	
	By Weight = By.xpath("//th[contains(text(),'Weight')]");
	By RuleReason = By.xpath("//th[contains(text(),'Reason')]");
	
	By MessageCoreHeader = By.xpath("//h5[@class='block-title margin-bottom-0']");
	By GetHeaders = By.xpath("//a[@href='#headers']");
	By MessageHeader = By.xpath("//th[contains(text(),'Message Header')]");
	By PanelMessageHeader = By.xpath("//h5[@class='block-title margin-bottom-0']");
	By Value = By.xpath("//th[contains(text(),'Value')]");
	
	By IpAddress = By.xpath("//a[@href='#ip_addresses']");
	By PanelIPAddressTitle = By.xpath("/h5[@class='pull-left block-title margin-bottom-0']");
	By IPAddress = By.xpath("//th[contains(text(),'IP Address')]");
	By Type = By.xpath("//th[contains(text(),'Type')]");
	
	By MessageBody = By.xpath("//a[contains(text(),'Message Body')]");
	By MessageBodyPanelTitle = By.xpath("//h5[@class='block-title margin-bottom-0']");
	By TextPlain = By.xpath("//a[contains(text(),'text/plain')]");
	By ApplicationRtf = By.xpath("//a[contains(text(),'application/rtf')]");
	
	By LinksDetected = By.xpath("//a[@href='#links']");
	By LinksDetectedPanelTitle = By.xpath("//h5[contains(text(),'Detected Links')]");
	By ExportLinks = By.xpath("//a[@class='pull-right btn btn-success btn-xs']");
	By LinkText = By.xpath("//th[contains(text(),'Link')]");
	By Attributes = By.xpath("//th[contains(text(),'Attributes')]");
	By DetectedUniqueDomains = By.xpath("//h5[contains(text(),'Detected Unique Domains')]");
	
	By Attachments = By.xpath("//a[contains(text(),'Attachments')]");
	By AttachmentPanelTitle = By.xpath("//h5[contains(text(),'Detected Attachments')]");
	By Name = By.xpath("//th[contains(text(),'Name')]");
	By MimeType = By.xpath("//th[contains(text(),'Mime Type')]");
	By Category = By.xpath("//th[contains(text(),'Category')]");
	By ByMimeType = By.xpath("//h5[contains(text(),'By Mime Type')]");
	
	By DownloadMessage = By.xpath("//a[contains(text(),'Download Message')]");
	By DownLoadOutlookMsg = By.xpath("//strong[contains(text(),'Download Outlook .msg')]");
	//Methods/
/*	public void clickMarkSelectedCasesTab() {
		driver.findElement(markSelectedCasesTab).isEnabled();
		driver.findElement(markSelectedCasesTab).isDisplayed();
		driver.findElement(markSelectedCasesTab).click();
		
		public void ClickOnCasesRecognitionTab() {
			driver.findElement(casesRequiringActionTab).click();
		}*/
	
		public void clickLiveReporting() {
			driver.findElement(LiveReportingtab).isDisplayed();
			driver.findElement(LiveReportingtab).click();
		}
		// public void clickDashBoard() {
	       // 	driver.findElement(dashBoard).click();
	      //  }
	public void clickViewCaseBtn() {
	driver.findElement(ViewCaseBttn).isEnabled();
	driver.findElement(ViewCaseBttn).isDisplayed();
    driver.findElement(ViewCaseBttn).click();
	}
	public void clickOverviewTab() {
		driver.findElement(OverviewTab).isEnabled();
		driver.findElement(OverviewTab).isDisplayed();
	    driver.findElement(OverviewTab).click();
	}
	 
	public void clickRulesTab() {
	driver.findElement(RulesTab).isEnabled();
	driver.findElement(RulesTab).isDisplayed();
	driver.findElement(RulesTab).click();	
	 }
	public void clickCoreHeaders() {
		driver.findElement(CoreHeaders).isEnabled();
		driver.findElement(CoreHeaders).isDisplayed();
		driver.findElement(CoreHeaders).click();	
	}
	public void clickHeaders() {
		driver.findElement(GetHeaders).isEnabled();
		driver.findElement(GetHeaders).isDisplayed();
		driver.findElement(GetHeaders).click();	
	}
	public void clickIpAddress() {
		driver.findElement(IpAddress).isEnabled();
		driver.findElement(IpAddress).isDisplayed();
		driver.findElement(IpAddress).click();	
	}
	
	public void clickMessageBody() {
		driver.findElement(MessageBody).isEnabled();
		driver.findElement(MessageBody).isDisplayed();
		driver.findElement(MessageBody).click();	
	}//LinksDetected
	
	public void clickLinks() {
		driver.findElement(LinksDetected).isEnabled();
		driver.findElement(LinksDetected).isDisplayed();
		driver.findElement(LinksDetected).click();	
	}
	public void verifyExportLinks() {
		driver.findElement(ExportLinks).isEnabled();
		driver.findElement(ExportLinks).isDisplayed();
		driver.findElement(ExportLinks).getText();	
	}
	
	public void verifyAttachments() {
		driver.findElement(Attachments).isEnabled();
		driver.findElement(Attachments).isDisplayed();
		driver.findElement(Attachments).click();	
	}
	
	public void verifyDownloadMessage() {
		driver.findElement(DownloadMessage).isEnabled();
		driver.findElement(DownloadMessage).isDisplayed();
		driver.findElement(DownloadMessage).click();	
	}
	public String  getReportedTitle() {
		return driver.findElement(Reported).getText();}
    
	public String  getReportedBy() {
		return driver.findElement(ReportedBy).getText();}
	
	public String  getUserName() {
		return driver.findElement(UserName).getText();}
	
	public String  getPanelTitle() {
		return driver.findElement(PanelTitel).getText();}
	
	public String  getviewCasebutton() {
		return driver.findElement(ViewCaseBttn).getText();
	}
	public String  getMessagetab() {
		return driver.findElement(Message).getText();
	}
	public String  getTriggeredRulePanel() {
		return driver.findElement(TriggeredRulePanel).getText();
	}
	
	public String  getTriggeredRule() {
		return driver.findElement(TreiggeredRules).getText();//Weight
	}
	
	public String  getWeight() {
		return driver.findElement(Weight).getText();//Weight
	}
	
	public String  getRuleReason() {
		return driver.findElement(RuleReason).getText();
	}
	public String  getMessageCoreHeader() {
		return driver.findElement(MessageCoreHeader).getText();//MessageHeader
	}
	
	public String  getMessageHeader() {
		return driver.findElement(MessageHeader).getText();//MessageHeader
	}
	
	public String  getValue() {
		return driver.findElement(Value).getText();//MessageHeader
	}
	
	public String  getPanelMessageHeaderTitle() {
		return driver.findElement(PanelMessageHeader).getText();//MessageHeader
	}
	
	public String  getPanelIPAddressTitle() {
		return driver.findElement(PanelMessageHeader).getText();//IPAddress
	}
	
	public String  getIPAddress() {
		return driver.findElement(IPAddress).getText();//IPAddress
	}
	public String  getType() {
		return driver.findElement(Type).getText();//MessageBodyPanelTitle
	}
	public String getMessageBodyPanelTitle() {
		return driver.findElement(MessageBodyPanelTitle).getText();
	}
	public String getTextPlain() {
		return driver.findElement(TextPlain).getText();//ApplicationRtf
	}
	
	public String getApplicationRtf() {
		return driver.findElement(ApplicationRtf).getText();//ApplicationRtf
	}//LinksDetectedPanelTitle
	
	public String getLinksDetectedPanelTitle() {
		return driver.findElement(LinksDetectedPanelTitle).getText();//ApplicationRtf
	}//ExportLinks
	
	public String getLinkText() {
		return driver.findElement(LinkText).getText();//ApplicationRtf
	}//Attributes
	public String getAttributes() {
		return driver.findElement(Attributes).getText();//ApplicationRtf
	}//DetectedUniqueDomains
	
	public String getDetectedUniqueDomains() {
		return driver.findElement(DetectedUniqueDomains).getText();//ApplicationRtf
	}//AttachmentPanelTitle
	public String getAttachmentPanelTitle() {
		return driver.findElement(AttachmentPanelTitle).getText();//ApplicationRtf
	}
	public String getName() {
		return driver.findElement(Name).getText();//ApplicationRtf
	}//MimeType
	
	public String getMimeType() {
		return driver.findElement(MimeType).getText();//ApplicationRtf
	}
	public String getCategory() {
		return driver.findElement(Category).getText();//ApplicationRtf
	}//ByMimeType
	
	public String getByMimeType() {
		return driver.findElement(ByMimeType).getText();//ApplicationRtf
	}//DownloadMessage
	
	public String getDownLoadOutlookMsg() {
		return driver.findElement(DownLoadOutlookMsg).getText();//ApplicationRtf
	}
	/*
	 * public boolean getviewCasetab(String View) {
	 * 
	 * List<WebElement> viewCases = driver.findElements(ViewCaseBttn); for
	 * (WebElement webElement : viewCases) { if
	 * (webElement.getText().trim().contains(View)) { return true; } } return false;
	 * }
	 */
}
  // public boolean getDomainSearchResults(String domainName) {
		
	//	List<WebElement> domainValues = driver.findElements(domainSearchreults);
		//for (WebElement webElement : domainValues) {
			//if (webElement.getText().trim().contains(domainName)) {
				//return true;
			//}
		//}
	//	return false;

	/*public LoginPage logOutTest() {
		// TODO Auto-generated method stub
	       clickSettingIcon();
	       clickLogoutButton("Log Out");
	       return new LoginPage(driver);
		
	}*/
