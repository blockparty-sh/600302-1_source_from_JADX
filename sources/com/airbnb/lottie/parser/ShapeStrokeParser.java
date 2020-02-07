package com.airbnb.lottie.parser;

class ShapeStrokeParser {
    private ShapeStrokeParser() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x014a  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0170  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0183  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x01a5  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x01ae  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x01b7  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x01c0  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0135  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.airbnb.lottie.model.content.ShapeStroke parse(android.util.JsonReader r18, com.airbnb.lottie.LottieComposition r19) throws java.io.IOException {
        /*
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r2 = 0
            r2 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
        L_0x000f:
            boolean r12 = r18.hasNext()
            if (r12 == 0) goto L_0x01c9
            java.lang.String r12 = r18.nextName()
            int r13 = r12.hashCode()
            r14 = 99
            java.lang.String r15 = "o"
            java.lang.String r1 = "d"
            r16 = -1
            if (r13 == r14) goto L_0x0095
            r14 = 100
            if (r13 == r14) goto L_0x008c
            r14 = 111(0x6f, float:1.56E-43)
            if (r13 == r14) goto L_0x0084
            r14 = 119(0x77, float:1.67E-43)
            if (r13 == r14) goto L_0x007a
            r14 = 3324(0xcfc, float:4.658E-42)
            if (r13 == r14) goto L_0x0070
            r14 = 3447(0xd77, float:4.83E-42)
            if (r13 == r14) goto L_0x0066
            r14 = 3454(0xd7e, float:4.84E-42)
            if (r13 == r14) goto L_0x005c
            r14 = 3487(0xd9f, float:4.886E-42)
            if (r13 == r14) goto L_0x0052
            r14 = 3519(0xdbf, float:4.931E-42)
            if (r13 == r14) goto L_0x0048
            goto L_0x009f
        L_0x0048:
            java.lang.String r13 = "nm"
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L_0x009f
            r12 = 0
            goto L_0x00a0
        L_0x0052:
            java.lang.String r13 = "ml"
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L_0x009f
            r12 = 6
            goto L_0x00a0
        L_0x005c:
            java.lang.String r13 = "lj"
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L_0x009f
            r12 = 5
            goto L_0x00a0
        L_0x0066:
            java.lang.String r13 = "lc"
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L_0x009f
            r12 = 4
            goto L_0x00a0
        L_0x0070:
            java.lang.String r13 = "hd"
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L_0x009f
            r12 = 7
            goto L_0x00a0
        L_0x007a:
            java.lang.String r13 = "w"
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L_0x009f
            r12 = 2
            goto L_0x00a0
        L_0x0084:
            boolean r12 = r12.equals(r15)
            if (r12 == 0) goto L_0x009f
            r12 = 3
            goto L_0x00a0
        L_0x008c:
            boolean r12 = r12.equals(r1)
            if (r12 == 0) goto L_0x009f
            r12 = 8
            goto L_0x00a0
        L_0x0095:
            java.lang.String r13 = "c"
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L_0x009f
            r12 = 1
            goto L_0x00a0
        L_0x009f:
            r12 = -1
        L_0x00a0:
            switch(r12) {
                case 0: goto L_0x01c0;
                case 1: goto L_0x01b7;
                case 2: goto L_0x01ae;
                case 3: goto L_0x01a5;
                case 4: goto L_0x0194;
                case 5: goto L_0x0183;
                case 6: goto L_0x0179;
                case 7: goto L_0x0170;
                case 8: goto L_0x00ab;
                default: goto L_0x00a3;
            }
        L_0x00a3:
            r0 = r19
            r1 = 0
            r18.skipValue()
            goto L_0x000f
        L_0x00ab:
            r18.beginArray()
        L_0x00ae:
            boolean r12 = r18.hasNext()
            if (r12 == 0) goto L_0x0154
            r18.beginObject()
            r12 = 0
            r13 = 0
        L_0x00b9:
            boolean r14 = r18.hasNext()
            if (r14 == 0) goto L_0x00fe
            java.lang.String r14 = r18.nextName()
            int r0 = r14.hashCode()
            r17 = r4
            r4 = 110(0x6e, float:1.54E-43)
            if (r0 == r4) goto L_0x00dc
            r4 = 118(0x76, float:1.65E-43)
            if (r0 == r4) goto L_0x00d2
            goto L_0x00e6
        L_0x00d2:
            java.lang.String r0 = "v"
            boolean r0 = r14.equals(r0)
            if (r0 == 0) goto L_0x00e6
            r0 = 1
            goto L_0x00e7
        L_0x00dc:
            java.lang.String r0 = "n"
            boolean r0 = r14.equals(r0)
            if (r0 == 0) goto L_0x00e6
            r0 = 0
            goto L_0x00e7
        L_0x00e6:
            r0 = -1
        L_0x00e7:
            if (r0 == 0) goto L_0x00f6
            r4 = 1
            if (r0 == r4) goto L_0x00f0
            r18.skipValue()
            goto L_0x00fb
        L_0x00f0:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r0 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r18, r19)
            r13 = r0
            goto L_0x00fb
        L_0x00f6:
            java.lang.String r0 = r18.nextString()
            r12 = r0
        L_0x00fb:
            r4 = r17
            goto L_0x00b9
        L_0x00fe:
            r17 = r4
            r18.endObject()
            int r0 = r12.hashCode()
            r4 = 100
            if (r0 == r4) goto L_0x0128
            r14 = 103(0x67, float:1.44E-43)
            if (r0 == r14) goto L_0x011c
            r14 = 111(0x6f, float:1.56E-43)
            if (r0 == r14) goto L_0x0114
            goto L_0x0132
        L_0x0114:
            boolean r0 = r12.equals(r15)
            if (r0 == 0) goto L_0x0132
            r0 = 0
            goto L_0x0133
        L_0x011c:
            r14 = 111(0x6f, float:1.56E-43)
            java.lang.String r0 = "g"
            boolean r0 = r12.equals(r0)
            if (r0 == 0) goto L_0x0132
            r0 = 2
            goto L_0x0133
        L_0x0128:
            r14 = 111(0x6f, float:1.56E-43)
            boolean r0 = r12.equals(r1)
            if (r0 == 0) goto L_0x0132
            r0 = 1
            goto L_0x0133
        L_0x0132:
            r0 = -1
        L_0x0133:
            if (r0 == 0) goto L_0x014a
            r12 = 1
            if (r0 == r12) goto L_0x0141
            r4 = 2
            if (r0 == r4) goto L_0x013e
            r0 = r19
            goto L_0x0150
        L_0x013e:
            r0 = r19
            goto L_0x0143
        L_0x0141:
            r4 = 2
            goto L_0x013e
        L_0x0143:
            r0.setHasDashPattern(r12)
            r3.add(r13)
            goto L_0x0150
        L_0x014a:
            r0 = r19
            r4 = 2
            r12 = 1
            r17 = r13
        L_0x0150:
            r4 = r17
            goto L_0x00ae
        L_0x0154:
            r0 = r19
            r17 = r4
            r12 = 1
            r18.endArray()
            int r1 = r3.size()
            if (r1 != r12) goto L_0x016b
            r1 = 0
            java.lang.Object r4 = r3.get(r1)
            r3.add(r4)
            goto L_0x016c
        L_0x016b:
            r1 = 0
        L_0x016c:
            r4 = r17
            goto L_0x000f
        L_0x0170:
            r0 = r19
            r1 = 0
            boolean r11 = r18.nextBoolean()
            goto L_0x000f
        L_0x0179:
            r0 = r19
            r1 = 0
            double r12 = r18.nextDouble()
            float r10 = (float) r12
            goto L_0x000f
        L_0x0183:
            r0 = r19
            r1 = 0
            com.airbnb.lottie.model.content.ShapeStroke$LineJoinType[] r9 = com.airbnb.lottie.model.content.ShapeStroke.LineJoinType.values()
            int r12 = r18.nextInt()
            r13 = 1
            int r12 = r12 - r13
            r9 = r9[r12]
            goto L_0x000f
        L_0x0194:
            r0 = r19
            r1 = 0
            r13 = 1
            com.airbnb.lottie.model.content.ShapeStroke$LineCapType[] r8 = com.airbnb.lottie.model.content.ShapeStroke.LineCapType.values()
            int r12 = r18.nextInt()
            int r12 = r12 - r13
            r8 = r8[r12]
            goto L_0x000f
        L_0x01a5:
            r0 = r19
            r1 = 0
            com.airbnb.lottie.model.animatable.AnimatableIntegerValue r6 = com.airbnb.lottie.parser.AnimatableValueParser.parseInteger(r18, r19)
            goto L_0x000f
        L_0x01ae:
            r0 = r19
            r1 = 0
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r7 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r18, r19)
            goto L_0x000f
        L_0x01b7:
            r0 = r19
            r1 = 0
            com.airbnb.lottie.model.animatable.AnimatableColorValue r5 = com.airbnb.lottie.parser.AnimatableValueParser.parseColor(r18, r19)
            goto L_0x000f
        L_0x01c0:
            r0 = r19
            r1 = 0
            java.lang.String r2 = r18.nextString()
            goto L_0x000f
        L_0x01c9:
            com.airbnb.lottie.model.content.ShapeStroke r12 = new com.airbnb.lottie.model.content.ShapeStroke
            r0 = r12
            r1 = r2
            r2 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r10
            r10 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.ShapeStrokeParser.parse(android.util.JsonReader, com.airbnb.lottie.LottieComposition):com.airbnb.lottie.model.content.ShapeStroke");
    }
}
