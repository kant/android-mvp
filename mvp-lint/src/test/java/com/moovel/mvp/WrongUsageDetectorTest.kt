package com.moovel.mvp

import com.android.tools.lint.checks.infrastructure.TestFiles.java
import com.android.tools.lint.checks.infrastructure.TestFiles.kt
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import org.junit.Test

class WrongUsageDetectorTest {
  private val presenterStub = java("""
      package com.moovel.mvp;

      public class MVPPresenter {
        protected void onCreate() {}
        protected void onStart() {}
        protected void onStop() {}
        protected void onPause() {}
        protected void onResume() {}
        protected void onDestroy() {}
        protected Object getView() { return null; }

      }""").indented()

  @Test fun onNoGetViewMethodCall() {
    lint()
        .files(presenterStub, java("""
            package com.moovel.mvp;
            public class Presenter extends MVPPresenter {
                protected void onCreate() {}
                protected void onStart() {}
                protected void onResume() {}
                protected void onPause() {}
                protected void onStop() {}
                protected void onDestroy() {}
            }""").indented())
        .issues(ISSUE_VIEW_USAGE_IN_CREATE)
        .run()
        .expectClean()
  }

  @Test fun errorOnCreateViewMethod() {
    lint()
        .files(presenterStub, java("""
            package com.moovel.mvp;
            public class Presenter extends MVPPresenter {
                protected void onCreateView() {
                    getView();
                }
            }""").indented())
        .issues(ISSUE_VIEW_USAGE_IN_CREATE)
        .run()
        .expect("""
            |src/com/moovel/mvp/Presenter.java:4: Error: Pointless call to getView [UnboundViewUsage]
            |        getView();
            |        ~~~~~~~~~
            |1 errors, 0 warnings""".trimMargin())
  }

  @Test fun errorOnActivityCreatedMethod() {
    lint()
        .files(presenterStub, java("""
            package com.moovel.mvp;
            public class Presenter extends MVPPresenter {
                protected void onActivityCreated() {
                    getView();
                }
            }""").indented())
        .issues(ISSUE_VIEW_USAGE_IN_CREATE)
        .run()
        .expect("""
            |src/com/moovel/mvp/Presenter.java:4: Error: Pointless call to getView [UnboundViewUsage]
            |        getView();
            |        ~~~~~~~~~
            |1 errors, 0 warnings""".trimMargin())
  }

  @Test fun errorOnAttachMethod() {
    lint()
        .files(presenterStub, java("""
            package com.moovel.mvp;
            public class Presenter extends MVPPresenter {
                protected void onAttach() {
                    getView();
                }
            }""").indented())
        .issues(ISSUE_VIEW_USAGE_IN_CREATE)
        .run()
        .expect("""
            |src/com/moovel/mvp/Presenter.java:4: Error: Pointless call to getView [UnboundViewUsage]
            |        getView();
            |        ~~~~~~~~~
            |1 errors, 0 warnings""".trimMargin())
  }

  @Test fun errorOnViewCreatedMethod() {
    lint()
        .files(presenterStub, java("""
            package com.moovel.mvp;
            public class Presenter extends MVPPresenter {
                protected void onViewCreated() {
                    getView();
                }
            }""").indented())
        .issues(ISSUE_VIEW_USAGE_IN_CREATE)
        .run()
        .expect("""
            |src/com/moovel/mvp/Presenter.java:4: Error: Pointless call to getView [UnboundViewUsage]
            |        getView();
            |        ~~~~~~~~~
            |1 errors, 0 warnings""".trimMargin())
  }

  @Test fun errorOnCreateMethod() {
    lint()
        .files(presenterStub, java("""
            package com.moovel.mvp;
            public class Presenter extends MVPPresenter {
                protected void onCreate() {
                    getView();
                }
            }""").indented())
        .issues(ISSUE_VIEW_USAGE_IN_CREATE)
        .run()
        .expect("""
            |src/com/moovel/mvp/Presenter.java:4: Error: Pointless call to getView [UnboundViewUsage]
            |        getView();
            |        ~~~~~~~~~
            |1 errors, 0 warnings""".trimMargin())
  }

  @Test fun errorOnDestroyMethod() {
    lint()
        .files(presenterStub, java("""
            package com.moovel.mvp;
            public class Presenter extends MVPPresenter {
                protected void onDestroy() {
                    getView();
                }
            }""").indented())
        .issues(ISSUE_VIEW_USAGE_IN_CREATE)
        .run()
        .expect("""
            |src/com/moovel/mvp/Presenter.java:4: Error: Pointless call to getView [UnboundViewUsage]
            |        getView();
            |        ~~~~~~~~~
            |1 errors, 0 warnings""".trimMargin())
  }

  @Test fun errorOnStartMethod() {
    lint()
        .files(presenterStub, java("""
            package com.moovel.mvp;
            public class Presenter extends MVPPresenter {
                protected void onStart() {
                    getView();
                }
            }""").indented())
        .issues(ISSUE_VIEW_USAGE_IN_CREATE)
        .run()
        .expectClean()
  }

  @Test fun errorOnStopMethod() {
    lint()
        .files(presenterStub, java("""
            package com.moovel.mvp;
            public class Presenter extends MVPPresenter {
                protected void onStop() {
                    getView();
                }
            }""").indented())
        .issues(ISSUE_VIEW_USAGE_IN_CREATE)
        .run()
        .expectClean()
  }

  @Test fun errorOnDestroyViewMethod() {
    lint()
        .files(presenterStub, kt("""
            package com.moovel.mvp
            class Presenter : MVPPresenter() {
                fun onDestroyView() {
                    view
                }
            }""").indented())
        .issues(ISSUE_VIEW_USAGE_IN_CREATE)
        .run()
        .expect("""
            |src/com/moovel/mvp/Presenter.kt:4: Error: Pointless call to getView [UnboundViewUsage]
            |        view
            |        ~~~~
            |1 errors, 0 warnings""".trimMargin())
  }

  @Test fun errorOnPauseMethod() {
    lint()
        .files(presenterStub, java("""
            package com.moovel.mvp;
            public class Presenter extends MVPPresenter {
                protected void onPause() {
                    getView();
                }
            }""").indented())
        .issues(ISSUE_VIEW_USAGE_IN_CREATE)
        .run()
        .expectClean()
  }

  @Test fun callingGetViewInOnAttachWithNoMvpPresenter() {
    lint()
        .files(presenterStub, java("""
            package com.moovel.mvp;
            public class Presenter {
                protected void onAttach() {
                    getView();
                }
            }""").indented())
        .issues(ISSUE_VIEW_USAGE_IN_CREATE)
        .run()
        .expectClean()
  }
}
