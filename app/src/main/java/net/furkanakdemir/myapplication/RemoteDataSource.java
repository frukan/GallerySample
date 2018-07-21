package net.furkanakdemir.myapplication;

import io.reactivex.Observable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource implements DataSource<Gallery> {

    private static final String                 BASE_URL = "https://api.unsplash.com/";
    private static final int                    COUNT    = 20;
    private static       HttpLoggingInterceptor logging  = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private GalleryMapper galleryMapper;

    public static final String API_KEY_UNSPLASH = BuildConfig.API_KEY_UNSPLASH;

    public RemoteDataSource(GalleryMapper galleryMapper) {
        this.galleryMapper = galleryMapper;
    }

    @Override
    public Observable<Gallery> get() {

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // Add logging interceptor
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
        }
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("client_id", API_KEY_UNSPLASH)
                    .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder().url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        // Add timeouts
        OkHttpClient okHttpClient = httpClient.readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build();

        builder.client(okHttpClient);
        Retrofit retrofit = builder.build();

        return retrofit.create(GalleryService.class).getImages(COUNT).map(galleryMapper);
    }

    @Override
    public void set() {

    }
}
