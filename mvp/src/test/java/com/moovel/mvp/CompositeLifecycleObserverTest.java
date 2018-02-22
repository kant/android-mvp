package com.moovel.mvp;

import android.os.Bundle;

import com.moovel.mvp.lifecycle.LifecycleObserver;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CompositeLifecycleObserverTest {

    private final Bundle emptyBundle = new Bundle();

    @Test
    public void testIfAddingAndRemovingWorksCorrect() {

        CompositeLifecycleObserver observers = new CompositeLifecycleObserver();

        LifecycleObserver observer1 = Mockito.mock(LifecycleObserver.class);
        LifecycleObserver observer2 = Mockito.mock(LifecycleObserver.class);
        LifecycleObserver observer3 = Mockito.mock(LifecycleObserver.class);


        observers.addLifecycleObserver(observer1);
        observers.addLifecycleObserver(observer2);
        observers.addLifecycleObserver(observer3);

        assertEquals(3, observers.observers.size());

        observers.removeLifecycleObserver(observer1);
        observers.removeLifecycleObserver(observer1);
        observers.removeLifecycleObserver(observer1);

        assertEquals(2, observers.observers.size());

        observers.removeLifecycleObserver(observer2);
        observers.removeLifecycleObserver(observer3);

        assertEquals(0, observers.observers.size());
    }

    @Test
    public void testIfMethodsAreCalled() {
        CompositeLifecycleObserver observers = new CompositeLifecycleObserver();
        LifecycleObserver observer1 = Mockito.mock(LifecycleObserver.class);
        LifecycleObserver observer2 = Mockito.mock(LifecycleObserver.class);
        observers.addLifecycleObserver(observer1);
        observers.addLifecycleObserver(observer2);

        observers.onCreate(emptyBundle);
        observers.onStart();
        observers.onResume();
        observers.onPause();
        observers.onSaveInstanceState(emptyBundle);
        observers.onStop();
        observers.onDestroy();
        observers.onLowMemory();

        verify(observer1, times(1)).onCreate(emptyBundle);
        verify(observer1, times(1)).onStart();
        verify(observer1, times(1)).onResume();
        verify(observer1, times(1)).onPause();
        verify(observer1, times(1)).onSaveInstanceState(emptyBundle);
        verify(observer1, times(1)).onStop();
        verify(observer1, times(1)).onDestroy();
        verify(observer1, times(1)).onLowMemory();
        verify(observer2, times(1)).onCreate(emptyBundle);
        verify(observer2, times(1)).onStart();
        verify(observer2, times(1)).onResume();
        verify(observer2, times(1)).onPause();
        verify(observer2, times(1)).onSaveInstanceState(emptyBundle);
        verify(observer2, times(1)).onStop();
        verify(observer2, times(1)).onDestroy();
        verify(observer2, times(1)).onLowMemory();
    }

    @Test
    public void addLifecycleObserverDoesNotAddTwice() {
        CompositeLifecycleObserver observers = new CompositeLifecycleObserver();
        new PresenterLifecycleObserver<>(null, null);
        PresenterLifecycleObserver lifecycleObserver = new PresenterLifecycleObserver<>(null, null);
        PresenterLifecycleObserver lifecycleObserver2 = new PresenterLifecycleObserver<>(null, null);
        observers.addLifecycleObserver(lifecycleObserver);
        observers.addLifecycleObserver(lifecycleObserver);

        assertEquals(1, observers.observers.size());

        observers.observers.clear();
        observers.addLifecycleObserver(lifecycleObserver);
        observers.addLifecycleObserver(lifecycleObserver2);

        assertEquals(2, observers.observers.size());
    }

    @Test
    public void testIfMethodsCalledInCorrectOrder() {
        CompositeLifecycleObserver observers = new CompositeLifecycleObserver();
        LifecycleObserver observer1 = Mockito.mock(LifecycleObserver.class);
        LifecycleObserver observer2 = Mockito.mock(LifecycleObserver.class);
        observers.addLifecycleObserver(observer1);
        observers.addLifecycleObserver(observer2);

        observers.onCreate(emptyBundle);
        observers.onStart();
        observers.onResume();
        observers.onPause();
        observers.onSaveInstanceState(emptyBundle);
        observers.onStop();
        observers.onDestroy();
        observers.onLowMemory();

        InOrder inOrder = inOrder(observer1, observer2);

        inOrder.verify(observer1).onCreate(emptyBundle);
        inOrder.verify(observer2).onCreate(emptyBundle);
        inOrder.verify(observer1).onStart();
        inOrder.verify(observer2).onStart();
        inOrder.verify(observer1).onResume();
        inOrder.verify(observer2).onResume();

        inOrder.verify(observer2).onPause();
        inOrder.verify(observer1).onPause();
        inOrder.verify(observer2).onSaveInstanceState(emptyBundle);
        inOrder.verify(observer1).onSaveInstanceState(emptyBundle);
        inOrder.verify(observer2).onStop();
        inOrder.verify(observer1).onStop();
        inOrder.verify(observer2).onDestroy();
        inOrder.verify(observer1).onDestroy();
        inOrder.verify(observer1).onLowMemory();
        inOrder.verify(observer2).onLowMemory();
    }

}
