package javakit.network;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class JavaKitClientResponse{
    public static void post(String url,String json,JavaKitClientResponseCallback<JsonNode> callback){
                if(url==null&&json.equals("")){
                    callback.failure(new Exception("url not empty"));
                }
            if(json==null&&json.equals("")){
                callback.failure(new Exception("json not empty"));
            }
        FutureTask<JsonNode> task = new FutureTask<JsonNode>(new Callable<JsonNode>() {
            private final  ObjectMapper objectMapper = new ObjectMapper();
            @Override
            public JsonNode call()  {
                // TODO Auto-generated method stub
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                JsonNode result=null;
                Response response = null;

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
                        result=objectMapper.readTree(response.body().string());
                    } catch (IOException e) {
                        callback.failure(e);
                    }
                }else {
                    callback.failure(new Exception("network request error : " + response.code()));

                }
                return result;
            }});
        new Thread(task).start();

        try {
            callback.success(task.get().binaryValue());
            callback.success(task.get().toString());
            callback.success(task.get(),task.get().toString());
        } catch (InterruptedException e) {
            callback.failure(e);
            return;
        } catch (ExecutionException e) {
            callback.failure(e);
            return;
        } catch (IOException e) {
            callback.failure(e);
            return;
        }
    }

    public static void bytes(String url, JavaKitClientResponseCallback<byte[]> resultCallback) {


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
        }
    }

    public static void get(String url, JavaKitClientResponseCallback<JsonNode> resultCallback) {

        FutureTask<JsonNode> task = new FutureTask<JsonNode>(new Callable<JsonNode>() {
            private final  ObjectMapper objectMapper = new ObjectMapper();
            @Override
            public JsonNode call()  {
                // TODO Auto-generated method stub
                JsonNode result = null;
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
                        result=objectMapper.readTree(response.body().string());
                    } catch (IOException e) {
                        resultCallback.failure(e);
                    }
                }else {
                    resultCallback.failure(new Exception("network request error : " + response.code()));
                }
                return result;
            }});
        new Thread(task).start();

        try {
            resultCallback.success(task.get().binaryValue());
            resultCallback.success(task.get().toString());
            resultCallback.success(task.get(),task.get().toString());
        } catch (InterruptedException e) {
            resultCallback.failure(e);
            return;
        } catch (ExecutionException e) {
            resultCallback.failure(e);
            return;
        } catch (IOException e) {
            resultCallback.failure(e);
            return;
        }
    }
}
