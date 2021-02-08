package kontrloni;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class MetodeSaucedo {
	
	private static final String USER_NAME_XPATH = "//*[@id=\"user-name\"]";
	private static final String PASSWORD_XPATH = "//*[@id=\"password\"]";
	private static final String LOGIN_XPATH = "//*[@id=\"login-button\"]";
	private static final String SORTBTN_XPATH = "//*[@id=\"inventory_filter_container\"]/select";
	private static final String SCROLBTN_XPATH = "//*[@id=\"inventory_filter_container\"]/select/option[3]";
	
	
	public static void userName(WebDriver driver, String userName) {
	WebElement we1 = driver.findElement(By.xpath(USER_NAME_XPATH));
	we1.click();
	we1.sendKeys(userName);
	}
	public static void Password(WebDriver driver, String password) {
	WebElement we = driver.findElement(By.xpath(PASSWORD_XPATH));
	we.click();
	we.sendKeys(password);
	}
	public static void LoginBTN(WebDriver driver) {
	driver.findElement(By.xpath(LOGIN_XPATH)).click();
	}
	public static void Lista(WebDriver driver) {
		
	List<WebElement> lista = driver.findElements(By.className("inventori_item_price"));
	
	 List<String> novaLista= new ArrayList<>();
	 SoftAssert sa = new SoftAssert();
	 for (WebElement size1 : lista ) { 
		 String drugi = size1.getText().toString();
	            novaLista.add(size1.getText().toString());
	                String actual = size1.toString();
	    			String expected = drugi.toString();
	    			sa.assertEquals(actual, expected);
	    }
		 if (novaLista.equals(lista)) {
			 System.out.println("Ovo je dobro sortirana lista!");		 
	 }
	 sa.assertAll();
	}
	

	public static void Sortiranje (WebDriver driver) {
		driver.findElement(By.xpath(SORTBTN_XPATH)).click();
		driver.findElement(By.xpath(SCROLBTN_XPATH)).click();
	}
	
}
