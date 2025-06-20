package com.example.antifakegps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.core.app.ActivityCompat;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONObject;

@CapacitorPlugin(name = "AntiFakeGps")
public class AntiFakeGpsPlugin extends Plugin {
    private static Location lastKnownLocation = null;

    @PluginMethod
    public void getMock(PluginCall call) {
        Context context = getContext(); // Jangan pakai 'context' di luar method
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);

        // Cek izin lokasi
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            call.reject("Permission denied: ACCESS_FINE_LOCATION not granted.");
            return;
        }

        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location == null) {
                call.reject("Location unavailable");
                return;
            }

            // Cek lokasi mencurigakan
            if (MockLocationDetector.isLocationSuspicious(location, lastKnownLocation)) {
                call.reject("Suspicious location detected.");
                return;
            }

            // Simpan lokasi terakhir
            lastKnownLocation = location;

            // Cek mock location
            Boolean isMock = MockLocationDetector.isMockLocation(location);

            // Buat JSON response
            try {
                JSONArray array = new JSONArray();
                JSONObject data = new JSONObject();

                data.put("latitude", location.getLatitude());
                data.put("longitude", location.getLongitude());
                data.put("provider", location.getProvider());
                data.put("accuracy", location.getAccuracy());
                data.put("timestamp", location.getTime());
                data.put("isMock", isMock);

                array.put(data);

                JSObject result = new JSObject();
                result.put("results", array);

                call.resolve(result);
            } catch (Exception e) {
                call.reject("Error building JSON result", e);
            }
        }).addOnFailureListener(e -> {
            call.reject("Failed to get location", e);
        });
    }
}
