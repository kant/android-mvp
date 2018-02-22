package com.moovel.mvp;

import com.moovel.mvp.dummy.DummyTestPresenter;
import com.moovel.mvp.dummy.DummyTestView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PresenterLifecycleObserverTest {

    private PresenterLifecycleObserver subject;
    @Mock DummyTestPresenter dummyTestPresenter;
    private DummyTestView dummyTestView = new DummyTestView(null);

    @Before
    public void setUp() {
        subject = new PresenterLifecycleObserver(dummyTestView, dummyTestPresenter);
    }

    @Test
    public void testOnStartAttachesView() {
        subject.onStart();
        verify(dummyTestPresenter).attachView(dummyTestView);
    }

    @Test
    public void testOnStopDetachesView() {
        subject.onStop();
        verify(dummyTestPresenter).detachView();
    }
}
