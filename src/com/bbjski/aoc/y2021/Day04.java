package com.bbjski.aoc.y2021;

import com.bbjski.aoc.model.BingoCard;

import java.util.ArrayList;
import java.util.Scanner;

public class Day04 {

    public static void main(String[] args) {

        System.out.println(">>> Enter bingo data:");

        Scanner input = new Scanner(System.in);

        boolean playedNumbersPopulated = false;
        String[] playedNumbers = new String[0];

        ArrayList<BingoCard> cards = new ArrayList<>();
        BingoCard currentCard = new BingoCard();

        while (input.hasNextLine()){
            if (!playedNumbersPopulated) {
                playedNumbers = input.nextLine().split(",");
                playedNumbersPopulated = true;
            } else {
                String line = input.nextLine().trim();
                if (line.length() == 0) {
                    currentCard = currentCard == null || !currentCard.isEmptyCard() ? new BingoCard() : currentCard;
                    cards.add(currentCard);
                } else {
                    currentCard.addLine(line);
                }

            }
        }

        currentCard = null;
        BingoCard lastCard = null;
        if (playedNumbers != null && playedNumbers.length > 0) {
            for (String number : playedNumbers) {
                boolean bingo = false;
                BingoCard winningCard = null;
                for(BingoCard card : (ArrayList<BingoCard>)cards.clone()) {
                    bingo = card.markNumber(Integer.parseInt(number));
                    if (bingo) {
                        currentCard = currentCard == null ? card : currentCard;
                        lastCard = card;
                        cards.remove(card);
                    }
                }
            }
        }

        int product1 = currentCard.getUnmarkedSum() * currentCard.getWinningNumber();
        int product2 = lastCard.getUnmarkedSum() * lastCard.getWinningNumber();

        System.out.println(">>> [part 1]: " + product1);
        System.out.println(">>> [part 2]: " + product2);
    }


}
