package com.moovel.mvp;

import com.moovel.mvp.dummy.DummyTestApplication;
import com.moovel.mvp.dummy.DummyTestMVPActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, application = DummyTestApplication.class)
public class MVPActivityTest {

    private MVPActivity subject;

    @Before
    public void setUp() throws Exception {
        subject = Robolectric.buildActivity(DummyTestMVPActivity.class).create().get();
    }

    @Test
    public void normalLifecycleHappyCase() {
        //in create state, view is detached
        assertViewIsDetached();

        subject.onStart();
        assertViewIsAttached();

        subject.onPause();
        assertViewIsAttached();

        subject.onStop();
        assertViewIsDetached();

        subject.onStart();
        subject.onResume();
        assertViewIsAttached();

        subject.onStop();
        subject.onDestroy();
        assertViewIsDetached();
    }

    private void assertViewIsAttached() {
        try {
            assertNotNull(subject.getPresenter().getView());
        } catch (ViewNotAttachedException e) {
            fail();
        }
    }

    private void assertViewIsDetached() {
        try {
            assertNull(subject.getPresenter().getView());
        } catch (ViewNotAttachedException e) {
            assertTrue(true);
        }
    }
}
