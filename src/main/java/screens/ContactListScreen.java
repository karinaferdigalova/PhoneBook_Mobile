package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ContactListScreen extends BaseScreen {
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }


    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityTextView;

    @FindBy(xpath = "//*[@content-desc='More options']")
    AndroidElement menuOptions;
    @FindBy(xpath = "//*[@content-desc='More options']")
    List<AndroidElement> menuOptionsList;

    @FindBy(xpath = "//*[@text='Logout']")
    AndroidElement logoutBtn;

    @FindBy(xpath = "//*[@content-desc = 'add']")
    AndroidElement plusBtn;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<AndroidElement> contactNameList;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<AndroidElement> contacList;

    @FindBy(id = "android:id/button1")
    AndroidElement yesBtn;


    @FindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    AndroidElement noContactsHereTextView;

    int countBefore;
    int countAfter;


    public boolean isActivityTitleDisplayed(String text) {
        // return activityTextView.getText().contains("Contact list");
        return isShouldHave(activityTextView, text, 10);
    }

    public AuthenticationScreen logout() {
        if (activityTextView.getText().equals("Contact list")) {
            menuOptions.click();
            logoutBtn.click();
        }
        return new AuthenticationScreen(driver);
    }

    public AuthenticationScreen logout2() {
        if (isElementPresentInList(menuOptionsList)) {
            menuOptions.click();
            logoutBtn.click();
        }
        return new AuthenticationScreen(driver);
    }

    public AuthenticationScreen logout3() {
        if (isElementDisplayed(menuOptions)) {
            menuOptions.click();
            logoutBtn.click();
        }
        return new AuthenticationScreen(driver);
    }


    public ContactListScreen isAccountOpened() {
        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));
        return this;
    }


    public AddNewContactScreen openContactForm() {
        //if(activityTextView.getText().equals("Contact list"))
        plusBtn.click();
        return new AddNewContactScreen(driver);
    }


    public ContactListScreen isContactAddedByName(String name, String lastName) {
        // List<AndroidElement> list = driver.findElements(By.xpath(""));
        isShouldHave(activityTextView, "Contact list", 10);
        System.out.println("size of list " + contactNameList.size());
        boolean isPresent = false;

        for (AndroidElement el : contactNameList) {
            if (el.getText().equals(name + " " + lastName)) {
                isPresent = true;
                break;
            }
        }
        Assert.assertTrue(isPresent);
        return this;
    }

    public ContactListScreen deleteFirstContact() {
        // isActivityTitleDisplayed("Contact list");
        countBefore = contacList.size();
        System.out.println(countBefore);
        AndroidElement first = contacList.get(0);
        Rectangle rectangle = first.getRect();
        int xFrom = rectangle.getX() + rectangle.getWidth() / 8;
        int y = rectangle.getY() + rectangle.getHeight() / 2;
        int xTo = rectangle.getX() + (rectangle.getWidth() / 8) * 7;
        // int xTo = rectangle.getWidth()-xFrom;
        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xFrom, y))
                .moveTo(PointOption.point(xTo, y))
                .release().perform();
        should(yesBtn, 8);
        yesBtn.click();
        pause(2000);
        countAfter = contacList.size();
        System.out.println(countAfter);


        return this;
    }

    public ContactListScreen isListSizeLessOnOne() {
        Assert.assertEquals(countBefore - countAfter, 1);
        return this;
    }


    public ContactListScreen removeAllContacts() {
        pause(1000);
        while (contacList.size() > 0) {
            deleteFirstContact();
        }
        return this;
    }

    public ContactListScreen isNoContactsHere() {
        isShouldHave(noContactsHereTextView, "No Contacts. Add One more!", 1000);
        return this;
    }
}

//    public ContactListScreen deleteFirstContact(){
//        countBefore= contactList.size();
//        System.out.println(countBefore);
//       AndroidElement first = contactList.get(0);
//        Rectangle rectangle = first.getRect();// проверка высоты ширины и координаты верхик точек
//        int xFrom = rectangle.getX()+ rectangle.getWidth()/8;
//        int y= rectangle.getY()+ rectangle.getHeight()/2;
////        int xTo = rectangle.getWidth()- xFrom;
//        int xTo = rectangle.getX()+ (rectangle.getWidth()/8)*7;
//
//        TouchAction<?>touchAction = new TouchAction<>(driver);
//        touchAction.longPress(PointOption.point(xFrom, y ))//делаем долгое нажатие на начальной точке.
//                .moveTo(PointOption.point(xTo, y))//двигаем палец вправо, создавая свайп.
//                .release().perform();//отпускаем палец, завершая действие
//
//        should(yesBtn, 10);
//        yesBtn.click();
//        pause(5000);
//
//        countAfter = contactList.size();
//        System.out.println(countAfter);
//        return this;
//    }
