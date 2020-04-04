package nl.neurone.domain;

public abstract class AbstractLeaf implements TreeNode {
    protected TreeNode parent;
    long frequency;

    public AbstractLeaf(long frequency) {
        this.frequency = frequency;
        this.parent = null;
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
        return false;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Leaf)) {
            return -1;
        }
        Leaf other = (Leaf)o;
        return (int) (this.frequency - other.frequency);
    }
}
