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
 * The page displayed when a user is creating a new account
 */
public class NewAccountActivity extends AppCompatActivity implements NewUserPage {
    /**
     * The username of the user logging in
     */
    private String username;

    /**
     * The NewAccountActivityPresenter associated with this activity
     */
    private NewAccountActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        presenter = new NewAccountActivityPresenter(new UserAccountManager(this), this);
    }

    /**
     * Register a new user by passing the request on to the presenter
     *
     * @param view - the View that called this method
     */
    public void register(View view) {
        username = getUsername();
        presenter.registerNewUser(username, getPassword());
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
        return ((TextView) findViewById(R.id.newAccountUsernameTextField)).getText().toString();
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
     * To be called when there is a problem creating a new account for the user
     *
     * @param message - the message to display to the user
     */
    @Override
    public void accountCreationError(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setPositiveButton("Ok", null).create().show();
    }
}
