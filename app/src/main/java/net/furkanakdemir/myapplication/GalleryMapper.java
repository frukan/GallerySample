package net.furkanakdemir.myapplication;

import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;

public class GalleryMapper implements Function<List<ImageRaw>, Gallery> {

    @Override
    public Gallery apply(List<ImageRaw> galleryRaw) throws Exception {

        List<Image> images = new ArrayList<>();

        for (ImageRaw imageRaw : galleryRaw) {
            images.add(new Image(imageRaw.getId(), imageRaw.getUrls().getCustom()));
        }

        return new Gallery(images);
    }
}
