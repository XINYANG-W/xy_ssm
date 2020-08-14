package com.xy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class abc {
    public static void main(String[] args) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String format = formatter.format(date);
        String substring = format.substring(0, 7);
        System.out.println(substring);
    }
}
