package nl.neurone.domain;

public class Node implements TreeNode {
    private long frequency;
    private TreeNode parent;
    private TreeNode left;
    private TreeNode right;

    public Node(TreeNode left, TreeNode right) {
        this(0, left, right);
    }

    protected Node(long frequency, TreeNode left, TreeNode right) {
        this.frequency = frequency;
        this.left = left;
        this.left.setParent(this);
        this.right = right;
        this.right.setParent(this);
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
    public int compareTo(Object o) {
        if (!(o instanceof TreeNode)) {
            return -1;
        }
        TreeNode other = (TreeNode) o;
        return (int) (this.frequency - other.getFrequency());
    }

    protected TreeNode getLeft() {
        return left;
    }

    protected TreeNode getRight() {
        return right;
    }
}
