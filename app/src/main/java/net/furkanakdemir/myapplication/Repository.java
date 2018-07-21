package net.furkanakdemir.myapplication;

import io.reactivex.Observable;

public interface Repository {

    Observable<Gallery> getImages();
}
