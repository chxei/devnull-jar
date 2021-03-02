package corona;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.LoggerFactory;
import net.dv8tion.jda.api.exceptions.HttpException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static corona.CoronaDataType.COUNTRYWIDE;
import static corona.CoronaDataType.WORLDWIDE;

public final class CoronaBot {
    static final String LINK = "https://www.trackcorona.live/api/";



    private CoronaBot() {}

    public static String getData(CoronaDataType dataType) {
        Corona c1 = new Corona();
        URL url;
        HttpURLConnection httpConn;
        try {
            if (dataType.equals(COUNTRYWIDE)) {
                url = new URL(LINK+"countries");
            } else {
                url = new URL(LINK);
            }

            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            int responseCode = httpConn.getResponseCode();
            if (responseCode != 200) {
                throw new HttpException("responseCode: "+responseCode);
            } else {
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
                }
                c1 = new Corona(WORLDWIDE, confirmedSum, deadSum, recoveredSum);
            }
        } catch (Exception e) {
            LoggerFactory.getLogger(CoronaBot.class).info(e.getMessage());
        }
        return c1.toString();
    }
}
