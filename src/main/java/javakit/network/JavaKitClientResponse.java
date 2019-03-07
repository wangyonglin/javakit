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
            callback.success(task.get(),task.get().toString());
        } catch (InterruptedException e) {
            callback.failure(e);
            return;
        } catch (ExecutionException e) {
            callback.failure(e);
            return;
        }
    }
}
