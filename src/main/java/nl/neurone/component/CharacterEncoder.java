package nl.neurone.component;

import nl.neurone.domain.CharacterHuffManTree;
import nl.neurone.stream.IBitStream;

public class CharacterEncoder extends Encoder {

	public CharacterEncoder(IBitStream bitStream) {
		super(bitStream);
		CharacterHuffManTree tree = new CharacterHuffManTree();
		setHuffManTree(tree);
	}
}
