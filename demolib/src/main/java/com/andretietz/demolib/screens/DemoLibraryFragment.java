package com.andretietz.demolib.screens;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andretietz.demolib.R;
import com.andretietz.demolib.model.ActivityObject;
import com.andretietz.demolib.model.ApplicationObject;
import com.andretietz.demolib.model.FragmentObject;
import com.moovel.mvp.MVPFragment;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class DemoLibraryFragment extends MVPFragment<DemoLibraryFragmentView, DemoLibraryFragmentPresenter>
        implements DemoLibraryFragmentView {

    @Inject
    DemoLibraryFragmentPresenter presenter;

    private TextView textView;

    @Override
    public void showInjectedObjects(ApplicationObject apo, ActivityObject aco, FragmentObject fro) {
        textView.setText("DemoLibraryFragment: \n" + apo + "\n" + aco + "\n" + fro);
    }

    @Override
    protected DemoLibraryFragmentPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        textView = (TextView) view.findViewById(R.id.fragmentText);
        return view;
    }
}
