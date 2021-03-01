package bootstrapper;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.LoggerFactory;
/**
 * @author postgresqltutorial.com
 */
public class DB {
    org.slf4j.Logger logger = LoggerFactory.getLogger(DB.class);
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
            logger.info(e.getMessage());
        }

        return conn;
    }
}