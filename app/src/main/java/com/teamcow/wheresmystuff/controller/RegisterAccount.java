package com.teamcow.wheresmystuff.controller;

import android.app.LoaderManager.LoaderCallbacks;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.content.CursorLoader;

import static com.teamcow.wheresmystuff.model.Users.getUsers;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.teamcow.wheresmystuff.R;
import com.teamcow.wheresmystuff.model.LocalUser;
import com.teamcow.wheresmystuff.model.UserDatabase;
import com.teamcow.wheresmystuff.model.UserType;

import java.util.ArrayList;
import java.util.List;



public class RegisterAccount extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    //UI references
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    private boolean selectUser;
    private boolean selectAdmin;

    private static UserDatabase userDatabase = new UserDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        getUsers();
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                return id == R.id.login || id == EditorInfo.IME_NULL;
            }
        });

        Button mExitButton = (Button) findViewById(R.id.ExitApp);
        mExitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button mRegisterButton = (Button) findViewById(R.id.registration);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegistration();
            }
        });

        RadioButton mUserButton = (RadioButton) findViewById(R.id.User);
        mUserButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectUser = true;
            }
        });

        RadioButton mAdminButton = (RadioButton) findViewById(R.id.Admin);
        mAdminButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAdmin = true;
            }
        });
    }

    /**
     * Attempts the registration by making sure all fields are filled,
     * and that the fields themselves are valid.
     */
    private void attemptRegistration() {
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        //Check if email is already registered
//        if (userDatabase.checkDuplicate(email)) {
//            mEmailView.setError("already exists");
//            focusView = mEmailView;
//            cancel = true;
//        }


        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError("doesnt contain @");

            focusView = mEmailView;
            cancel = true;
        }

//        if (cancel) {
//            focusView.requestFocus();
//        } else {
//            User u = new User(email, password, UserType.USER);
//            userDatabase.addUser(u);
//            Toast.makeText(getApplicationContext(), "Account Registered", Toast.LENGTH_LONG).show();
//            finish();
//        }
    }

    /**
     * Checks to see if the email entered is valid. Really basic right now, will update soon.
     * @param email is the email entered.
     * @return true if the email is valid
     */
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    /**
     * Checks to make sure password is valid. Basic implementation right now,
     * will update soon.
     * @param password is the password that was input.
     * @return true if password is valid
     */
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY),
                RegisterAccount.ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }
    }
    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {

    }
}