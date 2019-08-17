package com.fireeye.flarebear;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import kotlin.Metadata;

@Metadata(mo6116bv = {1, 0, 3}, mo6117d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo6118d2 = {"<anonymous>", "", "run"}, mo6119k = 3, mo6120mv = {1, 1, 15})
/* compiled from: FlareBearActivity.kt */
final class FlareBearActivity$activityUi$1 implements Runnable {
    final /* synthetic */ Drawable $drawable2;
    final /* synthetic */ FlareBearActivity this$0;

    FlareBearActivity$activityUi$1(FlareBearActivity flareBearActivity, Drawable drawable) {
        this.this$0 = flareBearActivity;
        this.$drawable2 = drawable;
    }

    public final void run() {
        ((ImageView) this.this$0._$_findCachedViewById(C0272R.C0274id.flareBearImageView)).setImageDrawable(this.$drawable2);
        ((ImageView) this.this$0._$_findCachedViewById(C0272R.C0274id.flareBearImageView)).setTag("activity2");
    }
}
