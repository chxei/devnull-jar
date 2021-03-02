package bootstrapper;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.LoggerFactory;

public class DB {
    private final String jdbcUrl;

    public DB(){
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();
        jdbcUrl = dotenv.get("DB_JDBC_URL");
    }

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            LoggerFactory.getLogger(DB.class).info(e.getMessage());
        }

        return conn;
    }
}