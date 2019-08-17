package com.fireeye.flarebear;

import kotlin.Metadata;

@Metadata(mo6116bv = {1, 0, 3}, mo6117d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, mo6118d2 = {"com/fireeye/flarebear/FlareBearActivity$changeFlareBearImage$r$1", "Ljava/lang/Runnable;", "run", "", "app_release"}, mo6119k = 1, mo6120mv = {1, 1, 15})
/* compiled from: FlareBearActivity.kt */
public final class FlareBearActivity$changeFlareBearImage$r$1 implements Runnable {
    final /* synthetic */ FlareBearActivity this$0;

    FlareBearActivity$changeFlareBearImage$r$1(FlareBearActivity flareBearActivity) {
        this.this$0 = flareBearActivity;
    }

    public void run() {
        this.this$0.getHandler().postDelayed(this, 1500);
        this.this$0.changeImageAndTag();
    }
}
