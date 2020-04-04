package nl.neurone.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleHuffmanTree extends AbstractHuffmanTree {

    @Override
    public void addValue(char c) {
        final Leaf newLeaf = new Leaf(1, c);
        leafs.add(newLeaf);
        updateTree();
    }

    @Override
    public void updateTree() {
        List<TreeNode> treeNodeList = new ArrayList<>(leafs);
        if (treeNodeList.size() == 1) {
            root = treeNodeList.get(0);
        } else {
            while (treeNodeList.size() > 1) {
                Node parent = new Node(treeNodeList.remove(0), treeNodeList.remove(0));
                addSorted(treeNodeList, parent);
            }
            root = treeNodeList.get(0);
        }
    }

    private void addSorted(List<TreeNode> treeNodeList, Node parent) {
        int index = Collections.binarySearch(treeNodeList, parent);
        if (index < 0) index = ~index;
        treeNodeList.add(index, parent);
    }

}


