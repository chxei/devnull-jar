import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;


//This is where things start
public class DevNull {
    public static void main(String[] args) throws Exception {
        JDABuilder jda = new JDABuilder();
        jda.setToken("NjUyMjI0NTk1NDAyODE3NTQ2.XodWdA.UgAieboc931cO5Zba7znzGjd4Do");

        jda.setActivity(Activity.watching("Luke Smith"));

        jda.addEventListeners(new Bootstrapper()).build();
    }
}
