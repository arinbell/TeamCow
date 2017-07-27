package com.teamcow.wheresmystuff.controller;

import android.content.SharedPreferences;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.teamcow.wheresmystuff.R;
import com.teamcow.wheresmystuff.model.LocalUser;

import java.util.Arrays;

/**
 * A launch page that allows users to login or register.
 */
public class WelcomepageActivity extends AppCompatActivity {

    private static final String GLOBAL_PREFS = "global_prefs";

    /**
     * creates welcome page
     * @param savedInstanceState saves the instance state
     */

    private static final int RC_SIGN_IN = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);

        SharedPreferences mPrefs =
                getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor pEditor = mPrefs.edit();

//        Gson gson = new Gson();
//        String json = mPrefs.getString("UserData", "");
//        LocalUser lUser = gson.fromJson(json, LocalUser.class);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(WelcomepageActivity.this, HomepageActivity.class);
            startActivity(intent);
            finish();
        }

        Button advanceToLogin = (Button) findViewById(R.id.login);
        advanceToLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                startActivityForResult(
                        AuthUI.getInstance().createSignInIntentBuilder()
                            .setAvailableProviders(
                                Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.TWITTER_PROVIDER).build()))
                            .setLogo(R.drawable.wheres_my_stuff_logo_black_720)
                            .setIsSmartLockEnabled(false)
                            .setLogo(R.drawable.logo_black_720)
                            .build(),
                        RC_SIGN_IN);

                //Intent intent = new Intent(WelcomepageActivity.this, LoginActivity.class);
                //startActivity(intent);
            }
        });

//        Button advanceToRegister = (Button) findViewById(R.id.register);
//        advanceToRegister.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(WelcomepageActivity.this, RegisterAccount.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            handleSignInResponse(resultCode, data, response);
            return;
        }
    }

    private void handleSignInResponse(int resultCode, Intent data, IdpResponse response) {

        if (resultCode == ResultCodes.OK) {
            startActivity(HomepageActivity.createIntent(this, IdpResponse.fromResultIntent(data)));
            finish();
            return;
        } else {
            if (response == null) {
                Snackbar.make(findViewById(R.id.login), "Sign in cancelled", Snackbar.LENGTH_LONG).show();
                return;
            }
            if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                Snackbar.make(findViewById(R.id.login), "No Internet connection", Snackbar.LENGTH_LONG).show();
                return;
            }
            Snackbar.make(findViewById(R.id.login), "Unknown error occured", Snackbar.LENGTH_LONG).show();
        }


//        if (resultCode == RESULT_OK) {
////            LocalUser user = new LocalUser(FirebaseAuth.getInstance().getCurrentUser());
////            SharedPreferences mPrefs = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
////            SharedPreferences.Editor pEditor = mPrefs.edit();
////            Gson gson = new Gson();
////            String json = gson.toJson(user);
////            pEditor.putString("UserData", json);
////            pEditor.commit();
//            startActivity(HomepageActivity.createIntent(this, IdpResponse.fromResultIntent(data)));
//            finish();
//            return;
//        }
//        if (resultCode == RESULT_CANCELED) {
//            Snackbar.make(findViewById(R.id.login), "Sign in cancelled", Snackbar.LENGTH_LONG).show();
//            return;
//        }
//        Snackbar.make(findViewById(R.id.login), "Login failed", Snackbar.LENGTH_LONG).show();
    }
}
