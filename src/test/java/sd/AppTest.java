package sd;

import org.junit.Test;
import sd.dao.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppTest {

    @Test
    public void shouldNotThrowError() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM client");
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            System.out.println(name);
        }
    }
}
