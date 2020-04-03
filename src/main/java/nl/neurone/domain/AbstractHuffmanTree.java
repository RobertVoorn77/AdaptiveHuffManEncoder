package nl.neurone.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHuffmanTree implements HuffmanTree {
    protected TreeNode root;
    List<Leaf> leafs = new ArrayList<>();

    @Override
    public TreeNode getRoot() {
        return root;
    }

    @Override
    public Leaf getLeafNode(char c) {
        for (Leaf l : leafs) {
            if (l.getValue() == c) {
                return l;
            }
        }
        return null;
    }

    @Override
    public abstract void addValue(char c);
}
