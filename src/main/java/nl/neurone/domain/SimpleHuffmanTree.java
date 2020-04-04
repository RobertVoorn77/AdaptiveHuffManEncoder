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
        rebuildTree();
    }

    @Override
    public void incrementFrequency(char c) {
        Leaf l = getLeafNode(c);
        l.frequency++;
        rebuildTree();
    }

    private void rebuildTree() {
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


