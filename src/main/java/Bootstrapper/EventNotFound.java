package Bootstrapper;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;
import java.util.*;

public class EventNotFound {
    static int count = 15;

    public static String[][] huh = new String[count][2];
    static{
        huh[0] = new String[]{"huh?", "text"};
        huh[1] = new String[]{"dunno", "text"};
        huh[2] = new String[]{"dunno.gif", "gif"};
        huh[3] = new String[]{"U sure?", "text"};
        huh[4] = new String[]{"We don't do that here", "text"};
        huh[5] = new String[]{"Kommando not found", "text"};
        huh[6] = new String[]{"<:wait:694344504911855686>", "emoji"};
        huh[7] = new String[]{"<:lolwhat:694341216267010078>", "emoji"};
        huh[8] = new String[]{"<:go:630877949012344872>", "emoji"};
        huh[9] = new String[]{"<:thomas1:647160141778518022>", "emoji"};
        huh[10] = new String[]{"<:GAY:645706036292091904>", "emoji"};
        huh[11] = new String[]{"<@694231335568408596> Is that your command?", "text"}; //python bot
        huh[12] = new String[]{"clinteastwood.gif", "gif"};
        huh[13] = new String[]{"excuzame.gif", "gif"};
        huh[14] = new String[]{"obama.gif", "gif"};
    }

    public static void commandNotFoundHandler(MessageReceivedEvent e){
        int index = new Random().nextInt(count-1);
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
