package nl.neurone.component;

import nl.neurone.domain.CharacterHuffManTree;

/**
 * This class uses the 'normal' decoder and populates it with all ASCII characters (and will be used to decode text files)
 * 
 * @author Robert Voorn
 */
public class CharacterDecoder extends Decoder {

	public CharacterDecoder() {
		CharacterHuffManTree tree = new CharacterHuffManTree();
		setHuffManTree(tree);
	}

}
