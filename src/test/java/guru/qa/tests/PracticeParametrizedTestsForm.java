package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import guru.qa.pages.RegistrationPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;


public class PracticeParametrizedTestsForm {

    RegistrationPage registrationPage = new RegistrationPage();
    String lastName = " Alexeev";
    String email = "emain@email.com";
    String gender = "Male";
    String mobile = "8002000500";

    @BeforeEach
    void preCondition() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @ValueSource(strings = {"Alex", "Ivan"})
    @ParameterizedTest(name = "Проверка отображения введенных сведений \"{0}\"")
    void commonCheckTest(String testData) {
        registrationPage.openPage()
                .setFirstName(testData)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setMobile(mobile)
                .clickSubmit().
                checkModalForm()
                .checkForm("Student Name", testData + lastName)
                .checkForm("Student Email", email)
                .checkForm("Gender", gender)
                .checkForm("Mobile", mobile);
    }

    @CsvSource(value = {
            "Alex| Alexeev| emain@email.com| Male| 8002000500",
            "Ivan| Ivanov| ivanov@email.com| Other| 8802000500"
    },
            delimiter = '|'
    )
    @ParameterizedTest(name = "Проверка отображения введенных сведений \"{1}\"")
    void complexCheckTest(String firstname, String lastname, String email, String gender, String phone) {
        registrationPage.openPage()
                .setFirstName(firstname)
                .setLastName(lastname)
                .setEmail(email)
                .setGender(gender)
                .setMobile(phone)
                .clickSubmit()
                .checkModalForm()
                .checkForm("Student Name", firstname + " " + lastname)
                .checkForm("Student Email", email)
                .checkForm("Gender", gender)
                .checkForm("Mobile", phone);
    }

    static Stream<Arguments> mixedArgumentsTestsCheck() {
        return Stream.of(
                Arguments.of("Alex", "Alexeev", "emain@email.com", "Male", "8002000500"),
                Arguments.of("Ivan", "Ivanov", "ivanov@email.com", "Other", "8802000500")
        );
    }

    @MethodSource(value = "mixedArgumentsTestsCheck")
    @ParameterizedTest(name = "Surname {1}")
    void mixedArgumentsTests(String firstname, String lastname, String email, String gender, String phone) {
        registrationPage.openPage()
                .setFirstName(firstname)
                .setLastName(lastname)
                .setEmail(email)
                .setGender(gender)
                .setMobile(phone)
                .clickSubmit()
                .checkModalForm()
                .checkForm("Student Name", firstname + " " + lastname)
                .checkForm("Student Email", email)
                .checkForm("Gender", gender)
                .checkForm("Mobile", phone);
    }
}