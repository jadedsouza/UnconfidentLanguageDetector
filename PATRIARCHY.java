// Idea: This program will check the user's text (i.e. an email message)
// and highlight any word or phrase that makes them sound less confident
// or professional (i.e. informal writing, doubtful writing, racial slurs, 
// cuss words, etc). Then, the user will have the option to automatically
// change all those words and/or phrases with the program's suggestions. 
// If the user doesn't like the edited version of their text, then,
// the user has the ability to obtain their original text file. 

import java.io.*;
import java.util.*;
import java.awt.*;

public class PATRIARCHY {
   
   //main method that asks user to input their file name. Also asks user if
   //they are happy with the edited version. If they are not, they receive 
   //old file
   public static void main (String[] args) throws FileNotFoundException {
      Scanner console = new Scanner(System.in);
      Scanner input = new Scanner(System.in);
      intro();
      // This calls the text file on the user's computer that contains the email. 
      // In the future, we would create a textbox that the user could directly 
      // input their email into.
      System.out.print("input file name (.txt)? "); 
      String fileNAME = console.next();   
      File printStream = new File(fileNAME);
      readingAndReplacingFile (printStream);
      System.out.println(); 
      display(new File("output.txt"));
       
      System.out.println(); 
      System.out.println(); 
      if (happy(input)) {
      display(new File("output.txt"));}
      else {
      display(printStream);
      }
   }
   
  
   // Introduces the program to the user 
   public static void intro() {
      System.out.println("Welcome! This platform will allow you to write emails "); 
      System.out.println("that exude confidence and professionalism. ");
      System.out.println("It will highlight and give you the option to delete words and phrases ");
      System.out.println("that are seen as less confident or not professional ");
      System.out.println("(i.e. informal, racial slurs, cuss words, etc).");
      System.out.println();
   }
   
   //checks if user is happy or not
   //    input - the users input
   public static boolean happy(Scanner input) {
      System.out.print("Are you satisfied with the edited version of your text? ");
      String satisfaction = input.next();
      System.out.println("Thanks for using the prototype, we appreciate");
      System.out.print("it! Here's your email");
      System.out.println();
      String sat = ""+ satisfaction.charAt(0);
      if (sat.equalsIgnoreCase("n")) {
         return false;
      }
      return true;
     }
     
    //Goes through file given, and replaces words that are not confident. 
    //Deletes words that aren't confident as well, and racial slurs or cuss 
    //words
    //    file - file that the program checks
    public static void readingAndReplacingFile (File file) throws FileNotFoundException{
       Scanner scanner = new Scanner (file);
       File filee = new File ("output.txt");
       PrintStream outputFile = new PrintStream(filee);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            Scanner checker = new Scanner(line);
            while (checker.hasNext()) {
                String word = checker.next();
                //have a method to call that goes to replacing and deleting
                //if its found in replacing, have an if statement. 
                //if its found in deleting, have an if statement
                if (word.equals("So") || word.equals("So,")) {
                    outputFile.print("");
                }
                
                else if (deleteFile(word)) {
                System.out.println(word);
                    outputFile.print("");
                }
                else if (replacingCheck(word)) {
                    outputFile.print(replaced(word));
                }
                else {
                    outputFile.print(word + " ");
                }
            }
            outputFile.println();
        }        
    }
    
    //helper method that replaces words
    //      word - represents the single word from the file that the program
    //             checks if is a not confident/not professional. 
    public static String replaced (String word) throws FileNotFoundException{
        File file = new File ("replacing.txt");
        Scanner check = new Scanner(file);
        String newSentence = "";
        while (check.hasNextLine()) { 
            Scanner line = new Scanner(check.nextLine()); //could - would //this could be the thing breaking everything.
            String badName = "";
            String firstWordNOTScanner = line.next();
            if (firstWordNOTScanner.equalsIgnoreCase(word)) { // yes!
               badName = line.next(); //skips single hyphen // 
                while(line.hasNext()) {
                    badName = line.next();
                    newSentence = newSentence + badName + " ";
                }
            }
        }
        return newSentence;
    }
   
    //method that checks if word exists in the txt file of words to replace
    //      word - represents the single word from the file that the program
    //             checks if is not confident/not professional.       
    public static boolean replacingCheck (String word) throws FileNotFoundException {
        File file = new File ("replacing.txt");
        Scanner check = new Scanner(file);
        
        while (check.hasNextLine()) {
            String checkk = check.next();
            if (checkk.equalsIgnoreCase(word)) {
                return true;
            }
           check.nextLine();
        }
        return false;
    }
    
    //checks if word must be deleted   
    //      word - represents the single word from the file that the program
    //             checks if is a not confident/not professional. 
    public static boolean deleteFile (String word) throws FileNotFoundException {
        File file = new File ("delete.txt");
        Scanner delete = new Scanner(file);
        while (delete.hasNext()) {        
            String wordFile2 = delete.next();
            word = word.replaceAll("[^a-zA-Z0-9\\s+]", "");
            if (wordFile2.equalsIgnoreCase(word))
                return true;
        }
        return false;
    }

   //displays file passed
   //    file - file to be displayed
    public static void display(File file) throws FileNotFoundException {
        //file = finalTouches(file);
        Scanner toDisplay = new Scanner(file);
        while(toDisplay.hasNextLine()) {
            System.out.println(toDisplay.nextLine());
        }
    }
    
    // wow. looked at this a year later and absolutely surprised by how bad the below
    // code is. i rememeber my head ACHING over this. fixed this way above! :) 
   
    //originally meant to edit phrases, and not just words. couldn't
    //finish this method in time
    /*public static File finalTouches(File file) {
        Scanner check = new Scanner(file);
        String threeWord = "";
        while (check.hasNextLine()) {
            String line = check.nextLine();
            Scanner lineCheck = new Scanner(line);
            while (lineCheck.hasNext()) {
                int spaces = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ' ') 
                        spaces++;
                }

                if (spaces >=3) {
                    threeWord = check.next() + " " +
                    check.next() + "  " + check.next();

                    if (threeWord.equalsIgnoreCase("I'm no expert")) { 
                        check.print();
                    }

                    if (spaces == 4) {
                        threeWord = threeWord + check.next();
                        if (threeWord.equalsIgnoreCase("Does that make sense?") ||
                        threeWord.equalsIgnoreCase("Am I Making Sense?") ||
                        threeWord.equalsIgnoreCase("That makes me worrisome")) {

                            if (threeWord.equalsIgnoreCase("Does that make sense?") 
                            || threeWord.equalsIgnoreCase("Am I Making Sense?")) {
                                check.print("I look forward to hearing your thoughts.");
                            }

                            if (threeWord.equalsIgnoreCase("That makes me worrisome")) {
                                check.print();
                            }
                        }
                        else {
                            check.print(threeWord);
                        }
                    }
                    else {
                        check.print(threeWord);
                    }
                }
                else { 
                    check.print(check.next());
                }
            }
        }
    }*/
}
