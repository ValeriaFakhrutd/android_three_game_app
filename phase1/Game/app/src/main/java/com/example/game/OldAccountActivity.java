package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.game.services.GameData;
import com.example.game.services.UserAccountManager;

/**
 * The page displayed when a user is logging in to an existing account
 */
public class OldAccountActivity extends AppCompatActivity implements OldUserPage {
    /**
     * The NewAccountActivityPresenter associated with this activity
     */
    private OldAccountActivityPresenter presenter;

    /**
     * The username of the user logging in
     */
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_account);

        presenter = new OldAccountActivityPresenter(new UserAccountManager(this), this);
    }

    /**
     * Login the user; proceed to the main page of the app and set the USERNAME field in GameData
     * to the user's username so that other classes can access it
     */
    public void login(View view) {
        username = getUsername();
        presenter.loginOldUser(username, getPassword());
    }

    /**
     * Access what the user typed into the password field
     *
     * @return what the user typed into the password field
     */
    private String getPassword() {
        return ((TextView) findViewById(R.id.newAccountPasswordTextField)).getText().toString();
    }

    /**
     * Access what the user typed into the name field
     *
     * @return what the user typed into the name field
     */
    private String getUsername() {
        return ((TextView) findViewById(R.id.oldAccountUsernameTextField)).getText().toString();
    }

    /**
     * Login the user; proceed to the main page of the app and set the USERNAME field in GameData
     * to the user's username so that other classes can access it
     */
    @Override
    public void login() {
        Intent intent = new Intent(this, MainActivity.class);
        GameData.setUsername(username);
        startActivity(intent);
    }

    /**
     * To be called when there is a problem logging in the user
     *
     * @param message - the message to display to the user
     */
    @Override
    public void loginError(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setPositiveButton("Ok", null).create().show();
    }
}
