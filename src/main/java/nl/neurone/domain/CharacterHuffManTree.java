package nl.neurone.domain;

public class CharacterHuffManTree extends HuffManTree {
	
	public CharacterHuffManTree() {
		for (char i = 1; i <= 255; i++) {
			addValue(i);
		}
	}
	
	/**
	 * Mainly for debugging purposes
	 * @return String representing the binary tree
	 */
	protected String getString() {
		return getRoot().getString();
	}
}
