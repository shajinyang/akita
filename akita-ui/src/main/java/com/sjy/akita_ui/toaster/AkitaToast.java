package com.sjy.akita_ui.toaster;

import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.sjy.akita_ui.application.AkitaUiContext;

/**
 * 弹框
 * Created by sjy on 2018/6/5.
 */

public class AkitaToast {

    private static Toast toast=null;
    private static Toast customViewtoast=null;

    /**
     * toast弹框
     * @param msg
     */
    public static void showToast(String msg){
        if(toast==null){
            toast= Toast.makeText(AkitaUiContext.application.getApplicationContext(),msg, Toast.LENGTH_SHORT);
        }else {
            toast.setText(msg);
        }
        toast.show();
    }

    /**
     * 复制内容到剪切板，并提示
     * @param msg 复制的内容
     */
    public static void showToastWithCopy(String msg){
        ClipboardManager cm = (ClipboardManager) AkitaUiContext.application.getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(msg);
        Toast.makeText(AkitaUiContext.application.getApplicationContext(),"复制成功", Toast.LENGTH_SHORT).show();
    }


    /**
     * 显示自定义viewToast
     * @param view
     */
    public static void showCustomToast(View view){
        if(customViewtoast==null){
            customViewtoast= Toast.makeText(AkitaUiContext.application.getApplicationContext(),"", Toast.LENGTH_SHORT);
        }
        customViewtoast.setView(view);
        customViewtoast.show();
    }
}
