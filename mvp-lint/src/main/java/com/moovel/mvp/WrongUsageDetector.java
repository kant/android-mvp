package com.moovel.mvp;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiClass;
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
            "Views are bound in the onStart method and unbound in the onStop Method. Usage at this location will cause" +
                    "the method to throw an ViewNotAttachedException.",
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
        if (element == null) return null;
        if (element instanceof PsiMethod) {
            return (PsiMethod) element;
        } else {
            return findMethodCall(element.getParent());
        }
    }

    static boolean wasCalledInUnboundAreas(PsiElement method) {
        if (method == null) return false;
        if (method instanceof PsiMethod && "onCreate".equals(((PsiMethod) method).getName())) return true;
        if (method instanceof PsiMethod && "onDestroy".equals(((PsiMethod) method).getName())) return true;
        return wasCalledInUnboundAreas(findMethodCall(method.getParent()));
    }

    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("getViewOrThrow");
    }

    @Override
    public void visitMethod(JavaContext context, JavaElementVisitor visitor, PsiMethodCallExpression call, PsiMethod method) {
        PsiReferenceExpression methodExpression = call.getMethodExpression();
        String methodName = methodExpression.getQualifiedName();
        // check if subclass of MVPPresenter
        PsiClass containingClass = method.getContainingClass();
        if (containingClass != null && "com.moovel.mvp.MVPPresenter".equals(containingClass.getQualifiedName())) {
            // check if any parent method is onCreate
            if (wasCalledInUnboundAreas(call)) {
                if ("com.moovel.mvp.MVPPresenter.getViewOrThrow".equals(methodName)) {
                    context.report(ISSUE_VIEW_USAGE_IN_CREATE, call, context.getLocation(call), "Test message");
                    return;
                }
            }

        }
    }
}