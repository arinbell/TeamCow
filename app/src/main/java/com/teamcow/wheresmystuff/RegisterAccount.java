package com.teamcow.wheresmystuff;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterAccount extends AppCompatActivity implements View.OnClickListener {
    private Button cancelButton, submitButton;
    private EditText user, email, password;
    private Switch admin;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
        mAuth = FirebaseAuth.getInstance();

        cancelButton = (Button) findViewById(R.id.registrationCancel);
        cancelButton.setOnClickListener(this);

        submitButton= (Button) findViewById(R.id.registrationSubmit);
        submitButton.setOnClickListener(this);

        email = (EditText) findViewById(R.id.registrationEmail);
        password = (EditText) findViewById(R.id.registrationPassword);
        admin = (Switch) findViewById(R.id.registrationAdmin);

    }

    @Override
    public void onClick(View v) {
        String inputUser = user.getText().toString();
        String inputEmail = email.getText().toString();
        String inputPassword = password.getText().toString();

        if (v == submitButton) {
            if ( inputUser.length() == 0 || inputEmail.length() == 0 ||
                    inputPassword.length() == 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Please fill out the required fields.");
                builder.setCancelable(true);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

             AlertDialog pop = builder.create();
                pop.show();
                return;
            }
            finish();
            startActivity(new Intent(this, HomepageActivity.class));
        }
        if (v == cancelButton) {
            finish();
            startActivity(new Intent(this, HomepageActivity.class));
        }
    }
}
