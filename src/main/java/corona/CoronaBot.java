package corona;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static corona.CoronaDataType.COUNTRYWIDE;
import static corona.CoronaDataType.WORLDWIDE;

public final class CoronaBot {
    String link = "https://www.trackcorona.live/api/";
    URL url;
    HttpURLConnection httpConn;
    List<Corona> coronas;

    public CoronaBot() {
    }

    public String getData(CoronaDataType dataType) {
        Corona c1 = new Corona();
        try {
            if (dataType.equals(COUNTRYWIDE)) {
                link += "countries";
            }
            url = new URL(link);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            int responseCode = httpConn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                //return structure {code: 200; data {} }
                JSONArray jobA = (JSONArray) ((JSONObject) (new JSONParser().parse(new InputStreamReader((InputStream) httpConn.getContent())))).get("data");
                long deadSum = 0;
                long confirmedSum = 0;
                long recoveredSum = 0;

                for (Object cor : jobA) {
                    Long dead = (Long) ((JSONObject) cor).get("dead");
                    Long confirmed = (Long) ((JSONObject) cor).get("confirmed");
                    Long recovered = (Long) ((JSONObject) cor).get("recovered");
                    deadSum += dead;
                    confirmedSum += confirmed;
                    recoveredSum += recovered;
                    //String countryCode = (String) ((JSONObject)cor).get("country_code");
                    //String location =(String) ((JSONObject)cor).get("location");
                    //double latitude = (double) ((JSONObject)cor).get("latitude");
                    //double longitude = (double) ((JSONObject)cor).get("longitude");
                    //Date updateDate =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSXXX").parse((String)((JSONObject)cor).get("updated"));
                    //Corona corona = new Corona(dataType,location,countryCode,latitude,longitude,confirmed,dead,recovered,updateDate);
                    //coronas.add(corona);
                }
                c1 = new Corona(WORLDWIDE, confirmedSum, deadSum, recoveredSum);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(c1);
        return c1.toString();
    }
}
