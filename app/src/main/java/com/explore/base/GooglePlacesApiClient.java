package com.explore.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResponse;
import com.google.android.gms.location.places.PlacePhotoResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Objects;
import java.util.Random;

public class GooglePlacesApiClient implements GoogleApiClient.OnConnectionFailedListener {
    private GeoDataClient mGeoDataClient;
    private Bitmap bitmap;
    private PlacePhotoMetadataBuffer photoMetadataBuffer;

    public GooglePlacesApiClient(Context context) {
        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(context, null);
    }

    public Bitmap getPhotos(final String placeId, final ImageView mTourPackagePhoto) {
        final Task<PlacePhotoMetadataResponse> photoMetadataResponse = mGeoDataClient.getPlacePhotos(placeId);
        photoMetadataResponse.addOnCompleteListener(new OnCompleteListener<PlacePhotoMetadataResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlacePhotoMetadataResponse> task) {
                if (task.isSuccessful()) {

                    Random rand = new Random();
                    int n = rand.nextInt(5) + 1;

                    // Get the list of photos.
                    PlacePhotoMetadataResponse photos = task.getResult();
                    // Get the PlacePhotoMetadataBuffer (metadata for all of the photos).
                    photoMetadataBuffer = Objects.requireNonNull(photos).getPhotoMetadata();
                    try {
                        // Get the first photo in the list.
                        PlacePhotoMetadata photoMetadata = photoMetadataBuffer.get(n);
                        // Get the attribution text.
                        CharSequence attribution = photoMetadata.getAttributions();
                        // Get a full-size bitmap for the photo.
                        Task<PlacePhotoResponse> photoResponse = mGeoDataClient.getPhoto(photoMetadata);
                        photoResponse.addOnCompleteListener(new OnCompleteListener<PlacePhotoResponse>() {
                            @Override
                            public void onComplete(@NonNull Task<PlacePhotoResponse> task) {
                                PlacePhotoResponse photo = task.getResult();
                                bitmap = Objects.requireNonNull(photo).getBitmap();
                                mTourPackagePhoto.setImageBitmap(bitmap);
                            }
                        });
                    } catch (IllegalStateException ex) {
                        photoMetadataBuffer.release();
                    }
                    photoMetadataBuffer.release();
                } else {
//                    createToastMessages("No Photos Found , Check Internet Access");
                }
            }
        });
        return bitmap;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
