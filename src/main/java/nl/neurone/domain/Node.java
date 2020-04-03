package nl.neurone.domain;

public class Node implements TreeNode {
    private long frequency;
    private TreeNode parent;
    private TreeNode left;
    private TreeNode right;

    public Node(TreeNode left, TreeNode right) {
        this(left.getFrequency() + right.getFrequency(), left, right);
    }

    protected Node(long frequency, TreeNode left, TreeNode right) {
        this.frequency = frequency;
        this.left = left;
        this.left.setParent(this);
        this.right = right;
        this.right.setParent(this);
        ensureLeftIsLessFrequent();
    }

     /* This is done to improve deterministic behaviour of a co
     *     /**mplete tree which is favourable for debugging, analysing
     * and unit testing
     */
    private void ensureLeftIsLessFrequent() {
        if (left.getFrequency() > right.getFrequency()) {
            TreeNode temp = right;
            right = left;
            left = temp;
        }
    }

    @Override
    public long getFrequency() {
        return frequency;
    }

    @Override
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public boolean isRoot() {
        return parent == null;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof TreeNode)) {
            return -1;
        }
        TreeNode other = (TreeNode) o;
        return (int) (this.frequency - other.getFrequency());
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }
}
