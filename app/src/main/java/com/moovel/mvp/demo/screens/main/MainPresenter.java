package com.moovel.mvp.demo.screens.main;

import com.moovel.mvp.RxLifecyclePresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;


public class MainPresenter extends RxLifecyclePresenter<MainView> {

    private String string;

    @Inject
    public MainPresenter(String injectedString) {
        this.string = injectedString;
    }

    public String getInjectedString() {
        return string;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        untilOnStop(
                Observable.interval(0, 5, TimeUnit.SECONDS)
                        .subscribe(item -> getView().log(String.format("Log: %d", item)))

        );
    }
}