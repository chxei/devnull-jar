package quotes;

import java.util.Date;

public class Quote {
    String quoteText;
    String author;
    String uploaderUserName;
    String uploaderUserId;
    Date uploadDate;
    Boolean active;

    public Quote(String quoteText, String author) {
        this.quoteText = quoteText;
        this.author = author;
    }
    @Override
    public String toString() {
        String newLine = System.getProperty("line.separator");
        return "> **"+this.quoteText+"**"+newLine
                + "__*"+this.author+"*__";
    }

}
//TODO get quote by author
//TODO Don't send duplicate quotes every
