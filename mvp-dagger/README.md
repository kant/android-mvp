# MVP Framework for Android Applications

This framework is our Android-Framework for MVP.

Dependencies:
Android Support-Library v22.2.0

# How to use it

Add the library to your dependencies:
```
compile 'com.moovel.mvp:mvp-dagger:0.1.1'
```



### 1. Create a View
```
public interface AwesomeView extends MVPView {
    ...
}
```

### 2. Create a Presenter
```
@SomeScope
public class AwesomePresenter extends MVPPresenter<AwesomeView> {
    @Inject
    public AwesomePresenter() {
    }
}
```

### 3. Create your screens
Extend your Fragments from DaggerMVPFragment or your Activities from DaggerMVPActivity. These are
generic classes using 2 generic types. The first one is the view interface, the second one is 
the type of presenter which you want to use.

```
public class AwesomeActivity extends DaggerMVPActivity<AwesomeView, AwesomePresenter> implements AwesomeView {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            findViewById(R.id.button).setOnClickListener(v -> getPresenter().onButtonClicked());
        }
}
```

The presenter will be automatically injected, as long as it can be constructor injected and provided in a module(!)

To setup modules and components use the [dagger 2 android documentation](https://google.github.io/dagger/android.html)