package Bootstrapper;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author postgresqltutorial.com
 */
public class DB {
    private String jdbc_url;

    {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();
        jdbc_url = dotenv.get("DB_JDBC_URL");

    }

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(jdbc_url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}