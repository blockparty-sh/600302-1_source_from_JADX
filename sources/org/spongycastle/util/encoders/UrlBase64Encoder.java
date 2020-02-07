package org.spongycastle.util.encoders;

public class UrlBase64Encoder extends Base64Encoder {
    public UrlBase64Encoder() {
        this.encodingTable[this.encodingTable.length - 2] = Framer.STDIN_FRAME_PREFIX;
        this.encodingTable[this.encodingTable.length - 1] = Framer.STDIN_REQUEST_FRAME_PREFIX;
        this.padding = 46;
        initialiseDecodingTable();
    }
}
