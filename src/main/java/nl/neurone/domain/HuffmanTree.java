package nl.neurone.domain;

public interface HuffmanTree {

    /**
     * Gets the root node of the Huffman tree
     * @return a node that is the root of the Huffman tree
     */
    TreeNode getRoot();

    /**
     * Gets a Leaf node for the value provided
     * @param c value for which a Leaf node is needed
     * @return the leaf node representing the value provided
     */
    Leaf getLeafNode(char c);

    /**
     * This is used by the encoder and decoder toe create the content of the tree during initialization
     */
    void initialize();

    /**
     * Adds a node to the Huffman tree
     * @param c value that needs to be in the Huffman tree for encoding/decoding
     */
    void addValue(char c);

    /**
     * This increments the frequency for a value in the Huffman tree by 1 and keep the Huffman tree balanced
     * @param c the value for which the frequency should be incremented
     */
    void incrementFrequency(char c);

    /**
     * This method is used by the encoder and decoder when an leaf has been encoded/decoded to update the tree according
     * to the updated frequencies
     */
    void updateTree();
}
