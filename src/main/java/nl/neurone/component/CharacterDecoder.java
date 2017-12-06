package nl.neurone.component;

import nl.neurone.domain.CharacterHuffManTree;
import nl.neurone.stream.IBitStream;

/**
 * This class uses the 'normal' decoder and populates it with all ASCII characters (and will be used to decode text files)
 * @author Robert Voorn
 *
 */
public class CharacterDecoder extends Decoder {

	public CharacterDecoder(IBitStream bitStream) {
		CharacterHuffManTree tree = new CharacterHuffManTree();
		setHuffManTree(tree);
	}

}
