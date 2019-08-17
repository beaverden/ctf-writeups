package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.URandomKt;
import kotlin.ranges.UIntProgression.Companion;
import org.jetbrains.annotations.NotNull;

@Metadata(mo6116bv = {1, 0, 3}, mo6117d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\n\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001e\u0010\u0000\u001a\u00020\b*\u00020\b2\u0006\u0010\u0002\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001e\u0010\u0000\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u001e\u0010\u000e\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0004\u001a\u001e\u0010\u000e\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0007\u001a\u001e\u0010\u000e\u001a\u00020\b*\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\n\u001a\u001e\u0010\u000e\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\r\u001a&\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a&\u0010\u0014\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a$\u0010\u0014\u001a\u00020\u0005*\u00020\u00052\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a&\u0010\u0014\u001a\u00020\b*\u00020\b2\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a$\u0010\u0014\u001a\u00020\b*\u00020\b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a&\u0010\u0014\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\b\u0010)\u001a\u0004\u0018\u00010\u0005H\nø\u0001\u0000¢\u0006\u0002\b*\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a\u001f\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\u000bH\u0002ø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\u0006\u0010&\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b0\u00101\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\u0006\u0010&\u001a\u00020\u0005H\u0002ø\u0001\u0000¢\u0006\u0004\b2\u00103\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\b\u0010)\u001a\u0004\u0018\u00010\bH\nø\u0001\u0000¢\u0006\u0002\b4\u001a\u001f\u0010#\u001a\u00020$*\u00020/2\u0006\u0010&\u001a\u00020\u000bH\u0002ø\u0001\u0000¢\u0006\u0004\b5\u00106\u001a\u001f\u00107\u001a\u000208*\u00020\u00012\u0006\u00109\u001a\u00020\u0001H\u0004ø\u0001\u0000¢\u0006\u0004\b:\u0010;\u001a\u001f\u00107\u001a\u000208*\u00020\u00052\u0006\u00109\u001a\u00020\u0005H\u0004ø\u0001\u0000¢\u0006\u0004\b<\u0010=\u001a\u001f\u00107\u001a\u00020>*\u00020\b2\u0006\u00109\u001a\u00020\bH\u0004ø\u0001\u0000¢\u0006\u0004\b?\u0010@\u001a\u001f\u00107\u001a\u000208*\u00020\u000b2\u0006\u00109\u001a\u00020\u000bH\u0004ø\u0001\u0000¢\u0006\u0004\bA\u0010B\u001a\u0015\u0010C\u001a\u00020\u0005*\u00020%H\bø\u0001\u0000¢\u0006\u0002\u0010D\u001a\u001c\u0010C\u001a\u00020\u0005*\u00020%2\u0006\u0010C\u001a\u00020EH\u0007ø\u0001\u0000¢\u0006\u0002\u0010F\u001a\u0015\u0010C\u001a\u00020\b*\u00020/H\bø\u0001\u0000¢\u0006\u0002\u0010G\u001a\u001c\u0010C\u001a\u00020\b*\u00020/2\u0006\u0010C\u001a\u00020EH\u0007ø\u0001\u0000¢\u0006\u0002\u0010H\u001a\f\u0010I\u001a\u000208*\u000208H\u0007\u001a\f\u0010I\u001a\u00020>*\u00020>H\u0007\u001a\u0015\u0010J\u001a\u000208*\u0002082\u0006\u0010J\u001a\u00020KH\u0004\u001a\u0015\u0010J\u001a\u00020>*\u00020>2\u0006\u0010J\u001a\u00020LH\u0004\u001a\u001f\u0010M\u001a\u00020%*\u00020\u00012\u0006\u00109\u001a\u00020\u0001H\u0004ø\u0001\u0000¢\u0006\u0004\bN\u0010O\u001a\u001f\u0010M\u001a\u00020%*\u00020\u00052\u0006\u00109\u001a\u00020\u0005H\u0004ø\u0001\u0000¢\u0006\u0004\bP\u0010Q\u001a\u001f\u0010M\u001a\u00020/*\u00020\b2\u0006\u00109\u001a\u00020\bH\u0004ø\u0001\u0000¢\u0006\u0004\bR\u0010S\u001a\u001f\u0010M\u001a\u00020%*\u00020\u000b2\u0006\u00109\u001a\u00020\u000bH\u0004ø\u0001\u0000¢\u0006\u0004\bT\u0010U\u0002\u0004\n\u0002\b\u0019¨\u0006V"}, mo6118d2 = {"coerceAtLeast", "Lkotlin/UByte;", "minimumValue", "coerceAtLeast-Kr8caGY", "(BB)B", "Lkotlin/UInt;", "coerceAtLeast-J1ME1BU", "(II)I", "Lkotlin/ULong;", "coerceAtLeast-eb3DHEI", "(JJ)J", "Lkotlin/UShort;", "coerceAtLeast-5PvTz6A", "(SS)S", "coerceAtMost", "maximumValue", "coerceAtMost-Kr8caGY", "coerceAtMost-J1ME1BU", "coerceAtMost-eb3DHEI", "coerceAtMost-5PvTz6A", "coerceIn", "coerceIn-b33U2AM", "(BBB)B", "coerceIn-WZ9TVnA", "(III)I", "range", "Lkotlin/ranges/ClosedRange;", "coerceIn-wuiCnnA", "(ILkotlin/ranges/ClosedRange;)I", "coerceIn-sambcqE", "(JJJ)J", "coerceIn-JPwROB0", "(JLkotlin/ranges/ClosedRange;)J", "coerceIn-VKSA0NQ", "(SSS)S", "contains", "", "Lkotlin/ranges/UIntRange;", "value", "contains-68kG9v0", "(Lkotlin/ranges/UIntRange;B)Z", "element", "contains-biwQdVI", "contains-fz5IDCE", "(Lkotlin/ranges/UIntRange;J)Z", "contains-ZsK3CEQ", "(Lkotlin/ranges/UIntRange;S)Z", "Lkotlin/ranges/ULongRange;", "contains-ULb-yJY", "(Lkotlin/ranges/ULongRange;B)Z", "contains-Gab390E", "(Lkotlin/ranges/ULongRange;I)Z", "contains-GYNo2lE", "contains-uhHAxoY", "(Lkotlin/ranges/ULongRange;S)Z", "downTo", "Lkotlin/ranges/UIntProgression;", "to", "downTo-Kr8caGY", "(BB)Lkotlin/ranges/UIntProgression;", "downTo-J1ME1BU", "(II)Lkotlin/ranges/UIntProgression;", "Lkotlin/ranges/ULongProgression;", "downTo-eb3DHEI", "(JJ)Lkotlin/ranges/ULongProgression;", "downTo-5PvTz6A", "(SS)Lkotlin/ranges/UIntProgression;", "random", "(Lkotlin/ranges/UIntRange;)I", "Lkotlin/random/Random;", "(Lkotlin/ranges/UIntRange;Lkotlin/random/Random;)I", "(Lkotlin/ranges/ULongRange;)J", "(Lkotlin/ranges/ULongRange;Lkotlin/random/Random;)J", "reversed", "step", "", "", "until", "until-Kr8caGY", "(BB)Lkotlin/ranges/UIntRange;", "until-J1ME1BU", "(II)Lkotlin/ranges/UIntRange;", "until-eb3DHEI", "(JJ)Lkotlin/ranges/ULongRange;", "until-5PvTz6A", "(SS)Lkotlin/ranges/UIntRange;", "kotlin-stdlib"}, mo6119k = 5, mo6120mv = {1, 1, 15}, mo6122xi = 1, mo6123xs = "kotlin/ranges/URangesKt")
/* compiled from: _URanges.kt */
class URangesKt___URangesKt {
    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int random(@NotNull UIntRange uIntRange) {
        return URangesKt.random(uIntRange, (Random) Random.Default);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long random(@NotNull ULongRange uLongRange) {
        return URangesKt.random(uLongRange, (Random) Random.Default);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final int random(@NotNull UIntRange uIntRange, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(uIntRange, "$this$random");
        Intrinsics.checkParameterIsNotNull(random, "random");
        try {
            return URandomKt.nextUInt(random, uIntRange);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    public static final long random(@NotNull ULongRange uLongRange, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(uLongRange, "$this$random");
        Intrinsics.checkParameterIsNotNull(random, "random");
        try {
            return URandomKt.nextULong(random, uLongRange);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: contains-biwQdVI reason: not valid java name */
    private static final boolean m904containsbiwQdVI(@NotNull UIntRange uIntRange, UInt uInt) {
        Intrinsics.checkParameterIsNotNull(uIntRange, "$this$contains");
        return uInt != null && uIntRange.m882containsWZ4Q5Ns(uInt.m161unboximpl());
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    @InlineOnly
    /* renamed from: contains-GYNo2lE reason: not valid java name */
    private static final boolean m900containsGYNo2lE(@NotNull ULongRange uLongRange, ULong uLong) {
        Intrinsics.checkParameterIsNotNull(uLongRange, "$this$contains");
        return uLong != null && uLongRange.m884containsVKZWuLQ(uLong.m230unboximpl());
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contains-68kG9v0 reason: not valid java name */
    public static final boolean m899contains68kG9v0(@NotNull UIntRange uIntRange, byte b) {
        Intrinsics.checkParameterIsNotNull(uIntRange, "$this$contains");
        return uIntRange.m882containsWZ4Q5Ns(UInt.m118constructorimpl(b & UByte.MAX_VALUE));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contains-ULb-yJY reason: not valid java name */
    public static final boolean m902containsULbyJY(@NotNull ULongRange uLongRange, byte b) {
        Intrinsics.checkParameterIsNotNull(uLongRange, "$this$contains");
        return uLongRange.m884containsVKZWuLQ(ULong.m187constructorimpl(((long) b) & 255));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contains-Gab390E reason: not valid java name */
    public static final boolean m901containsGab390E(@NotNull ULongRange uLongRange, int i) {
        Intrinsics.checkParameterIsNotNull(uLongRange, "$this$contains");
        return uLongRange.m884containsVKZWuLQ(ULong.m187constructorimpl(((long) i) & 4294967295L));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contains-fz5IDCE reason: not valid java name */
    public static final boolean m905containsfz5IDCE(@NotNull UIntRange uIntRange, long j) {
        Intrinsics.checkParameterIsNotNull(uIntRange, "$this$contains");
        return ULong.m187constructorimpl(j >>> 32) == 0 && uIntRange.m882containsWZ4Q5Ns(UInt.m118constructorimpl((int) j));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contains-ZsK3CEQ reason: not valid java name */
    public static final boolean m903containsZsK3CEQ(@NotNull UIntRange uIntRange, short s) {
        Intrinsics.checkParameterIsNotNull(uIntRange, "$this$contains");
        return uIntRange.m882containsWZ4Q5Ns(UInt.m118constructorimpl(s & UShort.MAX_VALUE));
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: contains-uhHAxoY reason: not valid java name */
    public static final boolean m906containsuhHAxoY(@NotNull ULongRange uLongRange, short s) {
        Intrinsics.checkParameterIsNotNull(uLongRange, "$this$contains");
        return uLongRange.m884containsVKZWuLQ(ULong.m187constructorimpl(((long) s) & 65535));
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: downTo-Kr8caGY reason: not valid java name */
    public static final UIntProgression m909downToKr8caGY(byte b, byte b2) {
        return UIntProgression.Companion.m881fromClosedRangeNkh28Cs(UInt.m118constructorimpl(b & UByte.MAX_VALUE), UInt.m118constructorimpl(b2 & UByte.MAX_VALUE), -1);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: downTo-J1ME1BU reason: not valid java name */
    public static final UIntProgression m908downToJ1ME1BU(int i, int i2) {
        return UIntProgression.Companion.m881fromClosedRangeNkh28Cs(i, i2, -1);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: downTo-eb3DHEI reason: not valid java name */
    public static final ULongProgression m910downToeb3DHEI(long j, long j2) {
        return ULongProgression.Companion.m883fromClosedRange7ftBX0g(j, j2, -1);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: downTo-5PvTz6A reason: not valid java name */
    public static final UIntProgression m907downTo5PvTz6A(short s, short s2) {
        return UIntProgression.Companion.m881fromClosedRangeNkh28Cs(UInt.m118constructorimpl(s & UShort.MAX_VALUE), UInt.m118constructorimpl(s2 & UShort.MAX_VALUE), -1);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final UIntProgression reversed(@NotNull UIntProgression uIntProgression) {
        Intrinsics.checkParameterIsNotNull(uIntProgression, "$this$reversed");
        return UIntProgression.Companion.m881fromClosedRangeNkh28Cs(uIntProgression.getLast(), uIntProgression.getFirst(), -uIntProgression.getStep());
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final ULongProgression reversed(@NotNull ULongProgression uLongProgression) {
        Intrinsics.checkParameterIsNotNull(uLongProgression, "$this$reversed");
        return ULongProgression.Companion.m883fromClosedRange7ftBX0g(uLongProgression.getLast(), uLongProgression.getFirst(), -uLongProgression.getStep());
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final UIntProgression step(@NotNull UIntProgression uIntProgression, int i) {
        Intrinsics.checkParameterIsNotNull(uIntProgression, "$this$step");
        RangesKt.checkStepIsPositive(i > 0, Integer.valueOf(i));
        Companion companion = UIntProgression.Companion;
        int first = uIntProgression.getFirst();
        int last = uIntProgression.getLast();
        if (uIntProgression.getStep() <= 0) {
            i = -i;
        }
        return companion.m881fromClosedRangeNkh28Cs(first, last, i);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    public static final ULongProgression step(@NotNull ULongProgression uLongProgression, long j) {
        Intrinsics.checkParameterIsNotNull(uLongProgression, "$this$step");
        RangesKt.checkStepIsPositive(j > 0, Long.valueOf(j));
        ULongProgression.Companion companion = ULongProgression.Companion;
        long first = uLongProgression.getFirst();
        long last = uLongProgression.getLast();
        if (uLongProgression.getStep() <= 0) {
            j = -j;
        }
        return companion.m883fromClosedRange7ftBX0g(first, last, j);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: until-Kr8caGY reason: not valid java name */
    public static final UIntRange m913untilKr8caGY(byte b, byte b2) {
        byte b3 = b2 & UByte.MAX_VALUE;
        if (Intrinsics.compare((int) b3, 0) <= 0) {
            return UIntRange.Companion.getEMPTY();
        }
        return new UIntRange(UInt.m118constructorimpl(b & UByte.MAX_VALUE), UInt.m118constructorimpl(UInt.m118constructorimpl(b3) - 1), null);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: until-J1ME1BU reason: not valid java name */
    public static final UIntRange m912untilJ1ME1BU(int i, int i2) {
        if (UnsignedKt.uintCompare(i2, 0) <= 0) {
            return UIntRange.Companion.getEMPTY();
        }
        return new UIntRange(i, UInt.m118constructorimpl(i2 - 1), null);
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: until-eb3DHEI reason: not valid java name */
    public static final ULongRange m914untileb3DHEI(long j, long j2) {
        if (UnsignedKt.ulongCompare(j2, 0) <= 0) {
            return ULongRange.Companion.getEMPTY();
        }
        ULongRange uLongRange = new ULongRange(j, ULong.m187constructorimpl(j2 - ULong.m187constructorimpl(((long) 1) & 4294967295L)), null);
        return uLongRange;
    }

    @ExperimentalUnsignedTypes
    @NotNull
    @SinceKotlin(version = "1.3")
    /* renamed from: until-5PvTz6A reason: not valid java name */
    public static final UIntRange m911until5PvTz6A(short s, short s2) {
        short s3 = s2 & UShort.MAX_VALUE;
        if (Intrinsics.compare((int) s3, 0) <= 0) {
            return UIntRange.Companion.getEMPTY();
        }
        return new UIntRange(UInt.m118constructorimpl(s & UShort.MAX_VALUE), UInt.m118constructorimpl(UInt.m118constructorimpl(s3) - 1), null);
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceAtLeast-J1ME1BU reason: not valid java name */
    public static final int m886coerceAtLeastJ1ME1BU(int i, int i2) {
        return UnsignedKt.uintCompare(i, i2) < 0 ? i2 : i;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceAtLeast-eb3DHEI reason: not valid java name */
    public static final long m888coerceAtLeasteb3DHEI(long j, long j2) {
        return UnsignedKt.ulongCompare(j, j2) < 0 ? j2 : j;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceAtLeast-Kr8caGY reason: not valid java name */
    public static final byte m887coerceAtLeastKr8caGY(byte b, byte b2) {
        return Intrinsics.compare((int) b & UByte.MAX_VALUE, (int) b2 & UByte.MAX_VALUE) < 0 ? b2 : b;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceAtLeast-5PvTz6A reason: not valid java name */
    public static final short m885coerceAtLeast5PvTz6A(short s, short s2) {
        return Intrinsics.compare((int) s & UShort.MAX_VALUE, (int) 65535 & s2) < 0 ? s2 : s;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceAtMost-J1ME1BU reason: not valid java name */
    public static final int m890coerceAtMostJ1ME1BU(int i, int i2) {
        return UnsignedKt.uintCompare(i, i2) > 0 ? i2 : i;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceAtMost-eb3DHEI reason: not valid java name */
    public static final long m892coerceAtMosteb3DHEI(long j, long j2) {
        return UnsignedKt.ulongCompare(j, j2) > 0 ? j2 : j;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceAtMost-Kr8caGY reason: not valid java name */
    public static final byte m891coerceAtMostKr8caGY(byte b, byte b2) {
        return Intrinsics.compare((int) b & UByte.MAX_VALUE, (int) b2 & UByte.MAX_VALUE) > 0 ? b2 : b;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceAtMost-5PvTz6A reason: not valid java name */
    public static final short m889coerceAtMost5PvTz6A(short s, short s2) {
        return Intrinsics.compare((int) s & UShort.MAX_VALUE, (int) 65535 & s2) > 0 ? s2 : s;
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceIn-WZ9TVnA reason: not valid java name */
    public static final int m895coerceInWZ9TVnA(int i, int i2, int i3) {
        if (UnsignedKt.uintCompare(i2, i3) > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot coerce value to an empty range: maximum ");
            sb.append(UInt.m155toStringimpl(i3));
            sb.append(" is less than minimum ");
            sb.append(UInt.m155toStringimpl(i2));
            sb.append('.');
            throw new IllegalArgumentException(sb.toString());
        } else if (UnsignedKt.uintCompare(i, i2) < 0) {
            return i2;
        } else {
            return UnsignedKt.uintCompare(i, i3) > 0 ? i3 : i;
        }
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceIn-sambcqE reason: not valid java name */
    public static final long m897coerceInsambcqE(long j, long j2, long j3) {
        if (UnsignedKt.ulongCompare(j2, j3) > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot coerce value to an empty range: maximum ");
            sb.append(ULong.m224toStringimpl(j3));
            sb.append(" is less than minimum ");
            sb.append(ULong.m224toStringimpl(j2));
            sb.append('.');
            throw new IllegalArgumentException(sb.toString());
        } else if (UnsignedKt.ulongCompare(j, j2) < 0) {
            return j2;
        } else {
            return UnsignedKt.ulongCompare(j, j3) > 0 ? j3 : j;
        }
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceIn-b33U2AM reason: not valid java name */
    public static final byte m896coerceInb33U2AM(byte b, byte b2, byte b3) {
        byte b4 = b2 & UByte.MAX_VALUE;
        byte b5 = b3 & UByte.MAX_VALUE;
        if (Intrinsics.compare((int) b4, (int) b5) <= 0) {
            byte b6 = b & UByte.MAX_VALUE;
            if (Intrinsics.compare((int) b6, (int) b4) < 0) {
                return b2;
            }
            return Intrinsics.compare((int) b6, (int) b5) > 0 ? b3 : b;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Cannot coerce value to an empty range: maximum ");
        sb.append(UByte.m86toStringimpl(b3));
        sb.append(" is less than minimum ");
        sb.append(UByte.m86toStringimpl(b2));
        sb.append('.');
        throw new IllegalArgumentException(sb.toString());
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceIn-VKSA0NQ reason: not valid java name */
    public static final short m894coerceInVKSA0NQ(short s, short s2, short s3) {
        short s4 = s2 & UShort.MAX_VALUE;
        short s5 = s3 & UShort.MAX_VALUE;
        if (Intrinsics.compare((int) s4, (int) s5) <= 0) {
            short s6 = 65535 & s;
            if (Intrinsics.compare((int) s6, (int) s4) < 0) {
                return s2;
            }
            return Intrinsics.compare((int) s6, (int) s5) > 0 ? s3 : s;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Cannot coerce value to an empty range: maximum ");
        sb.append(UShort.m291toStringimpl(s3));
        sb.append(" is less than minimum ");
        sb.append(UShort.m291toStringimpl(s2));
        sb.append('.');
        throw new IllegalArgumentException(sb.toString());
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceIn-wuiCnnA reason: not valid java name */
    public static final int m898coerceInwuiCnnA(int i, @NotNull ClosedRange<UInt> closedRange) {
        Intrinsics.checkParameterIsNotNull(closedRange, "range");
        if (closedRange instanceof ClosedFloatingPointRange) {
            return ((UInt) RangesKt.coerceIn(UInt.m112boximpl(i), (ClosedFloatingPointRange) closedRange)).m161unboximpl();
        }
        if (!closedRange.isEmpty()) {
            if (UnsignedKt.uintCompare(i, ((UInt) closedRange.getStart()).m161unboximpl()) < 0) {
                i = ((UInt) closedRange.getStart()).m161unboximpl();
            } else if (UnsignedKt.uintCompare(i, ((UInt) closedRange.getEndInclusive()).m161unboximpl()) > 0) {
                i = ((UInt) closedRange.getEndInclusive()).m161unboximpl();
            }
            return i;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Cannot coerce value to an empty range: ");
        sb.append(closedRange);
        sb.append('.');
        throw new IllegalArgumentException(sb.toString());
    }

    @ExperimentalUnsignedTypes
    @SinceKotlin(version = "1.3")
    /* renamed from: coerceIn-JPwROB0 reason: not valid java name */
    public static final long m893coerceInJPwROB0(long j, @NotNull ClosedRange<ULong> closedRange) {
        Intrinsics.checkParameterIsNotNull(closedRange, "range");
        if (closedRange instanceof ClosedFloatingPointRange) {
            return ((ULong) RangesKt.coerceIn(ULong.m181boximpl(j), (ClosedFloatingPointRange) closedRange)).m230unboximpl();
        }
        if (!closedRange.isEmpty()) {
            if (UnsignedKt.ulongCompare(j, ((ULong) closedRange.getStart()).m230unboximpl()) < 0) {
                j = ((ULong) closedRange.getStart()).m230unboximpl();
            } else if (UnsignedKt.ulongCompare(j, ((ULong) closedRange.getEndInclusive()).m230unboximpl()) > 0) {
                j = ((ULong) closedRange.getEndInclusive()).m230unboximpl();
            }
            return j;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Cannot coerce value to an empty range: ");
        sb.append(closedRange);
        sb.append('.');
        throw new IllegalArgumentException(sb.toString());
    }
}
