package nl.neurone.domain;

public class Leaf implements TreeNode {
	Object value;
	long frequency;
	TreeNode parent;

	public Leaf(Object value, long frequency) {
		this.value = value;
		setFrequency(frequency);
	}
	
	public Leaf(Object value) {
		this(value, 1);
	}
	
	public Object getValue() {
		return value;
	}
		
	void setFrequency(long f) {
		frequency = f;
	}
	
	public String getString() {
		return "(" + value.toString() + ", " + frequency + ")";
	}

	public Long getFrequency() {
		return frequency;
	}

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

	public void incrementFrequency() {
		frequency++;
	}
}
