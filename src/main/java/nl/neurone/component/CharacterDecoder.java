package nl.neurone.component;

import nl.neurone.domain.CharacterHuffManTree;
import nl.neurone.stream.IBitStream;

public class CharacterDecoder extends Decoder {

	public CharacterDecoder(IBitStream bitStream) {
		CharacterHuffManTree tree = new CharacterHuffManTree();
		setHuffManTree(tree);
	}

}
