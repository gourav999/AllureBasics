package AllureCustomization;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

//Itestlistner is an interface in testng.
//We will listen the fail test cases
public class AllureListener implements ITestListener {

	// Get the test Method name.
	// we have to overwrite the test methods for getting the name here in
	// itestlistener.
	private static String getTestMethodName(ITestResult itestResult) {
		return itestResult.getMethod().getConstructorOrMethod().getName();
	}

	// attachment is an allure annotations
	@Attachment
	public byte[] saveFailureScreenshot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

	}

	// along with the screenshot write log message
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	// ==========ITestListener interface Methods=================//
	// Onstart--OnStart method is called when any Test starts.Note on start we use
	// ITestCotext
	@Override
	public void onStart(ITestContext itestcontext) {
		System.out.println("I am in onStart Method " + itestcontext.getName());
		itestcontext.setAttribute("WebDriver", BaseClass.getDriver());
	}

	// -onFinish method is called after all Tests are executed.
	@Override
	public void onFinish(ITestContext itestcontext) {
		System.out.println("I am in onFinish Method " + itestcontext.getName());

	}

	// Note on start we use ITestResult
	@Override
	public void onTestStart(ITestResult itestresult) {
		System.out.println("I am in onTestStart Method " + getTestMethodName(itestresult));

	}

	// onTestSuccess method is called on the success of any Test.
	public void onTestSucess(ITestResult itestresult) {
		System.out.println("I am in on Test Sucess Method " + getTestMethodName(itestresult) + "Sucess");

	}

	/*
	 * onTestFailure method is called on the failure of any Test. Itestresult
	 * contains all the result information. Name, driver pass fail etc all.
	 */ public void onTestFailure(ITestResult itestresult) {
		System.out.println("I am in onTest Failure Method " + getTestMethodName(itestresult) + "Failed");
		// Here one object is creaetd and saving the instance. Object is a predefine
		// class in java.
		Object Testclass = itestresult.getInstance();
		WebDriver bd = BaseClass.getDriver();
		if (bd instanceof WebDriver) {
			System.out.println("Screenshot captured for  Test cases " + getTestMethodName(itestresult));
			saveFailureScreenshot(bd);

		}
		saveTextLog(getTestMethodName(itestresult) + "Test case is failed and screenshot taken!!");

	}

	// onTestSkipped method is called on skipped of any Test.
	public void onTestSkipped(ITestResult itestresult) {
		System.out.println("I am in onTest Skipped Method " + getTestMethodName(itestresult) + "Skipped");
		getTestMethodName(itestresult);
	}
}
