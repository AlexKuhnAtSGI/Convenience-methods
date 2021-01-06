//COMP 1006A/1406A Assignment 4 Problem 2
//By Alexander Kuhn, ID# 101023154, August 2nd, 2016
//Purpose: This class allows you to count the words in a text file, and prints the 10 most frequently occurring words to the console alongside their frequency.
//Preconditions: Needs a text file as argument
/* need some classes from java.io to read from files */
import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/* other classes ... add the ones you need here */
import java.util.Hashtable;

public class WordStats{
  
  
  public static void main(String[] args){
    /* the command line will be used to specify a filename */
    if( args == null || args.length != 1){
      System.out.println("Usage: java BookStats fname");
      return;
    }
    
    /* our dictionary */
    Hashtable<String, Integer> words = new Hashtable<String, Integer>();
    
    /* used for reading file */
    File    infile;
    Scanner in;
    
    
    /* let's measure the time */
    
    long startTime = System.nanoTime();
    try{
      infile = new File(args[0]);
      in = new Scanner(infile);
      
      /* ------------------------------------- */
      /* ------------------------------------- */
      /* you may modify this part of main      */
      /* (so not change anything else)         */
      
      String input = null;
      String[] sortingString = new String[2];
      //An empty string array that will copy arrays onto itself so I can sort arrays of words by their frequency
      int testSum = 0;
      //Retains the total number of words in the input file
      String visualAid = "";
      //Will be used to visually compare the frequency of the top 10 most-used words in the file
      double relativeFrequency = 0.0;
      //This will help adjust the size of the visualAid string depending on how often a word was used compared to the most frequent word
      int tieNumber = 0;
      //If words are tied for 10th most frequent, this will tell the for loop to keep checking the array and printing the other tied words
      String tabString = "";
      int spacesNeeded = 0;
      int lengthOfLongestWord = 0;
      //These three make sure the output strings are properly indented
      
      //I like to declare my variables early, so these won't come into play until later in the program
      
      while( in.hasNext() ){
        input = in.next().trim().toLowerCase();
        input = input.replaceAll("[\\p{P}&&[^\u0027]&&[^\u002D]]", "");
        if (input.length() >= lengthOfLongestWord) {
          lengthOfLongestWord = input.length();
        }
        if (words.containsKey(input)) {
          words.put(input, words.get(input) + 1);
        }
        else {
        words.put(input, 1);
        }
      }
      //The hashtable reads in all the words from the txt file provided and stores them alongside their frequency
      //In order to make this data sortable, I output it to an ordinary array
      
      String[][] wordSorter = new String[words.values().toArray().length][2];
      //This will have an array that contains one array for each unique word, storing its values from the hashtable
      
      for (int i = 0; i < words.values().toArray().length; i += 1) {
        wordSorter[i][0] = words.values().toArray()[i].toString();
        wordSorter[i][1] = words.keySet().toArray()[i].toString();
      }
      
       for (int i = wordSorter.length; i >= 0; i -= 1) {
         for (int x = 0; x < (wordSorter.length - 1); x += 1) {
           if ((Integer.parseInt(wordSorter[x][0])) < (Integer.parseInt(wordSorter[x + 1][0]))) {
             System.arraycopy(wordSorter[x], 0, sortingString, 0, wordSorter[x].length);
             System.arraycopy(wordSorter[x + 1], 0, wordSorter[x], 0, wordSorter[x + 1].length);
             System.arraycopy(sortingString, 0, wordSorter[x + 1], 0, sortingString.length);
           }
         }
      }
       //This is a simple bubblesort, putting the most frequent words at the top of the array and the least frequent at the bottom
      
      for (int i = 0; i < wordSorter.length; i += 1) {
        testSum += Integer.parseInt(wordSorter[i][0]);
      }
      
      System.out.println("Total number of words is " + testSum);
      if (testSum == 0) {
        System.out.println("Please pick a text file with words in it next time.");
        return;
      }
      //A 0-word text file would throw an exception if I tried to pass it through this next part, so I end that branch here
      
      System.out.println("-----------------------------------------------------------------");
      for (int i = 0; i < 10; i += 1) {
        visualAid = "";
        tabString = "";
        spacesNeeded = (lengthOfLongestWord - wordSorter[i + tieNumber][1].length());
        relativeFrequency = ((double)Integer.parseInt(wordSorter[i + tieNumber][0])) / ((double)Integer.parseInt(wordSorter[0][0]));
        
        for (int x = 0; x < spacesNeeded + 1; x += 1) {
          tabString += " ";
        }
        for (int x = 0; x < (50 * relativeFrequency); x += 1) {
          visualAid += "#";
        }
        //These two loops establish proper spacing for the output string as well as proper scale for the bars comparing their frequency
        
        System.out.println(wordSorter[i + tieNumber][1] + tabString + "|" + visualAid + "[" + wordSorter[i + tieNumber][0] + "]");
        
        if ((i + tieNumber) > wordSorter.length - 2) {
          break;
        }
        if ((Integer.parseInt(wordSorter[i + tieNumber][0]) == Integer.parseInt(wordSorter[i + tieNumber + 1][0])) && (i == 9)) {
          i -= 1;
          tieNumber += 1;
        }
        //Here I check to make sure I don't get an array-out-of-bounds exception, then I check to see if two things are true
        //Firstly, that the current word has a frequency equivalent to that of the next word
        //Secondly, that we have already gone through the main loop 10 times
        //If both these things are true, we need another main loop
      }
      System.out.println("-----------------------------------------------------------------");
      //This mimics the suggested output from the criteria
      
      
      /*                                       */
      /* this ends the part of main you can    */
      /* modify                                */
      /* ------------------------------------- */
      /* ------------------------------------- */
      
    }catch(FileNotFoundException e){
      System.err.println("File " + args[0] + " was not found");
      System.err.println("Exception thrown : " + e);      
    }
    
    long endTime = System.nanoTime();
    double time = (endTime-startTime)/1e9;
    System.err.println("finished in " + String.format("%0$.3f", time) + " seconds");
    
    
  }
  
}