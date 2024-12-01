package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;

	By signInModal = By.cssSelector(".chakra-modal__content.css-fs24wq");
	By email = By.id("email");
	By password = By.id("password");
	By signIn = By.cssSelector(".chakra-button.css-1bkxg6o");

	By deviceSelectionModal = By.cssSelector(".chakra-modal__content.css-vzkdbz");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public void enterEmailAddress(String userEmail) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(signInModal));
		WebElement modal = driver.findElement(signInModal);
		modal.findElement(email).sendKeys(userEmail);
	}

	public void enterPassword(String userPassword) {
		WebElement modal = driver.findElement(signInModal);
		modal.findElement(password).sendKeys(userPassword);
	}

	public void clickOnLogin() {
		WebElement modal = driver.findElement(signInModal);
		modal.findElement(signIn).click();
	}

	public void isUserLoggedIn() {
		WebElement deviceSelectionModalElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(deviceSelectionModal));
		Assert.assertNotNull(deviceSelectionModalElement, "User logged in");
	}

	public void verifyUserNotLoggedIn() {
		WebElement deviceSelectionModalElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(deviceSelectionModal));
		Assert.assertNotNull(deviceSelectionModalElement, "User logged in");
	}

}