package com.fireeye.flarebear;

import kotlin.Metadata;

@Metadata(mo6116bv = {1, 0, 3}, mo6117d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo6118d2 = {"<anonymous>", "", "run"}, mo6119k = 3, mo6120mv = {1, 1, 15})
/* compiled from: FlareBearActivity.kt */
final class FlareBearActivity$activityUi$3 implements Runnable {
    final /* synthetic */ FlareBearActivity this$0;

    FlareBearActivity$activityUi$3(FlareBearActivity flareBearActivity) {
        this.this$0 = flareBearActivity;
    }

    public final void run() {
        this.this$0.setMood();
        this.this$0.addPoos();
        this.this$0.changeFlareBearImage();
    }
}
