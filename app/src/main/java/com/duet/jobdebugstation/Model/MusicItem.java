package com.duet.jobdebugstation.Model;

import java.util.ArrayList;

public class MusicItem {
    private final boolean singleType;
    private ArrayList<ImageNameWithId> imageArrayList;
    private final int singleId;

    public MusicItem(boolean singleType, int singleId, ArrayList<ImageNameWithId> imageArrayList) {
        this.singleType = singleType;
        this.imageArrayList = imageArrayList;
        this.singleId = singleId;
    }

    public boolean isSingleType() { return singleType; }

    public ArrayList<ImageNameWithId> getImageArrayList() {
        return imageArrayList;
    }

    public int getSingleId() { return singleId; }
}
