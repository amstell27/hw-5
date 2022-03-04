package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    //locators
    public static SelenideElement
            headerTitle = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            genderInput = $("#genterWrapper"),
            emailInput = $("#userEmail"),
            mobileInput = $("#userNumber"),
            resultsTable = $(".table-responsive"),
            submit = $("#submit"),
            modalTitle = $(".modal-header");


    //actions
    public RegistrationPage openPage() {

        open("/automation-practice-form");
        headerTitle.shouldHave(text("Student Registration Form"));
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {

        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {

        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage setEmail(String email) {

        emailInput.setValue(email);
        return this;
    }

    public RegistrationPage setGender(String gender) {
        genderInput.$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setMobile(String mobile) {

        mobileInput.setValue(mobile);
        return this;

    }

    public RegistrationPage clickSubmit() {

        submit.click();
        return this;
    }

    public RegistrationPage checkModalForm() {

        modalTitle.shouldBe(text("Thanks for submitting the form"));
        return this;
    }

    public RegistrationPage checkForm(String fieldName, String value) {
        resultsTable.$(byText(fieldName))
                .parent().shouldHave(text(value));

        return this;
    }
}
