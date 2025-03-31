package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AuthenticationScreen extends BaseScreen {
    public AuthenticationScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }


    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    AndroidElement emailEditText;

    @FindBy(id = "com.sheygam.contactapp:id/inputPassword")
    AndroidElement passwordEditText;

    //@FindBy(xpath = "//*[text()='LOGIN']")
    @FindBy(xpath = "//*[@text='LOGIN']")
    AndroidElement loginButton;

    @FindBy(id = "com.sheygam.contactapp:id/regBtn")
    AndroidElement registrationBtn;


    public AuthenticationScreen fillEmail(String email) {
        //pause(4000)
        should(emailEditText, 10);
        type(emailEditText, email);
        return this;
    }


    public AuthenticationScreen fillPassword(String password) {
        type(passwordEditText, password);
        return this;
    }

    public ContactListScreen submitLogin() {
        loginButton.click();
        return new ContactListScreen(driver);
    }

    public AuthenticationScreen submitLoginNegative() {
        loginButton.click();
        return this;
    }

    public AuthenticationScreen isErrorMessageHasText(String text) {
        checkAlertText(text);
        return this;
    }


    public AuthenticationScreen fillLoginRegistrationForm(User user) {
        should(emailEditText, 10);
        type(emailEditText, user.getEmail());
        type(passwordEditText, user.getPassword());
        return this;
    }

    public ContactListScreen submitRegistration() {
        registrationBtn.click();
        return new ContactListScreen(driver);
    }

    public AuthenticationScreen submitRegistrationNegative() {
        registrationBtn.click();
        return this;
    }


}