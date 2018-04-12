package com.moovel.mvp

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API

class MvpIssueRegistry : IssueRegistry() {
  override val api = CURRENT_API

  override val issues get() = listOf(ISSUE_VIEW_USAGE_IN_CREATE)
}
