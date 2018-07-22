package net.furkanakdemir.myapplication.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import net.furkanakdemir.myapplication.data.Repository;
import net.furkanakdemir.myapplication.data.model.Gallery;
import timber.log.Timber;

public class GalleryViewModel extends ViewModel {

    private static final int                      DEFAULT_SOURCE_COUNT = 24;
    private              MutableLiveData<Gallery> galleryLiveData      = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();
    private Repository repository;

    public GalleryViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<Gallery> getGalleryLiveData() {
        return galleryLiveData;
    }

    public void refreshList() {

        refreshList(DEFAULT_SOURCE_COUNT);
    }

    public void refreshList(int sourceCount) {

        disposable.clear();

        repository.getImages(sourceCount)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<Gallery>() {
                @Override
                public void onSubscribe(Disposable d) {
                    disposable.add(d);
                }

                @Override
                public void onNext(Gallery gallery) {
                    galleryLiveData.setValue(gallery);
                }

                @Override
                public void onError(Throwable e) {
                    Timber.e(e);
                }

                @Override
                public void onComplete() {

                }
            });
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (disposable != null && !disposable.isDisposed()) {
            disposable.clear();
        }
    }
}
