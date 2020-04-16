package Quotes;

import Bootstrapper.DB;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuotesBot {
    static Quote[] quotes;


    public static void getQuote(MessageReceivedEvent e) {
        DB db = new DB();
        Connection conn = db.connect();
        String query = "SELECT quote, author from \"Quotes\" ORDER BY random() LIMIT 1;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Quote quote = new Quote(resultSet.getString(1),resultSet.getString(2));
                e.getChannel().sendMessage(quote.toString()).queue();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
