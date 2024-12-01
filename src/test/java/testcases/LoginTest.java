package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Homepage;
import pages.LoginPage;

public class LoginTest {

	WebDriver driver;

	Homepage homePage;
	LoginPage loginPage;

	String email = "biradarrekha@gmail.com";
	String password = "Test1234";
	String incorrectEmail = "incorrect@email.com";
	String incorrectPassword = "Wrongpwd1";

	String incorrectEmailError = "incorrect@email.com";
	String AuthErrorMessage = "Incorrect email or password.";

	@BeforeSuite
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		homePage = new Homepage(driver);
		loginPage = new LoginPage(driver);
	}

	@Test(priority = 1)
	public void testLoginFailureOnWrongEmail() {
		homePage.openHomepage();
		homePage.acceptCookie();
		homePage.clickOnSignIn();

		loginPage.enterEmailAddress(incorrectEmail);
		loginPage.enterPassword(password);
		loginPage.clickOnLogin();
		homePage.isErrorDisplayed(AuthErrorMessage);
	}

	@Test(priority = 2)
	public void testLoginFailureOnWrongPassword() {
		homePage.openHomepage();
		homePage.clickOnSignIn();

		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(incorrectPassword);
		loginPage.clickOnLogin();
		homePage.isErrorDisplayed(AuthErrorMessage);
	}

	@Test(priority = 3)
	public void testSuccessfullLogin() {
		homePage.openHomepage();
		homePage.clickOnSignIn();

		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		loginPage.clickOnLogin();
		loginPage.isUserLoggedIn();
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}
