package Steps;

import Pages.MainPage;
import io.qameta.allure.Step;

public class MainPageSteps {
    MainPage mainPage = new MainPage();





    @Step
    public MainPageSteps sendName(String firstname) {
        mainPage.firstname.sendKeys(firstname);
        return this;
    }

    @Step
    public MainPageSteps sendLastname(String lastname){
        mainPage.lastname.sendKeys(lastname);
        return this;
    }

    @Step
    public MainPageSteps sendNumber(String usernNumber){
        mainPage.userNumber.sendKeys(usernNumber);
        return this;
    }

}
