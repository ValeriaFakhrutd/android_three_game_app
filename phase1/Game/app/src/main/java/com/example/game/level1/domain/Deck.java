package com.example.game.level1.domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a deck of playing cards
 */
public class Deck {
    /**
     * The array of cards that are in this deck
     * <p>
     * The front of the ArrayList is the top of the deck. i.e. dealing from
     * the deck removes the first element from this ArrayList
     */
    private ArrayList<Card> deck;

    /**
     * Create an un-shuffled deck
     */
    public Deck() {
        deck = newDeck();
    }

    /**
     * Take the card on top of the deck, remove it from the deck, and return it
     *
     * @return The card dealt from the top of the deck
     */
    public Card deal() {
        return deck.remove(0);
    }

    /**
     * Deal multiple Cards from this deck at once
     *
     * @param numCards - how many Cards to deal
     * @return The array of cards dealt from this deck
     */
    public Card[] deal(int numCards) {
        Card[] cards = new Card[numCards];
        for (int i = 0; i < numCards; i++) {
            cards[i] = this.deal();
        }

        return cards;
    }

    /**
     * Shuffle this deck, turning it into a permutation of itself
     */
    public void shuffle() {
        Random rand = new Random();

        ArrayList<Card> newDeck = new ArrayList<>(deck);

        deck = new ArrayList<>();

        while (newDeck.size() > 0) {
            deck.add(newDeck.remove(rand.nextInt(newDeck.size())));
        }
    }

    /**
     * Create an ArrayList of cards that represents a fresh deck; one which is ordered
     * and has yet to be shuffled
     *
     * @return An ordered ArrayList of the 52 cards in a standard deck of cards
     */
    private ArrayList<Card> newDeck() {
        ArrayList<Card> newDeck = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                newDeck.add(new Card(rank, suit));
            }
        }

        return newDeck;
    }
}
