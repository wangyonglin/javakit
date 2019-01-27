package javakit.network;

import okhttp3.ResponseBody;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class HttpClientResponse{


    public static void Request(String url,ResultCallback<ResponseBody> resultCallback) {


        FutureTask<ResponseBody> task = new FutureTask<ResponseBody>(new Callable<ResponseBody>() {

            @Override
            public ResponseBody call()  {
                // TODO Auto-generated method stub
                ResponseBody responseBody = null;
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
                    responseBody = response.body();
                    }else {
                    resultCallback.failure(new Exception("network request error : " + response.code()));
                        return null;
                    }
                return responseBody;
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

    public interface ResultCallback<T>{
        void success(T res) throws IOException;
        default void failure(Exception e){};
    }

}
