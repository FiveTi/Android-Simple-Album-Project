package com.fiveti.a5tphoto.Database;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable {

    String albumName;
    ArrayList<String> allImagePath;

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public ArrayList<String> getAllImagePath() {
        return allImagePath;
    }

    public void setAllImagePath(ArrayList<String> allImagePath) {
        this.allImagePath = allImagePath;
    }

}
