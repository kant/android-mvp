# MVP Framework for Android Applications

This framework is an Android-Framework to get Dependency Injection and MVP working smoothly together. It's mainly for
dagger2 but you can use it with other DI libraries too, it's not depending on any.

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
If you cannot do this for some reason let your application class implement DependencyGraphProvider.

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
    public AwesomePresenter() {
    }
}
```

### 3. Create your screens
Extend your Fragment from MVPFragment or your Activity from MVPActivity. These are
generic classes using 3 generic types. The first one is the view interface it's supposed to implement.
The second one is the presenter that's provided by this screen, the third one is the Class of the DependencyGraph (Component)
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

LifecycleInterceptors are Interfaces that will be called during following Lifecycle-Events:

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
Sometimes you want to do a particular action, when a lifecycle event is happening. We needed this in the first
place to unsubscribe from rxjava Observables

This is why we introduced the LifecycleEventScheduler. It is an implementation of a LifecycleInterceptor
and can call an action whenever a lifecycle event is happening.

Example:
```
private static final LifecycleEventScheduler<Subscription> CLEANUP =
        // define an action that is happening, when an event was triggered
        new LifecycleEventScheduler<>((event, item) -> item.unsubscribe());

@Override
public void onCreate() {
    super.onCreate();
    // enqueue the action to trigger onStop
    CLEANUP.enqueue(STOP, getSomeObservable
            .subscribe(item -> {/* do something */}));
}
```

Example Code using mvpbase can be found [here](https://github.com/moovel/android-mvp/blob/master/app/src/main/java/com/moovel/mvpbase/demo/screens/main/MainActivity.java)