package com.example.game.level1.domain;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a Hand of playing cards
 */
public class Hand implements Iterable<Card> {
    /**
     * An array of cards representing this hand
     */
    private ArrayList<Card> hand;

    /**
     * Create an empty hand
     */
    Hand() {
        hand = new ArrayList<>();
    }

    /**
     * Add the given card to this hand
     *
     * @param card - the card to be added to this hand
     */
    void addCard(Card card) {
        this.hand.add(card);
    }

    /**
     * Return a card iterator for this object
     *
     * @return a card iterator for this object
     */
    @NonNull
    public Iterator<Card> iterator() {
        return new HandIterator();
    }

    private class HandIterator implements Iterator<Card> {
        /**
         * The index of the iterator with respect to hand
         */
        private int index;

        /**
         * Create a new HandIterator
         */
        HandIterator() {
            index = 0;
        }

        /**
         * Check if this iterator has another element to return
         *
         * @return - true if there is another element and false otherwise
         */
        public boolean hasNext() {
            return index < hand.size();
        }

        /**
         * Get the next element from the iterator
         *
         * @return - the next element
         */
        public Card next() {
            return hand.get(index++);
        }
    }
}
