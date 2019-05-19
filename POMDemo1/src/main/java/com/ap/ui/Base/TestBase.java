package com.ap.ui.Base;//import fucking dependecies!!!!In POM XML!!!!
//or else yur fucked
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.ap.ui.TestUtil.TestUtil;
import com.ap.ui.TestUtil.WebEventListener;

public class TestBase {
	public static WebDriver driver;
	public static Properties propt;
	public static EventFiringWebDriver en_driver;
	public static WebDriverEventListener eventListener;

public TestBase(){//constructor, using a try catch method to work with and access config file
	try{
		propt = new Properties();
		FileInputStream ipa = new FileInputStream(System.getProperty("user.dir")+"/POMDemo1/src/main/java/com/ap/ui/Configure/Configure.Properties");
		propt.load(ipa);
	}catch (FileNotFoundException e){
		e.printStackTrace();
	}catch(IOException e){
		e.printStackTrace();
	}
}
public static void CallBrowser(){
	String browserName = propt.getProperty("browser");
	
	if(browserName.equalsIgnoreCase("chrome")){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ProSmart\\Desktop\\test\\chromedriver.exe");
		driver = new ChromeDriver();
		}
	else if(browserName.equalsIgnoreCase("FF")){
		System.setProperty("webdriver.gecko.driver", "path of gecko driver");
		driver = new FirefoxDriver();
	}
	else if(browserName.equalsIgnoreCase("IE")){
		System.setProperty("webdriver.InternetExplorer.driver", "path of the ie driver");
		driver = new InternetExplorerDriver();
	}

	en_driver = new EventFiringWebDriver(driver);
	eventListener = new WebEventListener();
	en_driver.register(eventListener);
	
	driver = en_driver;
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(TestUtil.Implicit_Wait, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(TestUtil.Page_Load, TimeUnit.SECONDS);
	
	driver.get(propt.getProperty("url"));
	
	
	
}
}
