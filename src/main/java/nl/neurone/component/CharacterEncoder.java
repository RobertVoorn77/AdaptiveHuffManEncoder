package nl.neurone.component;

import nl.neurone.domain.CharacterHuffManTree;
import nl.neurone.stream.IBitStream;

/**
 * This class uses the 'normal' encoder and populates it with all ASCII characters (and will be used to encode text files)
 * @author Robert Voorn
 *
 */
public class CharacterEncoder extends Encoder {

	public CharacterEncoder(IBitStream bitStream) {
		super(bitStream);
		CharacterHuffManTree tree = new CharacterHuffManTree();
		setHuffManTree(tree);
	}
}
