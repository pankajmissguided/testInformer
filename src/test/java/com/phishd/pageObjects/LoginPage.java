package com.phishd.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	// all the page objects and methods will be created
	// Every page class needs to create webdriver instance
	// Page Class should be created with Constructor
	WebDriver driver;
	
	//Page Objects for Webelements from UI 
	// Page object from Login Page
	
	//Page Object for Login
	By username = By.name("username");
	//Page Object for password
	By password = By.name("password");
	
	// Login button
	By loginbtn = By.name("login");
	
	// error messages
	By therewasproblm= By.xpath("/html/body/div/div/div/div/div[2]/div/h3");
	
	//error message when entering incorrect password
	By aproblem=By.xpath("//*[@id=\"flash_message\"]/h3");
	
	
	//username error
	By usererror = By.xpath("/html/body/div/div/div/div/div[2]/div/p[1]");
	//password error
	
	By passerror = By.xpath("/html/body/div/div/div/div/div[2]/div/p[2]");
	
	//Password Error msg  when entering correct username and blank password
	By passerrormsg = By.xpath("/html/body/div/div/div/div/div[2]/div/p");
	
	//Incorrect UserName or Password
	By incorrectuserpassname = By.xpath("//*[@id=\"flash_message\"]/p");
	
	By logoutbtn = By.xpath("/html/body/div/div/div[2]/div[2]/ul[3]/li/div/div[2]/div/ul/li[4]/a");
	
	By logoutsuccessmsg = By.xpath("//*[@id='flash_message']/p");
	//*[@id="flash_message"]/p
	
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	// Page Object methods
	// username
	public void enterUserName(String uname) {
		driver.findElement(username).clear();
		driver.findElement(username).sendKeys(uname);
	}
	
	//password
	public void enterPassword(String pass) {
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(pass);
	}

	//loginbtn
	public DashBoardPage clickLoginbutton() {
		driver.findElement(loginbtn).click();
		return new DashBoardPage(driver);
	}
	
	
	public String  getTherepblmerrormsg() {
		return driver.findElement(therewasproblm).getText();
	}
	public String  getUsernameerror() {
		return driver.findElement(usererror).getText();
	}
	public WebElement  getPassworderror() {
		return driver.findElement(passerror);
	}
	public String getPasserrormsg() {                            //This is different error msg appearing when we not entering password with correct username.
		return driver.findElement(passerrormsg).getText();
	}
	public String getAProblemErrorMsg() {                    //This error msg appearing when we entering correct username and incorrect error msg.
		return driver.findElement(aproblem).getText();
	}
	public WebElement getInCorrectUserPassMsg() {                //This error msg for "incorrect username and password" when entering valid username and invalid password
		return driver.findElement(incorrectuserpassname);
	}
public String getLogOutSuccessMsg() {
	return driver.findElement(logoutsuccessmsg).getText();
}
	public String getTitle() {
		// TODO Auto-generated method stub
		return driver.getTitle();
	}

	public DashBoardPage login(String uname, String pass) {
		// TODO Auto-generated method stub
		enterUserName(uname);
		enterPassword(pass);
		 clickLoginbutton();
		 return new DashBoardPage(driver);
		
	}

	public void refreshPage() {
		// TODO Auto-generated method stub
		driver.navigate().refresh();
	}
}
