package com.moovel.mvpbase.demo.screens.main;

import com.moovel.mvpbase.BasePresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;


public class MainPresenter extends BasePresenter<MainView> {

    private final RxLifecycleInterceptor rxinterceptor;
    private String string;

    @Inject
    public MainPresenter(String injectedString) {
        this.string = injectedString;
        this.rxinterceptor = new RxLifecycleInterceptor();
        addLifecycleInterceptor(rxinterceptor);
    }

    public String getInjectedString() {
        return string;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        rxinterceptor.untilOnStop(
                Observable.interval(0, 5, TimeUnit.SECONDS)
                        .subscribe(item -> getView().log(String.format("Log: %d", item)))

        );
    }
}