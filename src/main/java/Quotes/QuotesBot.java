package Quotes;

import Bootstrapper.DB;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuotesBot {
    static Quote[] quotes;
    static DB db = new DB();
    static Connection conn = db.connect();
    static String query;

    public static void getQuote(MessageReceivedEvent e) {
        query = "SELECT quote, author from \"Quotes\" ORDER BY random() LIMIT 1;";
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

    public static void addQuote(MessageReceivedEvent event){

        query = "Insert into \"Quotes\"(quote,author,uploader) values(?,?,?)";
        String uploader = event.getAuthor().getName();
        String[] messageArray = event.getMessage().getContentRaw().split("\"");

        if(messageArray.length<=1){
            messageArray = event.getMessage().getContentRaw().split("\'");
        }
        String quote = messageArray[1];
        String author = messageArray[3];
        System.out.println("quote: "+quote+" author: "+author);
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,quote);
            preparedStatement.setString(2,author);
            preparedStatement.setString(3,uploader);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            event.getChannel().sendMessage(e.getMessage()).queue();
            // e.printStackTrace();
        }
    }
    public static void searchQuote(MessageReceivedEvent e){

        String[] messageArray = e.getMessage().getContentRaw().split("\"");
        String searchText = messageArray[1];
        query = "SELECT quote, author from \"Quotes\" WHERE LOWER(quote) like LOWER(\'%"+searchText+"%\') or LOWER(author) like LOWER(\'%"+searchText+"%\') ORDER BY random() LIMIT 1;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);;
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
