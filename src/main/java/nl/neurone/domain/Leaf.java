package nl.neurone.domain;

public class Leaf implements TreeNode {
    long frequency;
    private char value;
    private TreeNode parent;

    public Leaf(char value) {
        this(1, value);
    }

    protected Leaf(long frequency, char value) {
        this.frequency = frequency;
        this.value = value;
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

    public char getValue() {
        return value;
    }

    protected void setValue(char value) {
        this.value = value;
    }

}
