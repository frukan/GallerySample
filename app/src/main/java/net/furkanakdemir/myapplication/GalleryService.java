package net.furkanakdemir.myapplication;

import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GalleryService {

    @GET("/photos/random?w=100&&h=100")
    Observable<List<ImageRaw>> getImages(@Query("count") int count);
}
