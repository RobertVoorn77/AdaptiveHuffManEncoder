package nl.neurone.component;

import nl.neurone.domain.AbstractLeaf;
import nl.neurone.domain.AddLeaf;
import nl.neurone.domain.Leaf;
import nl.neurone.stream.IBitInputStream;

public class DecoderAdvanced extends AbstractDecoder {
    private AddLeaf addLeaf = null;

    public DecoderAdvanced(IBitInputStream inputStream) {
        super(inputStream);
    }

    public char decode() {
        if (addLeaf == null) {
            char firstElement = (char) inputStream.readLong();
            tree.addLeaf(new Leaf(firstElement));
            addLeaf = new AddLeaf(1);
            tree.addLeaf(addLeaf);
            return firstElement;
        }
        AbstractLeaf decodedLeaf = decodedLeaf();
        if (decodedLeaf instanceof AddLeaf) {
            char value = (char) inputStream.readLong();
            tree.addValue(value);
            return value;
        }
        return ((Leaf)decodedLeaf).getValue();
    }
}
