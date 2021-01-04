package khanacademy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class Test_VerifyLogin {

	public AndroidDriver driver;

	@BeforeClass
	public void beforeClass() throws MalformedURLException, InterruptedException {
		System.out.println("Test case execution has started and configuring the app and appium setting");
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("deviceName", "Ishwar");
		capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capability.setCapability(MobileCapabilityType.NO_RESET, true);
		capability.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "org.khanacademy.android");
		capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
				"org.khanacademy.android.ui.library.MainActivity");
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capability);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
	}

	@Test(priority = 1)
	public void loginUsingGoogleAccount() throws InterruptedException {
		System.out.println("Test execution started for :- Login using google account");

		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Sign in\")")).click();
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Continue with Google\")")).click();
		Thread.sleep(10000);

		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"Add another account\")")).click();
		Thread.sleep(10000);
		// System.out.println("current context of the application is " +
		// driver.getContext());
		Thread.sleep(10000);
		// System.out.println("list of contexts available on web page is " +
		// driver.getContextHandles());
		System.out.println("current context of the application is " + driver.getContext());
		Thread.sleep(5000);
		driver.findElementByClassName("android.widget.EditText").sendKeys("Manzoor");
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"NEXT\")")).click();
		Thread.sleep(6000);
		driver.findElementByClassName("android.widget.EditText").sendKeys("Manzoor");
		Thread.sleep(5000);
		driver.findElement(MobileBy.AndroidUIAutomator("UiSelector().text(\"NEXT\")")).click();

	}

	@Test(priority = 2)
	public void verifyCorrectUserLogin() throws InterruptedException {
		System.out.println("Varifying the correct user has login in the account");
		String expectedResult = "Wrong password. Try again or click Forgot password to reset it.";
		String actualResult = driver
				.findElement(MobileBy.AndroidUIAutomator(
						"UiSelector().text(\"Wrong password. Try again or click Forgot password to reset it.\")"))
				.getText();
		try {
			Assert.assertEquals(actualResult, expectedResult);
			System.out.println(actualResult);

		} catch (Exception e) {
			System.out.println("User other credentials for login");
			//System.out.println("actualResult");
			// loginUsingGoogleAccount();

		}
	}

	@AfterClass
	public void afterClass() {

	}

}
