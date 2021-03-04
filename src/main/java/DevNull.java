import bootstrapper.Bootstrapper;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EnumSet;
import java.util.TimeZone;

public class DevNull {
    public static void main(String[] args) throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone("Tbilisi"));

        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();
        EnumSet<GatewayIntent> intents = EnumSet.of(
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.GUILD_BANS
        );

        JDABuilder.createLight(dotenv.get("TOKEN"), intents)
                    .setRawEventsEnabled(true)
                    .setActivity(Activity.listening("Cabaret Nocturne"))
                    .addEventListeners(new Bootstrapper())
                    .build();
    }
}
//junit