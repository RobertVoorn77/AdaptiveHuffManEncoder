package nl.neurone.domain;

public class Leaf extends AbstractLeaf {
    private char value;

    public Leaf(char value) {
        this(1, value);
    }

    protected Leaf(long frequency, char value) {
        super(frequency);
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    protected void setValue(char value) {
        this.value = value;
    }

}
