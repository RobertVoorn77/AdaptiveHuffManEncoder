package nl.neurone.component;

import nl.neurone.domain.HuffmanTree;
import nl.neurone.domain.Node;
import nl.neurone.domain.TreeNode;
import nl.neurone.stream.IBitOutputStream;

public class Encoder {
    private IBitOutputStream outputStream;
    private HuffmanTree tree;

    public Encoder(IBitOutputStream outputStream, HuffmanTree tree) {
        this.outputStream = outputStream;
        this.tree = tree;
        tree.initialize();
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
