
package jotto;
import java.util.*;


public class JottoModel extends Observable
{
	public  static int NUM_LETTERS = 5;
	public  static final String[] LEVELS = {
      "Easy", "Medium", "Hard", "Any Difficulty"};
    private  Word THE_WORD;  
    public  int CUR_GUESSES = 0; 
    private  WordList list;
    private  boolean legal_Guessword;
    private  String illegal_Reason;
    private  ArrayList<String> already_guessed;
    private  int partial_Guesses;
    private  int exact_Guesses;
    private  String GUESS;
    private  ArrayList<Character> exactGuessed;
    private  ArrayList<Character> partialGuessed;
    private  ArrayList<Character> guesCHARS;

    public String charsguessed() {
        String s = "";
        s = guesCHARS.toString().replaceAll("[, \\[\\]]", "");
        return s;
    }

    public String charsexact() {
        String s = "";
        s = exactGuessed.toString().replaceAll("[, \\[\\]]", "");
        return s;
    } 

    public String charspartial() {
        String s = "";
        s = partialGuessed.toString().replaceAll("[, \\[\\]]", "");
        return s;
    }        
    
    public String target() {
        return THE_WORD.getWord();
    }

    public boolean legal_guess() {
    	return legal_Guessword;
    }

    public String reason() {
    	return illegal_Reason;
    }

    public int guesses() {
    	return CUR_GUESSES;
    }

    public String Whatwastheguess() {
        return GUESS;
    }

    public int partial() {
        return partial_Guesses;
    }

    public int exact() {
        return exact_Guesses;
    }


    public JottoModel() {
    	list = new WordList("words.txt");
    	already_guessed = new ArrayList<String>();
        exactGuessed = new ArrayList<Character>();
        partialGuessed = new ArrayList<Character>();
        guesCHARS = new ArrayList<Character>();
    	THE_WORD = list.randomWord();
    	setChanged();
    }

    public JottoModel(String s) {
        list = new WordList("words.txt");
        already_guessed = new ArrayList<String>();
        exactGuessed = new ArrayList<Character>();
        partialGuessed = new ArrayList<Character>();
        guesCHARS = new ArrayList<Character>();
        String str = s.toUpperCase();
        THE_WORD = new Word(str, 0);
        setChanged();
    }    

    public JottoModel(int diff) {
        list = new WordList("words.txt");
        already_guessed = new ArrayList<String>();
        exactGuessed = new ArrayList<Character>();
        partialGuessed = new ArrayList<Character>();
        guesCHARS = new ArrayList<Character>();
        THE_WORD = list.randomWord(diff);
        setChanged();
    }    
    
    public boolean guess(String str) {
    	boolean result = false;
    	legal_Guessword = false; 
    	if(already_guessed.contains(str)) { // word has been guesses before
    		legal_Guessword = true;
    		illegal_Reason = "Already guessed this word";
    	}
    	else if(str.length() != 5) { // word does not have 5 chars
    		legal_Guessword = true;
    		illegal_Reason = "Your word needs to have 5 characters";
    	}
    	else if(!list.contains(str)) { //word is not in the dictionary
    		legal_Guessword = true;
    		illegal_Reason = "Word is not in the dictionary";
    	}
    	else {
            GUESS = str;
    		CUR_GUESSES = CUR_GUESSES + 1;
    		already_guessed.add(str);
    		partial_Guesses = 0;
    		exact_Guesses = 0;
    		// Here we find out the partial and exact chars
    		char[] known = THE_WORD.getWord().toCharArray();
    		char[] unkown = str.toCharArray();
            // First we find the exact matches
            for(int i = 0; i < unkown.length; i++) {
                if(!guesCHARS.contains(unkown[i])) {
                    guesCHARS.add(unkown[i]);
                }               
                if(unkown[i] == known[i]) {
                    exact_Guesses = exact_Guesses+1;
                    if(!exactGuessed.contains(known[i])) {
                        exactGuessed.add(known[i]);
                    }
                    known[i] = '1'; //  a given letter participates in at most one match so we replace with non-alphabetic char

                }
            }
            // Then we find partial matches
    		for(int i = 0; i < known.length; i++) {
    			for(int j = 0; j < unkown.length; j++) {
    				if(known[i] == unkown[j]) {
                        partial_Guesses = partial_Guesses+1;
                        if(!partialGuessed.contains(known[i])) {
                            partialGuessed.add(known[i]);
                        }
    					known[i] = '1'; //  a given letter participates in at most one match so we replace with non-alphabetic char
                        break;
    				}
    			}
    		}
            if(exact_Guesses == 5) {
                result = true;
            }
    		setChanged();
    		notifyObservers();
    	}
    	return result;
    }

}

