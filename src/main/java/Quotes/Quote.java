package Quotes;

import java.util.Date;

public class Quote {
    String quote;
    String author;
    String uploaderUserName;
    String uploaderUserId;
    Date uploadDate;
    Boolean active;

    public Quote(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }
    @Override
    public String toString() {
        String newLine = System.getProperty("line.separator");
        return "> **"+this.quote+"**"+newLine
                + "__*"+this.author+"*__";
    }

}
//TODO get quote by author
//TODO Don't send duplicate quotes every
//TODO ability to search quote
