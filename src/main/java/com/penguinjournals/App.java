package com.penguinjournals;


import java.util.ArrayList;

public class App
{
    public static void main( String[] args ) {
        StAlbansScore stAlbansScore = new StAlbansScore();
        FileParser fileParser = new FileParser();
        ArrayList<String> positives = fileParser.Parse("positives.txt");
        ArrayList<String> negatives = fileParser.Parse("negatives.txt");

        for (String positive : positives) {
            stAlbansScore.score(positive);
        }

        for (String negative : negatives) {
            stAlbansScore.score(negative);
        }
    }
}
