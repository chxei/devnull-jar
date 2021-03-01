import bootstrapper.Bootstrapper;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EnumSet;

public class DevNull {
    public static void main(String[] args) throws Exception {

        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();
        EnumSet<GatewayIntent> intents = EnumSet.of(
                // We need messages in guilds to accept commands from users
                GatewayIntent.GUILD_MESSAGES,
                // We need voice states to connect to the voice channel
                GatewayIntent.GUILD_VOICE_STATES
        );

        JDABuilder.createLight(dotenv.get("TOKEN"), intents)
                    .setRawEventsEnabled(true)
                    .setActivity(Activity.listening("Cabaret Nocturne"))
                    .addEventListeners(new Bootstrapper())
                    .build();
    }
}
