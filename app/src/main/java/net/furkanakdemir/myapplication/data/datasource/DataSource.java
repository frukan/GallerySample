package net.furkanakdemir.myapplication.data.datasource;

import io.reactivex.Observable;

public interface DataSource<T> {

    Observable<T> get();

    void set();
}
