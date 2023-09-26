import Steps.CommonSteps;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class QueryingTests {


    CommonSteps commonSteps;

    public QueryingTests() throws SQLException {
        commonSteps = new CommonSteps();

    }





    @Test
    public void isa() {
        commonSteps.insertAndValidation();
        commonSteps.validation();
        commonSteps.update();
        commonSteps.getCustomerFirstName();
        commonSteps.closeConnection();
    }

}
