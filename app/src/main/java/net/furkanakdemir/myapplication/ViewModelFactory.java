package net.furkanakdemir.myapplication;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;

    public ViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GalleryViewModel.class)) {
            return (T) new GalleryViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

