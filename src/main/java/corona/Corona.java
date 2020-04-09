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
    public Corona(CoronaDataType dataType, String location, String countryCode, double latitude, double longitude, Long confirmed, Long dead, Long recovered, Date updateDate) {
        this.dataType = dataType;
        this.location = location;
        this.countryCode = countryCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.confirmed = confirmed;
        this.dead = dead;
        this.recovered = recovered;
        this.updateDate = updateDate;
    }
    public Corona(CoronaDataType dataType, Long confirmed, Long dead, Long recovered) {
        this.dataType = dataType;
        this.confirmed = confirmed;
        this.dead = dead;
        this.recovered = recovered;
    }


    @Override
    public String toString() {
        switch (this.dataType){
            case WORLDWIDE: return "დაინფიცირდა: " +confirmed+", მოკვდა: "+ dead+", გამოჯანმრთელდა: "+ recovered;
            default: return "Corona{" +
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

