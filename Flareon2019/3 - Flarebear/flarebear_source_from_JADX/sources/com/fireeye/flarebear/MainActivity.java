package com.fireeye.flarebear;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.p003v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo6116bv = {1, 0, 3}, mo6117d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0012\u0010\n\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\b\u0010\r\u001a\u00020\u0004H\u0014J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0010"}, mo6118d2 = {"Lcom/fireeye/flarebear/MainActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "continueGame", "", "view", "Landroid/view/View;", "hasExistingGame", "", "newGame", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "showCredits", "showHelp", "app_release"}, mo6119k = 1, mo6120mv = {1, 1, 15})
/* compiled from: MainActivity.kt */
public final class MainActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0272R.layout.activity_main);
        Button button = (Button) _$_findCachedViewById(C0272R.C0274id.buttonContinue);
        Intrinsics.checkExpressionValueIsNotNull(button, "buttonContinue");
        button.setEnabled(hasExistingGame());
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Button button = (Button) _$_findCachedViewById(C0272R.C0274id.buttonContinue);
        Intrinsics.checkExpressionValueIsNotNull(button, "buttonContinue");
        button.setEnabled(hasExistingGame());
    }

    private final boolean hasExistingGame() {
        return PreferenceManager.getDefaultSharedPreferences(this).contains("name");
    }

    public final void continueGame(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Context context = this;
        String str = "";
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString("name", str);
        Intent intent = new Intent(context, FlareBearActivity.class);
        intent.putExtra(str, string);
        startActivity(intent);
    }

    public final void newGame(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        startActivity(new Intent(this, NewActivity.class));
    }

    public final void showHelp(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Toast.makeText(this, "The virtual pet you never thought you'd need. Feed, play, and clean your bear to get it to prime condition.", 1).show();
    }

    public final void showCredits(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        startActivity(new Intent(this, CreditsActivity.class));
    }
}
