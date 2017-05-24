package com.moovel.mvp;

public class ViewNotAttachedException extends Exception {
    public ViewNotAttachedException() {
        super("The view was not attached. Note that the view is attached in onStart and detached in onStop");
    }
}
