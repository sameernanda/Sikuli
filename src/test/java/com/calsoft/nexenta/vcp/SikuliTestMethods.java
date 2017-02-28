package com.calsoft.nexenta.vcp;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.google.common.base.Function;
import com.nexenta.ftaf.utilities.connectors.fusionGUI.ExcelPojoForGUI;

public class SikuliTestMethods extends SikuliTestMethodsBase{
	protected static final Logger _logger = Logger.getLogger(SikuliTestMethods.class);

	public void fluentWait(Pattern imageFile) throws InterruptedException {
		try {
			Region mIcon = currentScreen.wait(imageFile,20);
			Thread.sleep(1000);
			mIcon.highlight();
			mIcon.click();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public String open_Browser(String browser) {
		_logger.info("Trying to open browser "+browser);
		browserName = browser;
		try{
			switch (browser) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver",_contextLocationForDriver+"/chromedriver.exe");
				driver = new ChromeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "IE":
				System.setProperty("webdriver.ie.driver",_contextLocationForDriver+"/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				break;
			case "Safari":
				System.setProperty("webdriver.safari.driver",_contextLocationForDriver+"/Safari.exe");
				driver = new SafariDriver();
				break;
			case "Opera":
				System.setProperty("webdriver.opera.driver",_contextLocationForDriver+"/operadriver.exe");
				driver = new OperaDriver();
				break;
			}
			wdw = new WebDriverWait(driver, 60);
//					driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}catch (Exception e){
			_logger.error("Unable to open Browser due to - "+e.getMessage());
			throw new RuntimeException("Unable to open Browser due to - "+e.getMessage());
		}
		_logger.info(browser + " is successfully opened");
		return browser + " is successfully opened";
	}

	public String open_URL(ExcelPojoForGUI pojo) {
		try {
			_logger.info("Trying to open URL "+pojo.getInput());
			currentScreen = new Screen();
			driver.get(pojo.getInput());
			_logger.info("Successfully opened URL "+pojo.getInput());
			returnMsg = "Successfully opened " + pojo.getInput();
		} catch (Exception pe) {
			pe.printStackTrace();
			_logger.error("Error occured while opening Url "+ pojo.getInput() + " because " + pe.toString());
			throw new RuntimeException("Error occured while opening Url "+ pojo.getInput() + " because " + pe.toString());
		}
		return returnMsg;
	}

	public String click_Link(ExcelPojoForGUI pojo) {
		try {
			_logger.info("Trying to enter the Input "+pojo.getInput());
			Pattern patternImage = new Pattern(pojo.getPropertyValue());
			fluentWait(patternImage);
			currentScreen.click();
			_logger.info("Successfully entered the Input "+pojo.getInput());
			returnMsg = "Successfully entered input in " + pojo.getPropertyValue() + " as "	+ pojo.getInput();
		} catch (Exception pe) {
			_logger.error("Error occured while writing input to "+ pojo.getPropertyValue() + " because " + pe.toString());
			throw new RuntimeException("Error occured while writing input to "+ pojo.getPropertyValue() + " because " + pe.toString());
		}
		return returnMsg;
	}

	public String enter_Text(ExcelPojoForGUI pojo) {
		try {
			_logger.info("Trying to enter the Input "+pojo.getInput());
			Pattern patternImage = new Pattern(pojo.getPropertyValue());
			fluentWait(patternImage);
			currentScreen.click();
			currentScreen.type(pojo.getInput());
			_logger.info("Successfully entered the Input "+pojo.getInput());
			returnMsg = "Successfully entered input in " + pojo.getPropertyValue() + " as "	+ pojo.getInput();
		} catch (Exception pe) {
			_logger.error("Error occured while writing input to "+ pojo.getPropertyValue() + " because " + pe.toString());
			throw new RuntimeException("Error occured while writing input to "+ pojo.getPropertyValue() + " because " + pe.toString());
		}
		return returnMsg;
	}

	public String verify_Image(ExcelPojoForGUI pojo) {
		try {
			_logger.info("Trying to verify whether object exists on webpage or not ");
			Pattern patternImage = new Pattern(pojo.getPropertyValue());
			Region mIcon = currentScreen.wait(patternImage,20);
			mIcon.highlight();
			assert(currentScreen.exists(patternImage)!=null);
			_logger.info("Successfully verified object exists on the web page");
			returnMsg = "Successfully verified object exists on the web page";
		} catch (Exception pe) {
			_logger.error("Error occured while verifing object exists on the web page "+ pe.toString());
			throw new RuntimeException("Error occured while verifing object exists on the web page " + pe.toString());
		}
		return returnMsg;
	}

	public void quit_Browser() throws InterruptedException {
		if (driver != null) {
			driver.quit();
		}
	}

}

