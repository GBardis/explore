package com.explore.features.tour.presentation;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.explore.MainActivity;
import com.explore.R;
import com.explore.features.IsToolbarSetter;
import com.explore.features.tour.domain.TourPackageUI;
import com.explore.features.tour.domain.TourPresenter;
import com.explore.features.tour.domain.TourRvAdapter;
import com.explore.features.tour.domain.TourUI;
import com.explore.features.tour.domain.TourView;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TourFragment extends Fragment implements TourView, IsToolbarSetter {

    Button tourDummyFetchButton;
    private TourPresenter mTourPresenter;

    @BindView(R.id.recycler_tour_list)
    public RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    //@BindView(R.id.text_tour_tourpackage_name)
    //TextView mTextViewTourPackageName;

    @BindView(R.id.text_tour_tourpackage_description)
    TextView mTextViewDescription;

    @BindView(R.id.text_tour_tourpackage_rating)
    TextView mTextViewTourPackageRating;

    public TourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tour, container, false);
        ButterKnife.bind(this, v);

        mTourPresenter = new TourPresenterImpl(this);
        mTourPresenter.getTourPackage("2");

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()),
                DividerItemDecoration.VERTICAL));

        return v;
    }

    @Override
    public void showTourPackage(ArrayList<TourUI> tourUIArrayList, TourPackageUI tourPackageUI) {
        setToolbarTitle(getActivity(), tourPackageUI.getName());
        mRecyclerView.setAdapter(new TourRvAdapter(tourUIArrayList));

        mTextViewDescription.setText(tourPackageUI.getDescription());
    }

    @Override
    public void setToolbarTitle(Activity activity, String title) {
        ((MainActivity) activity).setActivityToolbarTitle(title);
    }

    public interface TourFragmentListener{
        void transitionToTourFragment();
    }
}
