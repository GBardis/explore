package com.explore.rest.responses.tourResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TourRetrofitResponse {

    @SerializedName("_embedded")
    @Expose
    public TourEmbeddedResponse embedded;
}


/* Use this for all tours

                    Call<TourRetrofitResponse> tourResponseCall = RestClient.call().fetchTours(tourPackageId);
                    tourResponseCall.enqueue(new Callback<TourRetrofitResponse>() {

                        @Override
                        public void onResponse(Call<TourRetrofitResponse> call, Response<TourRetrofitResponse> response) {
                            TourRetrofitResponse retrofitResponse = response.body();
                            List<TourResponse> tourResponseList = retrofitResponse.embedded.tours;

                            for (TourResponse tourResponse : tourResponseList) {
                                tourDomainList.add(new TourDomain(
                                        tourResponse.getId(),
                                        tourResponse.getTitle(),
                                        tourResponse.getDescription(),
                                        tourResponse.getPrice(),
                                        tourResponse.getDuration(),
                                        tourResponse.getBullets(),
                                        tourResponse.getKeywords()
                                ));

                            }
                            observableTourList.changeDataset(tourDomainList);
                        }

                        @Override
                        public void onFailure(Call<TourRetrofitResponse> call, Throwable t) {

                        }
                    });
 */