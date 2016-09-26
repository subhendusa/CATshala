package com.sekhar.android.catshala;

import android.content.Intent;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.sekhar.android.catshala.activity.BaseActivity;
import com.sekhar.android.catshala.activity.GSignInActivity;
import com.sekhar.android.catshala.activity.MainActivity;
import com.sekhar.android.catshala.activity.SignInActivity;

/**
 * Activity to demonstrate basic retrieval of the Google user's ID, email address, and basic
 * profile.
 */
public class GoogleSignInUtils {

    public static void signOut(ResultCallback<Status> callback) {
        Auth.GoogleSignInApi.signOut(BaseActivity.mGoogleApiClient).setResultCallback(
                (ResultCallback<? super Status>) callback);
    }

    public static void setUserProfile(GoogleSignInAccount googleSignInAccount) {

        UserProfile signInAccount = SignInActivity.signInAccount;
        signInAccount.setDisplayName(googleSignInAccount.getDisplayName());
        signInAccount.setEmailId(googleSignInAccount.getEmail());
        signInAccount.setPhotoUri(googleSignInAccount.getPhotoUrl());
    }

   /* public static boolean isLoggedIn() {
        return true;
    }

    public static void setUserProfile(UserProfile signInAccount) {
        boolean enableButtons = AccessToken.getCurrentAccessToken() != null;

        Profile profile = Profile.getCurrentProfile();
        if (enableButtons && profile != null) {
            signInAccount.setDisplayName(profile.getName());
            signInAccount.setPhotoUri(profile.getLinkUri());
        }
    }*/
}
