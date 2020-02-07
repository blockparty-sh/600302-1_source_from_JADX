package p015io.github.luizgrp.sectionedrecyclerviewadapter;

/* renamed from: io.github.luizgrp.sectionedrecyclerviewadapter.SectionPositionIdentifier */
interface SectionPositionIdentifier {
    int getFooterPosition();

    int getHeaderPosition();

    int getPositionInAdapter(int i);

    int getPositionInSection(int i);

    int getSectionPosition();
}
