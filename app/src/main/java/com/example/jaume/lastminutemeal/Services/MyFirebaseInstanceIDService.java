package com.example.jaume.lastminutemeal.Services;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;
import java.util.Objects;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        try {
            if (mAuth.getUid() != null && mDatabase != null) {
                mDatabase.child("users").child(Objects.requireNonNull(mAuth.getUid())).child(refreshedToken).setValue(true);
                mDatabase.child("tokens").child(refreshedToken).setValue(true);
            }
        } catch(Exception ignored) {
            Log.d("Debug", ignored.toString());
        }
    }
}