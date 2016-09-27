package com.sekhar.android.catshala.utils;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.sekhar.android.catshala.UserProfile;
import com.sekhar.android.catshala.activity.BaseActivity;

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

        UserProfile signInAccount = BaseActivity.signInAccount;
        if(signInAccount == null) {
            signInAccount = new UserProfile();
        }
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
