package com.explore.features.reviewnew;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.explore.MainActivity;
import com.explore.R;
import com.explore.features.IsToolbarSetter;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ReviewNewFragment extends Fragment implements IsToolbarSetter {
    final static String FRAGMENT_TITLE = "Review Your TourPackage";
    @BindView(R.id.textInput_reviewnew_title)
    TextInputEditText textInputEditTextEmail;
    @BindView(R.id.ratingBar_reviewnew_rating)
    RatingBar ratingBar;
    @BindView(R.id.text_reviewnew_ratingdesc)
    TextView mtextViewRatingDesc;
    @BindString(R.string.text_reviewnew_ratingtext_1_star)
    String mRatingTextOneStar;
    @BindString(R.string.text_reviewnew_ratingtext_2_star)
    String mRatingTextTwoStar;
    @BindString(R.string.text_reviewnew_ratingtext_3_star)
    String mRatingTextThreeStar;
    @BindString(R.string.text_reviewnew_ratingtext_4_star)
    String mRatingTextFourStar;
    @BindString(R.string.text_reviewnew_ratingtext_5_star)
    String mRatingTextFiveStar;


    public ReviewNewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_review_new, container, false);

        ButterKnife.bind(this, v);

        setToolbarTitle(getActivity(), FRAGMENT_TITLE);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                mtextViewRatingDesc.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mtextViewRatingDesc.setText(mRatingTextOneStar);
                        break;
                    case 2:
                        mtextViewRatingDesc.setText(mRatingTextTwoStar);
                        break;
                    case 3:
                        mtextViewRatingDesc.setText(mRatingTextThreeStar);
                        break;
                    case 4:
                        mtextViewRatingDesc.setText(mRatingTextFourStar);
                        break;
                    case 5:
                        mtextViewRatingDesc.setText(mRatingTextFiveStar);
                        break;
                    default:
                        mtextViewRatingDesc.setText("");
                }
            }
        });
        return v;
    }

    @Override
    public void setToolbarTitle(Activity activity, String title) {
        ((MainActivity) activity).setActivityToolbarTitle(title);
    }
}
