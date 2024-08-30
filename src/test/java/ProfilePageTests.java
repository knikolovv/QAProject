import org.junit.jupiter.api.Test;

import java.util.Random;

public class ProfilePageTests extends BaseTest {
    //TODO finish the test
    public static Random random = new Random();

    @Test
    public void changingProfileInfoTest() {
        // Step - Sign in profile
        ProfilePage profilePage = ProfilePage.open(Props.getEmail(),Props.getPassword());

        //Step - Change phone number
        ChangePhonePage changePhonePage = profilePage.clickChangePhoneButton();

        String randomPhoneNumber = "08" + random.nextInt(10000000, 99999999);
        changePhonePage.enterPhoneNumber(randomPhoneNumber);

        profilePage = changePhonePage.clickChangePhoneButton();
        profilePage.verifyPageIsOpen();
        profilePage.verifyPhoneNumberIsChanged(randomPhoneNumber);

        // Step - Change gender
        String changedGender = "Жена";
        ChangeGenderPage changeGenderPage = profilePage.clickChangeGenderButton();
        changeGenderPage.openChangeGenderDropdown();
        changeGenderPage.selectGenderByIndex(3);

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


        //TODO Step - Check tickets

    }

    // TODO ChangePasswordTEST

}
