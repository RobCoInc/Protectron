package tech.secure.protectron.objects;

/**
 * Created by Morgan on 2017-11-18.
 */

public class Arrest {
    private long arrestID;
    private String date;
    private String time;
    private String type;
    private long userID;
    private long locationID;

    public long getArrestID() {
        return arrestID;
    }

    public void setArrestID(long arrestID) {
        this.arrestID = arrestID;
    }

    public long getLocationID() {
        return locationID;
    }

    public void setLocationID(long locationID) {
        this.locationID = locationID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
