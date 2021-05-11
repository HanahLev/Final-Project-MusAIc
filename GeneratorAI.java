//************************************************************************
// File: GeneratorAI.java               CPSC-112 CLASS PROJECT
// 
// Author: Hanah Leventhal		     	Email: hanah.leventhal@yale.edu
//
// Class: GeneratorAI
//
//   --------------------
//   
//      This program generates the times and notes for the song generator
//      feature. The songGenerator method takes the selected tempo, key,
//      and time signatures from the AutoSongGenerator autoModeGUI method.
//      A tree map is created, and once the time and note arrays are filled,
//      their values are filled into the map and returned. The times are
//      found by taking the tempo and creating an entry for each beat. The
//      notes are chosen through a series of nested if/else if/else 
//      statements containing various probability arrays. The probability
//      values are given for each case of a previous note chosen. These 
//      arrays are then used to chose the next note through a random
//      number and decimal ranges.
//
//************************************************************************

import java.util.*;

public class GeneratorAI {
    private static Double[] timeArr;          // times indicated by generator
	private static String[] notesArr;      // notes as strings from generator
    private static int arrLength;

    public static Map<Double,String> songGenerator(int selectedTempo, int selectedKey, int selectedTimeSig){
        // Create Map
        Map<Double, String> generatedSongMap = new TreeMap<Double, String>();
        double lastTime = 0;
        int tempo;

        // Time stamp for every beat occurred within ~1.5min
        if (selectedTempo == 0){
            tempo = 75;
            arrLength = ((int)(tempo * 1.5));
            timeArr = new Double[arrLength];
            notesArr = new String[arrLength];
            for (int n = 0; n < arrLength; n++) { // +3 for Chord resolution
                timeArr[n] = lastTime + (6000. / tempo);
                lastTime = timeArr[n];
            }
        }
        else if (selectedTempo == 1){
            tempo = 115;
            arrLength = ((int)(tempo * 1.5));
            timeArr = new Double[arrLength];
            notesArr = new String[arrLength];
            for (int n = 0; n < arrLength; n++) { // +3 for Chord resolution
                timeArr[n] = lastTime + (6000. / tempo);
                lastTime = timeArr[n];
            }
        }
        else{
            tempo = 155;
            arrLength = ((int)(tempo * 1.5));
            timeArr = new Double[arrLength];
            notesArr = new String[arrLength];
            for (int n = 0; n < arrLength; n++) { // +3 for Chord resolution
                timeArr[n] = lastTime + (6000. / tempo);
                lastTime = timeArr[n];
            }
        }

        double probabilitiesTotal = 0;
        double previousTotal = 0;
        // Selecting Notes -> inspiration from https://stackoverflow.com/questions/9330394/how-to-pick-an-item-by-its-probability
        for (int index = 0; index < arrLength; index++) {
            String[] notePossibilities = getNotePossibilities(index, selectedKey, selectedTimeSig);
            double[] noteProbabilities = getNoteProbabilities(index, selectedKey, selectedTimeSig, notePossibilities);
            for (int i = 0; i < notePossibilities.length; i++) {
                probabilitiesTotal += noteProbabilities[i];
            }
            double randNum = Math.random();
            for (int j = 0; j < notePossibilities.length; j++) {
                if (index == notesArr.length - 1) {
                    notesArr[index] = notePossibilities[0];
                }
                else if ((randNum >= (previousTotal / probabilitiesTotal)) && (randNum < ((previousTotal + noteProbabilities[j]) / probabilitiesTotal))) {
                    notesArr[index] = notePossibilities[j];
                }
                else {
                    int randomIndex = 0 + (int)(Math.random() * 14);
                    notesArr[index] = notePossibilities[randomIndex];
                }
                previousTotal += noteProbabilities[j];
            }
        }

        // Add values to the Map
        for (int i = 0; i < arrLength; i++) {
            generatedSongMap.put(timeArr[i], notesArr[i]);
        }

        return generatedSongMap;
    }

