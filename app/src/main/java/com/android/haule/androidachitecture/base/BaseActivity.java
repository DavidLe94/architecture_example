package com.android.haule.androidachitecture.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.android.haule.androidachitecture.R;
import java.util.Objects;
import butterknife.ButterKnife;

/**
 * Created by Hau Le on 2018-10-11.
 */
public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResoureId());
        bindViews();
    }

    /**
     * @return The layout id that's gonna be the activity view.
     */
    protected abstract int getLayoutResoureId();

    /**
     * Every object annotated with {@link butterknife.Bind} its gonna injected trough butterknife
     */
    private void bindViews() {
        ButterKnife.bind(this);
    }

    public static void hiddenKeySoft(final Activity activity, View view) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new EditText.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                hiddenKeySoft(activity, innerView);
            }
        }
    }

    /**
     * hidden soft keybroad when un-focus edit text
     */
    private static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        assert inputMethodManager != null;
        inputMethodManager.hideSoftInputFromWindow(
                Objects.requireNonNull(activity.getCurrentFocus()).getWindowToken(), 0);
    }

    public static Dialog showLoadingDialog(Context context, String str) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        TextView tvMess = dialog.findViewById(R.id.tv_message);
        tvMess.setText(str);
        return dialog;
    }
}
