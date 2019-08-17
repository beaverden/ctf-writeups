package com.fireeye.flarebear;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.widget.ImageView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo6116bv = {1, 0, 3}, mo6117d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, mo6118d2 = {"com/fireeye/flarebear/FlareBearActivity$dance$r$1", "Ljava/lang/Runnable;", "run", "", "app_release"}, mo6119k = 1, mo6120mv = {1, 1, 15})
/* compiled from: FlareBearActivity.kt */
public final class FlareBearActivity$dance$r$1 implements Runnable {
    final /* synthetic */ Drawable $drawable;
    final /* synthetic */ Drawable $drawable2;
    final /* synthetic */ Handler $handlerDancing;
    final /* synthetic */ FlareBearActivity this$0;

    FlareBearActivity$dance$r$1(FlareBearActivity flareBearActivity, Handler handler, Drawable drawable, Drawable drawable2) {
        this.this$0 = flareBearActivity;
        this.$handlerDancing = handler;
        this.$drawable2 = drawable;
        this.$drawable = drawable2;
    }

    public void run() {
        this.$handlerDancing.postDelayed(this, 500);
        ImageView imageView = (ImageView) this.this$0._$_findCachedViewById(C0272R.C0274id.flareBearImageView);
        String str = "flareBearImageView";
        Intrinsics.checkExpressionValueIsNotNull(imageView, str);
        String str2 = "ecstatic";
        String str3 = "ecstatic2";
        if (Intrinsics.areEqual(imageView.getTag(), (Object) str2)) {
            ((ImageView) this.this$0._$_findCachedViewById(C0272R.C0274id.flareBearImageView)).setImageDrawable(this.$drawable2);
            ((ImageView) this.this$0._$_findCachedViewById(C0272R.C0274id.flareBearImageView)).setTag(str3);
            return;
        }
        ImageView imageView2 = (ImageView) this.this$0._$_findCachedViewById(C0272R.C0274id.flareBearImageView);
        Intrinsics.checkExpressionValueIsNotNull(imageView2, str);
        if (Intrinsics.areEqual(imageView2.getTag(), (Object) str3)) {
            ((ImageView) this.this$0._$_findCachedViewById(C0272R.C0274id.flareBearImageView)).setImageDrawable(this.$drawable);
            ((ImageView) this.this$0._$_findCachedViewById(C0272R.C0274id.flareBearImageView)).setTag(str2);
            return;
        }
        this.$handlerDancing.removeCallbacksAndMessages(null);
    }
}
