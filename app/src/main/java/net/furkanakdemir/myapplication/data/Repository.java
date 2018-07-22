package net.furkanakdemir.myapplication.data;

import io.reactivex.Observable;
import net.furkanakdemir.myapplication.data.model.Gallery;

public interface Repository {

    Observable<Gallery> getImages(int count);
}
