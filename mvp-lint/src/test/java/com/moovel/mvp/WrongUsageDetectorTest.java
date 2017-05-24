package com.moovel.mvp;

import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;

import org.intellij.lang.annotations.Language;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class WrongUsageDetectorTest extends LintDetectorTest {
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
        String source = ""
                + "package foo;\n"
                + "import android.util.Log;\n"
                + "public class Example {\n"
                + "  public void log() {\n"
                + "    getViewOrThrow();"
                + "    Log.d(\"TAG\", \"msg\");\n"
                + "  }\n"
                + "}";
        assertThat(lintProject(java(source))).isEqualTo("src/foo/Example.java:5: "
                + "Warning: Using 'Log' instead of 'Timber' [LogNotTimber]\n"
                + "    Log.d(\"TAG\", \"msg\");\n"
                + "    ~~~~~\n"
                + "0 errors, 1 warnings\n");
    }
}

