package nl.neurone.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CharacterHuffManTreeTest {
	
	@Test
	public void testContainsAllValues() {
		// given
		CharacterHuffManTree tree = new CharacterHuffManTree();
		
		// when / then
		for (char i = 1; i <= 255; i++) {
			TreeNode leafByValue = tree.getLeafByValue(i);
			assertNotNull(leafByValue);
		}

		String string = tree.getString();
		assertNotNull(string);
		assertTrue(string.length() > 0);
	}
}
