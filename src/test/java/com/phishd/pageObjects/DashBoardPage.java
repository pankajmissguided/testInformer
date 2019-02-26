package com.phishd.pageObjects;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DashBoardPage {
	
	WebDriver driver;
	
	public DashBoardPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver =driver;
	}
//Page objects
	By settingicon = By.className("icon-cog");
	By settingOptions = By.className("dropdown-menu dropdown-left");
	//Client selection Dropdown
    By clientSelectDropdown = By.xpath("//*[@id=\"material_select_chosen\"]/a/span");
    
    //Inside Client Dropdown search Client
    
    By searchClient = By.xpath("//*[@id=\"material_select_chosen\"]/div/div/input");
    By clientName = By.xpath("//*[@class='contact-container']/span");
    
    // Report Search on Main Page of Informer : Reporter Search
    By reporterSearch = By.xpath("//a[contains(text(),'Reporter Search')]");
    By reporterSearchbox = By.xpath("//input[@placeholder='Reporter email address...']");
    By searchBtn = By.xpath("//*[@id=\"dashboard_search_user\"]/form/div[2]/div[2]/input");
    By reportersearchResult = By.xpath("//a[@data-placement='right']");
    
        By dashBoard = By.xpath("//a[@id='dashboard']");
    
      //Report IP Search on Main Page of Informer
        By ipSearch = By.xpath("//a[contains(text(),'IP Search')]");
        By ipSearchBox = By.xpath("//input[@placeholder='IP address...']");
        By ipSearchBtn =  By.xpath("//*[@id=\"dashboard_search_ip\"]/form/div[2]/div[2]/input");
        By ipSearchResult = By.xpath("//*[@id=\"search_results\"]/div/div/table/tbody/tr[1]/td[3]/p[1]/span");
    // Link Domain Search
        By linkDomainSearch = By.xpath("//a[contains(text(),'Link Domain Search')]");
        By linkDomainSearchBox = By.xpath("//input[@placeholder='Domain...']");
        By linkDomainSearchBtn = By.xpath("//*[@id='dashboard_search_link']/form/div[2]/div[2]/input");
      //*[@id="dashboard_search_link"]/form/div[2]/div[2]/input
        By domainSearchreults = By.xpath("//*[@id='search_results']/div/div/table/tbody/tr/td");
      //*[@id="search_results"]/div/div/table/tbody/tr[1]/td[2]/p/span
      //*[@id="search_results"]/div/div/table/tbody/tr[1]/td[2]/p[1]
        //invalid reporter search
        By problemOccured = By.xpath("//strong[contains(text(),'A problem occurred...')]"); 
        By noResultsFound = By.xpath("//h3[@class='font-w300 push-15']");	
        
        
        
           By linkSearchResult = By.tagName("select");
       
           //Verify Attachment Search
           By attachmentSearch = By.xpath("//a[@href='#dashboard_search_attachment']");
           By attachmentSearchBox = By.xpath("//input[@placeholder='Filename, md5, sha1, sha256 hash...']");
           By attachmentSearchBtn =  By.xpath("//*[@id='dashboard_search_attachment']/form/div[2]/div[3]/input");
          // By attachmentSearchResult = By.xpath("//*[@id=\"search_results\"]/div/div/table/tbody/tr[1]/td[3]/p[1]/span");  
        
           By clientNameinList = By.xpath("//*[@class='table table-striped table-condensed margin-top-15']/tbody/tr/td/p");
           By leftMenu = By.xpath("//*[@id='mCSB_1_container']/nav/ul/li/a");
         //Methods on Attachment Search
           public void clickAttachmentSearchBtn() {
           	driver.findElement(attachmentSearchBtn).click();
           }
           
           public void  enterAttachmentSearch(String DomainName) {
           	driver.findElement(attachmentSearchBox).clear();
               driver.findElement(attachmentSearchBox).sendKeys(DomainName);
           }
           public void clickAttachmentSearch() {
           	driver.findElement(attachmentSearch).click();
           } 
        //Methods on Link Domain Search
        public String IsLinkDomainAddressDisplayed() {
        	return driver.findElement(linkSearchResult).getText();
        }
       
        public void clickLinkDomainSearchBtn() {
        	driver.findElement(linkDomainSearchBtn).click();
        }
        public void  enterLinkDomainSearch(String DomainName) {
        	driver.findElement(linkDomainSearchBox).clear();
            driver.findElement(linkDomainSearchBox).sendKeys(DomainName);
        }
        public void clickLinkDomainSearch() {
        	driver.findElement(linkDomainSearch).click();
        }
        
        //Clicking on DashBoard to refresh main page
        public void clickDashBoard() {
        	driver.findElement(dashBoard).click();
        }
	//Method on IP search
        public WebElement IsIpAddressDisplayed() {
        	return driver.findElement(ipSearchResult);
        }
         public void  enterIpSearch(String IPAddress) {
        	driver.findElement(ipSearchBox).clear();
            driver.findElement(ipSearchBox).sendKeys(IPAddress);
        }
        public void clickIPSearch() {
        	driver.findElement(ipSearch).click();
        }
        public void clickIPSearchBtn() {
        	driver.findElement(ipSearchBtn).click();
        }
      //Method on Report Search
         public WebElement IsReporterSearchNameDisplayed() { 
	     return driver.findElement(reportersearchResult);
   }
         public void clickSearchBtn() {
    	 driver.findElement(searchBtn).click();
    }
        public void enterReporterSearch(String  reporterEmail) {
    	driver.findElement(reporterSearchbox).clear();
    	driver.findElement(reporterSearchbox).sendKeys(reporterEmail);
    }
        public void clickReporterSearch() {
    	driver.findElement(reporterSearch).click();
    }
        //Methods on clicking Setting Icon for Logout
    	public void clickSettingIcon() {
		driver.findElement(settingicon).click();
	}
	      public String getTitle() {
		   return driver.getTitle();
	}
	public String getCurentUrl() {
		return driver.getCurrentUrl();
	}
	
	
	public WebElement isClientNameDisplayed() {
		return driver.findElement(clientName);
		
	}
	public void clickOnClientDropDown(){
		driver.findElement(clientSelectDropdown).click();
	}
	
	public  void enterClientName(String ClientName) throws InterruptedException {
		driver.findElement(searchClient).sendKeys(ClientName);
		Thread.sleep(1500);
		driver.findElement(searchClient).sendKeys(Keys.ENTER);
	}
	public void setClientName(String clientName) throws InterruptedException {
		clickOnClientDropDown();
		enterClientName(clientName);
	}
	//Method for Invalid reporter search
	
	public String  getAProblemErrorMsg() {
		return driver.findElement(problemOccured).getText(); //noResultsFound
	}
	public String  getNoResultsFoundMsg() {
		return driver.findElement(noResultsFound).getText(); //noResultsFound
	}
	
	
	//LogOut
		public LoginPage clickLogoutButton(String option) {
			WebElement optionslist = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/ul[3]/li/div/div[2]/div/ul/li[4]/a"));
			Actions act = new Actions(driver);
//			List<WebElement> li = optionslist.findElements(By.tagName("span"));
//			for (WebElement webElement : li) {
//				System.out.println("get text - "+webElement.getText());
//				System.out.println("get Attribute - "+webElement.getAttribute("value"));
//				if (webElement.getText().trim().equals(option)) {
					act.moveToElement(optionslist).build().perform();
					act.click().build().perform();;
//				}
//			}
			return new LoginPage(driver);
		}

		//LogOut
				public ChangePasswordPage clickChangePassword(String option) {
					WebElement optionslist = driver.findElement(settingOptions);
					List<WebElement> li = optionslist.findElements(By.tagName("span"));
					for (WebElement webElement : li) {
						if (webElement.getText().trim().equals(option)) {
							webElement.click();
						}
					}
					return new ChangePasswordPage(driver);
				}
		
				public MyAccountPage clickMyAccount(String option) {
					WebElement optionslist = driver.findElement(settingOptions);
					List<WebElement> li = optionslist.findElements(By.tagName("span"));
					for (WebElement webElement : li) {
						if (webElement.getText().trim().equals(option)) {
							webElement.click();
						}
					}
					return new MyAccountPage(driver);
				}

				public LoginPage logOutTest() {
					// TODO Auto-generated method stub
				       clickSettingIcon();
				       clickLogoutButton("Log Out");
				       return new LoginPage(driver);
					
				}
				/**
				 * Domain Search Results
				 * 
				 */
				public boolean getDomainSearchResults(String domainName) {
					
					List<WebElement> domainValues = driver.findElements(domainSearchreults);
					for (WebElement webElement : domainValues) {
						if (webElement.getText().trim().contains(domainName)) {
							return true;
						}
					}
					return false;
				}
	public boolean getAttachmentSearchResults(String attachmentSearch) {
					
					List<WebElement> attachmentValues = driver.findElements(domainSearchreults);
					for (WebElement webElement : attachmentValues) {
						if (webElement.getText().trim().contains(attachmentSearch)) {
							return true;
						}
					}
					return false;
				}
	
	public void clickONLeftSideMenu(String link){
		List<WebElement> li = driver.findElements(leftMenu);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (WebElement webElement : li) {
			System.out.println(webElement.getAttribute("href"));
			System.out.println(webElement.getText());
			if (webElement.getText().contains(link)) {
//				js.executeScript("arguments[0].click();", webElement);
				webElement.click();
				break;
			}
		}
	
	}
}
