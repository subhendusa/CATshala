package com.sekhar.android.catshala;

import android.net.Uri;

/**
 * Created by sekhar on 25-09-2016.
 */
public class UserProfile {

    private String displayName;
    private String emailId;
    private Uri photoUri;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Uri getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(Uri photoUri) {
        this.photoUri = photoUri;
    }
}
