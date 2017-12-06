package nl.neurone.domain;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CharacterHuffManTreeTest {
	
	@Test
	public void testContainsAllValues() {
		// given
		CharacterHuffManTree tree = new CharacterHuffManTree();
		
		// when / then
		for (char i = 1; i <= 255; i++) {
			Leaf leafByValue = tree.getLeafByValue(i);
			assertNotNull(leafByValue);
		}
	}
}
