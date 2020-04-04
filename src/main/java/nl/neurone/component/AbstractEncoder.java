package nl.neurone.component;

import nl.neurone.domain.HuffmanTree;
import nl.neurone.domain.Node;
import nl.neurone.domain.SimpleHuffmanTree;
import nl.neurone.domain.TreeNode;
import nl.neurone.stream.IBitOutputStream;

public class AbstractEncoder {
    protected IBitOutputStream outputStream;
    protected HuffmanTree tree;

    public AbstractEncoder() {
    }

    public AbstractEncoder(IBitOutputStream outputStream) {
        this.outputStream = outputStream;
        this.tree = new SimpleHuffmanTree();
    }

    public void encode(char c) {
        TreeNode current = tree.getLeafNode(c);
        encodeRecursive(current);
        tree.incrementFrequency(c);
    }

    protected void encodeRecursive(TreeNode current) {
        if (current.isRoot()) {
            return;
        }
        final Node parent = (Node)current.getParent();
        encodeRecursive(parent);
        final boolean bit = parent.getRight() == current;
        outputStream.writeBit(bit);
    }
}
