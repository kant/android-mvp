package com.moovel.mvp.demo.screens.main;

import com.moovel.mvp.MVPPresenter;
import com.moovel.mvp.lifecycle.LifecycleEventScheduler;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;

import static com.moovel.mvp.lifecycle.LifecycleEvent.STOP;


public class AwesomePresenter extends MVPPresenter<AwesomeView> {

    private static final LifecycleEventScheduler<Subscription> CLEANUP =
            new LifecycleEventScheduler<>((event, item) -> item.unsubscribe());

    private final String string;

    @Inject
    public AwesomePresenter(String injectedString) {
        this.string = injectedString;
        addLifecycleInterceptor(CLEANUP);
    }

    public String getInjectedString() {
        return string;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CLEANUP.enqueue(STOP, Observable.interval(0, 5, TimeUnit.SECONDS)
                .subscribe(item -> getView().log(String.format("Log: %d", item))));
    }
}