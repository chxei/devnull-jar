package bootstrapper;

import corona.CoronaBot;
import corona.CoronaDataType;
import minibots.Remindme;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import quotes.QuotesBot;
import javax.annotation.Nonnull;

public class Bootstrapper extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        String messageText = msg.getContentRaw();
        if (messageText.length() == 0 || event.getAuthor().isBot()) {
            return;
        }
        String prefix = messageText.substring(0, 2);
        if (!prefix.equals("j ")) {
            return;
        }
        String[] messageArray = messageText.split(" ");
        String command = messageArray[1];

        MessageChannel channel = event.getChannel();
        long time = System.currentTimeMillis();

        switch (command) {
            case "ping":
                channel.sendMessage("Pong!").queue(response -> response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue() );

                break;
            case "corona":
                channel.sendMessage(CoronaBot.getData(CoronaDataType.COUNTRYWIDE)).queue();
                break;
            case "quote":
                if(messageArray.length==2){
                    QuotesBot.getQuote(event);
                } else if(messageArray[2].equals("add")){
                    QuotesBot.addQuote(event);
                } else if(messageArray[2].equals("search")){
                    QuotesBot.searchQuote(event);
                }
                break;
            case "remindme":
                new Remindme(event);
                break;
            default:
                EventNotFound.commandNotFoundHandler(event);
        }
    }

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent e) {
        String id = e.getUser().getId();
        String server = e.getGuild().getName();
        String message = "Hey <@" + id + ">, welcome to **" + server + "** "+Configurations.NICE_EMOJI_EMBED+" <#"
                + Configurations.WELCOME_CHANNEL_CODE + "> -ს და <#" + Configurations.RULES_CHANNEL_CODE + "> -ს გაეცანი და <#" +Configurations.WHOAMI_CHANNEL_CODE
                + "> -ში გაგვეცანი "+Configurations.BOOMER_EMOJI_EMBED;
        e.getGuild().getTextChannelById(Configurations.MAIN_CHANNEL_CODE).sendMessage(message).queue();
    }


    @Override
    public void onGuildMemberRemove(@Nonnull GuildMemberRemoveEvent e) {
        String id = e.getUser().getId();
        String message = "<@" + id + ">-მ დაგვტოვა";
        e.getGuild().getTextChannelById(Configurations.MAIN_CHANNEL_CODE).sendMessage(message).queue();
    }
}
