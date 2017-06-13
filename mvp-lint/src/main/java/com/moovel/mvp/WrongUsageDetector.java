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
            "Using a view which is not bound at this point",
            "Views are bound in the onStart method and unbound in the onStop Method. Usage at this location will cause" +
                    "the method to throw an ViewNotAttachedException.",
            Category.MESSAGES,
            8,
            Severity.ERROR,
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

    static boolean wasCalledInUnboundAreas(PsiElement element) {
        if (element == null) return false;
        if (element instanceof PsiMethod) {
            PsiMethod method = (PsiMethod) element;
            if ("onCreate".equals(method.getName()) || "onDestroy".equals(method.getName())) {
                return true;
            }
        }
        return wasCalledInUnboundAreas(findMethodCall(element.getParent()));
    }

    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("getView");
    }

    @Override
    public void visitMethod(JavaContext context, JavaElementVisitor visitor,
                            PsiMethodCallExpression call, PsiMethod method) {
        PsiReferenceExpression methodExpression = call.getMethodExpression();
        String methodName = methodExpression.getQualifiedName();
        // check if subclass of MVPPresenter
        PsiClass containingClass = method.getContainingClass();
        if (containingClass != null && "com.moovel.mvp.MVPPresenter".equals(containingClass.getQualifiedName())) {
            // check if any parent method is onCreate
            if (wasCalledInUnboundAreas(call) && "getViewOrThrow".equals(methodName)) {
                context.report(ISSUE_VIEW_USAGE_IN_CREATE, call, context.getLocation(call),
                        "Pointless call to getViewOrThrow");
            }
        }
    }
}