package com.example.game.service_activities;

import android.os.Bundle;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.game.R;

import java.util.List;

import static android.graphics.Typeface.BOLD;

public abstract class ScoreboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
    }

    /**
     * Takes the given data list which is assumed to be sorted in decreasing order of the second
     * argument in each pair, and inserts the data contained in this list into a table on the
     * screen, so that the data appears like a scoreboard, with the first entry in data marked "1."
     * on the first row, the second marked "2." on the second row, etc.
     * <p>
     * Displays noDataMessage instead if data is empty
     *
     * @param data          - the data to display in the table
     * @param noDataMessage - the message to be displayed if the data list is empty (for example, if
     *                      the data is a list of highscores, then noDataMessage might be "no scores
     *                      to display")
     */
    protected void initialize(List<Pair<String, Integer>> data, String noDataMessage) {
        TableLayout table = findViewById(R.id.highscoreTable);

        if (data.size() == 0) {
            ((TextView) findViewById(R.id.noHighscoresMessage)).setText(noDataMessage);
            return;
        }

        int rowIndex = 0;
        TableRow row;
        TextView rank, name, score;
        for (Pair<String, Integer> pair : data) {
            if (table.getChildAt(rowIndex) != null) {
                // Get the next row of the table
                row = (TableRow) table.getChildAt(rowIndex);

                // Put the rank, name, and score of the current highscore pair into the proper TextViews
                rank = (TextView) row.getChildAt(0);
                name = (TextView) row.getChildAt(1);
                score = (TextView) row.getChildAt(2);

                String rankString = rowIndex + 1 + ".";
                String scoreString = pair.second.toString();

                rank.setText(rankString);

                name.setText(pair.first);

                score.setText(scoreString);

                rowIndex++;
            } else {
                row = new TableRow(this);

                rank = new TextView(this);
                name = new TextView(this);
                score = new TextView(this);

                initializeTextViews(rank, name, score);

                String rankString = rowIndex + 1+ ".";
                String scoreString = pair.second.toString();
                rank.setText(rankString);
                name.setText(pair.first);
                score.setText(scoreString);

                row.addView(rank);
                row.addView(name);
                row.addView(score);

                table.addView(row);
                rowIndex++;
            }
        }
    }

    private void initializeTextViews(TextView rank, TextView name, TextView score) {
        rank.setPadding(0, 3, 0, 3);
        rank.setTextColor(ContextCompat.getColor(this, R.color.highscoreText));
        rank.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
        rank.setTypeface(rank.getTypeface(), BOLD);
        rank.setGravity(Gravity.CENTER);

        name.setPadding(0, 3, 0, 3);
        name.setTextColor(ContextCompat.getColor(this, R.color.highscoreText));
        name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
        name.setTypeface(name.getTypeface(), BOLD);
        name.setGravity(Gravity.CENTER);

        score.setPadding(0, 3, 0, 3);
        score.setTextColor(ContextCompat.getColor(this, R.color.highscoreText));
        score.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
        score.setTypeface(score.getTypeface(), BOLD);
        score.setGravity(Gravity.END);
    }
}
