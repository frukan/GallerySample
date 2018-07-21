package net.furkanakdemir.myapplication.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import net.furkanakdemir.myapplication.R;
import net.furkanakdemir.myapplication.data.GalleryMapper;
import net.furkanakdemir.myapplication.data.GalleryRepository;
import net.furkanakdemir.myapplication.data.datasource.remote.RemoteDataSource;
import net.furkanakdemir.myapplication.data.model.Gallery;
import net.furkanakdemir.myapplication.viewmodel.GalleryViewModel;
import net.furkanakdemir.myapplication.viewmodel.ViewModelFactory;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.gridview_main) MyGridView gridView;


    private GalleryViewModel galleryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timber.uprootAll();
        Timber.plant(new Timber.DebugTree());

        ButterKnife.bind(this);

        // TODO Inject via Dagger
        ViewModelFactory factory = new ViewModelFactory(new GalleryRepository(null, new RemoteDataSource(new GalleryMapper())));

        galleryViewModel = ViewModelProviders.of(this, factory).get(GalleryViewModel.class);

        galleryViewModel.getGalleryLiveData().observe(this, new Observer<Gallery>() {
            @Override
            public void onChanged(Gallery gallery) {
                Timber.d("[GALLERY] %s", gallery.toString());

                gridView.setGallery(gallery);
            }
        });

        galleryViewModel.refreshList();

    }
}
