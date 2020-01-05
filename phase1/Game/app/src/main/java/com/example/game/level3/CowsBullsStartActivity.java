package com.example.game.level3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;

public class CowsBullsStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cows_bulls_start);
    }


    /**
     * Method for button toGame to proceed to CowsBullsActivity
     */
    public void toGame(View view) {
        Intent intent = new Intent(this, CowsBullsActivity.class);
        startActivity(intent);
    }
}
