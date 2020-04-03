package nl.neurone.component;

import nl.neurone.domain.*;
import nl.neurone.stream.IBitInputStream;

public class Decoder {
    private IBitInputStream inputStream;
    private HuffmanTree tree;

    public Decoder(IBitInputStream inputStream) {
        this.inputStream = inputStream;
        this.tree = new SimpleHuffmanTree();
        for (char c = 0; c <= 255; c++) {
            tree.addValue(c);
        }
    }

    public char decode() {
        TreeNode current = tree.getRoot();
        while (!(current instanceof Leaf)) {
            Node curNode = (Node)current;
            final boolean bit = inputStream.readBit();
            current = bit ? curNode.getRight() : curNode.getLeft();
        }
        final char value = ((Leaf) current).getValue();
        tree.incrementFrequency(value);
        return value;
    }
}
