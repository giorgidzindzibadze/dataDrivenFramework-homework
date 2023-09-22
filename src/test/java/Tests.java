import Steps.MainPageSteps;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.open;

public class Tests extends ConfigTest {
    MainPageSteps mainPageSteps = new MainPageSteps();




        @Test(dataProvider = "data", dataProviderClass = DataProviderTest.class)
        public void loginTest(String firstName, String lastName, String mobileNumber){


            open("https://demoqa.com/automation-practice-form");

            mainPageSteps.sendName(firstName);
            mainPageSteps.sendLastname(lastName);
            mainPageSteps.sendNumber(mobileNumber);






    }
}



