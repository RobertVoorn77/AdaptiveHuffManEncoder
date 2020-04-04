package nl.neurone.component;

import nl.neurone.stream.IBitInputStream;

public class DecoderNormal extends AbstractDecoder {

    public DecoderNormal(IBitInputStream inputStream) {
        super(inputStream);
        for (char c = 0; c <= 255; c++) {
            tree.addValue(c);
        }
    }

}
