package com.fireeye.flarebear;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.p003v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo6116bv = {1, 0, 3}, mo6117d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, mo6118d2 = {"Lcom/fireeye/flarebear/NewActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "savePetName", "petName", "", "showFlareBear", "view", "Landroid/view/View;", "app_release"}, mo6119k = 1, mo6120mv = {1, 1, 15})
/* compiled from: NewActivity.kt */
public final class NewActivity extends AppCompatActivity {
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
        setContentView((int) C0272R.layout.activity_new);
    }

    public final void showFlareBear(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        EditText editText = (EditText) _$_findCachedViewById(C0272R.C0274id.flareBearNameInput);
        Intrinsics.checkExpressionValueIsNotNull(editText, "flareBearNameInput");
        String obj = editText.getText().toString();
        if (obj.length() == 0) {
            ((EditText) _$_findCachedViewById(C0272R.C0274id.flareBearNameInput)).setError("Your FLARE Bear must have a name");
            return;
        }
        savePetName(obj);
        startActivity(new Intent(this, FlareBearActivity.class));
    }

    public final void savePetName(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "petName");
        Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
        edit.clear();
        edit.putString("name", str);
        edit.commit();
    }
}
