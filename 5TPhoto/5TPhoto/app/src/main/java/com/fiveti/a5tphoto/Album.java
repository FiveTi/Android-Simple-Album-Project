package com.fiveti.a5tphoto;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable {

    String folder;
    ArrayList<String> allImagePath;

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public ArrayList<String> getAllImagePath() {
        return allImagePath;
    }

    public void setAllImagePath(ArrayList<String> allImagePath) {
        this.allImagePath = allImagePath;
    }

}
