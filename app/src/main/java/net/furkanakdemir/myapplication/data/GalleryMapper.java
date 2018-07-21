package net.furkanakdemir.myapplication.data;

import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import net.furkanakdemir.myapplication.data.model.Gallery;
import net.furkanakdemir.myapplication.data.model.Image;
import net.furkanakdemir.myapplication.data.model.ImageRaw;

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
