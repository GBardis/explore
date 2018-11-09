package com.explore.rest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.explore.base.ExploreDatabase;
import com.explore.features.tourpackage.data.TourPackageDao;
import com.explore.features.tourpackage.domain.TourPackageUI;
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

import java.io.ByteArrayOutputStream;
import java.util.Objects;
import java.util.Random;

public class GooglePlacesApiClient implements GoogleApiClient.OnConnectionFailedListener {
    private GeoDataClient mGeoDataClient;
    private PlacePhotoMetadataBuffer photoMetadataBuffer;

    public GooglePlacesApiClient(Context context) {
        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(context, null);
    }

    private void getPhotos(final TourPackageUI tourPackageUI, final ImageView mTourPackagePhoto, final Context context) {
        final Task<PlacePhotoMetadataResponse> photoMetadataResponse = mGeoDataClient.getPlacePhotos(tourPackageUI.getPlaceId());
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
                                Bitmap bitmap = Objects.requireNonNull(task.getResult()).getBitmap();
                                saveImageToDB(bitmap, tourPackageUI.getId(), context);
                                mTourPackagePhoto.setImageBitmap(Objects.requireNonNull(bitmap));
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
    }

    public void tourPackageHasImage(final TourPackageUI tourPackageUI, final ImageView mTourPackagePhoto, final Context context) {
        final TourPackageDao tourPackageDao = ExploreDatabase.getDatabase(context).tourPackageDao();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                byte[] image = tourPackageDao.getTourPackageImage(tourPackageUI.getId());
                if (image == null) {
                    getPhotos(tourPackageUI,mTourPackagePhoto,context);
                }else {
                    setBitmapToRVItem(image, mTourPackagePhoto);
                }
            }
        });
    }

    private void setBitmapToRVItem(final byte[] image,final ImageView mTourPackagePhoto) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                mTourPackagePhoto.setImageBitmap(bitmap);
            }
        });
    }

    private void saveImageToDB(final Bitmap bitmap, final String id, Context context) {
        final TourPackageDao tourPackageDao = ExploreDatabase.getDatabase(context).tourPackageDao();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                tourPackageDao.updateTourPackageImage(convertBitmapToByteArray(bitmap), id);
            }
        });
    }

    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
