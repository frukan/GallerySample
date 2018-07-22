package net.furkanakdemir.myapplication.data;

import io.reactivex.Observable;
import net.furkanakdemir.myapplication.data.datasource.DataSource;
import net.furkanakdemir.myapplication.data.model.Gallery;

public class GalleryRepository implements Repository {

    private DataSource<Gallery> localDataSource;
    private DataSource<Gallery> remoteDataSource;

    public GalleryRepository(DataSource<Gallery> localDataSource,
                             DataSource<Gallery> remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Observable<Gallery> getImages(int count) {

        return remoteDataSource.get(count);
    }
}
