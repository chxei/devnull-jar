package Bootstrapper;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;
import java.util.*;

public class EventNotFound {
    public static String[][] huh;

    static{
        huh = new String[][]{
                {"huh?", "text"},
                {"dunno", "text"},
                {"dunno.gif", "gif"},
                {"U sure?", "text"},
                {"We don't do that here", "text"},
                {"Kommando not found", "text"},
                {"<:wait:694344504911855686>", "emoji"},
                {"<:lolwhat:694341216267010078>", "emoji"},
                {"<:go:630877949012344872>", "emoji"},
                {"<:thomas1:647160141778518022>", "emoji"},
                {"<:GAY:645706036292091904>", "emoji"},
                {"<@694231335568408596> Is that your command?", "text"}, //python bot
                {"clinteastwood.gif", "gif"},
                {"excuzame.gif", "gif"},
                {"obama.gif", "gif"}
        };
    }

    public static void commandNotFoundHandler(MessageReceivedEvent e){
        int index = new Random().nextInt(huh.length-1);
        switch(huh[index][1]){
            case "text":
            case "emoji":
                    e.getChannel().sendMessage(huh[index][0]).queue();
                break;
            case "gif":
                    e.getChannel().sendFile(new File("src/main/resources/gifs/"+huh[index][0])).queue();
                break;
        }
    }
}
//TODO some AI based answers? huh?
