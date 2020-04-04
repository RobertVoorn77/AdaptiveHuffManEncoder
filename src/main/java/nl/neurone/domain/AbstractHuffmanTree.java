package nl.neurone.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractHuffmanTree implements HuffmanTree {
    protected TreeNode root;
    List<Leaf> leafs = new ArrayList<>();
    private Comparator<? super TreeNode> comparator = new TreeNodeComparator();

    @Override
    public abstract void addValue(char c);

    @Override
    public TreeNode getRoot() {
        return root;
    }

    @Override
    public void updateTree() {
        List<TreeNode> treeNodeList = new ArrayList<>(leafs);
        if (treeNodeList.size() == 1) {
            root = treeNodeList.get(0);
        } else {
            while (treeNodeList.size() > 1) {
                // TODO: implement sorting list zoals in 'https://stackoverflow.com/questions/4903611/java-list-sorting-is-there-a-way-to-keep-a-list-permantly-sorted-automatically'
                treeNodeList.sort(comparator);
                Node parent = new Node(treeNodeList.remove(0), treeNodeList.remove(0));
                treeNodeList.add(parent);
            }
            root = treeNodeList.get(0);
        }
//        System.out.println(root.getFrequency());
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

    public void incrementFrequency(char c) {
        Leaf l = getLeafNode(c);
        l.frequency++;
        updateTree();
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
