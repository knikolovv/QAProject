package tests;

import pages.*;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import utils.Props;

public class ProfilePageTests extends BaseTest {
    public static Random random = new Random();

    @Test
    @DisplayName("Changing profile information test")
    @Description("Testing changing personal information functionality")
    public void changingProfileInfoTest() {
        // Step - Sign in profile
        ProfilePage profilePage = ProfilePage.open(Props.getEmail(), Props.getPassword());

        //Step - Change phone number
        ChangePhonePage changePhonePage = profilePage.clickChangePhoneButton();

        String randomPhoneNumber = "08" + random.nextInt(10000000, 99999999);
        changePhonePage.enterPhoneNumber(randomPhoneNumber);

        profilePage = changePhonePage.clickChangePhoneButton();
        profilePage.verifyPageIsOpen();
        profilePage.verifyPhoneNumberIsChanged(randomPhoneNumber);

        // Step - Change gender
        String changedGender = profilePage.getCurrentGender().equals("ºÍ÷") ? "∂’›–" : "ºÍ÷";

        ChangeGenderPage changeGenderPage = profilePage.clickChangeGenderButton();
        changeGenderPage.openChangeGenderDropdown();

        //changeGenderPage.selectGenderByIndex(changedGender.equals("ºÍ÷") ? 2 : 3);
        if (changedGender.equals("ºÍ÷")) {
            changeGenderPage.selectGenderByIndex(2);
        } else {
            changeGenderPage.selectGenderByIndex(3);
        }

        profilePage = changeGenderPage.clickChangeGenderButton();
        profilePage.verifyPageIsOpen();
        profilePage.verifyGenderIsChanged(changedGender);

        // Step - Change birthdate
        String changedBirthDate = "1.01.1990";
        ChangeBirthDatePage changeBirthDatePage = profilePage.clickChangeBirthDateButton();
        changeBirthDatePage.enterBirthDateField(changedBirthDate);
        changeBirthDatePage.closeDatePicker();
        profilePage = changeBirthDatePage.clickChangeBirthDateButton();

        profilePage.verifyPageIsOpen();
        profilePage.verifyBirthDateIsChanged(changedBirthDate);


    }


    @Test
    @DisplayName("Check ticket history")
    @Description("Checking ticket history in the profile section and verifying correct functionality in it's elements")
    public void checkTicketHistoryTest() {
        ProfilePage profilePage = ProfilePage.open(Props.getEmail(), Props.getPassword());

        ProfileTicketsPage profileTicketsPage = profilePage.clickTicketsButton();
        profileTicketsPage.verifyPageIsOpen();
        profileTicketsPage.clickTicketByIndex(1);
        profileTicketsPage.clickTicketByIndex(2);
        profileTicketsPage.clickRefreshButton();

    }

    @Test
    @DisplayName("Change Password")
    @Description("Checking fields responsibility and their error messages")
    public void changePasswordTest() {
        ProfilePage profilePage = ProfilePage.open(utils.Props.getEmail(), utils.Props.getPassword());

        ChangePasswordPage changePasswordPage = profilePage.clickChangePasswordProfileButton();

        profilePage = changePasswordPage.clickCancelButton();

        changePasswordPage = profilePage.clickChangePasswordProfileButton();

        changePasswordPage.clickChangePasswordButtonWithWronglyPopulatedFields();
        changePasswordPage.verifyBaseErrorMessagesAppear();

        changePasswordPage.enterWrongCurrentPassword();
        changePasswordPage.clickChangePasswordButtonWithWronglyPopulatedFields();
        // verifies newPasswordFieldMessage
        changePasswordPage.verifyFieldIsMandatoryErrorMessage();

        changePasswordPage.enterSameNewAndOldPassword();
        changePasswordPage.clickChangePasswordButtonWithWronglyPopulatedFields();
        changePasswordPage.verifyPasswordsMustBeDifferentErrorMessage();

        changePasswordPage.enterDifferentNewAndRepeatedPassword();
        changePasswordPage.verifyPasswordMustMatchErrorMessage();

        changePasswordPage.enterWrongCurrentPassword();
        changePasswordPage.enterSameNewAndRepeatPassword();
        changePasswordPage.clickChangePasswordButtonWithWronglyPopulatedFields();
        changePasswordPage.verifyWrongCurrentPasswordErrorMessage();

        String newPassword = "N3wPass!";
        String currentPassword = Props.getPassword();

        changePasswordPage.enterAllValidPassword(currentPassword,newPassword);

        // Returns login page object
        LoginPage loginPage = changePasswordPage.clickChangePasswordButtonWithAllPasswordsCorrect();

        // Return old password to be the current one
        profilePage = loginPage.loginToProfilePage(Props.getEmail(),newPassword);

        changePasswordPage = profilePage.clickChangePasswordProfileButton();
        changePasswordPage.enterAllValidPassword(newPassword,currentPassword);
        changePasswordPage.clickChangePasswordButtonWithAllPasswordsCorrect();
    }

}
