package com.moovel.mvpbase.demo.screens.main;

import com.moovel.mvpbase.rx.RxBasePresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class MainPresenter extends RxBasePresenter<MainView> {

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
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                getView().log(String.format("Log: %d", aLong));
                            }
                        })
        );
    }
}