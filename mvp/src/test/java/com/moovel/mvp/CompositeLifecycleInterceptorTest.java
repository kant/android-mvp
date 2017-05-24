package com.moovel.mvp;

import com.moovel.mvp.lifecycle.LifecycleInterceptor;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CompositeLifecycleInterceptorTest {

    @Test
    public void testIfAddingAndRemovingWorksCorrect() {
        CompositeLifecycleInterceptor interceptors = new CompositeLifecycleInterceptor();

        LifecycleInterceptor li1 = Mockito.mock(LifecycleInterceptor.class);
        LifecycleInterceptor li2 = Mockito.mock(LifecycleInterceptor.class);
        LifecycleInterceptor li3 = Mockito.mock(LifecycleInterceptor.class);


        interceptors.addLifecycleInterceptor(li1);
        interceptors.addLifecycleInterceptor(li2);
        interceptors.addLifecycleInterceptor(li3);

        assertEquals(3, interceptors.interceptors.size());

        interceptors.removeLifecycleInterceptor(li1);
        interceptors.removeLifecycleInterceptor(li1);
        interceptors.removeLifecycleInterceptor(li1);

        assertEquals(2, interceptors.interceptors.size());

        interceptors.removeLifecycleInterceptor(li2);
        interceptors.removeLifecycleInterceptor(li3);

        assertEquals(0, interceptors.interceptors.size());
    }

    @Test
    public void testIfMethodsAreCalled() {
        CompositeLifecycleInterceptor interceptors = new CompositeLifecycleInterceptor();
        LifecycleInterceptor li1 = Mockito.mock(LifecycleInterceptor.class);
        LifecycleInterceptor li2 = Mockito.mock(LifecycleInterceptor.class);
        interceptors.addLifecycleInterceptor(li1);
        interceptors.addLifecycleInterceptor(li2);

        interceptors.doOnCreate();
        interceptors.doOnStart();
        interceptors.doOnPause();
        interceptors.doOnResume();
        interceptors.doOnStop();
        interceptors.doOnDestroy();

        verify(li1, times(1)).doOnCreate();
        verify(li1, times(1)).doOnStart();
        verify(li1, times(1)).doOnResume();
        verify(li1, times(1)).doOnPause();
        verify(li1, times(1)).doOnStop();
        verify(li1, times(1)).doOnDestroy();
        verify(li2, times(1)).doOnCreate();
        verify(li2, times(1)).doOnStart();
        verify(li2, times(1)).doOnResume();
        verify(li2, times(1)).doOnPause();
        verify(li2, times(1)).doOnStop();
        verify(li2, times(1)).doOnDestroy();
    }

    @Test
    public void testIfMethodsCalledInCorrectOrder() {
        CompositeLifecycleInterceptor interceptors = new CompositeLifecycleInterceptor();
        LifecycleInterceptor li1 = Mockito.mock(LifecycleInterceptor.class);
        LifecycleInterceptor li2 = Mockito.mock(LifecycleInterceptor.class);
        interceptors.addLifecycleInterceptor(li1);
        interceptors.addLifecycleInterceptor(li2);

        interceptors.doOnCreate();
        interceptors.doOnStart();
        interceptors.doOnResume();
        interceptors.doOnPause();
        interceptors.doOnStop();
        interceptors.doOnDestroy();

        InOrder inOrder = inOrder(li1, li2);

        inOrder.verify(li1).doOnCreate();
        inOrder.verify(li2).doOnCreate();
        inOrder.verify(li1).doOnStart();
        inOrder.verify(li2).doOnStart();
        inOrder.verify(li1).doOnResume();
        inOrder.verify(li2).doOnResume();

        inOrder.verify(li2).doOnPause();
        inOrder.verify(li1).doOnPause();
        inOrder.verify(li2).doOnStop();
        inOrder.verify(li1).doOnStop();
        inOrder.verify(li2).doOnDestroy();
        inOrder.verify(li1).doOnDestroy();
    }

}