import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    public static Connection getConnection() throws SQLException {
        Connection con;
        try (FileInputStream f = new FileInputStream("C:\\Users\\giorg\\Desktop\\dataDriverFramework-homework\\src\\main\\resources\\db.properties"))
        {
            Properties prop = new Properties();
            prop.load(f);
            String url =prop.getProperty("url");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            con= DriverManager.getConnection(url,username,password);

        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return con;
    }
}
