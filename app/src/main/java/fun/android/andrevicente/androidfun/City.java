package fun.android.andrevicente.androidfun;


import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class City implements Comparable {

    @SerializedName("country")
    public String mCountry;

    @SerializedName("name")
    public String mName;

    @SerializedName("_id")
    public int mId;

    @SerializedName("coord")
    public Coordinates mCoordinates;

    public String mDisplayLabel;

    @Override
    public int compareTo(@NonNull Object o) {
        City city = (City) o;
        return this.mName.compareTo(city.mName);
    }

    /**
     * Coordinates class allows Gson to serialize the coordinates.
     */
    public class Coordinates {
        @SerializedName("lat")
        public double mLatitude;
        @SerializedName("lon")
        public double mLongitude;
    }

}
