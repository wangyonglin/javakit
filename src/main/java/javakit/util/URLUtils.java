package javakit.util;

import java.util.*;

public class URLUtils {

    private Map<String, String> mMapStrcat=new HashMap<String, String>();
    private static String DomainString;
    public static URLUtils make(String domain){
        DomainString=domain;
        return new URLUtils();
    }


    public void put(String key,String val) {
        mMapStrcat.put(key,val);
    }

    public  String getDomainString() {
        return DomainString;
    }

    private String build(){
        StringBuffer buffer = new StringBuffer();
        Collection<String> keyset= mMapStrcat.keySet();
        List list=new ArrayList<String>(keyset);
        Collections.sort(list);

        for(int i=0;i<list.size();i++) {
            buffer.append("&"+list.get(i)+"="+mMapStrcat.get(list.get(i)));
        }
        return "?"+buffer.toString();
    }
    public String url(){
        return DomainString+build();
    }
}
