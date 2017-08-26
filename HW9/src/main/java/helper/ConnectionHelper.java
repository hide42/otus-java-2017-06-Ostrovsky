package helper;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper implements Closeable{
    private static Connection connection;
    public static Connection getConnection() {
        try {
            if(connection!=null&&!connection.isClosed())return connection;

            connection = null;
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            String url = "jdbc:mysql://" +       //db type
                    "localhost:" +               //host name
                    "3306/" +                    //port
                    "test?" +              //db name
                    "useSSL=false&" +            //do not use ssl
                    "user=root&" +              //login
                    "password=root";            //password
            connection = DriverManager.getConnection(url);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
