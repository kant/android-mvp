package com.moovel.mvp;

import com.moovel.mvp.lifecycle.LifecycleObserver;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CompositeLifecycleObserverTest {

    @Test
    public void testIfAddingAndRemovingWorksCorrect() {
        CompositeLifecycleObserver observers = new CompositeLifecycleObserver();

        LifecycleObserver observer1 = Mockito.mock(LifecycleObserver.class);
        LifecycleObserver observer2 = Mockito.mock(LifecycleObserver.class);
        LifecycleObserver observer3 = Mockito.mock(LifecycleObserver.class);


        observers.addLifecycleInterceptor(observer1);
        observers.addLifecycleInterceptor(observer2);
        observers.addLifecycleInterceptor(observer3);

        assertEquals(3, observers.observers.size());

        observers.removeLifecycleInterceptor(observer1);
        observers.removeLifecycleInterceptor(observer1);
        observers.removeLifecycleInterceptor(observer1);

        assertEquals(2, observers.observers.size());

        observers.removeLifecycleInterceptor(observer2);
        observers.removeLifecycleInterceptor(observer3);

        assertEquals(0, observers.observers.size());
    }

    @Test
    public void testIfMethodsAreCalled() {
        CompositeLifecycleObserver observers = new CompositeLifecycleObserver();
        LifecycleObserver observer1 = Mockito.mock(LifecycleObserver.class);
        LifecycleObserver observer2 = Mockito.mock(LifecycleObserver.class);
        observers.addLifecycleInterceptor(observer1);
        observers.addLifecycleInterceptor(observer2);

        observers.doOnCreate();
        observers.doOnStart();
        observers.doOnPause();
        observers.doOnResume();
        observers.doOnStop();
        observers.doOnDestroy();

        verify(observer1, times(1)).doOnCreate();
        verify(observer1, times(1)).doOnStart();
        verify(observer1, times(1)).doOnResume();
        verify(observer1, times(1)).doOnPause();
        verify(observer1, times(1)).doOnStop();
        verify(observer1, times(1)).doOnDestroy();
        verify(observer2, times(1)).doOnCreate();
        verify(observer2, times(1)).doOnStart();
        verify(observer2, times(1)).doOnResume();
        verify(observer2, times(1)).doOnPause();
        verify(observer2, times(1)).doOnStop();
        verify(observer2, times(1)).doOnDestroy();
    }

    @Test
    public void testIfMethodsCalledInCorrectOrder() {
        CompositeLifecycleObserver observers = new CompositeLifecycleObserver();
        LifecycleObserver observer1 = Mockito.mock(LifecycleObserver.class);
        LifecycleObserver observer2 = Mockito.mock(LifecycleObserver.class);
        observers.addLifecycleInterceptor(observer1);
        observers.addLifecycleInterceptor(observer2);

        observers.doOnCreate();
        observers.doOnStart();
        observers.doOnResume();
        observers.doOnPause();
        observers.doOnStop();
        observers.doOnDestroy();

        InOrder inOrder = inOrder(observer1, observer2);

        inOrder.verify(observer1).doOnCreate();
        inOrder.verify(observer2).doOnCreate();
        inOrder.verify(observer1).doOnStart();
        inOrder.verify(observer2).doOnStart();
        inOrder.verify(observer1).doOnResume();
        inOrder.verify(observer2).doOnResume();

        inOrder.verify(observer2).doOnPause();
        inOrder.verify(observer1).doOnPause();
        inOrder.verify(observer2).doOnStop();
        inOrder.verify(observer1).doOnStop();
        inOrder.verify(observer2).doOnDestroy();
        inOrder.verify(observer1).doOnDestroy();
    }

}