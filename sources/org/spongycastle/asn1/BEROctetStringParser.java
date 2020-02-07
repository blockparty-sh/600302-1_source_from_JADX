package org.spongycastle.asn1;

import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.util.p026io.Streams;

public class BEROctetStringParser implements ASN1OctetStringParser {
    private ASN1StreamParser _parser;

    BEROctetStringParser(ASN1StreamParser aSN1StreamParser) {
        this._parser = aSN1StreamParser;
    }

    public InputStream getOctetStream() {
        return new ConstructedOctetStream(this._parser);
    }

    public ASN1Primitive getLoadedObject() throws IOException {
        return new BEROctetString(Streams.readAll(getOctetStream()));
    }

    public ASN1Primitive toASN1Primitive() {
        try {
            return getLoadedObject();
        } catch (IOException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("IOException converting stream to byte array: ");
            sb.append(e.getMessage());
            throw new ASN1ParsingException(sb.toString(), e);
        }
    }
}
