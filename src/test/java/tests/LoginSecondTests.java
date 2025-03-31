package tests;

import config.AppiumConfig;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;

public class LoginSecondTests extends AppiumConfig {
    @Test
    public void loginSuccess(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm
                        (User.builder().email("karinmc9@mail.ru").password("Rfhbyrf29$")
                                .build())
                .submitLogin()
                .isAccountOpened()
                .logout();

    }
    @Test
    public void loginSuccessModel(){
//    boolean result = new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
        boolean result = new AuthenticationScreen(driver)
                .fillLoginRegistrationForm
                        (User.builder().email("karinmc@mail.ru")
                                .password("Rfhbyrf29$").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);

    }

    @Test
    public void loginWrongEmail(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(User.builder()
                        .email("karinmcmail.ru").password("Rfhbyrf29$")
                        .build())
                .submitLoginNegative()
                .isErrorMessageHasText("Login or Password incorrect");

    }


}
