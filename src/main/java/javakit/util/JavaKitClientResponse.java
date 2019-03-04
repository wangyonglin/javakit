package javakit.util;

import javakit.jackson.JacksonUtil;

import javakit.network.HttpClientResponse;
import javakit.result.JavaKitClientResponseCallback;
import okhttp3.*;
import okio.BufferedSink;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class JavaKitClientResponse{
    private static String json=null;
    public static void postRawJson(String url,Object obj, JavaKitClientResponseCallback<String> callback){
        try {
            json = JacksonUtil.obj2json(obj);
            System.out.println(json);
        } catch (Exception e) {
            callback.failure(e);
        }
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call()  {
                // TODO Auto-generated method stub
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                String result = null;
                Response response = null;
                if(json==null){
                    callback.failure(new NullPointerException());
                }
                RequestBody post= RequestBody.create(JSON,json);
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .post(post)
                        .build();

                try {
                    response = client.newCall(request).execute();
                } catch (IOException e) {
                    callback.failure(e);
                }

                if (response.isSuccessful()) {
                    try {
                        result = response.body().string();
                    } catch (IOException e) {
                        callback.failure(e);
                    }
                }else {
                    callback.failure(new Exception("network request error : " + response.code()));
                    return null;
                }
                return result;
            }});
        new Thread(task).start();

        try {
            callback.success(task.get());
        } catch (InterruptedException e) {
            callback.failure(e);
            return;
        } catch (ExecutionException e) {
            callback.failure(e);
            return;
        }
    }
}
