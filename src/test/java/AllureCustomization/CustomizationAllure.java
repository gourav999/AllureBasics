package AllureCustomization;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Listeners(AllureListener.class)
public class CustomizationAllure extends BaseClass {
	// WebDriver driver;
	// public WebDriver driver;
	public WebDriver bs;

	@BeforeClass
	public void setUp() throws InterruptedException {
		// Commenting this code becasue we have implemented this on base class
		// WebDriverManager.chromedriver().setup();
		// We can also do-->WebDriverManager.firefoxdriver().setup();
		// We can also do-->WebDriverManager.iedriver().setup();
		// commenting as base class we have created. driver = new ChromeDriver();

		BaseClass bs = new BaseClass();

		driver = bs.inilizeDriver();// Here we are creating one object bs which is taking the return values of
									// inilizedriver method.
		driver.get("http://jaingourav999.blogspot.com/");
		driver.manage().window().maximize();
		// ((WebDriver) b).get("http://jaingourav999.blogspot.com/");
		// ((WebDriver) b).manage().window().maximize();

	}

	@Test(priority = 1, description = "Validaton of Title") // Note: in TestNG we can provide the description
	@Description("Verifiy title is present and displayed correctly") // Note: Below description is provided by Allure
	@Epic("BlogTest")
	@Feature("Feature1: Blog verify")
	@Story("User story is TC-logo-001")
	@Step("Step is to Verifiy title is present and displayed correctly")
	@Severity(SeverityLevel.CRITICAL)
	public void validateTitle() {
		// Title verification
		String titleofWebpage = driver.getTitle();
		System.out.println("This is the tile of webpae-->" + titleofWebpage);
		assertEquals("Software Testing", titleofWebpage);

	}

	/*
	 * in TestNG we can provide the descriptionNote: Below description is provided
	 * by Allure
	 */
	@Description("Verifiy blog page is displayed")
	@Epic("BlogTest")
	@Feature("Feature1: Blog verify")
	@Story("User story is TC-Blog-002")
	@Step("Step is to Verifiy blog page is displayed")
	@Severity(SeverityLevel.NORMAL)
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

	// This will skip the test.
	@Description("Verify skipping this test case")
	@Epic("BlogTest")
	@Feature("Feature3: Skipping feature")
	@Test(priority = 3)
	public void validateSkippingTest() {
		throw new SkipException("Skipping the test");
	}

	// This will fail.
	@Description("Verifiy Homelink page is displayed correctly")
	@Epic("BlogTest")
	@Feature("Feature1: Blog verify")
	@Story("User story is TC-Blog-004")
	@Step("Step is to Homelink page is displayed correctly")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4)
	@Epic("BlogTest")
	public void verifyHomeLink() {
		String homeLink = driver.findElement(By.xpath("//h3[contains(text(),'Create a maven project in eclipse')]"))
				.getText();
		assertEquals("HOOMEEPage", homeLink);
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();

	}
}
