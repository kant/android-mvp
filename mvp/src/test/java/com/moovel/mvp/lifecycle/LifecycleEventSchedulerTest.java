package com.moovel.mvp.lifecycle;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LifecycleEventSchedulerTest {

    @Test
    @SuppressWarnings("unchecked")
    public void testConstructor() {

        final LifecycleEventScheduler.LifecycleEventListener<Object> eventListener =
                Mockito.mock(LifecycleEventScheduler.LifecycleEventListener.class);
        LifecycleEventScheduler scheduler = new LifecycleEventScheduler<>(eventListener);

        assertNotNull(scheduler.container);
        assertNotNull(scheduler.eventListener);
        assertEquals(eventListener, scheduler.eventListener);
    }

//    @Test
    @SuppressWarnings("unchecked")
    public void testEnqueuingItems() {
        Runnable mock = Mockito.mock(Runnable.class);
        final LifecycleEventScheduler.LifecycleEventListener<Runnable> eventListener =
                Mockito.mock(LifecycleEventScheduler.LifecycleEventListener.class);
        LifecycleEventScheduler scheduler = new LifecycleEventScheduler<>(eventListener);

        scheduler.enqueue(LifecycleEvent.CREATE, mock);
        scheduler.enqueue(LifecycleEvent.START, mock);
        scheduler.enqueue(LifecycleEvent.RESUME, mock);
        scheduler.enqueue(LifecycleEvent.PAUSE, mock);
        scheduler.enqueue(LifecycleEvent.STOP, mock);
        scheduler.enqueue(LifecycleEvent.DESTROY, mock);

        assertEquals(6, scheduler.container.size());
    }
}
