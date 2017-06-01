package com.moovel.mvp;

import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;

import org.intellij.lang.annotations.Language;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings({"ClassNameDiffersFromFileName", "JUnit4AnnotatedMethodInJUnit3TestCase"})
public class WrongUsageDetectorTest extends LintDetectorTest {


    private static final String NO_WARNING = "No warnings.";

    @Language("JAVA")
    String presenterStubString = ""
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
        return Arrays.asList(WrongUsageDetector.getIssues());
    }


    @Test
    public void testOnNoGetViewMethodCall() throws Exception {
        @Language("JAVA") String source = ""
                + "package com.moovel.mvp;\n" +
                "public class Presenter extends MVPPresenter {\n" +
                "    protected void onCreate() {}\n" +
                "    protected void onStart() {}\n" +
                "    protected void onResume() {}\n" +
                "    protected void onPause() {}\n" +
                "    protected void onStop() {}\n" +
                "    protected void onDestroy() {}\n" +
                "}";
        assertEquals(NO_WARNING, lintFiles(java(source), presenterStub));
    }

    @Test
    public void testErrorOnCreateMethod() throws Exception {
        @Language("JAVA") String source = ""
                + "package com.moovel.mvp;\n" +
                "public class Presenter extends MVPPresenter {\n" +
                "    protected void onCreate() {\n" +
                "        getViewOrThrow();\n" +
                "    }\n" +
                "}";
        assertEquals("src/com/moovel/mvp/Presenter.java:4: Error: Pointless call to getViewOrThrow [UnboundViewUsage]\n" +
                "        getViewOrThrow();\n" +
                "        ~~~~~~~~~~~~~~~~\n" +
                "1 errors, 0 warnings\n", lintFiles(java(source), presenterStub));
    }

    @Test
    public void testErrorOnDestroyMethod() throws Exception {
        @Language("JAVA") String source = ""
                + "package com.moovel.mvp;\n" +
                "public class Presenter extends MVPPresenter {\n" +
                "    protected void onDestroy() {\n" +
                "        getViewOrThrow();\n" +
                "    }\n" +
                "}";
        assertEquals("src/com/moovel/mvp/Presenter.java:4: Error: Pointless call to getViewOrThrow [UnboundViewUsage]\n" +
                "        getViewOrThrow();\n" +
                "        ~~~~~~~~~~~~~~~~\n" +
                "1 errors, 0 warnings\n", lintFiles(java(source), presenterStub));
    }

    @Test
    public void testErrorOnStartMethod() throws Exception {
        @Language("JAVA") String source = ""
                + "package com.moovel.mvp;\n" +
                "public class Presenter extends MVPPresenter {\n" +
                "    protected void onStart() {\n" +
                "        getViewOrThrow();\n" +
                "    }\n" +
                "}";
        assertEquals(NO_WARNING, lintFiles(java(source), presenterStub));
    }

    @Test
    public void testErrorOnStopMethod() throws Exception {
        @Language("JAVA") String source = ""
                + "package com.moovel.mvp;\n" +
                "public class Presenter extends MVPPresenter {\n" +
                "    protected void onStop() {\n" +
                "        getViewOrThrow();\n" +
                "    }\n" +
                "}";
        assertEquals(NO_WARNING, lintFiles(java(source), presenterStub));
    }

    @Test
    public void testErrorOnPauseMethod() throws Exception {
        @Language("JAVA") String source = ""
                + "package com.moovel.mvp;\n" +
                "public class Presenter extends MVPPresenter {\n" +
                "    protected void onPause() {\n" +
                "        getViewOrThrow();\n" +
                "    }\n" +
                "}";
        assertEquals(NO_WARNING, lintFiles(java(source), presenterStub));
    }

    @Test
    public void testErrorOnResumeMethod() throws Exception {
        @Language("JAVA") String source = ""
                + "package com.moovel.mvp;\n" +
                "public class Presenter extends MVPPresenter {\n" +
                "    protected void onResume() {\n" +
                "        getViewOrThrow();\n" +
                "    }\n" +
                "}";
        assertEquals(NO_WARNING, lintFiles(java(source), presenterStub));
    }
}

