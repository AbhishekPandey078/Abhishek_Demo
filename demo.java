package PPS_d.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice.Return;

public class demo {
	public WebDriver driver;

	@Test(dataProvider = "getdata")
	public void verifyLogin(String username, String password) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//input[@id='user-name']")).click();
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='password']")).click();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='login-button']")).click();

		boolean appLogoDisplay = driver.findElement(By.xpath("//div[@class='app_logo']")).isDisplayed();
		Assert.assertEquals(appLogoDisplay, true);
		driver.quit();
	}

	@Test()
	public void verifyWithInvalidCredential() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//input[@id='user-name']")).click();
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
		driver.findElement(By.xpath("//input[@id='password']")).click();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Abishek@123");
		driver.findElement(By.xpath("//input[@id='login-button']")).click();

		boolean errorMessegeDislay = driver.findElement(By.xpath("//h3[@data-test='error']")).isDisplayed();
		Assert.assertEquals(errorMessegeDislay, true);

		driver.quit();
	}

	@Test(dataProvider = "getdata")
	public void verifyAddToCartFunctionality(String username, String password) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//input[@id='user-name']")).click();
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='password']")).click();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='login-button']")).click();

		boolean AddToCartClickeble = driver.findElement(By.xpath("//button[@name='add-to-cart-sauce-labs-backpack']"))
				.isEnabled();
		Assert.assertEquals(AddToCartClickeble, true);

		driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']")).click();

		driver.findElement(By.xpath("//button[@id='remove-sauce-labs-bike-light']")).isDisplayed();

		driver.quit();
	}

	@Test(dataProvider = "getdata")
	public void verifyRemoveButton(String username, String password) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//input[@id='user-name']")).click();
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='password']")).click();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
		driver.findElement(By.xpath("//button[@name='add-to-cart-sauce-labs-backpack']")).click();
		boolean RemoveEnable=driver.findElement(By.xpath("//button[@id='remove-sauce-labs-backpack']")).isEnabled();
		Assert.assertEquals(RemoveEnable, true);	
	
		driver.findElement(By.xpath("//button[@id='remove-sauce-labs-backpack']")).click();
		driver.quit();
	}

	@DataProvider
	public Object[][] getdata() {
		Object[][] data = { { "problem_user", "secret_sauce" }, };
		return data;

	}

}
