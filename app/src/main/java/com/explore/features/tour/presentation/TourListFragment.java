package com.explore.features.tour.presentation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.explore.R;
import com.explore.features.tour.domain.ReviewUI;
import com.explore.features.tour.domain.TourPackageUI;
import com.explore.features.tour.domain.TourPresenter;
import com.explore.features.tour.domain.TourRvAdapter;
import com.explore.features.tour.domain.TourUI;
import com.explore.features.tour.domain.TourView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TourListFragment extends Fragment implements TourView {

    private TourPresenter mTourPresenter;

    @BindView(R.id.recycler_tour_list)
    public RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private String mParentArg;
    Bundle bundle;

    public TourListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tour_list, container, false);
        ButterKnife.bind(this, v);

        bundle = new Bundle();

        if (getArguments() != null) {
            mParentArg = getArguments().getString("TOUR_PACKAGE_ID");
        }

        bundle.putString("TOUR_PACKAGE_ID", mParentArg);

        mTourPresenter = new TourPresenterImpl(getActivity(),this);
        mTourPresenter.getTourList(getActivity(),mParentArg);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        return v;
    }


    @Override
    public void showTourPackage(TourPackageUI tourPackageUI) {

    }

    @Override
    public void showTourList(ArrayList<TourUI> tourUIArrayList) {
        mRecyclerView.setAdapter(new TourRvAdapter(tourUIArrayList));
    }

    @Override
    public void showTourPackageReviewList(ArrayList<ReviewUI> reviewUIArrayList) {

    }


}
