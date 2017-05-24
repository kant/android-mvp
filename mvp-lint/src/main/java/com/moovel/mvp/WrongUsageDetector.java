package com.moovel.mvp;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiReferenceExpression;

import java.util.Arrays;
import java.util.List;


public class WrongUsageDetector extends Detector implements Detector.JavaPsiScanner {


    static final Issue ISSUE_VIEW_USAGE_IN_CREATE = Issue.create(
            "UnboundViewUsage",
            "You try using a view which cannot be bound there.",
            "Views are bound in the onStart method and unbound in the onStop Method. To fix this issue...",
            Category.MESSAGES,
            10,
            Severity.FATAL,
            new Implementation(WrongUsageDetector.class, Scope.JAVA_FILE_SCOPE)

    );

    public WrongUsageDetector() {
    }

    public static Issue[] getIssues() {
        return new Issue[]{
                ISSUE_VIEW_USAGE_IN_CREATE
        };
    }

    static PsiMethod findMethodCall(PsiElement element) {
        if (element == null) {
            return null;
        } else if (element instanceof PsiMethod) {
            return (PsiMethod) element;
        } else {
            return findMethodCall(element.getParent());
        }
    }

    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("onCreate");
    }

    @Override
    public void visitMethod(JavaContext context, JavaElementVisitor visitor, PsiMethodCallExpression call, PsiMethod method) {
        PsiReferenceExpression methodExpression = call.getMethodExpression();
        String methodName = methodExpression.getQualifiedName();
        if ("com.moovel.mvp.MVPPresenter.getViewOrThrow".equals(methodName)) {
            context.report(ISSUE_VIEW_USAGE_IN_CREATE, call, context.getLocation(call), "Test message");
            return;
        }
    }
}