import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class DataProviderTest {
    DbCustomer dbCustomer = new DbCustomer();



    @DataProvider(name = "data")
    public Object[][] getInformation() throws SQLException, FileNotFoundException {
        List<String> namelist = dbCustomer.getCustomerInfro().get(0) ;
        List<String> lastNameList = dbCustomer.getCustomerInfro().get(1);
        List<String> phoneList = dbCustomer.getCustomerInfro().get(2);




        Object [][] dates= new Object[][]{{namelist.get(0),lastNameList.get(0),phoneList.get(0)},
                {namelist.get(1),lastNameList.get(1), phoneList.get(1)},
                {namelist.get(2), lastNameList.get(2), phoneList.get(2)}


        };
        return dates;
    }
}
