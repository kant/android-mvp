# MVP Framework for Android Applications

This framework is an Android-Framework to get Dependency Injection and MVP working smoothly together. It's mainly for
dagger2 but you can use it with other DI libraries too, it's not depending on any.

Dependencies:
Android Support-Library v22.2.0

## MVPBase


# How to use it
DI Frameworks provide a Dependency Graph. In Dagger2 this is called "Component". This is why in all our examples we're
using this terminology.

### 1. Provide a DependencyGraph and a DependencyGraphProvider

```
public class MoovelApplication extends Application implements DependencyGraphProvider<MoovelComponent> {
    private MoovelComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        // this is quite dagger 2 specific. Can be some other DI Framework too
        component = DaggerMoovelComponent.builder().moovelModule(new MoovelModule()).build();
    }

    @Override
    public MoovelComponent getDependencyGraph() {
        return component;
    }
}
```

In this piece of code you see a DependencyGraphProvider (MoovelApplication), that is able to provide a dependencyGraph
(MoovelComponent)

### 2. Create a BaseActivity and/or a BaseFragment class in your project.
This class should extend the (Rx)MVPBaseActivity/(Rx)MVPBaseFragment define what dependency graph is provided and which
class provides it.

```
public abstract class MoovelBaseActivity<V extends MVPBase.View, P extends BasePresenter<V>>
        extends MVPActivity<V, P, MoovelComponent, MoovelApplication> {

    @Override
    public MoovelApplication getDependencyGraphProvider() {
        return (MoovelApplication) getApplication();
    }
}
```

Here we created the MoovelBaseActivity, extending MVPBaseActivity and specified which DependencyGraph to
use (MoovelComponent) and which class is providing it (MoovelApplication). **View and Presenter stay unspecified!**

Additionally we tell the Base class how to get the DependencyGraphProvider.

### 3. Straight forward MVP coding

#### Create your View extending MVPBase.View
```
public interface MainView extends MVPBase.View {
    ... // your view methods
}
```
#### Create your presenter extending (Rx)BasePresenter<view>
```
public class MainPresenter extends BasePresenter<MainView> {
    ... // your presenter methods
}
```
#### Implement your View
In your actual implementation you must provide the view and the presenter as generic types.
And your ViewClass **must implement the View! If not an exception will be thrown!**
```
public class MainActivity extends MoovelBaseActivity<MainView, MainPresenter> implements MainView {

    @Inject
    MainPresenter presenter;

    @Override
    public MainPresenter inject(MoovelComponent dependencyGraph) {
        dependencyGraph.inject(this);
        return presenter;
    }

    ...
}
```
The method "inject" must be implemented if you followed this howto correctly. It will provide you the required
dependency graph with which you can inject your dependencies.
In this example you can see this quite nice. After injecting MainActivity using MoovelComponent the presenter is available


# Lifecycle Interceptors

LifecycleInterceptors are Interfaces that will be called during following Lifecycle-Events:

* onCreate
* onStart
* onResume
* onPause
* onStop
* onDestroy

Within the library you have the chance to add LifecycleInterceptors to:
 * BasePresenter (add/removeLifecycleInterceptor)
 * MVPActivity (add/removeLifecycleInterceptor)
 * MVPFragment (add/removeLifecycleInterceptor)

I highly recommend using the Defaultconstructor to register them.

We are using them for autounsubscribing from rxjava Subscriptions or as they are called in rxjava2 Disposables.

An implementation could look like this:

```
public final class RxLifecycleInterceptor implements LifecycleAdapter {

    private static final int PAUSE = 0;
    private static final int STOP = 1;
    private static final int DESTROY = 2;

    private final SparseArray<CompositeSubscription> subscriptionMap = new SparseArray<>(3);

    /**
     * auto unsubscribes from subscription on "onStop()"
     *
     * @param subscription to unsubscribe from
     */
    protected void untilOnStop(Subscription subscription) {
        addSubscription(STOP, subscription);
    }

    /**
     * auto unsubscribes from subscription on "onDestroy()"
     *
     * @param subscription to unsubscribe from
     */
    protected void untilOnDestroy(Subscription subscription) {
        addSubscription(DESTROY, subscription);
    }

    /**
     * auto unsubscribes from subscription on "onPause()"
     *
     * @param subscription to unsubscribe from
     */
    protected void untilOnPause(Subscription subscription) {
        addSubscription(PAUSE, subscription);
    }


    @Override
    public void doOnStop() {
        clearSubscription(STOP);
    }

    @Override
    public void doOnDestroy() {
        clearSubscription(DESTROY);
    }

    @Override
    public void doOnPause() {
        clearSubscription(PAUSE);
    }

    private void addSubscription(int idx, Subscription subscription) {
        CompositeSubscription idxSubsription = subscriptionMap.get(idx);
        if (idxSubsription == null) {
            idxSubsription = new CompositeSubscription();
            subscriptionMap.put(idx, idxSubsription);
        }
        idxSubsription.add(subscription);
    }

    private void clearSubscription(int idx) {
        CompositeSubscription idxSubsription = subscriptionMap.get(idx);
        if (idxSubsription == null) return;
        idxSubsription.clear();

    }
}

```


Example Code using mvpbase can be found [here](https://github.com/moovel/android-mvp/blob/master/app/src/main/java/com/moovel/mvpbase/demo/screens/main/MainActivity.java)