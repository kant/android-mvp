package com.moovel.demo.screens;

import com.andretietz.demolib.injection.scopes.ActivityScope;
import com.andretietz.demolib.model.ActivityObject;
import com.andretietz.demolib.model.ApplicationObject;
import com.moovel.mvp.MVPPresenter;
import com.moovel.mvp.ViewNotAttachedException;
import com.moovel.mvp.lifecycle.LifecycleEvent;
import com.moovel.mvp.lifecycle.LifecycleEventScheduler;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableAmb;

@ActivityScope
public class DemoPresenter extends MVPPresenter<DemoView> {
    private final ApplicationObject apo;
    private final ActivityObject aco;

    LifecycleEventScheduler<Disposable> scheduler = new LifecycleEventScheduler<>((event, item) -> item.dispose());

    @Inject
    public DemoPresenter(ApplicationObject apo, ActivityObject aco) {
        this.apo = apo;
        this.aco = aco;
        addLifecycleObserver(scheduler);
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            getView().showInjectedObjects(apo, aco);
        } catch (ViewNotAttachedException e) {
            // view not available
        }

        scheduler.enqueue(LifecycleEvent.STOP, Observable.just("").subscribe());
    }

    public void onButtonClicked() {
        try {
            getView().openLibraryActivity();
        } catch (ViewNotAttachedException e) {
            // view not available
        }
    }
}
