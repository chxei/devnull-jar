package Bootstrapper;

import corona.Corona;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public class Bootstrapper<onGuildJoinEvent> extends ListenerAdapter {


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        String rawContent = msg.getContentRaw();
        String prefix = rawContent.substring(0, 1);
        if (!prefix.equals("j") || event.getAuthor().isBot()) {
            return;
        }
        String command = rawContent.substring(2, rawContent.length());
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
                channel.sendMessage(new Corona().getData()).queue();
                break;
            case "what can you do?":
                event.getChannel().sendMessage("I'm useless").queue();
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
                + welcomeChannel + " -ს და " + rulesChannel + " -ს გაეცანი და " + whoami + " -ში გაგვეცანი <:boomer:645706743279517709>";
        e.getGuild().getTextChannelById("617042233719259181").sendMessage(message).queue();
    }
}
