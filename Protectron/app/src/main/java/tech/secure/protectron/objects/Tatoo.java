package tech.secure.protectron.objects;

/**
 * Created by Morgan on 2017-11-27.
 */

public class Tatoo {

    private long _id;
    private String location;
    private String size;
    private String description;
    private String primary;
    private String secondary;
    private long descriptionId;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public long getDescriptionId() {
        return descriptionId;
    }

    public void setDescriptionId(long descriptionId) {
        this.descriptionId = descriptionId;
    }
}
