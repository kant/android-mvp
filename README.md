# MVP Framework for Android Applications

This framework is an Android-Framework to get Dependency Injection and MVP working smoothly together. It's mainly for
dagger2 but you can use it with other DI libraries too, it's not depending on any.

It is available in 3 different versions:

## MVPBase

The base library to use MVP.

```gradle
compile 'com.moovel:mvpbase:0.1.0'
```
## MVPBase rx1

This is an extension for the base library to be used with [RxJava v1.X](https://github.com/ReactiveX/RxJava/tree/1.x)
```gradle
compile 'com.moovel:mvpbase-rx1:0.1.0'
```

## MVPBase rx

This is an extension for the base library to be used with [RxJava v2.X](https://github.com/ReactiveX/RxJava/tree/2.x)
```gradle
compile 'com.moovel:mvpbase-rx:0.1.0'
```


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

# Rx Implementation differences

In both rx implementations there are some additional methods in Activity/Fragment and Presenter available, which
you can use to auto unsubscribe from Subscriptions(rx1)/Disposables(rx2).

* RxMVPActivity
  * untilOnStop(Subscription/Disposable) unsubscribes automatically on the lifecycles "onStop" call
  * untilOnDestroy(Subscription/Disposable) unsubscribes automatically on the lifecycles "onDestroy" call

* RxMVPFragment
  * untilOnStop(Subscription/Disposable) unsubscribes automatically on the lifecycles "onStop" call
  * untilOnDestroy(Subscription/Disposable) unsubscribes automatically on the lifecycles "onDestroy" call

* RxBasePresenter
  * untilOnStop(Subscription/Disposable) unsubscribes automatically on the lifecycles "onStop" call
  * untilOnDestroy(Subscription/Disposable) unsubscribes automatically on the lifecycles "onDestroy" call


Example Code using mvpbase-rx can be found [here]()