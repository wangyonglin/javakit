package javakit.network;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class WebClient {
    public interface ResultCallback<T>{
        void success(T res) throws IOException;
        default void failure(Exception e){};
    }
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    public static void postJson(String url,String json,ResultCallback<byte[]> resultCallback){

        FutureTask<byte[]> task = new FutureTask<byte[]>(new Callable<byte[]>() {

            @Override
            public byte[] call()  {
                // TODO Auto-generated method stub
                byte[] bytes = null;
                Response response = null;
                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create(JSON,json);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();

                try {
                    response = client.newCall(request).execute();
                } catch (IOException e) {
                    resultCallback.failure(e);
                }

                if (response.isSuccessful()) {
                    try {
                        bytes = response.body().bytes();
                    } catch (IOException e) {
                        resultCallback.failure(e);
                    }
                }else {
                    resultCallback.failure(new Exception("network request error : " + response.code()));
                    return null;
                }
                return bytes;
            }});
        new Thread(task).start();

        try {
            resultCallback.success(task.get());
        } catch (InterruptedException e) {
            resultCallback.failure(e);
            return;
        } catch (ExecutionException e) {
            resultCallback.failure(e);
            return;
        } catch (IOException e) {
            resultCallback.failure(e);
        }

    }

    public static void putJson(String url,String json,ResultCallback<byte[]> resultCallback){

        FutureTask<byte[]> task = new FutureTask<byte[]>(new Callable<byte[]>() {

            @Override
            public byte[] call()  {
                // TODO Auto-generated method stub
                byte[] bytes = null;
                Response response = null;
                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create(JSON,json);
                Request request = new Request.Builder()
                        .url(url)
                        .put(body)
                        .build();

                try {
                    response = client.newCall(request).execute();
                } catch (IOException e) {
                    resultCallback.failure(e);
                }

                if (response.isSuccessful()) {
                    try {
                        bytes = response.body().bytes();
                    } catch (IOException e) {
                        resultCallback.failure(e);
                    }
                }else {
                    resultCallback.failure(new Exception("network request error : " + response.code()));
                    return null;
                }
                return bytes;
            }});
        new Thread(task).start();

        try {
            resultCallback.success(task.get());
        } catch (InterruptedException e) {
            resultCallback.failure(e);
            return;
        } catch (ExecutionException e) {
            resultCallback.failure(e);
            return;
        } catch (IOException e) {
            resultCallback.failure(e);
        }

    }

    public static void get(String url,ResultCallback<byte[]> resultCallback) {


        FutureTask<byte[]> task = new FutureTask<byte[]>(new Callable<byte[]>() {

            @Override
            public byte[] call()  {
                // TODO Auto-generated method stub
                byte[] bytes = null;
                Response response = null;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                try {
                    response = client.newCall(request).execute();
                } catch (IOException e) {
                    resultCallback.failure(e);
                }

                if (response.isSuccessful()) {
                    try {
                        bytes = response.body().bytes();
                    } catch (IOException e) {
                        resultCallback.failure(e);
                    }
                }else {
                    resultCallback.failure(new Exception("network request error : " + response.code()));
                    return null;
                }
                return bytes;
            }});
        new Thread(task).start();

        try {
            resultCallback.success(task.get());
        } catch (InterruptedException e) {
            resultCallback.failure(e);
            return;
        } catch (ExecutionException e) {
            resultCallback.failure(e);
            return;
        } catch (IOException e) {
            resultCallback.failure(e);
        }
    }



}
