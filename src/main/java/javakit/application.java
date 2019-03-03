package javakit;


import javakit.date.DateUtils;
import javakit.security.RSAKeys;
import javakit.security.RSAUtils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class application {
    public static void main(String args[]) {
        System.out.println("Hello World!");
            Date d= DateUtils.replace("2018-03-01T05:35:13.514Z");
       int x= DateUtils.differentDays(d,new Date());
        System.out.println("Hello World! "+x);
    }

}
