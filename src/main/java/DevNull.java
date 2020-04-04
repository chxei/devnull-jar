import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;


//This is where things start
public class DevNull {
    public static void main(String[] args) throws Exception {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();
        JDABuilder jda = new JDABuilder();
        System.out.println(dotenv.get("TOKEN"));
        System.out.println((String)dotenv.get("TOKEN"));
        jda.setToken(dotenv.get("TOKEN"));
        jda.setActivity(Activity.watching("Luke Smith"));

        jda.addEventListeners(new Bootstrapper()).build();
    }
}
