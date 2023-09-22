package Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public SelenideElement firstname = $("#firstName");
    public SelenideElement lastname = $("#lastName");
    public SelenideElement userNumber = $("#userNumber");

}
