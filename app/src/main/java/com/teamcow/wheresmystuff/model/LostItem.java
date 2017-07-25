package com.teamcow.wheresmystuff.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.location.places.GeoDataApi;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;

import java.util.Arrays;
import java.util.List;

/**
 * Created by WAnya on 6/22/2017.
 * Edited by Tian Ze Qi
 * This class holds data for the items registered on the app
 */

public class LostItem implements Parcelable{
    //holds the legal item types
    public static List<String> legalType = Arrays.asList("Clothes", "Toiletry", "Jewelry",
            "Electronic", "Other");

    private String id;

    //holds the name of item
    private String name;

    // holds the description of item
    private String description;

    // holds the user associated with a given item
    private String user;

    // holds url for image of the item
    //private String imageUrl;

    // holds the x-coordinate
    private double x_coord;

    // holds the y-coordinate
    private double y_coord;

    // holds the item type
    private ItemType type;

    // holds whether the poster lost or found the item
    private PosterType poster;

//    private Place location;

    private String uri;

    public LostItem(String id, String name, String description, String user, double x_coord, double y_coord, ItemType type, PosterType poster, String imageUri) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.type = type;
        this.poster = poster;
        this.uri = imageUri;
    }

//    public LostItem(String id, String name, String description, String user, double x_coord, double y_coord, ItemType type, PosterType poster, Uri imageUri) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.user = user;
//        this.x_coord = x_coord;
//        this.y_coord = y_coord;
//        this.type = type;
//        this.poster = poster;
//        this.uri = imageUri.toString();
//    }

    public LostItem() {

    }

    public static List<String> getLegalType() {
        return legalType;
    }

    public static void setLegalType(List<String> legalType) {
        LostItem.legalType = legalType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getX_coord() {
        return x_coord;
    }

    public void setX_coord(double x_coord) {
        this.x_coord = x_coord;
    }

    public double getY_coord() {
        return y_coord;
    }

    public void setY_coord(double y_coord) {
        this.y_coord = y_coord;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public PosterType getPoster() {
        return poster;
    }

    public void setPoster(PosterType poster) {
        this.poster = poster;
    }

//    public Uri getImageUri() {
//        return Uri.parse(uri);
//    }
//
//    public void setImageUri(Uri imageUri) {
//        this.uri = imageUri.toString();
//    }

    public String getUri() {
        return uri;
    }

    public void setUri(String u) {
        uri = u;
    }

    public static Creator<LostItem> getCREATOR() {
        return CREATOR;
    }

    public static int findPosition(String code) {
        int i = 0;
        while (i < legalType.size()) {
            if (code.equals(legalType.get(i))) return i;
            ++i;
        }
        return 0;
    }

    //    public LostItem(String name, String des, ItemType type, PosterType poster) {
//        this.name = name;
//        this.description = des;
//        this.type = type;
//        this.poster = poster;
//        x_coord = 0;
//        y_coord = 0;
//    }
//
//    public LostItem(String name, String des, ItemType type, PosterType poster, double x_coord,
//                    double y_coord) {
//        this.name = name;
//        this.description = des;
//        this.type = type;
//        this.poster = poster;
//        this.x_coord = x_coord;
//        this.y_coord = y_coord;
//    }

//    public String getId() { return id; }
//
//    /**
//     * Allows the name of an item to be retrieved
//     * @return the name of an item
//     */
//    public String getName() {
//        return name;
//    }
//
//    /**
//     * Allows the description of an item to be retrieved
//     * @return the description of an item
//     */
//    public String getDescription() {
//        return description;
//    }
//
//    /**
//     * Allows the user associated with an item to be retrieved
//     * @return the user associated with an item
//     */
//    public String getUser() {
//        return user;
//    }
//
//    /**
//     * Allows the x_coord of an item to be retrieved
//     * @return the x_coord of an item
//     */
//    public double getX_Coord() {
//        return x_coord;
//    }
//
//    /**
//     * Allows the y_coord of an item to be retrieved
//     * @return the y_coord of an item
//     */
//    public double getY_Coord() {
//        return y_coord;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(user);
        dest.writeSerializable(type);
        dest.writeSerializable(poster);
        dest.writeDouble(x_coord);
        dest.writeDouble(y_coord);
        dest.writeString(uri);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<LostItem> CREATOR = new Parcelable.Creator<LostItem>() {
        public LostItem createFromParcel(Parcel in) {
            return new LostItem(in);
        }

        public LostItem[] newArray(int size) {
            return new LostItem[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private LostItem(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        user = in.readString();
        type = (ItemType) in.readSerializable();
        poster = (PosterType) in.readSerializable();
        x_coord = in.readDouble();
        y_coord = in.readDouble();
        uri = in.readString();
    }
}
