package Bootstrapper;

import Quotes.QuotesBot;
import corona.CoronaBot;
import corona.CoronaDataType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.annotation.Nonnull;

public class Bootstrapper<onGuildJoinEvent> extends ListenerAdapter {

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
                channel.sendMessage("Pong!") /* => RestAction<Message> */
                        .queue(response /* => Message */ -> {
                            response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                        });

                break;
            case "corona":
                channel.sendMessage(new CoronaBot().getData(CoronaDataType.COUNTRYWIDE)).queue();
                break;
            case "quote":
                System.out.println(messageArray[2]);
                if(messageArray[2].equals("add")){
                    QuotesBot.addQuote(event);
                } else {
                    QuotesBot.getQuote(event);
                }
                break;
            default:
                EventNotFound.commandNotFoundHandler(event);
        }
    }

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent e) {
        String id = e.getUser().getId();
        String server = e.getGuild().getName();
        String rulesChannel = "<#617264349995859968>";
        String welcomeChannel = "<#619881226794303499>";
        String whoami = "<#619530585047695400>";
        String message = "Hey <@" + id + ">, welcome to **" + server + "** <:thomas1:647160141778518022> "
                + welcomeChannel + " -ს და " + rulesChannel + " -ს გაეცანი და " + whoami
                + " -ში გაგვეცანი <:boomer:645706743279517709>";
        e.getGuild().getTextChannelById("617042233719259181").sendMessage(message).queue();
    }


    @Override
    public void onGuildMemberRemove(@Nonnull GuildMemberRemoveEvent e) {
        String id = e.getUser().getId();
        String message = "<@" + id + ">-მ დაგვტოვა";
        e.getGuild().getTextChannelById("617042233719259181").sendMessage(message).queue();
    }
}
