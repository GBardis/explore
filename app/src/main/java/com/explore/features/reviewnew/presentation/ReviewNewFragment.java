package com.explore.features.reviewnew.presentation;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.explore.MainActivity;
import com.explore.R;
import com.explore.features.IsToolbarSetter;
import com.explore.features.reviewnew.domain.ReviewPresenter;
import com.explore.features.tour.presentation.TourFragment;

import java.util.Objects;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;


public class ReviewNewFragment extends Fragment implements IsToolbarSetter, ReviewNewView {

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
    @BindString(R.string.reviewnew_fragment_title)
    String reviewNewFragmentTitle;
    @Getter
    ReviewPresenter reviewNewPresenter;

    String mParentArg;
    Bundle bundle;


    public ReviewNewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_review_new, container, false);
        ButterKnife.bind(this, v);

        if (getArguments() != null) {
            mParentArg = getArguments().getString("TOUR_PACKAGE_ID");
            bundle = new Bundle();
            bundle.putString("TOUR_PACKAGE_ID", mParentArg);
        }

        setToolbarTitle(getActivity(), reviewNewFragmentTitle);

        // Check if Title is not empty
        mtextViewRatingDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (Objects.requireNonNull(mtextViewRatingDesc.getText()).length() > 0) {
                    mtextViewRatingDesc.setError(null);
                } else {
                    mtextViewRatingDesc.setError("Review can't be blank");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textInputEditTextReviewMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (Objects.requireNonNull(textInputEditTextReviewMessage.getText()).length() > 0) {
                    textInputEditTextReviewMessage.setError(null);
                } else {
                    textInputEditTextReviewMessage.setError("Message can't be blank");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        reviewNewPresenter = new ReviewPresenterImpl(getActivity(), this);

        mButtonSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = (int) mRatingBar.getRating();
                String comment = textInputEditTextReviewMessage.getText().toString();


                if (comment.equals("")) {
                    Toast.makeText(getActivity(), "Title and Message can't be blank", Toast.LENGTH_LONG).show();
                } else {
                    reviewNewPresenter.postReview(score, comment, "teamBlack", mParentArg);
                    try {


                        // Then just use the following:
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
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
    public void afterSubmit(String successToast) {
        getFragmentManager().popBackStack();
        TourFragment.TourFragmentListener TourFragmentListener = (TourFragment.TourFragmentListener) getActivity();
        Objects.requireNonNull(TourFragmentListener).transitionToTourFragment(bundle);
        Toast.makeText(getActivity(), successToast, Toast.LENGTH_LONG).show();
    }

    public interface ReviewNewFragmentListener {
        void transitionToReviewNewFragment(Bundle bundle);
    }
}
