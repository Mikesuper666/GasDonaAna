package com.example.maico.donaanaapp.Helper;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Data {
        public static String Data_Hora(String layout, Context context)
        {
            switch (layout) {
                case "0":
                    layout = "yyyy-MM-dd-HH-mm-ss";
                    break;
                case "1":
                    layout = "dd/MM/yyyy";
                    break;
                case "2":
                    layout = "HH:mm";
                    break;
                default:
                    Menssagens.showToast("161", context);
                    break;
            }

            SimpleDateFormat dateFormat =  new SimpleDateFormat(layout, Locale.ENGLISH);

            Date data = new Date();

            Calendar cal = Calendar.getInstance();
            cal.setTime(data);
            Date data_atual = cal.getTime();

            return dateFormat.format(data_atual);
        }
}
