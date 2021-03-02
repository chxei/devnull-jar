package bootstrapper;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;
import javax.sql.DataSource;

public class DB {
    private static final String JDBC_URL = Dotenv.configure()
                                                .ignoreIfMissing()
                                                .ignoreIfMalformed()
                                                .load()
                                                .get("DB_JDBC_URL");
    public static final DataSource datasource = initiate();
    private DB(){}

    public static DataSource initiate(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(JDBC_URL);
        return  dataSource;
    }
}