    public static String[] getNotePossibilities(int index, int  selectedKey, int selectedTimeSig) {
        String[] notePossibilities = new String[14];
        // Define note possibilities based on key and time signature
        if (selectedKey == 0){ // C major -> source chord info http://www.piano-keyboard-guide.com/key-of-c.html
            if ((selectedTimeSig == 0 && index % 4 == 0) || (selectedTimeSig == 1 && index % 3 == 0)){ // special case for down beats of 4/4 and 3/4 respectively
                notePossibilities[0] = "qetu";
                notePossibilities[1] = "qetuo";
                notePossibilities[2] = "wryi";
                notePossibilities[3] = "wryip";
                notePossibilities[4] = "etuo";
                notePossibilities[5] = "etuo[";
                notePossibilities[6] = "ryip";
                notePossibilities[7] = "ryipz";
                notePossibilities[8] = "euo[";
                notePossibilities[9] = "euo[x";
                notePossibilities[10] = "eipz";
                notePossibilities[11] = "eipzc";
                notePossibilities[12] = "eo[x";
                notePossibilities[13] = "eo[xv";
            }
            else {
                 //                                C       C7     dm     dm7     em     em7      F      F7      G     Gd7     am     am7     Bo     bm7f5
                //                               CEG     CEGB    DFA    DFAC    EGB    EGBD    FAC    FACE    GBD    GBDF    ACE    ACEG    BDF    BDFA
                notePossibilities = new String[]{"etu", "etuo", "ryi", "ryip", "tuo", "tuo[", "yip", "yipz", "uo[", "uo[x", "ipz", "ipzc", "o[x", "o[xv"};
            }
        }
        else {// G major -> source chord info http://www.piano-keyboard-guide.com/key-of-g.html
            if ((selectedTimeSig == 0 && index % 4 == 0) || (selectedTimeSig == 1 && index % 3 == 0)){ // special case for down beats of 4/4 and 3/4 respectively
                notePossibilities[0] = "tuo[";
                notePossibilities[1] = "tuo[d";
                notePossibilities[2] = "7ipz";
                notePossibilities[3] = "7ipzc";
                notePossibilities[4] = "tuod";
                notePossibilities[5] = "tuodv";
                notePossibilities[6] = "qetu";
                notePossibilities[7] = "qetuo";
                notePossibilities[8] = "wr7i";
                notePossibilities[9] = "wr7ip";
                notePossibilities[10] = "etuo";
                notePossibilities[11] = "etuo[";
                notePossibilities[12] = "r7ip";
                notePossibilities[13] = "r7ipz";
            }
            else {
                //                                 G     G7      am     am7     bm     bm7     C      C7      D       Dd7    em     em7   Fsharpo Fsharpm7f5
                //                                GBD GBDFsharp  ACE    ACEG BDFsharp BDFsharpA CEG   CEGB DFsharpA DFsharpAC EGB  EGBD  FsharpAC FsharpACE 
                notePossibilities = new String[]{"uo[", "uo[d", "ipz", "ipzc", "uod", "uodv", "etu", "etuo", "r7i", "r7ip", "tuo", "tuo[", "7ip", "7ipz"};
            }
        }
        return notePossibilities;
    }

