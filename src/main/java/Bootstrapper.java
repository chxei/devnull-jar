import corona.Corona;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Bootstrapper extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        String rawContent = msg.getContentRaw();
        String prefix = rawContent.substring(0, 1);
        if (!prefix.equals("j")) {
            return;
        }
        String command = rawContent.substring(2, rawContent.length());
        MessageChannel channel = event.getChannel();
        long time = System.currentTimeMillis();

        if (command.equals("ping")) {
            channel.sendMessage("Pong!") /* => RestAction<Message> */
                    .queue(response /* => Message */ -> {
                        response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                    });

        } else if (command.equals("corona")) {
            channel.sendMessage(new Corona().getData()).queue();
        }
    }
}
