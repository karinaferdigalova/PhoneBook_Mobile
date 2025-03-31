package tests;

import config.AppiumConfig;
import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class DeleteContactsTests extends AppiumConfig {

    @BeforeClass
    public void preCondition() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(User.builder().email("karinmc9@mail.ru")
                        .password("Rfhbyrf29$").build())
                .submitLogin();
    }
    @Test
    public void deleteFirstContact(){
        new ContactListScreen(driver)
                .deleteFirstContact()
        .isListSizeLessOnOne();

    }
    @Test
    public void removeAllContactsSuccess(){
        new ContactListScreen(driver) .removeAllContacts()
                .isNoContactsHere();

    }
}
