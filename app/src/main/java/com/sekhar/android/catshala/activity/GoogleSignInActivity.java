package com.sekhar.android.catshala.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.sekhar.android.catshala.FbSignInUtils;
import com.sekhar.android.catshala.GoogleSignInUtils;
import com.sekhar.android.catshala.R;

/**
 * Activity to demonstrate basic retrieval of the Google user's ID, email address, and basic
 * profile.
 */
public class GoogleSignInActivity extends BaseActivity {

    private static final String TAG = "GSignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private static SignInMode signInMode;
    private ProgressDialog mProgressDialog;

    public static SignInMode getSignInMode() {
        if (signInMode != null) {
            return signInMode;
        } else {
            if (FbSignInUtils.isLoggedIn()) {
                return SignInMode.FACEBOOK;
            } else {
                return SignInMode.GOOGLE;
            }
        }
    }

    public void setSignInMode(SignInMode signInMode) {
        this.signInMode = signInMode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        // Button listeners
        findViewById(R.id.google_sign_in_button).setOnClickListener(this);

        SignInButton signInButton = (SignInButton) findViewById(R.id.google_sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getSignInMode().equals(SignInMode.GOOGLE)) {
            OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
            if (opr.isDone()) {
                Log.d(TAG, "Got cached sign-in");
                GoogleSignInResult result = opr.get();
                handleSignInResult(result);
            } else {
                showProgressDialog();
                opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                    @Override
                    public void onResult(GoogleSignInResult googleSignInResult) {
                        hideProgressDialog();
                        handleSignInResult(googleSignInResult);
                    }
                });
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.google_sign_in_button:
                setSignInMode(SignInMode.GOOGLE);
                signIn();
                break;
            case R.id.fb_sign_in_button:
                setSignInMode(SignInMode.FACEBOOK);
                //signIn();
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        //updateUI(false);
                    }
                });
    }

    private void updateUI() {
        if (FbSignInUtils.isLoggedIn()) {
            FbSignInUtils.setUserProfile(signInAccount);

            Intent signInToMain = new Intent(this, MainActivity.class);
            startActivity(signInToMain);

        } else if (getSignInMode().equals(SignInMode.GOOGLE)) {

            findViewById(R.id.google_sign_in_button).setVisibility(View.GONE);
            Intent signInToMain = new Intent(this, MainActivity.class);
            startActivity(signInToMain);
        }
    }

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInUtils.setUserProfile(result.getSignInAccount());
            updateUI();
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    public enum SignInMode {
        GOOGLE, FACEBOOK
    }
}
