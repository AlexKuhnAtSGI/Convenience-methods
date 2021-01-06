//COMP 1006A/1406A Assignment 4 Problem 1
//By Alexander Kuhn, ID# 101023154, August 2nd, 2016
//Purpose: This class allows you to check if the parentheses ("()") and brackets ("{}, []") in a string are balanced or not,
//as well as count how many strings in an array are balanced.
import java.util.Stack;

public class Balanced{
  
  public static boolean isBalanced(String s){
    //Purpose: Checks if a string is balanced.
    //Preconditions: Parameter s is a non-null string.
    //Postconditions: Returns true if the input parameter s is balanced and false otherwise.
    
    Stack<Character> balanceChecker = new Stack<Character>();
    
    for (int i = 0; i < s.length(); i +=1) {
      if (s.charAt(i) == '(') {
        balanceChecker.push('(');
      }
      else if (s.charAt(i) == '{') {
        balanceChecker.push('{');
      }
      else if (s.charAt(i) == '[') {
        balanceChecker.push('[');
      }
      
      //The for loop runs through every character in the input string
      //These three ifs check to see if any of the characters are parentheses or brackets
      //If they are, and they're opening, it pushes them to the top of the stack
               
      else if (s.charAt(i) == ')') {
        if (balanceChecker.isEmpty() == true) {
          return false;
        }
        else if (balanceChecker.pop() != '(') {
          return false;
        }
      }
      else if (s.charAt(i) == '}') {
        if (balanceChecker.isEmpty() == true) {
          return false;
        }
        else if (balanceChecker.pop() != '{') {
          return false;
        }
      }
      else if (s.charAt(i) == ']') {
        if (balanceChecker.isEmpty() == true) {
          return false;
        }
        else if (balanceChecker.pop() != '[') {
          return false;
        }
      }
      
      //If the character the loop is on isn't an opening parenthesis or bracket, then these three ifs check to see
      //if it's a closing parenthesis or bracket
      //If it is, and the stack's empty, this returns false
      //If it is, and the first thing in the stack isn't a corresponding open parenthesis/bracket, then this returns false too
      //It has to check if the stack's empty first, because if it's empty and it tries to pop from there, it throws an exception
      
    }
    
    if (balanceChecker.isEmpty() == true) {
      return true;
    }
    else {
      return false;
    }
    //This checks one last time to see if every parenthesis/bracket is accounted for
    //If there are open parentheses/brackets left on the stack then the string isn't balanced
  }
  
  public static int numberOfBalancedStrings(String[] in){
    //Purpose: Counts how many strings in the input array are balanced.
    //Preconditions: Parameter in must be a non-null array of strings.
    //Postconditions: Will return the number of strings in the input that are balanced.
    int balancedCount = 0;
    
    if (in.length != 0) {
      for (int i = 0; i < in.length; i += 1) {
        if (isBalanced(in[i]) == true) {
          balancedCount += 1;
        }
      }
    }
    return balancedCount;
  }
  
}
  