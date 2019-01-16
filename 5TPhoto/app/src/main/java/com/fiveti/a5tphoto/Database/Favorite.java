package com.fiveti.a5tphoto.Database;

import java.io.Serializable;
import java.util.ArrayList;

public class Favorite implements Serializable {


    String allImagePath;


    public String getAllImagePath() {
        return allImagePath;
    }

    public void setAllImagePath(String allImagePath) {
        this.allImagePath = allImagePath;
    }

}
