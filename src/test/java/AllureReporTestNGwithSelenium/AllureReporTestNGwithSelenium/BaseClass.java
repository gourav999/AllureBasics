package AllureReporTestNGwithSelenium.AllureReporTestNGwithSelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;

	public static ThreadLocal<WebDriver> gdriver = new ThreadLocal<WebDriver>();

	public WebDriver inilizeDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		gdriver.set(driver);
		return getDriver();

	}

	public static synchronized WebDriver getDriver() {
		return gdriver.get();
	}

}
