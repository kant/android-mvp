package com.moovel.mvp;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;

import java.util.Arrays;
import java.util.List;


public class WrongUsageDetector extends Detector implements Detector.JavaPsiScanner {


    static final Issue ISSUE_VIEW_USAGE_IN_CREATE = Issue.create(
            "UnboundViewUsage",
            "You try using a view when it's not bound!",
            "Views are bound in the onStart method and unbound in the onStop Method. To fix this issue...",
            Category.MESSAGES,
            10,
            Severity.FATAL,
            new Implementation(WrongUsageDetector.class, Scope.CLASS_FILE_SCOPE)

    );

    @Override
    public List<String> getApplicableMethodNames() {
//        return Arrays.asList("tag", "format", "v", "d", "i", "w", "e", "wtf");
        return Arrays.asList("getViewOrThrow");
    }

    @Override
    public void visitMethod(JavaContext context, JavaElementVisitor visitor, PsiMethodCallExpression call, PsiMethod method) {
        System.out.println("call this method");
        context.report(ISSUE_VIEW_USAGE_IN_CREATE, method, context.getLocation(method), "Test message");
    }
}