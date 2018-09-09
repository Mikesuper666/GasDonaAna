package com.gasdonaana.gasdonaana.Helper;

import android.content.Context;
import android.widget.Toast;

public class Menssagens {
    public static void showToast(String msg, Context context) {
        Toast.makeText(context,"ERRO: " + msg,Toast.LENGTH_LONG).show();
    }
    public static void showToastSucesso(String msg, Context context) {
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
