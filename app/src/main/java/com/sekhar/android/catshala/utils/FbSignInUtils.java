package com.sekhar.android.catshala.utils;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.sekhar.android.catshala.UserProfile;
import com.sekhar.android.catshala.activity.BaseActivity;

public class FbSignInUtils {

    public static void signOut() {
        if (isLoggedIn()) {
            LoginManager.getInstance().logOut();
        }
    }

    public static boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    public static void setUserProfile() {
        boolean enableButtons = AccessToken.getCurrentAccessToken() != null;

        Profile profile = Profile.getCurrentProfile();

        UserProfile signInAccount = BaseActivity.signInAccount;
        if(signInAccount == null) {
            signInAccount = new UserProfile();
        }
        if (enableButtons && profile != null) {
            signInAccount.setDisplayName(profile.getName());
            signInAccount.setPhotoUri(profile.getLinkUri());
        }
    }
}
