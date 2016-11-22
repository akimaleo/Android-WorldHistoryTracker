package com.letit0or1.androidworldhistorytracker.view.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.letit0or1.androidworldhistorytracker.R;
import com.letit0or1.androidworldhistorytracker.entity.User;
import com.letit0or1.androidworldhistorytracker.entity.UserDto;
import com.letit0or1.androidworldhistorytracker.view.TokenUtil;
import com.letit0or1.androidworldhistorytracker.view.main.MapListViewHolder;
import com.letit0or1.androidworldhistorytracker.webapp.factory.ServicesFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private AutoCompleteTextView usernameField;
    private EditText passwordField;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        usernameField = (AutoCompleteTextView) findViewById(R.id.username);
        passwordField = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.email_sign_in_button);
        loginButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MapListViewHolder.class);
                startActivity(intent);
                View e = LoginActivity.this.getCurrentFocus();
                if (e != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });

        checkAccessToken();
    }

    public void checkAccessToken() {
        progressBar.setVisibility(View.VISIBLE);
        String token = loadToken();
        if (token.contains("none") || token.isEmpty()) {
            progressBar.setVisibility(View.INVISIBLE);
        } else {
            goMainApp();
        }
    }

    public void doLogin() {
//        Intent intent = new Intent(LoginActivity.this, MapListViewHolder.class);
//        startActivity(intent);
        if (usernameField.getText().length() < 1 || passwordField.getText().length() < 1) {
            Toast.makeText(getApplicationContext(), "Some fields are empty, write something", Toast.LENGTH_LONG).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        UserDto loginUser = new UserDto(usernameField.getText().toString(), passwordField.getText().toString());
        ServicesFactory.getInstance().getUserService().authorization(loginUser).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("RESPONSE", "OK");
                String head = response.headers().get("authorization");

                if (head.contains("NULL"))
                    new RegisterDialog(LoginActivity.this).show();
                else {
                    Log.i("LOGIN TOKEN", head);
                    saveToken(head);
                    goMainApp();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("RESPONSE", "FAIL");
                progressBar.setVisibility(View.GONE);
                troubleDialog();
            }
        });
    }

    public void doRegister() {

        progressBar.setVisibility(View.VISIBLE);
        UserDto loginUser = new UserDto(usernameField.getText().toString(), passwordField.getText().toString());
        ServicesFactory.getInstance().getUserService().registration(loginUser).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                String head = response.headers().get("authorization");

                Log.i("REG TOKEN", head);
                saveToken(head);
                goMainApp();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("RESPONSE", "FAIL");
                new RegisterDialog(LoginActivity.this).show();
                progressBar.setVisibility(View.GONE);

                troubleDialog();
            }
        });
    }

    private void troubleDialog() {
        new AlertDialog.Builder(LoginActivity.this)
                .setTitle("Some trouble")
                .setNegativeButton(R.string.got_it, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    void saveToken(String text) {
        TokenUtil.setToken(this, text);
        TokenUtil.setUsername(this, usernameField.getText().toString());
    }

    String loadToken() {
        return TokenUtil.getToken(this);
    }

    void goMainApp() {
        Intent intent = new Intent(LoginActivity.this, MapListViewHolder.class);
        startActivity(intent);
    }
}



