package com.explore.features.tour.domain;

import android.support.v4.app.Fragment;

public class FragmentSettable extends Fragment implements AcceptsArgumentsFromParentFragment {

    public String parentFragmentParam;

    @Override
    public void setStringAttr(String s) {
        this.parentFragmentParam = s;
    }
}
