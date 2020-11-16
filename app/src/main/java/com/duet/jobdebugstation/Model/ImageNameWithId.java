package com.duet.jobdebugstation.Model;

public class ImageNameWithId {
    private final int imageId;
    private final String name;

    public ImageNameWithId(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }
}
