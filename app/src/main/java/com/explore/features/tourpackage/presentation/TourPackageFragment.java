package com.explore.features.tourpackage.presentation;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.explore.R;
import com.explore.features.tourpackage.domain.OnTourPackageClickListener;
import com.explore.features.tourpackage.domain.TourPackagePresenter;
import com.explore.features.tourpackage.domain.TourPackageUI;
import com.explore.features.tourpackage.domain.TourPackageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TourPackageFragment extends Fragment implements TourPackageView {
    @BindView(R.id.tourpackage_rv)
    RecyclerView tourPackageRv;

    TourPackagePresenter tourPackagePresenter;

    public TourPackageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tour_package, container, false);
        ButterKnife.bind(this, v);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        tourPackageRv.setLayoutManager(layoutManager);
        tourPackagePresenter = new TourPackagePresenterImpl(this);
        tourPackagePresenter.getTourPackages();
        return v;
    }

    @Override
    public void showTourPackages(List<TourPackageUI> tourPackageArrayList) {
        TourPackagesRvAdapter tourPackagesRvAdapter = new TourPackagesRvAdapter(tourPackageArrayList, new OnTourPackageClickListener() {
            @Override
            public void onTourPackageClicked(TourPackageUI tourPackageUI) {

            }
        }, getActivity());
        tourPackageRv.setAdapter(tourPackagesRvAdapter);
    }
}
