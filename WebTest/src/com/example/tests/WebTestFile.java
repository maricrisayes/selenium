package com.example.tests;

//import java.util.regex.Pattern;
//import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.RemoteWebDriver;


public class WebTestFile {
	 WebDriver driver;
	   String baseUrl, nodeURL;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @BeforeClass(alwaysRun = true)
	  public void setUp() throws MalformedURLException {
	   
	    baseUrl = "http://52.42.226.149/SchoolSchedule/";
	    nodeURL = "http://selenium-hub:4444/wd/hub";
	    DesiredCapabilities capability = DesiredCapabilities.firefox();
	    capability.setBrowserName("firefox");
	    //capability.setPlatform(Platform.XP);
	    driver = new RemoteWebDriver(new URL(nodeURL), capability);
	    
	    //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	
	
	  @Test
	  public void testLink() throws Exception {
	    driver.get(baseUrl);
	    driver.findElement(By.name("title")).clear();
	    driver.findElement(By.name("title")).sendKeys("Web Development");
	    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
	    assertEquals(driver.findElement(By.linkText("TeamTAE")).getText(), "TeamTAE");
	  }
	  @Test
      public void testAddWebDev() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.name("title")).clear();
        driver.findElement(By.name("title")).sendKeys("Web Development");
        driver.findElement(By.xpath("(//input[@name='day'])[3]")).click();
        new Select(driver.findElement(By.name("starttime"))).selectByVisibleText("2:00pm");
        new Select(driver.findElement(By.name("endtime"))).selectByVisibleText("5:00pm");
        driver.findElement(By.name("Submit")).click();
        assertEquals(driver.findElement(By.xpath("//tr[8]/td[4]")).getText(), "Web Development");
        assertEquals(driver.findElement(By.xpath("//tr[9]/td[4]")).getText(), "Web Development");
        assertEquals(driver.findElement(By.xpath("//tr[10]/td[4]")).getText(), "Web Development");
      }
      @Test
      public void testAddAlgebra() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.name("title")).clear();
        driver.findElement(By.name("title")).sendKeys("College Algebra");
        driver.findElement(By.xpath("(//input[@name='day'])[2]")).click();
        new Select(driver.findElement(By.name("starttime"))).selectByVisibleText("9:00am");
        new Select(driver.findElement(By.name("endtime"))).selectByVisibleText("11:00am");
        driver.findElement(By.name("Submit")).click();
        assertEquals(driver.findElement(By.xpath("//tr[3]/td[3]")).getText(), "College Algebra");
        assertEquals(driver.findElement(By.xpath("//tr[4]/td[3]")).getText(), "College Algebra");
      }
      
      
      
      
	  @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
	  private boolean isElementPresent(By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		  }

		  private boolean isAlertPresent() {
		    try {
		      driver.switchTo().alert();
		      return true;
		    } catch (NoAlertPresentException e) {
		      return false;
		    }
		  }

		  private String closeAlertAndGetItsText() {
		    try {
		      Alert alert = driver.switchTo().alert();
		      String alertText = alert.getText();
		      if (acceptNextAlert) {
		        alert.accept();
		      } else {
		        alert.dismiss();
		      }
		      return alertText;
		    } finally {
		      acceptNextAlert = true;
		    }
		  }
}
