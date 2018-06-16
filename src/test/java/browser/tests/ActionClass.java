package browser.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionClass {
WebDriver driver;
	@BeforeClass
	public void setup() {
	 WebDriverManager.chromedriver().setup();	
		 driver=new ChromeDriver();
		 driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2fTestComplete11%2fWebOrders%2fDefault.aspx");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("tester");
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
	}
	
	@Test
	public void test() {
		Actions action=new Actions(driver);
		
		
	}
	
	@AfterClass
	public void teardown() {
		driver.close();
	}
}
