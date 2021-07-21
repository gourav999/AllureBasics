package AllureCustomization;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;

	/*
	 * This class provides thread-local variables. These variables differ from
	 * their normal counterparts in that each thread that accesses one (via its get
	 * or set method) has its own, independently initialized copy of the variable.
	 * ThreadLocal instances are typically private static fields in classes that
	 * wish to associate state with a thread (e.g., a user ID or Transaction ID).
	 */
	public static ThreadLocal<WebDriver> gdriver = new ThreadLocal<WebDriver>();

	public WebDriver inilizeDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		gdriver.set(driver);
		return getDriver();

	}

	public static synchronized WebDriver getDriver() {
		System.out.println("This is my driver-->"+gdriver.get());
		return gdriver.get();
	}

}
