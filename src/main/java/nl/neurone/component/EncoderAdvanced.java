package nl.neurone.component;

import nl.neurone.domain.*;
import nl.neurone.stream.IBitOutputStream;

public class EncoderAdvanced extends AbstractEncoder {
    private AddLeaf addLeaf = null;

    public EncoderAdvanced(IBitOutputStream outputStream) {
        super(outputStream);
    }

    @Override
    public void encode(char c) {
        // this is the first element to encode
        if (addLeaf == null) {
            outputStream.writeLong(c);
            tree.addValue(c);
            addLeaf = new AddLeaf(1L);
            tree.addLeaf(addLeaf);
            return;
        }
        if (tree.getLeafNode(c) == null) {
            super.encodeRecursive(addLeaf);
            outputStream.writeLong(c);  // TODO: should there be a 'char' variant of this method?
            tree.addValue(c);
            return;
        }
        super.encode(c);
    }

}
