package kontrloni;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;




public class TestiranjeSaucedo {
	private static WebDriver driver;
	
	public static final String URL = "https://www.saucedemo.com/inventory.html";
	
	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver","C:\\ChromeDriver\\ChromeDriver.exe");
		driver = new ChromeDriver();
	}

	@Test (priority = 1)
	public void odlazakNaStranicuCorect() {
		driver.navigate().to(Home.URL);
//		driver.manage().window().maximize();
		String userName = "standard_user";
		String password = "secret_sauce";
		MetodeSaucedo.userName(driver,userName);
		MetodeSaucedo.Password(driver, password);
		MetodeSaucedo.LoginBTN(driver);
		String actual = driver.getCurrentUrl();
		String expected = URL;
		
		Assert.assertEquals(actual, expected);
	}
	@Test (priority = 2)
	public void Sort() {
		driver.navigate().to(Home.URL);
		String userName = "standard_user";
		String password = "secret_sauce";
		MetodeSaucedo.userName(driver,userName);
		MetodeSaucedo.Password(driver, password);
		MetodeSaucedo.LoginBTN(driver);
		MetodeSaucedo.Sortiranje(driver);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}MetodeSaucedo.Lista(driver);
		
	}	@Test (priority = 1)
	public void odlazakNaStranicuError() {
		driver.navigate().to(Home.URL);
		String userName = "zika";
		String password = "peric";
		MetodeSaucedo.userName(driver,userName);
		MetodeSaucedo.Password(driver, password);
		MetodeSaucedo.LoginBTN(driver);
		String actual = driver.getCurrentUrl();
		String expected = URL;
		
		Assert.assertEquals(actual, expected);
		
	}
	@Test (priority = 3)
	public void CorectLogin() {
		
	
	File f = new File("Data.xlsx"); 
	try {
		InputStream inp = new FileInputStream(f); 
		XSSFWorkbook wb = new XSSFWorkbook(inp); 
		Sheet sheet = wb.getSheetAt(0); 
	
		SoftAssert sa = new SoftAssert();
		
		for(int i = 0; i <3; i++) {
			Row row = sheet.getRow(i); 
			Cell c1 =row.getCell(0);
			Cell c2 =row.getCell(1);
			
			String userName = c1.toString();
			String pass = c2.toString();
		
			driver.navigate().to( Home.URL);
			MetodeSaucedo.userName(driver, userName);
			MetodeSaucedo.Password(driver, pass);
			MetodeSaucedo.LoginBTN(driver);
			
			String actual = driver.getCurrentUrl();
			String expected = URL;
			sa.assertEquals(actual, expected);
		}
		sa.assertAll();
		
			driver.quit();
			driver.close();
			// Nece da mi zatvori WB, ali ne znam zasto.
	}
	 catch (IOException e) {
		System.out.println("Nije pronadjen fajl!");
		e.printStackTrace();
	}
	}


}


