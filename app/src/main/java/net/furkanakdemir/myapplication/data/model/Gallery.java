package net.furkanakdemir.myapplication.data.model;

import java.util.List;

public class Gallery {

    private List<Image> images;

    public Gallery(List<Image> images) {
        this.images = images;
    }

    public List<Image> getImages() {
        return images;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Image image : images) {
            stringBuilder.append(image.getId());
            stringBuilder.append(" [");
            stringBuilder.append(image.getUrl());
            stringBuilder.append("]");
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
