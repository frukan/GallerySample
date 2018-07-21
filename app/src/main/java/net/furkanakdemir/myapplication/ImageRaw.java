package net.furkanakdemir.myapplication;

import com.google.gson.annotations.SerializedName;

public class ImageRaw {

    @SerializedName("urls") private Urls   urls;
    @SerializedName("id") private   String id;

    public Urls getUrls() {
        return urls;
    }

    public String getId() {
        return id;
    }

    public static class Urls {

        @SerializedName("thumb") private   String thumb;
        @SerializedName("small") private   String small;
        @SerializedName("regular") private String regular;
        @SerializedName("full") private    String full;
        @SerializedName("raw") private     String raw;
        @SerializedName("custom") private  String custom;

        public String getThumb() {
            return thumb;
        }

        public String getSmall() {
            return small;
        }

        public String getRegular() {
            return regular;
        }

        public String getFull() {
            return full;
        }

        public String getRaw() {
            return raw;
        }

        public String getCustom() {
            return custom;
        }
    }
}
