package com.android.haule.androidachitecture.mvvm.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;
import com.android.haule.androidachitecture.R;

/**
 * Created by Hau Le on 2018-10-15.
 */
public class Loading {
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
