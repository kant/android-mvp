package com.andretietz.demolib.screens;

import android.annotation.SuppressLint;
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
import com.moovel.mvp.DaggerMVPFragment;

public class DemoLibraryFragment extends DaggerMVPFragment<DemoLibraryFragmentView, DemoLibraryFragmentPresenter>
        implements DemoLibraryFragmentView {

    private TextView textView;

    @Override @SuppressLint("SetTextI18n")
    public void showInjectedObjects(ApplicationObject apo, ActivityObject aco, FragmentObject fro) {
        textView.setText("DemoLibraryFragment: \n" + apo + "\n" + aco + "\n" + fro);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        textView = (TextView) view.findViewById(R.id.fragmentText);
        return view;
    }
}
