package com.phishd.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CasesPage {
	
	WebDriver driver;
	public CasesPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver= driver;
	}
	
	By allcaseslnk= By.id("report_cases_cases_");
	public String getTile(){
		 return driver.getTitle();
		 
	}
	public String getCurrentUrl(){
		return driver.getCurrentUrl();
	}
	
	public void clickOnCaseLink(String caselink){
		List<WebElement> li = driver.findElements(allcaseslnk);
		for (WebElement webElement : li) {
			System.out.println(webElement.getText());
			if (webElement.getText().contains(caselink)) {
				webElement.click();
				break;
			}
		}
	}
	

}
