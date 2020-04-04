package nl.neurone.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SimpleHuffmanTree extends AbstractHuffmanTree {
    private Comparator<? super TreeNode> comparator = new TreeNodeComparator();

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

    @Override
    public void addLeaf(AbstractLeaf leaf) {
        leafs.add(leaf);
        updateTree();
    }

    private void addSorted(List<TreeNode> treeNodeList, Node parent) {
        // TODO: fix deze implementatie, veel sneller maar dan werken de EncoderTest/DecoderTest test niet meer omdat deze een specifieke ordering van de treenodes verwacht
//        int index = Collections.binarySearch(treeNodeList, parent, comparator);
//        if (index < 0) index = ~index;
//        treeNodeList.add(index, parent);
        treeNodeList.add(parent);
        treeNodeList.sort(comparator);
    }

    static class TreeNodeComparator implements Comparator<TreeNode> {
        @Override
        public int compare(TreeNode o1, TreeNode o2) {
            return (int) (o1.getFrequency() - o2.getFrequency());
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }
}


