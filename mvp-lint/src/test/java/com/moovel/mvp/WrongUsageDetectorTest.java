package com.moovel.mvp;

import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;

import org.intellij.lang.annotations.Language;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class WrongUsageDetectorTest extends LintDetectorTest {

    @Language("JAVA") String presenterStubString = ""
            + "package com.moovel.mvp;\n" +
            "public class MVPPresenter {\n" +
            "    public MVPPresenter() {}\n" +
            "    protected void onCreate() {}\n" +
            "    protected void onStart() {}\n" +
            "    protected void onStop() {}\n" +
            "    protected void onPause() {}\n" +
            "    protected void onResume() {}\n" +
            "    protected void onDestroy() {}\n" +
            "    protected Object getViewOrThrow() {return null;}\n"
            + "}";

    private final TestFile presenterStub = java(presenterStubString);

    @Override
    protected boolean allowCompilationErrors() {
        return true;
    }

    @Override
    protected Detector getDetector() {
        return new WrongUsageDetector();
    }

    @Override
    protected List<Issue> getIssues() {
        return Arrays.asList(WrongUsageDetector.ISSUE_VIEW_USAGE_IN_CREATE);
    }

    @Test
    public void testSmth() throws Exception {
        @Language("JAVA") String source = ""
                + "package com.moovel.mvp;\n" +
                "public class Presenter extends MVPPresenter {\n" +
                "    protected void onCreate() {\n" +
                "        super.onCreate();\n" +
                "        getViewOrThrow();\n" +
                "    }\n" +
                "}";
        assertEquals("src/com/moovel/mvp/Presenter.java:5: Error: Test message [UnboundViewUsage]\n" +
                "        getViewOrThrow();\n" +
                "        ~~~~~~~~~~~~~~~~\n" +
                "1 errors, 0 warnings\n", lintFiles(java(source), presenterStub));
    }
}

