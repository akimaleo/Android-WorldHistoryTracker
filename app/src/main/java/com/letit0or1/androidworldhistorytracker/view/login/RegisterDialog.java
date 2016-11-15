package com.letit0or1.androidworldhistorytracker.view.login;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.letit0or1.androidworldhistorytracker.R;
import com.letit0or1.androidworldhistorytracker.view.login.LoginActivity;

/**
 * Created by akimaleo on 15.11.16.
 */

public class RegisterDialog extends Dialog {

    private LoginActivity loginActivity;

    public RegisterDialog(LoginActivity loginActivity) {
        super(loginActivity);
        this.loginActivity = loginActivity;

    }

    private Button cancel, confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_registration);
//init
        cancel = (Button) findViewById(R.id.cancel_action);
        confirm = (Button) findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginActivity.doRegister();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
