import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbCustomer {
    public  List<List<String>> getCustomerInfro() throws SQLException, FileNotFoundException {
//        Map<String,String> result = new HashMap<>();
        List<List<String>> allList =new ArrayList<>();
        List<String> namelist = new ArrayList<>();
        List<String> lastNameLIst = new ArrayList<>();
        List<String> phoneList = new ArrayList<>();
        String name = "";
        String lastname ="";
        String phone = "";
        Connection connection = DbConnection.getConnection();
        try(Statement stm = connection.createStatement()){
            String query = "select * from students;";
            ResultSet resultSet = stm.executeQuery(query);
            while (resultSet.next()){
                name=resultSet.getString("firstName");
                lastname=resultSet.getString("lastName");
                phone = resultSet.getString("phone");
                namelist.add(name);
                lastNameLIst.add(lastname);
                phoneList.add(phone);
            }


            allList.add(namelist);
            allList.add(lastNameLIst);
            allList.add(phoneList);

        }
        return allList;





    }
}
