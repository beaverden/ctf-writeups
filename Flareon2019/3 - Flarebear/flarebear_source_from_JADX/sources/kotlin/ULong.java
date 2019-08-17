package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.ranges.ULongRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ExperimentalUnsignedTypes
@SinceKotlin(version = "1.3")
@Metadata(mo6116bv = {1, 0, 3}, mo6117d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b@\u0018\u0000 m2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001mB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b \u0010\u000bJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010\t\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020\rHÖ\u0001J\u0013\u0010'\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b(\u0010\u0005J\u0013\u0010)\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u0005J\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b,\u0010\u001dJ\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b-\u0010\u001fJ\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b.\u0010\u000bJ\u001b\u0010+\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b/\u0010\"J\u001b\u00100\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\b1\u0010\u000bJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u001dJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u001fJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u000bJ\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b6\u0010\"J\u001b\u00107\u001a\u0002082\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\b<\u0010\u001dJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\b=\u0010\u001fJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b>\u0010\u000bJ\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\b?\u0010\"J\u001b\u0010@\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\rH\fø\u0001\u0000¢\u0006\u0004\bB\u0010\u001fJ\u001b\u0010C\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\rH\fø\u0001\u0000¢\u0006\u0004\bD\u0010\u001fJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\nø\u0001\u0000¢\u0006\u0004\bF\u0010\u001dJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\nø\u0001\u0000¢\u0006\u0004\bG\u0010\u001fJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\bH\u0010\u000bJ\u001b\u0010E\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\nø\u0001\u0000¢\u0006\u0004\bI\u0010\"J\u0010\u0010J\u001a\u00020KH\b¢\u0006\u0004\bL\u0010MJ\u0010\u0010N\u001a\u00020OH\b¢\u0006\u0004\bP\u0010QJ\u0010\u0010R\u001a\u00020SH\b¢\u0006\u0004\bT\u0010UJ\u0010\u0010V\u001a\u00020\rH\b¢\u0006\u0004\bW\u0010XJ\u0010\u0010Y\u001a\u00020\u0003H\b¢\u0006\u0004\bZ\u0010\u0005J\u0010\u0010[\u001a\u00020\\H\b¢\u0006\u0004\b]\u0010^J\u000f\u0010_\u001a\u00020`H\u0016¢\u0006\u0004\ba\u0010bJ\u0013\u0010c\u001a\u00020\u000eH\bø\u0001\u0000¢\u0006\u0004\bd\u0010MJ\u0013\u0010e\u001a\u00020\u0011H\bø\u0001\u0000¢\u0006\u0004\bf\u0010XJ\u0013\u0010g\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\bh\u0010\u0005J\u0013\u0010i\u001a\u00020\u0016H\bø\u0001\u0000¢\u0006\u0004\bj\u0010^J\u001b\u0010k\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\fø\u0001\u0000¢\u0006\u0004\bl\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006n"}, mo6118d2 = {"Lkotlin/ULong;", "", "data", "", "constructor-impl", "(J)J", "data$annotations", "()V", "and", "other", "and-VKZWuLQ", "(JJ)J", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(JB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(JI)I", "compareTo-VKZWuLQ", "(JJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(JS)I", "dec", "dec-impl", "div", "div-7apg3OU", "(JB)J", "div-WZ4Q5Ns", "(JI)J", "div-VKZWuLQ", "div-xj2QHRw", "(JS)J", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-VKZWuLQ", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/ULongRange;", "rangeTo-VKZWuLQ", "(JJ)Lkotlin/ranges/ULongRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-impl", "shr", "shr-impl", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(J)B", "toDouble", "", "toDouble-impl", "(J)D", "toFloat", "", "toFloat-impl", "(J)F", "toInt", "toInt-impl", "(J)I", "toLong", "toLong-impl", "toShort", "", "toShort-impl", "(J)S", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-VKZWuLQ", "Companion", "kotlin-stdlib"}, mo6119k = 1, mo6120mv = {1, 1, 15})
/* compiled from: ULong.kt */
public final class ULong implements Comparable<ULong> {
    public static final Companion Companion = new Companion(null);
    public static final long MAX_VALUE = -1;
    public static final long MIN_VALUE = 0;
    public static final int SIZE_BITS = 64;
    public static final int SIZE_BYTES = 8;
    private final long data;

    @Metadata(mo6116bv = {1, 0, 3}, mo6117d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, mo6118d2 = {"Lkotlin/ULong$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/ULong;", "J", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, mo6119k = 1, mo6120mv = {1, 1, 15})
    /* compiled from: ULong.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @NotNull
    /* renamed from: box-impl reason: not valid java name */
    public static final /* synthetic */ ULong m181boximpl(long j) {
        return new ULong(j);
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ reason: not valid java name */
    private int m183compareToVKZWuLQ(long j) {
        return m184compareToVKZWuLQ(this.data, j);
    }

    @PublishedApi
    /* renamed from: constructor-impl reason: not valid java name */
    public static long m187constructorimpl(long j) {
        return j;
    }

    @PublishedApi
    public static /* synthetic */ void data$annotations() {
    }

    /* renamed from: equals-impl reason: not valid java name */
    public static boolean m193equalsimpl(long j, @Nullable Object obj) {
        if (obj instanceof ULong) {
            if (j == ((ULong) obj).m230unboximpl()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: equals-impl0 reason: not valid java name */
    public static final boolean m194equalsimpl0(long j, long j2) {
        throw null;
    }

    /* renamed from: hashCode-impl reason: not valid java name */
    public static int m195hashCodeimpl(long j) {
        return (int) (j ^ (j >>> 32));
    }

    @InlineOnly
    /* renamed from: toByte-impl reason: not valid java name */
    private static final byte m218toByteimpl(long j) {
        return (byte) ((int) j);
    }

    @InlineOnly
    /* renamed from: toInt-impl reason: not valid java name */
    private static final int m221toIntimpl(long j) {
        return (int) j;
    }

    @InlineOnly
    /* renamed from: toLong-impl reason: not valid java name */
    private static final long m222toLongimpl(long j) {
        return j;
    }

    @InlineOnly
    /* renamed from: toShort-impl reason: not valid java name */
    private static final short m223toShortimpl(long j) {
        return (short) ((int) j);
    }

    @InlineOnly
    /* renamed from: toULong-impl reason: not valid java name */
    private static final long m227toULongimpl(long j) {
        return j;
    }

    public boolean equals(Object obj) {
        return m193equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m195hashCodeimpl(this.data);
    }

    @NotNull
    public String toString() {
        return m224toStringimpl(this.data);
    }

    /* renamed from: unbox-impl reason: not valid java name */
    public final /* synthetic */ long m230unboximpl() {
        return this.data;
    }

    @PublishedApi
    private /* synthetic */ ULong(long j) {
        this.data = j;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return m183compareToVKZWuLQ(((ULong) obj).m230unboximpl());
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU reason: not valid java name */
    private static final int m182compareTo7apg3OU(long j, byte b) {
        return UnsignedKt.ulongCompare(j, m187constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw reason: not valid java name */
    private static final int m186compareToxj2QHRw(long j, short s) {
        return UnsignedKt.ulongCompare(j, m187constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns reason: not valid java name */
    private static final int m185compareToWZ4Q5Ns(long j, int i) {
        return UnsignedKt.ulongCompare(j, m187constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ reason: not valid java name */
    private static int m184compareToVKZWuLQ(long j, long j2) {
        return UnsignedKt.ulongCompare(j, j2);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU reason: not valid java name */
    private static final long m203plus7apg3OU(long j, byte b) {
        return m187constructorimpl(j + m187constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw reason: not valid java name */
    private static final long m206plusxj2QHRw(long j, short s) {
        return m187constructorimpl(j + m187constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns reason: not valid java name */
    private static final long m205plusWZ4Q5Ns(long j, int i) {
        return m187constructorimpl(j + m187constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ reason: not valid java name */
    private static final long m204plusVKZWuLQ(long j, long j2) {
        return m187constructorimpl(j + j2);
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU reason: not valid java name */
    private static final long m198minus7apg3OU(long j, byte b) {
        return m187constructorimpl(j - m187constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw reason: not valid java name */
    private static final long m201minusxj2QHRw(long j, short s) {
        return m187constructorimpl(j - m187constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns reason: not valid java name */
    private static final long m200minusWZ4Q5Ns(long j, int i) {
        return m187constructorimpl(j - m187constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ reason: not valid java name */
    private static final long m199minusVKZWuLQ(long j, long j2) {
        return m187constructorimpl(j - j2);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU reason: not valid java name */
    private static final long m214times7apg3OU(long j, byte b) {
        return m187constructorimpl(j * m187constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw reason: not valid java name */
    private static final long m217timesxj2QHRw(long j, short s) {
        return m187constructorimpl(j * m187constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns reason: not valid java name */
    private static final long m216timesWZ4Q5Ns(long j, int i) {
        return m187constructorimpl(j * m187constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ reason: not valid java name */
    private static final long m215timesVKZWuLQ(long j, long j2) {
        return m187constructorimpl(j * j2);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU reason: not valid java name */
    private static final long m189div7apg3OU(long j, byte b) {
        return UnsignedKt.m318ulongDivideeb3DHEI(j, m187constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw reason: not valid java name */
    private static final long m192divxj2QHRw(long j, short s) {
        return UnsignedKt.m318ulongDivideeb3DHEI(j, m187constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns reason: not valid java name */
    private static final long m191divWZ4Q5Ns(long j, int i) {
        return UnsignedKt.m318ulongDivideeb3DHEI(j, m187constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ reason: not valid java name */
    private static final long m190divVKZWuLQ(long j, long j2) {
        return UnsignedKt.m318ulongDivideeb3DHEI(j, j2);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU reason: not valid java name */
    private static final long m208rem7apg3OU(long j, byte b) {
        return UnsignedKt.m319ulongRemaindereb3DHEI(j, m187constructorimpl(((long) b) & 255));
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw reason: not valid java name */
    private static final long m211remxj2QHRw(long j, short s) {
        return UnsignedKt.m319ulongRemaindereb3DHEI(j, m187constructorimpl(((long) s) & 65535));
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns reason: not valid java name */
    private static final long m210remWZ4Q5Ns(long j, int i) {
        return UnsignedKt.m319ulongRemaindereb3DHEI(j, m187constructorimpl(((long) i) & 4294967295L));
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ reason: not valid java name */
    private static final long m209remVKZWuLQ(long j, long j2) {
        return UnsignedKt.m319ulongRemaindereb3DHEI(j, j2);
    }

    @InlineOnly
    /* renamed from: inc-impl reason: not valid java name */
    private static final long m196incimpl(long j) {
        return m187constructorimpl(j + 1);
    }

    @InlineOnly
    /* renamed from: dec-impl reason: not valid java name */
    private static final long m188decimpl(long j) {
        return m187constructorimpl(j - 1);
    }

    @InlineOnly
    /* renamed from: rangeTo-VKZWuLQ reason: not valid java name */
    private static final ULongRange m207rangeToVKZWuLQ(long j, long j2) {
        ULongRange uLongRange = new ULongRange(j, j2, null);
        return uLongRange;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=long, for r2v0, types: [int, long] */
    @kotlin.internal.InlineOnly
    /* renamed from: shl-impl reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final long m212shlimpl(long r0, long r2) {
        /*
            long r0 = r0 << r2
            long r0 = m187constructorimpl(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ULong.m212shlimpl(long, int):long");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=long, for r2v0, types: [int, long] */
    @kotlin.internal.InlineOnly
    /* renamed from: shr-impl reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final long m213shrimpl(long r0, long r2) {
        /*
            long r0 = r0 >>> r2
            long r0 = m187constructorimpl(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ULong.m213shrimpl(long, int):long");
    }

    @InlineOnly
    /* renamed from: and-VKZWuLQ reason: not valid java name */
    private static final long m180andVKZWuLQ(long j, long j2) {
        return m187constructorimpl(j & j2);
    }

    @InlineOnly
    /* renamed from: or-VKZWuLQ reason: not valid java name */
    private static final long m202orVKZWuLQ(long j, long j2) {
        return m187constructorimpl(j | j2);
    }

    @InlineOnly
    /* renamed from: xor-VKZWuLQ reason: not valid java name */
    private static final long m229xorVKZWuLQ(long j, long j2) {
        return m187constructorimpl(j ^ j2);
    }

    @InlineOnly
    /* renamed from: inv-impl reason: not valid java name */
    private static final long m197invimpl(long j) {
        return m187constructorimpl(~j);
    }

    @InlineOnly
    /* renamed from: toUByte-impl reason: not valid java name */
    private static final byte m225toUByteimpl(long j) {
        return UByte.m51constructorimpl((byte) ((int) j));
    }

    @InlineOnly
    /* renamed from: toUShort-impl reason: not valid java name */
    private static final short m228toUShortimpl(long j) {
        return UShort.m256constructorimpl((short) ((int) j));
    }

    @InlineOnly
    /* renamed from: toUInt-impl reason: not valid java name */
    private static final int m226toUIntimpl(long j) {
        return UInt.m118constructorimpl((int) j);
    }

    @InlineOnly
    /* renamed from: toFloat-impl reason: not valid java name */
    private static final float m220toFloatimpl(long j) {
        return (float) UnsignedKt.ulongToDouble(j);
    }

    @InlineOnly
    /* renamed from: toDouble-impl reason: not valid java name */
    private static final double m219toDoubleimpl(long j) {
        return UnsignedKt.ulongToDouble(j);
    }

    @NotNull
    /* renamed from: toString-impl reason: not valid java name */
    public static String m224toStringimpl(long j) {
        return UnsignedKt.ulongToString(j);
    }
}
