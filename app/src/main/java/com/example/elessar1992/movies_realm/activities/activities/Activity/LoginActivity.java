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

    private TextInputLayout textInputLayoutUsername;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextUsername;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;
    private AppCompatButton showAllUsers;
    private AppCompatButton showAllEvents;

    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;

    private Realm myrealm;
    private User user;


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
        showAllEvents.setOnClickListener(this);
    }

    private void initObjects()
    {
        user = new User();
        inputValidation = new InputValidation(activity);
        myrealm = Realm.getDefaultInstance();

    }

    private void initViews()
    {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutUsername = (TextInputLayout) findViewById(R.id.textInputLayoutUsername);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextUsername = (TextInputEditText) findViewById(R.id.textInputEditTextUsername);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        showAllEvents = (AppCompatButton) findViewById(R.id.appCompatShowAllEvents);
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
                Intent showAllUsers = new Intent(getApplicationContext(), ShowAllUserActivity.class);
                startActivity(showAllUsers);
                break;
            case R.id.appCompatShowAllEvents:
                Intent showAllEvents = new Intent(getApplicationContext(), ShowAllFavorite.class);
                startActivity(showAllEvents);
        }
    }

    private void verifyFromSQLite()
    {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextUsername, textInputLayoutUsername, getString(R.string.error_message_email))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }

        if (checkUser(textInputEditTextUsername.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim()))
        {

            Intent accountsIntent = new Intent(activity, MainActivity.class);
            accountsIntent.putExtra("USERNAME", textInputEditTextUsername.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);

        }
        else
        {
            // Snack Bar to show success message that record is wrong
            Toast.makeText(this, "Username or Password is Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkUser(String username, String password)
    {
        RealmResults<User> users = myrealm.where(User.class).findAll();
        for(User myusers:users)
        {
            if(username.equals(myusers.getUsername()) && password.equals(myusers.getPassword()))
            {
                return true;
            }
        }
        return false;
    }


    private void emptyInputEditText()
    {
        textInputEditTextUsername.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
