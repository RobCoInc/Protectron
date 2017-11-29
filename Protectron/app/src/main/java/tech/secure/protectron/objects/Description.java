package tech.secure.protectron.objects;

/**
 * Created by Morgan on 2017-11-27.
 */

public class Description
{
    private long descriptionId;
    private String name;
    private int Height;
    private int Weight;
    private String hairColor;
    private String eyeColor;
    private String skinColor;
    private String hasTatoo;

    public String getHasTatoo() {
        return hasTatoo;
    }

    public void setHasTatoo(String hasTatoo) {
        this.hasTatoo = hasTatoo;
    }

    private long arrestId;

    public long getDescriptionId() {
        return descriptionId;
    }

    public void setDescriptionId(long descriptionId) {
        this.descriptionId = descriptionId;
    }

    public long getArrestId() {
        return arrestId;
    }

    public void setArrestId(long arrestId) {
        this.arrestId = arrestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        this.Height = height;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        this.Weight = weight;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }
}
