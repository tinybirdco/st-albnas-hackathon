package com.penguinjournals;

import java.util.ArrayList;

public class StAlbansScore {

    public StAlbansScore() {}
    private static final String ST_ALBANS = "St. Albans";
    private static final Double LOWEST_SCORE_ACHIEVED_BY_A_POSITIVE_CANDIDATE = 5.75;
    private static final Double MATCHING_SCORE = 1.0;
    private static final Double NEAR_MATCHING_SCORE = 0.75;
    private static final Double NOT_MATCHING_PENALTY = -0.75;
    private static final Double OUT_OF_SIZE_PENALTY = -1.0;

    public void score(String input) {
        String message = "NAY";
        Double wordScore = 0.0;
        String tolowerizedInput = input.toLowerCase();
        for (int position=0; position < tolowerizedInput.length(); position++) {
            char character = tolowerizedInput.charAt(position);
            wordScore = wordScore + scoreForCharacterAndPosition(character, position);
        }
        if (wordScore >= LOWEST_SCORE_ACHIEVED_BY_A_POSITIVE_CANDIDATE) {
            message = "YAY";
        }
        System.out.println(input + " -> " + message + " (" + wordScore + ")");
    }

    private Double scoreForCharacterAndPosition(char character, int position) {
        Double characterScore = 0.0;
        char[] stAlbansCharacters = ST_ALBANS.toLowerCase().toCharArray();
        if (position < stAlbansCharacters.length) {
            if (stAlbansCharacters[position] == character) {
                characterScore = MATCHING_SCORE;
            } else if (position > 0 && stAlbansCharacters[position-1] == character) {
                characterScore = NEAR_MATCHING_SCORE;
            } else if (position < (stAlbansCharacters.length - 1) && stAlbansCharacters[position+1] == character) {
                characterScore = NEAR_MATCHING_SCORE;
            } else {
                characterScore = NOT_MATCHING_PENALTY;
            }
        } else {
            characterScore = OUT_OF_SIZE_PENALTY;
        }
        return characterScore;
    }
}
