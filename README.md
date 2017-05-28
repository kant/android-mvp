# MVP Framework for Android Applications

This framework is an Android-Framework for MVP. The Demo shows how to use it in combination with dependency

Dependencies:
Android Support-Library v22.2.0


Then add the library to your dependencies:
```
compile 'com.moovel.mvp:mvp:0.1.0-SNAPSHOT'
```

# How to use it
DI Frameworks usually provide a Dependency Graph. In Dagger2 this is called "Component". This is why in all our examples
we're using this terminology.

### 1. Provide a DependencyGraphProvider

Let your Application-class extend MVPApplication would be the easiest way of providing a DependencyGraphProvider.
If you cannot do this for some reason let your Application class implement the interface DependencyGraphProvider.

```
    /**
     * registers a component for injection
     */
    public <T> void registerComponent(Class<T> componentClass, T component);

    /**
     * @param componentClass to receive
     * @throws IllegalStateException when the component is not registered
     */
    public <T> T getComponent(Class<T> componentClass);
```


### 2. Create a View and a Presenter for each screen you have
Create a basic view interface
```
public interface AwesomeView extends MVPView {
    ...
}
```

Create the Presenter, it's recommended to inject everything you need in the constructor of the presenter already,
so don't forget the @Inject Annotation if you need it.
```
public class AwesomePresenter extends MVPPresenter<AwesomeView> {
    @Inject
    public AwesomePresenter(ContructorInjectedDependency dependency) {
    }
}
```

### 3. Create your screens
Extend your Fragment from MVPFragment or your Activity from MVPActivity. These are
generic classes using 3 generic types. The first one is the view interface it's supposed to implement.
The second one is the presenter that's provided by the screen and the third one is the Class of the DependencyGraph (Component)
required.
Here an example for an activity:


```
public class AwesomeActivity extends MVPActivity<
    AwesomeView,
    AwesomePresenter,
    AwesomeComponent> implements AwesomeView {

    @Inject
    AwesomePresenter presenter;

    ...

    @Override
    protected AwesomePresenter inject(AwesomeComponent component) {
        component.inject(this);
        return presenter;
    }

    @Override
    protected Class<AwesomeComponent> getComponentClass() {
        return AwesomeComponent.class;
    }
}
```

### 4. Create your DependencyGraph
```
public AwesomeApplication extends MVPApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        registerComponent(AwesomeComponent.class, DaggerAwesomeComponent
                .builder()
                .awesomeModule(new AwesomeModule())
                .build());
    }
}
```

Now you should be able to start your app and use a straight forward MVP pattern.

# Lifecycle Interceptors

LifecycleInterceptors are Interfaces that have callbacks on following Lifecycle-Events:

* onCreate
* onStart
* onResume
* onPause
* onStop
* onDestroy

Within the library you have the chance to add LifecycleInterceptors to:
 * MVPPresenter (add/removeLifecycleInterceptor)
 * MVPActivity (add/removeLifecycleInterceptor)
 * MVPFragment (add/removeLifecycleInterceptor)

I recommend using the Defaultconstructor to register them.

We are using them for autounsubscribing from rxjava Subscriptions, or as they are called in rxjava2, Disposables.

# Lifecycle Event Scheduler
Sometimes you want to execute an action, when a lifecycle event happens i.e. you want to cancel http requests when
the activity is stops or you want to unsubscribe from an rxjava subscription/disposable when the
activity is pausing.

This is why we introduced the LifecycleEventScheduler. It is an implementation of a LifecycleInterceptor which you need to
add manually to your MVPFragment/MVPActivity/MVPPresenter using the methods mentioned in the
LifecycleInterceptor - Section.

Here's an example of executing a runnable on stop
```
private final LifecycleEventScheduler<Runnable> lifecycleScheduler
        = new LifecycleEventScheduler<>((event, item) -> item.run());

@Override
public void onCreate() {
    super.onCreate();
    // enqueue the action to trigger onStop, in this case it unsubscribes from
    // a possible active subscription
    lifecycleScheduler.enqueue(STOP, () -> Log.e(TAG, "onStop was called"));
    lifecycleScheduler.enqueue(RESUME, () -> Log.e(TAG, "onResume was called"));
}
```
The action will be called ONCE for all events you register an action for. On registering an action
you have to pass the event itself (predefined integer) and an item you want to do something with.

A sample for auto unsubscribing on rxjava subscriptions you can find in [the demo project](demo/src/main/java/com/moovel/mvp/demo/screens/main/AwesomePresenter.java).

## LICENSE
```
Copyrights 2016 moovel Group GmbH

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