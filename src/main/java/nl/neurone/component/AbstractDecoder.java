package nl.neurone.component;

import nl.neurone.domain.*;
import nl.neurone.stream.IBitInputStream;

public class AbstractDecoder {
    protected IBitInputStream inputStream;
    protected HuffmanTree tree;

    public AbstractDecoder(IBitInputStream inputStream) {
        this.inputStream = inputStream;
        this.tree = new SimpleHuffmanTree();
    }

    public char decode() {
        Leaf leaf = (Leaf) decodedLeaf();
        char value = leaf.getValue();
        tree.incrementFrequency(value);
        return value;
    }

    AbstractLeaf decodedLeaf() {
        TreeNode current = tree.getRoot();
        while (!(current instanceof AbstractLeaf)) {
            Node curNode = (Node)current;
            final boolean bit = inputStream.readBit();
            current = bit ? curNode.getRight() : curNode.getLeft();
        }
        return (AbstractLeaf) current;
    }
}
