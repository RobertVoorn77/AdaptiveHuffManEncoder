package nl.neurone.domain;

public class Leaf implements TreeNode {
    private long frequency;
    private Object value;
    private TreeNode parent;

    public Leaf(Object value) {
        this(1, value);
    }

    protected Leaf(long frequency, Object value) {
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
    public int compareTo(Object o) {
        if (!(o instanceof Leaf)) {
            return -1;
        }
        Leaf other = (Leaf)o;
        return (int) (this.frequency - other.frequency);
    }

    public Object getValue() {
        return value;
    }

    protected void setValue(Object value) {
        this.value = value;
    }
}