    public static double[] getNoteProbabilities(int index, int  selectedKey, int selectedTimeSig, String[] notePossibilities) {
        double[] noteProbabilities = new double[14];
        // Define note possibilities based on key and time signature
        if (selectedKey == 0){ // C major
            //  C       C7     dm     dm7     em     em7      F      F7      G     Gd7     am     am7     Bo     bm7f5

            // Create Note possibilities -> source Chord progressions from https://www.hooktheory.com/trends
            if (index == 0) {
                noteProbabilities[0] = .15;
                noteProbabilities[1] = .008;
                noteProbabilities[2] = .05; 
                noteProbabilities[3] = .02;
                noteProbabilities[4] = .03;
                noteProbabilities[5] = .01;
                noteProbabilities[6] = .15;
                noteProbabilities[7] = .02;
                noteProbabilities[8] = .14;
                noteProbabilities[9] = .002;
                noteProbabilities[10] = .14;
                noteProbabilities[11] = .02;
                noteProbabilities[12] = .002;
                noteProbabilities[13] = .001;
            }
            else if (notesArr[index-1] == notePossibilities[0]){ // if previous note is C
                noteProbabilities[0] = 0.;
                noteProbabilities[1] = .01;
                noteProbabilities[2] = .06;
                noteProbabilities[3] = .02;
                noteProbabilities[4] = .04;
                noteProbabilities[5] = .007;
                noteProbabilities[6] = .19;
                noteProbabilities[7] = .01;
                noteProbabilities[8] = .25;
                noteProbabilities[9] = .009;
                noteProbabilities[10] = .10;
                noteProbabilities[11] = .01;
                noteProbabilities[12] = .02;
                noteProbabilities[13] = .02;
            }
            else if (notesArr[index-1] == notePossibilities[1]){ // if previous note is C7
                noteProbabilities[0] = .1;
                noteProbabilities[1] = 0.;
                noteProbabilities[2] = .02;
                noteProbabilities[3] = .07;
                noteProbabilities[4] = .009;
                noteProbabilities[5] = .03;
                noteProbabilities[6] = .07;
                noteProbabilities[7] = .15;
                noteProbabilities[8] = .03;
                noteProbabilities[9] = .01;
                noteProbabilities[10] = .05;
                noteProbabilities[11] = .06;
                noteProbabilities[12] = .006;
                noteProbabilities[13] = .009;
            }
            else if (notesArr[index-1] == notePossibilities[2]){ // if previous note is dm
                noteProbabilities[0] = .13;
                noteProbabilities[1] = .03;
                noteProbabilities[2] = 0.;
                noteProbabilities[3] = .02;
                noteProbabilities[4] = .09;
                noteProbabilities[5] = .008;
                noteProbabilities[6] = .16;
                noteProbabilities[7] = .007;
                noteProbabilities[8] = .16;
                noteProbabilities[9] = .02;
                noteProbabilities[10] = .18;
                noteProbabilities[11] = .008;
                noteProbabilities[12] = .01;
                noteProbabilities[13] = .01;
            }
            else if (notesArr[index-1] == notePossibilities[3]){ // if previous note is dm7
                noteProbabilities[0] = .06;
                noteProbabilities[1] = .02;
                noteProbabilities[2] = .02;
                noteProbabilities[3] = 0.;
                noteProbabilities[4] = .03;
                noteProbabilities[5] = .1;
                noteProbabilities[6] = .08;
                noteProbabilities[7] = .04;
                noteProbabilities[8] = .13;
                noteProbabilities[9] = .007;
                noteProbabilities[10] = .04;
                noteProbabilities[11] = .07;
                noteProbabilities[12] = .007;
                noteProbabilities[13] = .007;
            }
            else if (notesArr[index-1] == notePossibilities[4]){ // if previous note is em
                noteProbabilities[0] = .05;
                noteProbabilities[1] = .001;
                noteProbabilities[2] = .08;
                noteProbabilities[3] = .02;
                noteProbabilities[4] = 0.;
                noteProbabilities[5] = .001;
                noteProbabilities[6] = .33;
                noteProbabilities[7] = .02;
                noteProbabilities[8] = .08;
                noteProbabilities[9] = .008;
                noteProbabilities[10] = .26;
                noteProbabilities[11] = .02;
                noteProbabilities[12] = .001;
                noteProbabilities[13] = .001;
            }
            else if (notesArr[index-1] == notePossibilities[5]){ // if previous note is em7
                noteProbabilities[0] = .02;
                noteProbabilities[1] = .006;
                noteProbabilities[2] = .02;
                noteProbabilities[3] = .11;
                noteProbabilities[4] = .03;
                noteProbabilities[5] = 0.;
                noteProbabilities[6] = .12;
                noteProbabilities[7] = .13;
                noteProbabilities[8] = .03;
                noteProbabilities[9] = .007;
                noteProbabilities[10] = .15;
                noteProbabilities[11] = .15;
                noteProbabilities[12] = .001;
                noteProbabilities[13] = .001;
            }
            else if (notesArr[index-1] == notePossibilities[6]){ // if previous note is F
                noteProbabilities[0] = .29;
                noteProbabilities[1] = .001;
                noteProbabilities[2] = .05;
                noteProbabilities[3] = .01;
                noteProbabilities[4] = .04;
                noteProbabilities[5] = .006;
                noteProbabilities[6] = 0.;
                noteProbabilities[7] = .01;
                noteProbabilities[8] = .29;
                noteProbabilities[9] = .009;
                noteProbabilities[10] = .1;
                noteProbabilities[11] = .01;
                noteProbabilities[12] = .009;
                noteProbabilities[13] = .009;
            }
            else if (notesArr[index-1] == notePossibilities[7]){ // if previous note is F7
                noteProbabilities[0] = .08;
                noteProbabilities[1] = .04;
                noteProbabilities[2] = .03;
                noteProbabilities[3] = .04;
                noteProbabilities[4] = .04;
                noteProbabilities[5] = .1;
                noteProbabilities[6] = .05;
                noteProbabilities[7] = 0.;
                noteProbabilities[8] = .22;
                noteProbabilities[9] = .02;
                noteProbabilities[10] = .07;
                noteProbabilities[11] = .03;
                noteProbabilities[12] = .001;
                noteProbabilities[13] = .001;
            }
            else if (notesArr[index-1] == notePossibilities[8]){ // if previous note is G
                noteProbabilities[0] = .21;
                noteProbabilities[1] = .001;
                noteProbabilities[2] = .06;
                noteProbabilities[3] = .02;
                noteProbabilities[4] = .04;
                noteProbabilities[5] = .009;
                noteProbabilities[6] = .21;
                noteProbabilities[7] = .01;
                noteProbabilities[8] = 0.;
                noteProbabilities[9] = .01;
                noteProbabilities[10] = .26;
                noteProbabilities[11] = .02;
                noteProbabilities[12] = .01;
                noteProbabilities[13] = .01;
            }
            else if (notesArr[index-1] == notePossibilities[9]){ // if previous note is Gd7
                noteProbabilities[0] = .39;
                noteProbabilities[1] = .06;
                noteProbabilities[2] = .01;
                noteProbabilities[3] = .04;
                noteProbabilities[4] = .009;
                noteProbabilities[5] = .02;
                noteProbabilities[6] = .05;
                noteProbabilities[7] = .01;
                noteProbabilities[8] = .07;
                noteProbabilities[9] = 0.;
                noteProbabilities[10] = .04;
                noteProbabilities[11] = .04;
                noteProbabilities[12] = .008;
                noteProbabilities[13] = .008;
            }
            else if (notesArr[index-1] == notePossibilities[10]){ // if previous note is am
                noteProbabilities[0] = .11;
                noteProbabilities[1] = .01;
                noteProbabilities[2] = .06;
                noteProbabilities[3] = .01;
                noteProbabilities[4] = .06;
                noteProbabilities[5] = .007;
                noteProbabilities[6] = .24;
                noteProbabilities[7] = .02;
                noteProbabilities[8] = .2;
                noteProbabilities[9] = .01;
                noteProbabilities[10] = 0.;
                noteProbabilities[11] = .01;
                noteProbabilities[12] = .01;
                noteProbabilities[13] = .01;
            }
            else if (notesArr[index-1] == notePossibilities[11]){ // if previous note is am7
                noteProbabilities[0] = .05;
                noteProbabilities[1] = .01;
                noteProbabilities[2] = .03;
                noteProbabilities[3] = .09;
                noteProbabilities[4] = .02;
                noteProbabilities[5] = .06;
                noteProbabilities[6] = .13;
                noteProbabilities[7] = .05;
                noteProbabilities[8] = .16;
                noteProbabilities[9] = .006;
                noteProbabilities[10] = .06;
                noteProbabilities[11] = 0.;
                noteProbabilities[12] = .001;
                noteProbabilities[13] = .001;
            }
            else if (notesArr[index-1] == notePossibilities[12]){ // if previous note is Bo
                noteProbabilities[0] = .24;
                noteProbabilities[1] = .007;
                noteProbabilities[2] = .007;
                noteProbabilities[3] = .001;
                noteProbabilities[4] = .04;
                noteProbabilities[5] = .02;
                noteProbabilities[6] = .04;
                noteProbabilities[7] = .007;
                noteProbabilities[8] = .05;
                noteProbabilities[9] = .007;
                noteProbabilities[10] = .21;
                noteProbabilities[11] = .01;
                noteProbabilities[12] = 0.;
                noteProbabilities[13] = .01;
            }
            else { // if the previous note is bm7f5
                noteProbabilities[0] = .06;
                noteProbabilities[1] = .01;
                noteProbabilities[2] = .001;
                noteProbabilities[3] = .02;
                noteProbabilities[4] = .03;
                noteProbabilities[5] = .03;
                noteProbabilities[6] = .04;
                noteProbabilities[7] = .001;
                noteProbabilities[8] = .02;
                noteProbabilities[9] = .008;
                noteProbabilities[10] = .03;
                noteProbabilities[11] = .01;
                noteProbabilities[12] = 0.;
                noteProbabilities[13] = .001;
            }
        }
        else {// G major
            //  G     G7      am     am7     bm     bm7     C      C7      D       Dd7    em     em7   Fsharpo Fsharpm7f5

            // Create Note possibilities -> source Chord progressions from https://www.hooktheory.com/trends
            if (index == 0) {
                noteProbabilities[0] = .15;
                noteProbabilities[1] = .008;
                noteProbabilities[2] = .05;
                noteProbabilities[3] = .02;
                noteProbabilities[4] = .03;
                noteProbabilities[5] = .01;
                noteProbabilities[6] = .15;
                noteProbabilities[7] = .02;
                noteProbabilities[8] = .14;
                noteProbabilities[9] = .01;
                noteProbabilities[10] = .14;
                noteProbabilities[11] = .02;
                noteProbabilities[12] = .002;
                noteProbabilities[13] = .001;
            }
            else if (notesArr[index-1] == notePossibilities[0]){ // if previous note is G
                noteProbabilities[0] = 0.;
                noteProbabilities[1] = .01;
                noteProbabilities[2] = .06;
                noteProbabilities[3] = .02;
                noteProbabilities[4] = .04;
                noteProbabilities[5] = .007;
                noteProbabilities[6] = .19;
                noteProbabilities[7] = .01;
                noteProbabilities[8] = .25;
                noteProbabilities[9] = .009;
                noteProbabilities[10] = .1;
                noteProbabilities[11] = .01;
                noteProbabilities[12] = .001;
                noteProbabilities[13] = .001;
            }
            else if (notesArr[index-1] == notePossibilities[1]){ // if previous note is G7
                noteProbabilities[0] = .1;
                noteProbabilities[1] = 0.;
                noteProbabilities[2] = .02;
                noteProbabilities[3] = .07;
                noteProbabilities[4] = .009;
                noteProbabilities[5] = .03;
                noteProbabilities[6] = .07;
                noteProbabilities[7] = .15;
                noteProbabilities[8] = .03;
                noteProbabilities[9] = .01;
                noteProbabilities[10] = .05;
                noteProbabilities[11] = .06;
                noteProbabilities[12] = .001;
                noteProbabilities[13] = .009;
            }
            else if (notesArr[index-1] == notePossibilities[2]){ // if previous note is am
                noteProbabilities[0] = .13;
                noteProbabilities[1] = .001;
                noteProbabilities[2] = 0.;
                noteProbabilities[3] = .02;
                noteProbabilities[4] = .09;
                noteProbabilities[5] = .008;
                noteProbabilities[6] = .16;
                noteProbabilities[7] = .007;
                noteProbabilities[8] = .16;
                noteProbabilities[9] = .02;
                noteProbabilities[10] = .18;
                noteProbabilities[11] = .008;
                noteProbabilities[12] = .001;
                noteProbabilities[13] = .001;
            }
            else if (notesArr[index-1] == notePossibilities[3]){ // if previous note is am7
                noteProbabilities[0] = .06;
                noteProbabilities[1] = .02;
                noteProbabilities[2] = .02;
                noteProbabilities[3] = 0.;
                noteProbabilities[4] = .03;
                noteProbabilities[5] = .1;
                noteProbabilities[6] = .08;
                noteProbabilities[7] = .04;
                noteProbabilities[8] = .13;
                noteProbabilities[9] = .12;
                noteProbabilities[10] = .05;
                noteProbabilities[11] = .07;
                noteProbabilities[12] = .007;
                noteProbabilities[13] = .007;
            }
            else if (notesArr[index-1] == notePossibilities[4]){ // if previous note is bm
                noteProbabilities[0] = .05;
                noteProbabilities[1] = .001;
                noteProbabilities[2] = .08;
                noteProbabilities[3] = .02;
                noteProbabilities[4] = 0.;
                noteProbabilities[5] = .001;
                noteProbabilities[6] = .33;
                noteProbabilities[7] = .02;
                noteProbabilities[8] = .08;
                noteProbabilities[9] = .001;
                noteProbabilities[10] = .26;
                noteProbabilities[11] = .02;
                noteProbabilities[12] = .001;
                noteProbabilities[13] = .001;
            }
            else if (notesArr[index-1] == notePossibilities[5]){ // if previous note is bm7
                noteProbabilities[0] = .02;
                noteProbabilities[1] = .006;
                noteProbabilities[2] = .02;
                noteProbabilities[3] = .11;
                noteProbabilities[4] = .03;
                noteProbabilities[5] = 0.;
                noteProbabilities[6] = .12;
                noteProbabilities[7] = .13;
                noteProbabilities[8] = .03;
                noteProbabilities[9] = .007;
                noteProbabilities[10] = .15;
                noteProbabilities[11] = .15;
                noteProbabilities[12] = .001;
                noteProbabilities[13] = .001;
            }
            else if (notesArr[index-1] == notePossibilities[6]){ // if previous note is C
                noteProbabilities[0] = .29;
                noteProbabilities[1] = .001;
                noteProbabilities[2] = .05;
                noteProbabilities[3] = .01;
                noteProbabilities[4] = .04;
                noteProbabilities[5] = .006;
                noteProbabilities[6] = 0.;
                noteProbabilities[7] = .01;
                noteProbabilities[8] = .29;
                noteProbabilities[9] = .009;
                noteProbabilities[10] = .1;
                noteProbabilities[11] = .01;
                noteProbabilities[12] = .001;
                noteProbabilities[13] = .001;
            }
            else if (notesArr[index-1] == notePossibilities[7]){ // if previous note is C7
                noteProbabilities[0] = .08;
                noteProbabilities[1] = .04;
                noteProbabilities[2] = .03;
                noteProbabilities[3] = .04;
                noteProbabilities[4] = .04;
                noteProbabilities[5] = .1;
                noteProbabilities[6] = .05;
                noteProbabilities[7] = 0.;
                noteProbabilities[8] = .22;
                noteProbabilities[9] = .02;
                noteProbabilities[10] = .07;
                noteProbabilities[11] = .03;
                noteProbabilities[12] = .001;
                noteProbabilities[13] = .001;
            }
            else if (notesArr[index-1] == notePossibilities[8]){ // if previous note is D
                noteProbabilities[0] = .21;
                noteProbabilities[1] = .001;
                noteProbabilities[2] = .06;
                noteProbabilities[3] = .02;
                noteProbabilities[4] = .04;
                noteProbabilities[5] = .009;
                noteProbabilities[6] = .21;
                noteProbabilities[7] = .01;
                noteProbabilities[8] = 0.;
                noteProbabilities[9] = .01;
                noteProbabilities[10] = .26;
                noteProbabilities[11] = .02;
                noteProbabilities[12] = .001;
                noteProbabilities[13] = .001;
            }
            else if (notesArr[index-1] == notePossibilities[9]){ // if previous note is Dd7
                noteProbabilities[0] = .39;
                noteProbabilities[1] = .06;
                noteProbabilities[2] = .01;
                noteProbabilities[3] = .04;
                noteProbabilities[4] = .009;
                noteProbabilities[5] = .02;
                noteProbabilities[6] = .05;
                noteProbabilities[7] = .01;
                noteProbabilities[8] = .07;
                noteProbabilities[9] = 0.;
                noteProbabilities[10] = .04;
                noteProbabilities[11] = .04;
                noteProbabilities[12] = .001;
                noteProbabilities[13] = .001;
            }
            else if (notesArr[index-1] == notePossibilities[10]){ // if previous note is em
                noteProbabilities[0] = .11;
                noteProbabilities[1] = .001;
                noteProbabilities[2] = .06;
                noteProbabilities[3] = .01;
                noteProbabilities[4] = .06;
                noteProbabilities[5] = .007;
                noteProbabilities[6] = .24;
                noteProbabilities[7] = .02;
                noteProbabilities[8] = .2;
                noteProbabilities[9] = .001;
                noteProbabilities[10] = 0.;
                noteProbabilities[11] = .01;
                noteProbabilities[12] = .001;
                noteProbabilities[13] = .001;
            }
            else if (notesArr[index-1] == notePossibilities[11]){ // if previous note is em7
                noteProbabilities[0] = .05;
                noteProbabilities[1] = .01;
                noteProbabilities[2] = .03;
                noteProbabilities[3] = .09;
                noteProbabilities[4] = .02;
                noteProbabilities[5] = .06;
                noteProbabilities[6] = .13;
                noteProbabilities[7] = .05;
                noteProbabilities[8] = .16;
                noteProbabilities[9] = .01;
                noteProbabilities[10] = .06;
                noteProbabilities[11] = 0.;
                noteProbabilities[12] = .001;
                noteProbabilities[13] = .001;
            }
            else if (notesArr[index-1] == notePossibilities[12]){ // if previous note is Fsharpo
                noteProbabilities[0] = .24;
                noteProbabilities[1] = .001;
                noteProbabilities[2] = .007;
                noteProbabilities[3] = .001;
                noteProbabilities[4] = .04;
                noteProbabilities[5] = .02;
                noteProbabilities[6] = .04;
                noteProbabilities[7] = .007;
                noteProbabilities[8] = .05;
                noteProbabilities[9] = .007;
                noteProbabilities[10] = .21;
                noteProbabilities[11] = .01;
                noteProbabilities[12] = 0.;
                noteProbabilities[13] = .01;
            }
            else { // if previous note is Fsharpm7f5
                noteProbabilities[0] = .06;
                noteProbabilities[1] = .01;
                noteProbabilities[2] = .001;
                noteProbabilities[3] = .02;
                noteProbabilities[4] = .03;
                noteProbabilities[5] = .03;
                noteProbabilities[6] = .04;
                noteProbabilities[7] = .001;
                noteProbabilities[8] = .02;
                noteProbabilities[9] = .008;
                noteProbabilities[10] = .03;
                noteProbabilities[11] = .01;
                noteProbabilities[12] = .03;
                noteProbabilities[13] = 0.;
            }
        }
        return noteProbabilities;
    }
}