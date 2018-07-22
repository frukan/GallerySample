package net.furkanakdemir.myapplication.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
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
    private AlertDialog      columnDialog;

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

        setupColumnDialog();
    }

    private void setupColumnDialog() {
        NumberPicker picker = new NumberPicker(this);
        picker.setMinValue(1);
        picker.setMaxValue(6);

        FrameLayout layout = new FrameLayout(this);
        layout.addView(picker, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

        columnDialog = new AlertDialog.Builder(this).setView(layout)
            .setTitle(R.string.title_dialog_column)
            .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                int columnCount = picker.getValue();

                gridView.setColumnCount(columnCount);
            })
            .setNegativeButton(android.R.string.cancel, null)
            .create();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.column:
                columnDialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
