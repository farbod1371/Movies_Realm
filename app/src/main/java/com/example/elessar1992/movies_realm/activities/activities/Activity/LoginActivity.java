package com.example.elessar1992.movies_realm.activities.activities.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.example.elessar1992.movies_realm.R;
import com.example.elessar1992.movies_realm.activities.activities.Database.InputValidation;
import com.example.elessar1992.movies_realm.activities.activities.Model.User;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by elessar1992 on 3/8/18.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{

    private final AppCompatActivity activity = LoginActivity.this;
    private static final String TAG = MainActivity.class.getSimpleName();

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;
    private AppCompatButton showAllUsers;

    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;

    private Realm myrealm;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        initViews();
        initListeners();
        initObjects();
    }

    private void initListeners()
    {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
        showAllUsers.setOnClickListener(this);
    }

    private void initObjects()
    {
        inputValidation = new InputValidation(activity);
        myrealm = Realm.getDefaultInstance();

    }

    private void initViews()
    {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        showAllUsers = (AppCompatButton) findViewById(R.id.appCompatShowAllUsers);
        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);

    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intentRegister);
                break;
            case R.id.appCompatShowAllUsers:
                Intent showAllUsers = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(showAllUsers);
                break;
        }
    }

    private void verifyFromSQLite()
    {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }

        if (checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {


            /*Intent accountsIntent = new Intent(activity, UserActivity.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);*/
            Intent accountsIntent = new Intent(activity, MainActivity.class);
            emptyInputEditText();
            startActivity(accountsIntent);


        }
        else
        {
            // Snack Bar to show success message that record is wrong
            Toast.makeText(this, "Username or Password is Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkUser(String email, String password)
    {
        RealmResults<User> users = myrealm.where(User.class).findAll();
        for(User myusers:users)
        {
            if(email.equals(myusers.getUsername()) && password.equals(myusers.getPassword()))
            {
                return true;
            }
        }
        return false;
    }


    private void emptyInputEditText()
    {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
