package com.moovel.mvp

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.getContainingMethod

val ISSUE_VIEW_USAGE_IN_CREATE = Issue.create("UnboundViewUsage",
    "Using a view which is not bound at this point",
    "Views are bound in the onStart method and unbound in the onStop Method. Usage at this location will cause" + "the method to throw an ViewNotAttachedException.",
    Category.MESSAGES, 8, Severity.ERROR,
    Implementation(WrongUsageDetector::class.java, Scope.JAVA_FILE_SCOPE))

private val unboundAreas = listOf(
    "onAttach",
    "onCreate",
    "onCreateView",
    "onActivityCreated",
    "onViewCreated",
    "onDestroy",
    "onDestroyView"
)

class WrongUsageDetector : Detector(), Detector.UastScanner {
  override fun getApplicableMethodNames() = listOf("getView")

  override fun visitMethod(context: JavaContext, node: UCallExpression, method: PsiMethod) {
    val isGetViewMethod = "getView" == node.methodName
    val isInPresenter = context.evaluator.isMemberInClass(method, "com.moovel.mvp.MVPPresenter")

    if (isGetViewMethod && isInPresenter) {
      val parentMethod = node.getContainingMethod()

      if (unboundAreas.any { it == parentMethod?.name }) {
        context.report(ISSUE_VIEW_USAGE_IN_CREATE, node, context.getLocation(node), "Pointless call to getView")
      }
    }
  }
}
