package net.furkanakdemir.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private GalleryViewModel galleryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timber.uprootAll();
        Timber.plant(new Timber.DebugTree());

        // TODO Inject via Dagger
        ViewModelFactory factory = new ViewModelFactory(new GalleryRepository(null, new RemoteDataSource(new GalleryMapper())));

        galleryViewModel = ViewModelProviders.of(this, factory).get(GalleryViewModel.class);

        galleryViewModel.getGalleryLiveData().observe(this, new Observer<Gallery>() {
            @Override
            public void onChanged(@Nullable Gallery gallery) {
                Timber.d("[GALLERY] %s", gallery.toString());
            }
        });

        galleryViewModel.refreshList();

    }
}
