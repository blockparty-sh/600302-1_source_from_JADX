package org.bitcoinj.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import org.bitcoinj.core.Block;
import org.bitcoinj.core.NetworkParameters;

public class BlockFileLoader implements Iterable<Block>, Iterator<Block> {
    private FileInputStream currentFileStream = null;
    private Iterator<File> fileIt;
    private Block nextBlock = null;
    private NetworkParameters params;

    public Iterator<Block> iterator() {
        return this;
    }

    public static List<File> getReferenceClientBlockFileList() {
        String str;
        String lowerCase = System.getProperty("os.name").toLowerCase();
        if (lowerCase.indexOf("win") >= 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(System.getenv("APPDATA"));
            sb.append("\\.bitcoin\\blocks\\");
            str = sb.toString();
        } else {
            String str2 = "user.home";
            if (lowerCase.indexOf("mac") >= 0 || lowerCase.indexOf("darwin") >= 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(System.getProperty(str2));
                sb2.append("/Library/Application Support/Bitcoin/blocks/");
                str = sb2.toString();
            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(System.getProperty(str2));
                sb3.append("/.bitcoin/blocks/");
                str = sb3.toString();
            }
        }
        LinkedList linkedList = new LinkedList();
        int i = 0;
        while (true) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str);
            sb4.append(String.format(Locale.US, "blk%05d.dat", new Object[]{Integer.valueOf(i)}));
            File file = new File(sb4.toString());
            if (!file.exists()) {
                return linkedList;
            }
            linkedList.add(file);
            i++;
        }
    }

    public BlockFileLoader(NetworkParameters networkParameters, List<File> list) {
        this.fileIt = list.iterator();
        this.params = networkParameters;
    }

    public boolean hasNext() {
        if (this.nextBlock == null) {
            loadNextBlock();
        }
        return this.nextBlock != null;
    }

    public Block next() throws NoSuchElementException {
        if (hasNext()) {
            Block block = this.nextBlock;
            this.nextBlock = null;
            return block;
        }
        throw new NoSuchElementException();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:41|42|63) */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r1 = r9.currentFileStream.read();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0038, code lost:
        if (r1 == -1) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0049, code lost:
        if (((long) r1) == ((r9.params.getPacketMagic() >>> 24) & 255)) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004b, code lost:
        r1 = r9.currentFileStream.read();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0052, code lost:
        r1 = r9.currentFileStream.read();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0065, code lost:
        if (((long) r1) == ((r9.params.getPacketMagic() >>> 16) & 255)) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0068, code lost:
        r1 = r9.currentFileStream.read();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x007b, code lost:
        if (((long) r1) == ((r9.params.getPacketMagic() >>> 8) & 255)) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007e, code lost:
        r1 = r9.currentFileStream.read();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x008f, code lost:
        if (((long) r1) != (r9.params.getPacketMagic() & 255)) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0091, code lost:
        r2 = new byte[4];
        r9.currentFileStream.read(r2, 0, 4);
        r1 = org.bitcoinj.core.C3447Utils.readUint32BE(org.bitcoinj.core.C3447Utils.reverseBytes(r2), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a7, code lost:
        if (r1 > 16000000) goto L_0x0000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ad, code lost:
        if (r1 > 0) goto L_0x00b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00b1, code lost:
        r2 = (int) r1;
        r1 = new byte[r2];
        r9.currentFileStream.read(r1, 0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r9.nextBlock = r9.params.getDefaultSerializer().makeBlock(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r9.nextBlock = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0000, code lost:
        continue;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00c6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadNextBlock() {
        /*
            r9 = this;
        L_0x0000:
            r0 = 0
            java.util.Iterator<java.io.File> r1 = r9.fileIt     // Catch:{ IOException -> 0x0018 }
            boolean r1 = r1.hasNext()     // Catch:{ IOException -> 0x0018 }
            if (r1 != 0) goto L_0x0025
            java.io.FileInputStream r1 = r9.currentFileStream     // Catch:{ IOException -> 0x0018 }
            if (r1 == 0) goto L_0x00c5
            java.io.FileInputStream r1 = r9.currentFileStream     // Catch:{ IOException -> 0x0018 }
            int r1 = r1.available()     // Catch:{ IOException -> 0x0018 }
            r2 = 1
            if (r1 >= r2) goto L_0x0025
            goto L_0x00c5
        L_0x0018:
            r9.currentFileStream = r0
            java.util.Iterator<java.io.File> r1 = r9.fileIt
            boolean r1 = r1.hasNext()
            if (r1 != 0) goto L_0x0025
            goto L_0x00c5
        L_0x0025:
            java.io.FileInputStream r1 = r9.currentFileStream     // Catch:{ IOException -> 0x00ce }
            if (r1 == 0) goto L_0x00d0
            java.io.FileInputStream r1 = r9.currentFileStream     // Catch:{ IOException -> 0x00ce }
            int r1 = r1.available()     // Catch:{ IOException -> 0x00ce }
            if (r1 <= 0) goto L_0x00d0
            java.io.FileInputStream r1 = r9.currentFileStream     // Catch:{ IOException -> 0x00ca }
            int r1 = r1.read()     // Catch:{ IOException -> 0x00ca }
        L_0x0037:
            r2 = -1
            if (r1 == r2) goto L_0x0091
            long r1 = (long) r1     // Catch:{ IOException -> 0x00ca }
            org.bitcoinj.core.NetworkParameters r3 = r9.params     // Catch:{ IOException -> 0x00ca }
            long r3 = r3.getPacketMagic()     // Catch:{ IOException -> 0x00ca }
            r5 = 24
            long r3 = r3 >>> r5
            r5 = 255(0xff, double:1.26E-321)
            long r3 = r3 & r5
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r7 == 0) goto L_0x0052
            java.io.FileInputStream r1 = r9.currentFileStream     // Catch:{ IOException -> 0x00ca }
            int r1 = r1.read()     // Catch:{ IOException -> 0x00ca }
            goto L_0x0037
        L_0x0052:
            java.io.FileInputStream r1 = r9.currentFileStream     // Catch:{ IOException -> 0x00ca }
            int r1 = r1.read()     // Catch:{ IOException -> 0x00ca }
            long r2 = (long) r1     // Catch:{ IOException -> 0x00ca }
            org.bitcoinj.core.NetworkParameters r4 = r9.params     // Catch:{ IOException -> 0x00ca }
            long r7 = r4.getPacketMagic()     // Catch:{ IOException -> 0x00ca }
            r4 = 16
            long r7 = r7 >>> r4
            long r7 = r7 & r5
            int r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x0068
            goto L_0x0037
        L_0x0068:
            java.io.FileInputStream r1 = r9.currentFileStream     // Catch:{ IOException -> 0x00ca }
            int r1 = r1.read()     // Catch:{ IOException -> 0x00ca }
            long r2 = (long) r1     // Catch:{ IOException -> 0x00ca }
            org.bitcoinj.core.NetworkParameters r4 = r9.params     // Catch:{ IOException -> 0x00ca }
            long r7 = r4.getPacketMagic()     // Catch:{ IOException -> 0x00ca }
            r4 = 8
            long r7 = r7 >>> r4
            long r7 = r7 & r5
            int r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x007e
            goto L_0x0037
        L_0x007e:
            java.io.FileInputStream r1 = r9.currentFileStream     // Catch:{ IOException -> 0x00ca }
            int r1 = r1.read()     // Catch:{ IOException -> 0x00ca }
            long r2 = (long) r1     // Catch:{ IOException -> 0x00ca }
            org.bitcoinj.core.NetworkParameters r4 = r9.params     // Catch:{ IOException -> 0x00ca }
            long r7 = r4.getPacketMagic()     // Catch:{ IOException -> 0x00ca }
            long r4 = r7 & r5
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x0037
        L_0x0091:
            r1 = 4
            byte[] r2 = new byte[r1]     // Catch:{ IOException -> 0x00ca }
            java.io.FileInputStream r3 = r9.currentFileStream     // Catch:{ IOException -> 0x00ca }
            r4 = 0
            r3.read(r2, r4, r1)     // Catch:{ IOException -> 0x00ca }
            byte[] r1 = org.bitcoinj.core.C3447Utils.reverseBytes(r2)     // Catch:{ IOException -> 0x00ca }
            long r1 = org.bitcoinj.core.C3447Utils.readUint32BE(r1, r4)     // Catch:{ IOException -> 0x00ca }
            r5 = 16000000(0xf42400, double:7.9050503E-317)
            int r3 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r3 > 0) goto L_0x0000
            r5 = 0
            int r3 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r3 > 0) goto L_0x00b1
            goto L_0x0000
        L_0x00b1:
            int r2 = (int) r1     // Catch:{ IOException -> 0x00ca }
            byte[] r1 = new byte[r2]     // Catch:{ IOException -> 0x00ca }
            java.io.FileInputStream r3 = r9.currentFileStream     // Catch:{ IOException -> 0x00ca }
            r3.read(r1, r4, r2)     // Catch:{ IOException -> 0x00ca }
            org.bitcoinj.core.NetworkParameters r2 = r9.params     // Catch:{ ProtocolException -> 0x00c6 }
            org.bitcoinj.core.MessageSerializer r2 = r2.getDefaultSerializer()     // Catch:{ ProtocolException -> 0x00c6 }
            org.bitcoinj.core.Block r1 = r2.makeBlock(r1)     // Catch:{ ProtocolException -> 0x00c6 }
            r9.nextBlock = r1     // Catch:{ ProtocolException -> 0x00c6 }
        L_0x00c5:
            return
        L_0x00c6:
            r9.nextBlock = r0     // Catch:{ IOException -> 0x00ca }
            goto L_0x0000
        L_0x00ca:
            r9.currentFileStream = r0
            goto L_0x0000
        L_0x00ce:
            r9.currentFileStream = r0
        L_0x00d0:
            java.util.Iterator<java.io.File> r1 = r9.fileIt
            boolean r1 = r1.hasNext()
            if (r1 != 0) goto L_0x00dd
            r9.nextBlock = r0
            r9.currentFileStream = r0
            return
        L_0x00dd:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x00ee }
            java.util.Iterator<java.io.File> r2 = r9.fileIt     // Catch:{ FileNotFoundException -> 0x00ee }
            java.lang.Object r2 = r2.next()     // Catch:{ FileNotFoundException -> 0x00ee }
            java.io.File r2 = (java.io.File) r2     // Catch:{ FileNotFoundException -> 0x00ee }
            r1.<init>(r2)     // Catch:{ FileNotFoundException -> 0x00ee }
            r9.currentFileStream = r1     // Catch:{ FileNotFoundException -> 0x00ee }
            goto L_0x0025
        L_0x00ee:
            r9.currentFileStream = r0
            goto L_0x0025
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.utils.BlockFileLoader.loadNextBlock():void");
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
