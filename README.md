# MVP Framework for Android Applications

This framework is our Android-Framework for MVP.

Dependencies:
Android Support-Library v22.2.0

# How to use it

Add the library to your dependencies:
```
compile 'com.moovel.mvp:mvp:0.4.0'
```



### 1. Create a View
```
public interface AwesomeView extends MVPView {
    ...
}
```

### 2. Create a Presenter
```
public class AwesomePresenter extends MVPPresenter<AwesomeView> {
    public AwesomePresenter() {
    }
}
```

### 3. Create your screens
Extend your Fragments from MVPFragment or your Activities from MVPActivity. These are
generic classes using 2 generic types. The first one is the view interface, the second one is 
the type of presenter which you want to use

```
public class AwesomeActivity extends MVPActivity<AwesomeView, AwesomePresenter> 
    implements AwesomeView {

    AwesomePresenter presenter = new AwesomePresenter();
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    protected AwesomePresenter getPresenter() {
        return presenter;
    }
```

Note: the Method "getPresenter" is used during the "onCreate" process, so make sure the presenter exists before.


# Lifecycle
* The view gets attached to the presenter in the "onStart" method and gets unbind "onStop".
* To access the view from the presenter call "getView". This method either returns the view or throws an ViewNotAttachedException
* Calling "getView" in "onCreate" or "onDestroy" will cause Lint to fail.
# Lifecycle Observers

LifecycleObservers are Interfaces that have callbacks on following Lifecycle-Events:

* onCreate
* onStart
* onResume
* onPause
* onStop
* onDestroy
* onLowMemory
* onSaveInstanceState

Within the library you have the chance to add LifecycleObserver to:
 * MVPPresenter (add/removeLifecycleObserver)
 * MVPActivity (add/removeLifecycleObserver)
 * MVPFragment (add/removeLifecycleObserver)

# Lifecycle Event Scheduler
Sometimes you want to execute an action, when a lifecycle event happens i.e. you want to cancel http requests when
the activity is stops or you want to unsubscribe from an rxjava subscription/disposable when the
activity is pausing.

This is why we introduced the LifecycleEventScheduler. It is an implementation of a LifecycleObserver which you can 
add manually to your MVPFragment/MVPActivity/MVPPresenter using the methods mentioned in the
LifecycleObservers - Section.

Here's an example of executing a runnable on stop
```
private final LifecycleEventScheduler<Runnable> lifecycleScheduler
        = new LifecycleEventScheduler<>((event, item, state) -> item.run());

@Override
public void onCreate() {
    super.onCreate();
    // enqueue the action to trigger onStop, in this case it unsubscribes from
    // a possible active subscription
    lifecycleScheduler.enqueue(STOP, () -> Log.e(TAG, "onStop was called"));
    lifecycleScheduler.enqueue(RESUME, () -> Log.e(TAG, "onResume was called"));
}
```
Note that the action will be called ONCE for all events you register an action for. On registering an action
you have to pass the [event name](mvp/src/main/java/com/moovel/mvp/lifecycle/LifecycleEvent.java) and an item you want to do something with.


# Dagger 2 Extension
To simplyfy our lifes, when using dagger 2, we wrote an implementation, that simplifies presenter injections.
[Dagger Extension](mvp-dagger/)
## LICENSE
```
Copyrights 2017 moovel Group GmbH

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

<http://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```