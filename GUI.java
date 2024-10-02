import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;  

public class GUI implements ActionListener {

    // Declare Variables
    ArrayList<String> scrambledWord;
    TreeMapDictionary dictionary = new TreeMapDictionary();
    
    private int frameWidth = 700;
    private int frameHeight = 700;
    private JFrame GUIFrame = new JFrame(); // window
    
    private JPanel inputPanel = new JPanel(); // upper layout
    
    private JPanel buttonPanel = new JPanel(); // lower layout
    private JButton letter1 = new JButton();
    private JButton letter2 = new JButton();
    private JButton letter3 = new JButton();
    private JButton letter4 = new JButton();
    private JButton letter5 = new JButton();
    private JButton letter6 = new JButton();
    private JButton del = new JButton("DEL");
    private JButton enter = new JButton("ENTER");
    private JButton exit = new JButton("EXIT");

    private JTextField guessBox = new JTextField(); // text field to show input
    StringBuffer guess = new StringBuffer(""); // input guess
    
    public GUI() {
        dictionary.createDictionary(); //create sorted dictionary
        scrambledWord = dictionary.scramble(); // get the scrambled word
        dictionary.cheatSheet(); // outputs the cheat sheet
        
        // configure GUI frame
        GUIFrame.setSize(frameWidth, frameHeight); // width and height (in pixels)
        GUIFrame.setTitle("Stan's Text Twist Player"); // title of window
        GUIFrame.setDefaultCloseOperation(GUIFrame.EXIT_ON_CLOSE); // closes frame
        
        // configure input panel
        inputPanel.setPreferredSize(new Dimension(frameWidth, frameHeight/3));
        inputPanel.setLayout(null); // layout
        inputPanel.setBackground(Color.decode("#196480")); // background color
        GUIFrame.add(inputPanel, BorderLayout.NORTH);
        // configure button panel
        buttonPanel.setPreferredSize(new Dimension(frameWidth, (frameHeight/3)*2));
        buttonPanel.setLayout(new GridLayout(3, 3, 50, 50)); // creates grid (int rows, int columns, int horizontal gap, int vertical gap)
        buttonPanel.setBackground(Color.decode("#196480")); // background color
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60)); // add padding
        GUIFrame.add(buttonPanel, BorderLayout.SOUTH);

        // configure username text
        guessBox.setMargin(new Insets(20, 20, 20, 20)); // add padding
        guessBox.setBounds(frameWidth/2-150, 100, 300, 75); // (new x, new y, width, height)
        inputPanel.add(guessBox); // add the text field
        guessBox.setEditable(false); // make text field uneditable
        
        // configure buttons
        letter1.setBackground(Color.WHITE);
        letter1.setText(scrambledWord.get(0));
        letter2.setBackground(Color.WHITE);
        letter2.setText(scrambledWord.get(1));
        letter3.setBackground(Color.WHITE);
        letter3.setText(scrambledWord.get(2));
        letter4.setBackground(Color.WHITE);
        letter4.setText(scrambledWord.get(3));
        letter5.setBackground(Color.WHITE);
        letter5.setText(scrambledWord.get(4));
        letter6.setBackground(Color.WHITE);
        letter6.setText(scrambledWord.get(5));
        del.setBackground(Color.WHITE);
        enter.setBackground(Color.WHITE);
        exit.setBackground(Color.WHITE);
        // adds actionListener to buttons
        letter1.addActionListener(this);
        letter1.setActionCommand("letter1");
        letter2.addActionListener(this); 
        letter2.setActionCommand("letter2");
        letter3.addActionListener(this); 
        letter3.setActionCommand("letter3");
        letter4.addActionListener(this); 
        letter4.setActionCommand("letter4");
        letter5.addActionListener(this); 
        letter5.setActionCommand("letter5");
        letter6.addActionListener(this); 
        letter6.setActionCommand("letter6");
        del.addActionListener(this); 
        del.setActionCommand("del");
        enter.addActionListener(this); 
        enter.setActionCommand("enter");
        exit.addActionListener(this); 
        exit.setActionCommand("exit");
        // add buttons
        buttonPanel.add(letter1);
        buttonPanel.add(letter2);
        buttonPanel.add(letter3);
        buttonPanel.add(letter4);
        buttonPanel.add(letter5);
        buttonPanel.add(letter6);
        buttonPanel.add(del);
        buttonPanel.add(enter);
        buttonPanel.add(exit);

        GUIFrame.pack();
        GUIFrame.setVisible(true); // display frame
        
    }//end of GUI constructor

    // when user presses any button, this method is called
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
            
        switch (command) {
            case "letter1": // letter 1
                guess.append(scrambledWord.get(0));
                guessBox.setText(guess.toString());
                break;
            case "letter2": // letter 2
                guess.append(scrambledWord.get(1));
                guessBox.setText(guess.toString());
                break;
            case "letter3": // letter 3
                guess.append(scrambledWord.get(2));
                guessBox.setText(guess.toString());
                break;
            case "letter4": // letter 4
                guess.append(scrambledWord.get(3));
                guessBox.setText(guess.toString());
                break;
            case "letter5": // letter 5
                guess.append(scrambledWord.get(4));
                guessBox.setText(guess.toString());
                break;
            case "letter6": // letter 6
                guess.append(scrambledWord.get(5));
                guessBox.setText(guess.toString());
                break;
            case "del": // delete
                if (guess.length() > 0) {
                    guess.deleteCharAt(guess.length()-1);
                    guessBox.setText(guess.toString());
                }
                break;
            case "enter": // enter
                if (guess.toString().length() > 6) // if the word is too long
                    System.out.println("Please only use each letter once.");
                else if (guess.toString().length() <= 0) // if the word is too short
                    System.out.println("Please enter a guess.");
                else if (dictionary.isWord(guess.toString()) == true) { // if the word is in the dictionary
                    System.out.println("Correct!");
                }
                else // if the word is not in the dictionary
                    System.out.println("That is not a valid word.");
                break;
            case "exit": // user wants to exit the program
                System.out.print("\nThanks for playing!    ");
                System.exit(0);
                break;
        }//switch
        
    }//actionPerformed

}//GUI Class