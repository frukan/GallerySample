package net.furkanakdemir.myapplication;

import io.reactivex.Observable;

public interface DataSource<T> {

    Observable<T> get();

    void set();
}
