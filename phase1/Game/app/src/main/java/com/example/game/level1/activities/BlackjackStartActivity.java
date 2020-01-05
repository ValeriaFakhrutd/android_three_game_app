package com.example.game.level1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;

/**
 * The page that introduces the player to Blackjack
 */
public class BlackjackStartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.black_jack_start);
        ((TextView) findViewById(R.id.blackjackBlurb)).setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * The method called when the "start" button is clicked; starts BlackjackPlayActivity
     *
     * @param view - the View that called this method
     */
    public void startGame(View view) {
        Intent intent = new Intent(this, BlackjackPlayActivity.class);
        startActivity(intent);
    }
}
