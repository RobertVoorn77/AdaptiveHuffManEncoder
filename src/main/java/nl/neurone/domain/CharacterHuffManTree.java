package nl.neurone.domain;

public class CharacterHuffManTree extends HuffManTree {
	
	public CharacterHuffManTree() {
		for (char i = 1; i <= 255; i++) {
			System.out.println("Adding char value: " + (byte)i + " >> " + i);
			addValue(i);
		}
	}
	
	public String toString() {
		return getRoot().getString();
	}
}
