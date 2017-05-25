package com.moovel.mvp.demo.screens.main;

import com.moovel.mvp.MVPPresenter;
import com.moovel.mvp.ViewNotAttachedException;
import com.moovel.mvp.demo.LifecycleLogger;
import com.moovel.mvp.lifecycle.LifecycleEventScheduler;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

import static com.moovel.mvp.lifecycle.LifecycleEvent.STOP;

public class AwesomePresenter extends MVPPresenter<AwesomeView> {

    private static final String TAG = AwesomePresenter.class.getSimpleName();

    private final LifecycleEventScheduler<Disposable> lifecycleScheduler =
            new LifecycleEventScheduler<>((event, item) -> item.dispose());

    private final String string;
    // Counter survives config changes, because it's application scoped
    private int counter = 0;

    @Inject
    public AwesomePresenter(String injectedString) {
        this.string = injectedString;
        addLifecycleInterceptor(lifecycleScheduler);
        addLifecycleInterceptor(new LifecycleLogger(TAG));
    }

    @Override
    public void onStart() {
        super.onStart();
        // view is now attached
        try {
            getViewOrThrow().showText(String.format(Locale.getDefault(), "Log: %s %d", string, ++counter));
        } catch (ViewNotAttachedException e) {
            Timber.e(e);
        }

        lifecycleScheduler.enqueue(STOP, Observable.interval(0, 5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                // writes a log every 5 seconds until onPause was called
                .subscribe(item -> {
                    try {
                        getViewOrThrow().showText(String.format(Locale.getDefault(), "Log: %s %d", string, ++counter));
                    } catch (ViewNotAttachedException e) {
                        Timber.i(e);
                    }
                }));
    }
}