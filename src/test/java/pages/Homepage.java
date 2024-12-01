package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Homepage {
	WebDriver driver;
	WebDriverWait wait;

	String url = "https://freecash.com/en";

	By signInButton = By.xpath("//*[@id='freecash-loggedout-auth-container']/button[1]");
	By acceptCookieButton = By.cssSelector(".ch2-btn");

	By errorMessage = By.id("toast-toast-title");

	public Homepage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public void openHomepage() {
		driver.manage().window().maximize();
		driver.get(url);
	}

	public void acceptCookie() {
		wait.until(ExpectedConditions.elementToBeClickable(acceptCookieButton)).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(acceptCookieButton));
	}

	public void clickOnSignIn() {
		wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
	}

	public void isErrorDisplayed(String error) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
		String actualText = driver.findElement(errorMessage).getText();
		Assert.assertEquals(actualText, error);
	}

}
