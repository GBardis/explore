package com.explore.features.tourpackage.presentation;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.explore.MainActivity;
import com.explore.R;
import com.explore.features.IsToolbarSetter;
import com.explore.features.tour.presentation.TourFragment;
import com.explore.features.tourpackage.domain.OnTourPackageClickListener;
import com.explore.features.tourpackage.domain.TourPackagePresenter;
import com.explore.features.tourpackage.domain.TourPackageUI;
import com.explore.features.tourpackage.domain.TourPackageView;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TourPackageFragment extends Fragment implements TourPackageView, IsToolbarSetter {
    @BindString(R.string.tourpackage_fragment_title)
    String tourPackageFragmentTitle;
    @BindView(R.id.tourpackage_rv)
    RecyclerView tourPackageRv;
    TourPackagePresenter tourPackagePresenter;
    TourPackagesRvAdapter tourPackagesRvAdapter;
    Bundle bundle;


    public TourPackageFragment() {
        // Required empty public constructor
    }

    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tour_package, container, false);
        ButterKnife.bind(this, v);

        bundle = new Bundle();

        setToolbarTitle(getActivity(), tourPackageFragmentTitle);
        //Setup LayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        tourPackageRv.setLayoutManager(layoutManager);
        tourPackagePresenter = new TourPackagePresenterImpl(this);
        tourPackagePresenter.getTourPackages();
        return v;
    }

    @Override
    public void showTourPackages(List<TourPackageUI> tourPackageArrayList) {
        tourPackagesRvAdapter = new TourPackagesRvAdapter(tourPackageArrayList, new OnTourPackageClickListener() {
            @Override
            public void onTourPackageClicked(TourPackageUI tourPackageUI) {
                bundle.putString("TOUR_PACKAGE_ID",tourPackageUI.getId());
                TourFragment.TourFragmentListener tourFragmentListener = (TourFragment.TourFragmentListener) getActivity();
                tourFragmentListener.transitionToTourFragment(bundle);

            }
        }, getActivity());
        tourPackageRv.setAdapter(tourPackagesRvAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_tourpackage, menu);

        // Setup menu with actionbar
        MenuInflater menuInflater = new MenuInflater(getActivity());
        menuInflater.inflate(R.menu.menu_tourpackage, menu);
        MenuItem item = menu.findItem(R.id.menu_action_search);
        SearchView searchView = (SearchView) item.getActionView();


        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                tourPackagesRvAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                tourPackagesRvAdapter.getFilter().filter(query);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setToolbarTitle(Activity activity, String title) {
        ((MainActivity) activity).setActivityToolbarTitle(title);
    }

    public interface TourPackageListener {
        void transitionToTourPackage();
    }
}
