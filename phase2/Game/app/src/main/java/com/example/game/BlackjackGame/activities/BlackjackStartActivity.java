package com.example.game.BlackjackGame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.data.GameData;

import static com.example.game.data.GameConstants.TAG;
import static com.example.game.data.GameConstants.USERNAME_KEY;

/**
 * The page that introduces the player to Blackjack
 */
public class BlackjackStartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blackjack_start);
        TextView blackjackBlurb = findViewById(R.id.blackjackBlurb);
        if (GameData.MULTIPLAYER) {
            blackjackBlurb.setText(R.string.blackjack_multiplayer_blurb);
        }

        ((TextView) findViewById(R.id.blackjackBlurb)).setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * The method called when the "start" button is clicked; starts BlackjackPlayActivity
     *
     * @param view - the View that called this method
     */
    public void startGame(View view) {
        Intent intent = new Intent(this, BlackjackPlayActivity.class);
        intent.putExtra(TAG + USERNAME_KEY, GameData.USERNAME);
        startActivity(intent);
    }
}
