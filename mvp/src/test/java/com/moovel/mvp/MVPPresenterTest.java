package com.moovel.mvp;

import com.moovel.mvp.dummy.DummyTestMVPActivity;
import com.moovel.mvp.dummy.DummyTestPresenter;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class MVPPresenterTest {

    private MVPPresenter subject;
    private MVPView dummyView = new DummyTestMVPActivity();

    @Before
    public void setUp() throws Exception {
        subject = new DummyTestPresenter();
    }

    @Test
    public void attachViewAttachesTheView() throws Exception {
        subject.attachView(dummyView);
        assertEquals(subject.getView(), dummyView);
    }

    @Test
    public void detachViewDetachesTheView() throws Exception {
        subject.attachView(dummyView);
        subject.detachView();
        try {
            subject.getView();
        } catch (ViewNotAttachedException e) {
            assertTrue(true);
        }
    }
}
