package Quotes;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.File;
import java.util.Random;

public class QuotesBot {
    static Quote[] quotes;

    static{
        quotes = new Quote[] {
            new Quote("Snow is so beautiful, it doesn't have to be useful","Richard Stallman"),
            new Quote("People sometimes ask me if it is a sin in the Church of Emacs to use vi. Using a free version of vi is not a sin; it is a penance. So happy hacking","Richard Stallman"),
            new Quote("Playfully doing something difficult, whether useful or not, that is hacking","Richard Stallman"),
            new Quote("Software is like sex: it's better when it's free","Linus Torvalds"),
            new Quote("You know you're brilliant, but maybe you'd like to understand what you did 2 weeks from now","Linus Torvalds"),
            new Quote("If it compiles, it is good; if it boots up, it is perfect","Linus Torvalds"),
            new Quote("Nobody actually creates perfect code the first time around, except me. But there's only one of me","Linus Torvalds"),
            new Quote("Microsoft isn't evil, they just make really crappy operating systems ","Linus Torvalds"),
            new Quote("It's what I call \"mental masturbation\", when you engage is some pointless intellectual exercise that has no possible meaning","Linus Torvalds"),
            new Quote("Standards are paper. I use paper to wipe my butt every day. That's how much that paper is worth","Linus Torvalds"),
            new Quote("I like offending people, because I think people who get offended should be offended","Linus Torvalds"),
            new Quote("There aren't enough swear-words in the English language, so now I'll have to call you perkeleen vittupää just to express my disgust and frustration with this crap","Linus Torvalds"),
            new Quote("Sometimes when you fill a vacuum, it still sucks","dennis ritchie"),
        };
    }

    public static void getQuote(MessageReceivedEvent e){
        int index = new Random().nextInt(quotes.length-1);
        e.getChannel().sendMessage(quotes[index].toString()).queue();
    }
}
