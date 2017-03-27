package com.moovel.mvp.demo.screens.main;

import com.moovel.mvp.MVPPresenter;
import com.moovel.mvp.lifecycle.LifecycleEventScheduler;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;

import static com.moovel.mvp.lifecycle.LifecycleEvent.STOP;


public class AwesomePresenter extends MVPPresenter<AwesomeView> {

    private final LifecycleEventScheduler<Subscription> lifecycleScheduler =
            new LifecycleEventScheduler<>((event, item) -> item.unsubscribe());

    private final String string;

    @Inject
    public AwesomePresenter(String injectedString) {
        this.string = injectedString;
        addLifecycleInterceptor(lifecycleScheduler);
    }

    public String getInjectedString() {
        return string;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        lifecycleScheduler.enqueue(STOP, Observable.interval(0, 5, TimeUnit.SECONDS)
                // writes a log every 5 seconds until onPause was called
                .subscribe(item -> getView().log(String.format("Log: %d", item))));
    }
}