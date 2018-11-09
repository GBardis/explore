package com.explore.features.tour.presentation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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

    private com.explore.features.tourpackage.domain.TourPackageUI mParentArg;
    Bundle bundle;

    SwipeRefreshLayout mSwipeRefreshLayout;

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

        mSwipeRefreshLayout = v.findViewById(R.id.swiperefresh_tour_list);


        if (getArguments() != null) {
            mParentArg = getArguments().getParcelable("TOUR_PACKAGE");
        }

        bundle.putParcelable("TOUR_PACKAGE", mParentArg);

        mTourPresenter = new TourPresenterImpl(getActivity(), this);
        mTourPresenter.getTourList(getActivity(), mParentArg.getId(), false);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mTourPresenter.getTourList(getActivity(), mParentArg.getId(), true);
            }
        });

        return v;
    }


    @Override
    public void showTourPackage(TourPackageUI tourPackageUI) {

    }

    @Override
    public void showTourList(ArrayList<TourUI> tourUIArrayList) {
        mSwipeRefreshLayout.setRefreshing(false);
        mRecyclerView.setAdapter(new TourRvAdapter(tourUIArrayList));
    }

    @Override
    public void showTourPackageReviewList(ArrayList<ReviewUI> reviewUIArrayList) {

    }

}
