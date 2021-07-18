package AllureReporTestNGwithSelenium.AllureReporTestNGwithSelenium;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		// WebDriverManager.firefoxdriver().setup();
		// WebDriverManager.iedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://jaingourav999.blogspot.com/");
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void validateTitle() {
		// Title verification
		String titleofWebpage = driver.getTitle();
		System.out.println("This is the tile of webpae-->" + titleofWebpage);
		assertEquals("Software Testing", titleofWebpage);

	}

	@Test(priority = 2)
	public void validateBlogIsOpening() {
		// Click on blog link from the right panel
		WebElement blogLink = driver.findElement(By.xpath("//a[contains(text(),'Create a maven project in eclipse')]"));
		blogLink.click();

		// Verifying that blog is opened
		String blogHeading = driver.findElement(By.xpath("//h3[contains(text(),'Create a maven project in eclipse')]"))
				.getText();
		assertEquals("Create a maven project in eclipse", blogHeading);
	}

	//This will skip the test.
	@Test(priority = 3)
	public void validateSkippingTest() {
		throw new SkipException("Skipping the test");
	}

	// This will fail.
	@Test(priority = 4)
	public void verifyHomeLink() {
		driver.findElement(By.xpath("x")).click();

	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();

	}
}
