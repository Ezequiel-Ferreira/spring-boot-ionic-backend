package com.example.cursomc.util;

import org.springframework.stereotype.Component;


import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataUtil {
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private static Date horaTempo;

    public DataUtil() {
    }

    public DataUtil(String horaTempo) {
        try {
            DataUtil.horaTempo = sdf.parse(horaTempo);
        } catch (Exception e) {

        }
    }

    public static Date convertToDate(String horaData) {
        try {
            horaTempo = sdf.parse(horaData);
            return horaTempo;
        } catch (Exception e) {
           e.printStackTrace();
           return null;
        }
    }
}
