package edu.nyu.cs9053.homework8;

public enum ValidTextKeyPress{
    Two(new char[] {'a', 'b', 'c', 'A', 'B', 'C'}),
    Three(new char[] {'d', 'e', 'f', 'D', 'E', 'F'}),
    Four(new char[] {'g', 'h', 'i', 'G', 'H', 'I'}),
    Five(new char[] {'j', 'k', 'l', 'J', 'K', 'L'}),
    Six(new char[] {'m', 'n', 'o', 'M', 'N', 'O'}),
    Seven(new char[] {'p', 'q', 'r', 's', 'P', 'Q', 'R', 'S'}),
    Eight(new char[] {'t', 'u', 'v', 'T', 'U', 'V'}),
    Nine(new char[] {'w', 'x', 'y', 'z', 'W', 'X', 'Y', 'Z'});
	
    private final char[] characters;
	
    ValidTextKeyPress(char[] characters){
        this.characters = characters;
    }
	
    public char[] getCharacters(){
	return characters;
    }
	
	
	
	
}
