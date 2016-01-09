package com.trishwhetzel.activityrecognition;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;

/**
 * Created by whetzel on 1/8/16.
 */
public class DetectedActivitiesIntentService extends IntentService {
    protected static String TAG = "detection_is";

    public DetectedActivitiesIntentService() {
        // Use TAG to name the worker thread
        super(TAG);
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    /**
     * Handles incoming intents.
     * @param intent The Intent is provided (inside a PendingIntent) when requestActivityUpdates()
     * is called. See Google sample: https://github.com/googlesamples/android-play-location/blob/master/ActivityRecognition/app/src/main/java/com/google/android/gms/location/sample/activityrecognition/DetectedActivitiesIntentService.java
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
        Intent localIntent = new Intent(Constants.BROADCAST_ACTION);

        // Get the list of the probable activities associated with the current state of the
        // device. Each activity is associated with a confidence level, which is an int between
        // 0 and 100.
        ArrayList<DetectedActivity> detectedActivities = (ArrayList) result.getProbableActivities();

        // Log each activity.
        Log.i(TAG, "activities detected");

        // Broadcast the list of detected activities.
        localIntent.putExtra(Constants.ACTIVITY_EXTRA, detectedActivities);
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);

    }
}
