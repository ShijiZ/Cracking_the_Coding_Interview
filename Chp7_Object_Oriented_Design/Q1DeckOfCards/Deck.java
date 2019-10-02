package Q1DeckOfCards;

import CtCILibrary.AssortedMethods;

import java.util.ArrayList;

public class Deck <T extends Card> {
    private ArrayList<T> cards; // all cards, dealt or not
    private int dealtIndex = 0; // marks first undealt card

    public Deck(){}

    public void setDeckOfCards(ArrayList<T> deckOfCards){
        cards = deckOfCards;
    }

    public void shuffle(){
        for (int i = 0; i < cards.size(); i++){
            int j = AssortedMethods.randomIntInRange(i, cards.size() - 1 - i);
            T cards1 = cards.get(i);
            T cards2 = cards.get(j);
            cards.set(i, cards2);
            cards.set(j, cards1);
        }
    }

    public int remainingCards(){
        return cards.size() - dealtIndex;
    }

    public T[] dealHand(int number){
        if (remainingCards() < number)
            return null;

        T[] hand = (T[]) new Card[number];
        int count = 0;
        while (count < number){
            T card = dealCard();
            if (card != null){
                hand[count] = card;
                count++;
            }
        }
        return hand;
    }

    public T dealCard(){
        if (remainingCards() == 0)
            return null;
        T card = cards.get(dealtIndex);
        card.markUnavailable();
        dealtIndex++;

        return card;
    }

    public void print(){
        for (Card card : cards)
            card.print();
    }

}
