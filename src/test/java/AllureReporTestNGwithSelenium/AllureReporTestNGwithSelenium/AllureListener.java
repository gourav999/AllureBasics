package AllureReporTestNGwithSelenium.AllureReporTestNGwithSelenium;

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

	
	
	
	//Get the test Method name.
	//we have to overwrite the test methods for getting the name here in itestlistener.
	private static String getTestMethodName(ITestResult itestResult) {
		return itestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	//attachment is an allure annotations
	@Attachment
	public byte[] saveFailureScreenshot(WebDriver driver)
	{
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		
	}
	
	//along with the screenshot write log message
	@Attachment(value= "{0}", type="text/plain")
	public static String saveTextLog(String message)
	{
		return message;
	}
	


	@Override
	public void onStart(ITestContext itestcontext)
	{
		itestcontext.setAttribute("WebDriver", BaseClass.getDriver());
	}
	
	@Override
	public void onFinish(ITestContext itestcontext)
	{
		itestcontext.getName();
	}
	
	@Override
	public void onTestStart(ITestResult itestresult)
	{
		getTestMethodName(itestresult);
	}
	
	
	public void onTestSucess(ITestResult itestresult)
	{
		System.out.println("I am in on Test Failure Method "+getTestMethodName(itestresult)+"Sucess");
		getTestMethodName(itestresult);
	}
	
	
	public void onTestFailure(ITestResult itestresult)
	{
		System.out.println("I am in on Test Failure Method "+getTestMethodName(itestresult)+"Failed");
		Object Testclass=itestresult.getInstance();
		WebDriver DX=BaseClass.getDriver();
		if(DX instanceof WebDriver)
		{
			System.out.println("Screenshot captured for  Test cases "+getTestMethodName(itestresult));
			saveFailureScreenshot(DX);
			
		}
		saveTextLog(getTestMethodName(itestresult)+"failed and screenshot taken!!");
		
	}
	
	public void onTestSkipped(ITestResult itestresult)
	{
		System.out.println("I am in on Test Skipped Method "+getTestMethodName(itestresult)+"Skipped");
		getTestMethodName(itestresult);
	}
}
