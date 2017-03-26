package fr.esilv.s8.tp_note.models;

import com.google.gson.annotations.SerializedName;

public class Thumbnails {
    @SerializedName("default")
    private Medium defaultX;

    public Medium getMedium() {
        return defaultX;
    }

    public void setMedium(Medium defaultX) {
        this.defaultX = defaultX;
    }
}
