package com.example.antifakegps;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class MockLocationDetector {

    private static final String TAG = "MockLocationDetector";
    private static Location lastKnownLocation = null;

    /**
     * Cek apakah lokasi berasal dari mock provider (Fake GPS).
     * Berlaku untuk API 18 ke atas (Android 4.3+)
     */
    public static boolean isMockLocation(Location location) {
//        if (location == null) return false;

        boolean isMock = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            isMock = location.isMock();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            isMock = location.isFromMockProvider();
        }

        return isMock;
    }


    /**
     * Cek apakah ada indikasi mencurigakan dari data lokasi.
     * Misalnya: lokasi terlalu cepat berpindah atau data tidak valid.
     */


    public static boolean isLocationSuspicious(Location current, Location previous) {
        if (current == null || previous == null) return false;

        long timeDeltaMillis = current.getTime() - previous.getTime();
        double timeDeltaSeconds = timeDeltaMillis / 1000.0;
        if (timeDeltaSeconds <= 0) return false;

        float distance = current.distanceTo(previous);
        double speed = distance / timeDeltaSeconds;

        boolean tooFast = speed > 50; // >180 km/h
        boolean hugeJump = distance > 5000; // loncat 5 km
        boolean badAccuracy = current.hasAccuracy() && current.getAccuracy() > 100;

        Log.d("Suspicious", "Speed=" + speed + " m/s, Distance=" + distance + " m, Δt=" + timeDeltaSeconds + " s");

        return tooFast || hugeJump || badAccuracy;
    }



}