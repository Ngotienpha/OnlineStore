package com.example.onlinestoreapp.utils;

import com.example.onlinestoreapp.model.GioHang;
import com.example.onlinestoreapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static final String BASE_URL="http://192.168.1.109/onlinestore/";
    public static List<GioHang> manggiohang;
    public static List<GioHang> mangmuahang = new ArrayList<>();
    public static User user_current = new User();

    public static String ID_RECEIVED;
    public static final String SENDID = "idsend";
    public static final String RECEIVEDID = "idreceived";
    public static final String MESS = "message";
    public static final String DATETIME = "datetime";
    public static final String PATH_CHAT = "chat";
    public static String statusOrder(int status){
        String result = "";
        switch (status){
            case 0:
                result ="Order is being processed";
                break;
            case 1:
                result ="Order accepted";
                break;
            case 2:
                result ="The order has been handed over to the shipping unit.";
                break;
            case 3:
                result ="Order has been delivered successfully";
                break;
            case 4:
                result ="Order has been cancelled";
                break;
            default:
                result = "...";
        }
        return result;
    }
}
