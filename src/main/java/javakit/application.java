package javakit;


import javakit.network.JavaKitClientResponse;
import javakit.network.JavaKitClientResponseCallback;
import javakit.util.JavaKitJsonUtils;

import java.util.Iterator;
import java.util.List;

public class application {
    public static void main(String args[])throws Exception{

        String uri= "http://apis.eeob.com/webcams/all";
        JavaKitClientResponse.get(uri, new JavaKitClientResponseCallback<String>() {
            @Override
            public void success(String res) {
                System.out.print(res);
                try {
                    List<Webcams> webcams = JavaKitJsonUtils.json2list(res,Webcams.class);
                    for(Iterator iterators = webcams.iterator();iterators.hasNext();){
                        Webcams example = (Webcams) iterators.next();//获取当前遍历的元素，指定为Example对象
                               String name = example.getTitle();
                                System.out.print("Name:"+name);
                            }

                } catch (Exception e) {
                   e.printStackTrace();
                }
            }
        });
    }

}
