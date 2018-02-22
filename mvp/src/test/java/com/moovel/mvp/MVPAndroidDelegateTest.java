package com.moovel.mvp;

import com.moovel.mvp.dummy.DummyTestPresenter;
import com.moovel.mvp.dummy.DummyTestView;
import com.moovel.mvp.lifecycle.LifecycleObserver;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;

import static junit.framework.Assert.assertEquals;

public class MVPAndroidDelegateTest {

    private MVPAndroidDelegate subject;
    private DummyTestView dummyTestView = new DummyTestView(null);
    private DummyTestPresenter dummyTestPresenter = new DummyTestPresenter();

    @Before
    public void setUp() {
        subject = new MVPAndroidDelegate(dummyTestView);
    }

    @Test
    public void attachViewRemovesOldPresenterInstance() throws Exception {
        subject.attachView(dummyTestView, dummyTestPresenter);
        subject.attachView(dummyTestView, dummyTestPresenter);

        Field f = subject.getClass().getDeclaredField("observer");
        f.setAccessible(true);
        LinkedList<LifecycleObserver> observers = ((CompositeLifecycleObserver) f.get(subject)).observers;
        assertEquals(observers.size(), 1);
        assertEquals(new ArrayList<>(observers).get(0).getClass(), PresenterLifecycleObserver.class);
    }
}
