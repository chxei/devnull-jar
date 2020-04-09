import Bootstrapper.Bootstrapper;
import Bootstrapper.DB;
import corona.CoronaBot;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import java.sql.Connection;


//This is where things start
public class DevNull {
    public static void main(String[] args) throws Exception {
        DB db = new DB();
        Connection conn = db.connect();
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();
        JDABuilder jda = new JDABuilder();
        jda.setToken(dotenv.get("TOKEN"));
        jda.setActivity(Activity.watching("Luke Smith"));

        jda.addEventListeners(new Bootstrapper()).build();
    }
}
