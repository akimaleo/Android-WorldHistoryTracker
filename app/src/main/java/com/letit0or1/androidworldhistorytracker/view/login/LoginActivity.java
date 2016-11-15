package com.letit0or1.androidworldhistorytracker.view.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.letit0or1.androidworldhistorytracker.R;
import com.letit0or1.androidworldhistorytracker.entity.User;
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
                return false;
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
    }

    public void doLogin() {
        Intent intent = new Intent(LoginActivity.this, MapListViewHolder.class);
startActivity(intent);
//        progressBar.setVisibility(View.VISIBLE);
//        User loginUser = new User(usernameField.getText().toString(), passwordField.getText().toString());
//        ServicesFactory.getInstance().getUserService().authorization(loginUser).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("RESPONSE", "OK");
//                if (response.body().contains("404")) {
//                    new RegisterDialog(LoginActivity.this).show();
//                }
//
////                        progressBar.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.i("RESPONSE", "FAIL");
//                progressBar.setVisibility(View.GONE);
//                troubleDialog();
//            }
//        });
    }

    public void doRegister() {

        progressBar.setVisibility(View.VISIBLE);
        User loginUser = new User(usernameField.getText().toString(), passwordField.getText().toString());
        ServicesFactory.getInstance().getUserService().registration(loginUser).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("RESPONSE", "OK");
                new RegisterDialog(LoginActivity.this).show();

                Intent intent = new Intent(LoginActivity.this, MapListViewHolder.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
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

    public void cancelApp() {
        finish();
    }
}



