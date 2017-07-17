package com.tolido.optics.tolidooptics.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Home on 2017-07-16.
 */

public class Post {
    @SerializedName("data")
    private List<Datum> data = null;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
}
