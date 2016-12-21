package com.moovel.mvpbase;

public interface MVPBase {
    interface View {
    }

    abstract class Presenter<V extends View> {
        abstract void attachView(V view);
        abstract void detachView();
    }
}
