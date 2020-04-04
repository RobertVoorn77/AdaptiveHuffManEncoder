package nl.neurone.domain;

public class SimpleHuffmanTree extends AbstractHuffmanTree {

    @Override
    public void initialize() {
        for (char c = 0; c <= 255; c++) {
            addValue(c);
        }
    }

    @Override
    public void addValue(char c) {
        final Leaf newLeaf = new Leaf(1, c);
        leafs.add(newLeaf);
        updateTree();
    }
}


