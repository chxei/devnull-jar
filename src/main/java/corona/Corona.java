package corona;

import java.util.Date;
public class Corona {

    CoronaDataType dataType;
    String location;
    String countryCode;
    double latitude;
    double longitude;
    Long confirmed;
    Long dead;
    Long recovered;
    Date updateDate;

    public Corona(){}
    public Corona(CoronaDataType dataType, Long confirmed, Long dead, Long recovered) {
        this.dataType = dataType;
        this.confirmed = confirmed;
        this.dead = dead;
        this.recovered = recovered;
    }


    @Override
    public String toString() {
        if (this.dataType == CoronaDataType.WORLDWIDE ) {
            return "დაინფიცირდა: " +confirmed+", მოკვდა: "+ dead+", გამოჯანმრთელდა: "+ recovered;
        } else{
            return "Corona{" +
                    "dataType=" + dataType +
                    ", location='" + location + '\'' +
                    ", countryCode='" + countryCode + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", confirmed=" + confirmed +
                    ", dead=" + dead +
                    ", recovered=" + recovered +
                    ", updateDate=" + updateDate +
                    '}';
        }
    }
}
