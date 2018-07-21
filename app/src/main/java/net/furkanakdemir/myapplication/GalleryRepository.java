package net.furkanakdemir.myapplication;

import io.reactivex.Observable;

public class GalleryRepository implements Repository {

    private DataSource<Gallery> localDataSource;
    private DataSource<Gallery> remoteDataSource;

    public GalleryRepository(DataSource<Gallery> localDataSource,
                             DataSource<Gallery> remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Observable<Gallery> getImages() {

        return remoteDataSource.get();
    }
}
