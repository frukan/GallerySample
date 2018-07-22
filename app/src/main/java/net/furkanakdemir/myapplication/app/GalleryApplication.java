package net.furkanakdemir.myapplication.app;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;
import timber.log.Timber;

public class GalleryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.uprootAll();
        Timber.plant(new Timber.DebugTree());

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
