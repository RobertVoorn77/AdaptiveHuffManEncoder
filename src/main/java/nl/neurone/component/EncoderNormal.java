package nl.neurone.component;

import nl.neurone.stream.IBitOutputStream;

public class EncoderNormal extends AbstractEncoder {

    public EncoderNormal(IBitOutputStream outputStream) {
        super(outputStream);
        for (char c = 0; c <= 255; c++) {
            tree.addValue(c);
        }
    }
}
