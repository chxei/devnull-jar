package corona;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Corona {
    URL url;
    HttpURLConnection conn;
    String inline = "";

    long confirmed = 0;
    long deaths = 0;
    long recovered = 0;
    public static String output = "";

    public Corona() {

    }
    public String getData(){
        try {
            this.url = new URL("https://wuhan-coronavirus-api.laeyoung.endpoint.ainize.ai/jhu-edu/brief");
            this.conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {

                    inline += sc.nextLine();
                }
                System.out.println("\nJSON data in string format");
                System.out.println(inline);
                JSONParser parse = new JSONParser();
                JSONObject jobj = (JSONObject) parse.parse(inline);
                confirmed = (long) jobj.get("confirmed");
                deaths = (long) jobj.get("deaths");
                recovered = (long) jobj.get("recovered");
                output = "დაინფიცირდა: " + confirmed + ", მოკვდა: " + deaths + ", გამოჯანმრთელდა: " + recovered;
                sc.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return  output;
    }

}
