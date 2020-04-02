import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;

public class DevNull {
    public static void main(String[] args) throws Exception {
        //init jdabuilder
        JDABuilder jda = new JDABuilder();
        jda.setToken("NjUyMjI0NTk1NDAyODE3NTQ2.XoZJVg.Mgh02OZGs4CkAGgnnULYJcP9_40");

        jda.setActivity(Activity.watching("Luke Smith"));

        jda.addEventListeners(new Bootstrapper()).build();
    }
}
