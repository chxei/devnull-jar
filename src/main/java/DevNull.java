import Bootstrapper.Bootstrapper;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;


//This is where things start
public class DevNull {
    public static void main(String[] args) throws Exception {

        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();
        JDABuilder jda = new JDABuilder(AccountType.BOT);
        jda.setToken(dotenv.get("TOKEN"));
        jda.setActivity(Activity.listening("Cabaret Nocturne"));

        jda.addEventListeners(new Bootstrapper()).build();
    }
}
