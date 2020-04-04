package nl.neurone.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHuffmanTree implements HuffmanTree {
    protected TreeNode root;
    List<AbstractLeaf> leafs = new ArrayList<>();

    @Override
    public abstract void addValue(char c);

    @Override
    public TreeNode getRoot() {
        return root;
    }

    @Override
    public Leaf getLeafNode(char c) {
        for (AbstractLeaf al : leafs) {
            if (al instanceof Leaf) {
                Leaf l = (Leaf)al;
                if (l.getValue() == c) {
                    return l;
                }
            }
        }
        return null;
    }

    public void incrementFrequency(char c) {
        Leaf l = getLeafNode(c);
        l.frequency++;
        updateTree();
    }
}
