package com.explore.features.reviewnew.presentation;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.explore.MainActivity;
import com.explore.R;
import com.explore.features.IsToolbarSetter;
import com.explore.features.reviewnew.domain.ReviewNewPresenter;
import com.explore.features.tour.presentation.TourFragment;

import java.util.Objects;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;


public class ReviewNewFragment extends Fragment implements IsToolbarSetter, ReviewNewView {
    final static String FRAGMENT_TITLE = "Review Your TourPackage";

    @BindView(R.id.textInput_reviewnew_title)
    TextInputEditText textInputEditTextTitle;
    @BindView(R.id.ratingBar_reviewnew_rating)
    RatingBar mRatingBar;
    @BindView(R.id.text_reviewnew_ratingdesc)
    TextView mtextViewRatingDesc;
    @BindView(R.id.button_reviewnew_sumbitreview)
    Button mButtonSubmitReview;
    @BindView(R.id.text_reviewnew_reviewmessage)
    TextInputEditText textInputEditTextReviewMessage;
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

    @Getter
    ReviewNewPresenter reviewNewPresenter;


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

        // Check if Title is not empty
        textInputEditTextTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (Objects.requireNonNull(textInputEditTextTitle.getText()).length() > 0) {
                    textInputEditTextTitle.setError(null);
                } else {
                    textInputEditTextTitle.setError("Title can be blank");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        reviewNewPresenter = new ReviewNewPresenterImpl(this);
        
        mButtonSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = textInputEditTextTitle.getText().toString();
                float rating = mRatingBar.getRating();
                String message = textInputEditTextReviewMessage.getText().toString();


                reviewNewPresenter.setReviewNew(title, rating, message);
            }
        });

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
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

    @Override
    public void afterSubmit() {
        TourFragment.TourFragmentListener TourFragmentListener = (TourFragment.TourFragmentListener) getActivity();
        Objects.requireNonNull(TourFragmentListener).transitionToTourFragment();
    }
}
