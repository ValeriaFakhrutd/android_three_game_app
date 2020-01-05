package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The page displayed when the app initially starts up
 */
public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void newAccount(View view) {
        Intent intent = new Intent(this, NewAccountActivity.class);
        startActivity(intent);
    }

    public void oldAccount(View view) {
        Intent intent = new Intent(this, OldAccountActivity.class);
        startActivity(intent);
    }

    public void Test(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
