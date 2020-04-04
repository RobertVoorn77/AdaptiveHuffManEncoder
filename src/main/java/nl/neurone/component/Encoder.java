package nl.neurone.component;

import nl.neurone.domain.HuffmanTree;
import nl.neurone.domain.Node;
import nl.neurone.domain.SimpleHuffmanTree;
import nl.neurone.domain.TreeNode;
import nl.neurone.stream.IBitOutputStream;

public class Encoder {
    private IBitOutputStream outputStream;
    private HuffmanTree tree;

    public Encoder(IBitOutputStream outputStream) {
        this.outputStream = outputStream;
        this.tree = new SimpleHuffmanTree();
        for (char c = 0; c <= 255; c++) {
            tree.addValue(c);
        }
    }

    public void encode(char c) {
        TreeNode current = tree.getLeafNode(c);
        encodeRecursive(current);
        tree.incrementFrequency(c);
    }

    private void encodeRecursive(TreeNode current) {
        if (current.isRoot()) {
            return;
        }
        final Node parent = (Node)current.getParent();
        encodeRecursive(parent);
        final boolean bit = parent.getRight() == current;
        outputStream.writeBit(bit);
    }
}
