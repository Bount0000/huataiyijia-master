package com.huatai.czx.huataiyijia_master.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.text.Spannable;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.huatai.czx.huataiyijia_master.R;


/**
 * Created by Administrator on 2017/9/19.
 * 对话框封装类
 */

public class DialogUtils {
    private static MaterialDialog mMaterialDialog;
    private static PopupWindow popupWindow;
    private static MaterialDialog materialDialog;
    private static AlertDialog alertDialog;
    private static EditText edt_input;

    /**
     * dialog提示
     *
     * @param context
     */
    public static void showMdDialog(Activity context, String title, String content, String leftInfo, String rightInfo,
                                    final OnClickConfirmListener listener) {
        mMaterialDialog = new MaterialDialog(context);
        mMaterialDialog.setTitle(title == null ? UIUtils.getString(R.string.tips) : title)
                .setMessage(content)
                .setPositiveButton(rightInfo == null ? context.getResources().getString(R.string.confirm)
                        : rightInfo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onConfirm();
                        }
                        mMaterialDialog.dismiss();
                    }
                });
        if (leftInfo == null) {
            mMaterialDialog.setNegativeButton(leftInfo == null ? context.getResources().getString(R.string.cancel)
                    : leftInfo, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMaterialDialog.dismiss();
                    if (listener != null) {
                        listener.onCancel();
                    }
                }
            });
        } else if (! leftInfo.equals("")) {
            mMaterialDialog.setNegativeButton(leftInfo == null ? context.getResources().getString(R.string.cancel)
                            : leftInfo, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                            if (listener != null) {
                                listener.onCancel();
                            }
                        }
                    });
        }
        mMaterialDialog.setCanceledOnTouchOutside(true);
        mMaterialDialog.show();
    }

    /**
     * dialog提示
     *
     * @param context
     */
    public static void showMdRedDialog(Activity context, String title, String content, Spannable spannable, String leftInfo, String rightInfo,
                                       final OnClickConfirmListener listener) {
        mMaterialDialog = new MaterialDialog(context);
        mMaterialDialog.setTitle(title == null ? UIUtils.getString(R.string.tips) : title)
                .setMessage(content + "\n" + spannable)
                .setPositiveButton(rightInfo == null ? context.getResources().getString(R.string.confirm)
                        : rightInfo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onConfirm();
                        }
                        mMaterialDialog.dismiss();
                    }
                })
                .setNegativeButton(leftInfo == null ? context.getResources().getString(R.string.cancel)
                        : leftInfo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                        if (listener != null) {
                            listener.onCancel();
                        }
                    }
                });
        mMaterialDialog.setCanceledOnTouchOutside(true);
        mMaterialDialog.show();
    }

    /**
     * 定义点击确定按钮的接口
     */
    public interface OnClickConfirmListener {
        void onConfirm();//点击确定逻辑处理

        void onCancel();//点击取消逻辑处理
    }

}
