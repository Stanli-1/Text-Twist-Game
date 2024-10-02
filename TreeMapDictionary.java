import java.util.*;
import java.io.*;
import java.util.Random;

public class TreeMapDictionary {

    // TreeMap called "dictionary" will contain integer arraylist pairs 
    // int = word length, arraylist = words of that length
    TreeMap<Integer, ArrayList<String>> sortedDictionary = new TreeMap<Integer, ArrayList<String>>();
    // picks a random 6 letter word for the user and returns it scrambled 
    String unscrambledWord;
    String[] unscrambledWordSpilt;
     // Random number generator
    private Random random = new Random();
    // Temp array for holdign words when checking
    String[] tempArray;
    ArrayList<String> scrambledWord;
    boolean realWord;
    
    // Method that converts dictionary in TreeMap format
    public void createDictionary() {
        
        // create a file for the robustdictionary
        File dictionary = new File("RobustDictionary.txt");
        // FileReader is for reading streams of characters in the file
        FileReader input;
        // Bufferes files for efficiency, especially for larger files
        BufferedReader readFile;
        // Stores each line of text in the file
        String lineOfText;
        // Max word length
        final int maxWordLength = 6;
    
        try {
            input = new FileReader(dictionary);
            readFile = new BufferedReader(input);  
            // Creates new file for all possible combinations
            File cheatSheet = new File("cheatSheet.out");
    
            // Reads line by line through the robustdictionary file
            while ((lineOfText = readFile.readLine()) != null) {
                // If word is a new length and less than 6, create an arrayList in dictionary and add new word
                if ( !sortedDictionary.containsKey(lineOfText.length()) && lineOfText.length() <= maxWordLength ) {
                    ArrayList<String> words = new ArrayList<String>();
                    // Add word to the dictionary
                    words.add(lineOfText);
                    sortedDictionary.put(lineOfText.length(), words);
                }
                // If word is not a new length, add it to the arraylist corresponding to that length
                else if (lineOfText.length() <= maxWordLength) { 
                    sortedDictionary.get(lineOfText.length()).add(lineOfText);
                }
            }//while
            
            // Closes scanners 
            readFile.close();
            input.close();
        }//try
    
        // This exception will be thrown if the file cannot be found
        catch (FileNotFoundException e) {
            System.out.println("File does not exist or could not be found.");
            System.err.println("FileNotFoundException; " + e.getMessage());
        }
        // This exception will be thrown if the file is being used somewhere else
        catch (IOException e) {
            System.out.println("A error has occured.");
            System.err.println("IOExcpetion; " + e.getMessage());
        }

    }//create treemap dictionary

    
    // Returns true or false if argument is a word in dictionary
    public boolean isWord(String word) {
        if (sortedDictionary.get(word.length()).contains(word) == true)
            return true;
        else
            return false;
    }//isWord

    
    // Returns random 6 letter word from dictionary to scramble for letters
    public ArrayList<String> scramble() {
        // search key length 6 then random element from the arraylist
        unscrambledWord = sortedDictionary.get(6).get( random.nextInt(sortedDictionary.get(6).size()) );
        // convert unscrambled word into an array
        unscrambledWordSpilt = unscrambledWord.split("");

        // convert array to arraylist
        scrambledWord = new ArrayList<String>(Arrays.asList(unscrambledWordSpilt));
        // scramble the arraylist
        Collections.shuffle(scrambledWord);   

        return scrambledWord;
    }//scrambledWord

    // Output all possible words from prompt
    public void cheatSheet() { 
        
        try {
            FileWriter myWriter = new FileWriter("dictionary.out");
            //Loop through dictionary treemap
            for ( Map.Entry<Integer, ArrayList<String>> entry: sortedDictionary.entrySet() ) {
                //loop through each word in each length
                for (int i = 0; i < entry.getValue().size(); i++) {
                    tempArray = entry.getValue().get(i).split("");

                    //loop through each word and check if each letter is in the 6 random letters 
                    for (int j = 0; j < tempArray.length; j++) {
                        if (scrambledWord.contains(tempArray[j])) {
                            realWord = true;
                        }
                        else {
                            realWord = false;
                            break;
                        }
                    }

                    //if the word is real, write it to file
                    if (realWord) 
                        myWriter.write( entry.getValue().get(i) + "\n" );
                }
            }
        myWriter.close();
        }//try
            
        // This exception will be thrown if the file cannot be found
        catch (FileNotFoundException e) {
            System.out.println("File does not exist or could not be found.");
            System.err.println("FileNotFoundException; " + e.getMessage());
        }
        // This exception will be thrown if the file is being used somewhere else
        catch (IOException e) {
            System.out.println("A error has occured.");
            System.err.println("IOExcpetion; " + e.getMessage());
        }
       
    }// cheatSheet


    
    
}// TreeMapDictionary