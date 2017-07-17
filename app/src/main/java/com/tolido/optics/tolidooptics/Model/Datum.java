package com.tolido.optics.tolidooptics.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Home on 2017-07-17.
 */

public class Datum {

    @SerializedName("full_picture")
    private String fullPicture;

    @SerializedName("message")
    private String message;



    public String getFullPicture() {
        return fullPicture;
    }

    public void setFullPicture(String fullPicture) {
        this.fullPicture = fullPicture;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}