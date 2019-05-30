package nl.neurone.domain;

/**
 * This class represents a leaf in the binary tree (HuffManTree) and represents a value that can be encoded. This node will also contain a frequency value
 * that will be the amount of times this value was encoded already. This value is needed to build the HuffManTree.
 *  
 * @author Robert Voorn
 *
 */
public class Leaf implements TreeNode {
	private final Object value;
	private Long frequency;
	private TreeNode parent;

	Leaf(Object value, long frequency) {
		this.value = value;
		setFrequency(frequency);
	}
	
	Leaf(Object value) {
		this(value, 1);
	}
	
	public Object getValue() {
		return value;
	}
		
	private void setFrequency(long f) {
		frequency = f;
	}
	
	public String getString() {
		return "(" + value.toString() + ", " + frequency + ")";
	}

	public Long getFrequency() {
		return frequency;
	}

	
	/**
	 * This method will compare 2 leafs of tree nodes based on:
	 * 1. the frequency
	 * 2. if the frequencies are equal the comparison will be done on the value itself
	 * 
	 */
	@Override
	public int compareTo(Object that) {
		if (that == null) {
			return -1;
		} else {
			long thatFrequency = ((TreeNode)that).getFrequency();
			int compared = (int) (getFrequency() - thatFrequency);
			if (compared == 0) {
				// the frequencies are equals, so leafs should be compared on value
				if (that instanceof Leaf) {
					Leaf thatLeaf = (Leaf)that;
					compared = getString().compareTo(thatLeaf.getString());
				}
			}
			return compared;
		}
	}

	@Override
	public void setParent(TreeNode node) {
		this.parent = node;
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	void incrementFrequency() {
		frequency++;
	}
}
