No plugins are required to run our project.

We have UML diagrams in the phase2/Game/UML Diagrams directory. Due to the complexity of parts of our code, we used
Visual Paradigm to generate these diagrams from our Java code. As a result, the diagrams have Visual Paradigm
watermarks on them, but are still perfectly legible. If you want to look at them, some of our systems are quite large,
and you will need to be able to scroll in to actually read text.

A quick clarification about our scoreboard system: from the main page of our app, you can click the button "See Local
Scoreboards" to see a leaderboard based on score for each of our three games. From the
statistics page, accessible via the "stats" button on the main page, you can click any of the three buttons at the
bottom to view a ranking of all users on the phone with respect to each game's statistic (longest winning streak
for Blackjack, fewest guesses for Guess the Number, time taken for Cows and Bulls). So we have six distinct
scoreboards, three that rank people with respect to score, and three that rank them with respect to other game
statistics.

Note also that Guess the Number & Cows and Bulls track score in such a way that the smallest score is best, and their
score leaderboards are sorted accordingly.