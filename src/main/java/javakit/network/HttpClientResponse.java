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


    public static void Request(String url,ResultCallback<ResponseBody> callback) {


        FutureTask<ResponseBody> task = new FutureTask<ResponseBody>(new Callable<ResponseBody>() {

            @Override
            public ResponseBody call() throws Exception,IOException {
                // TODO Auto-generated method stub
                Response response = null;
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    response = client.newCall(request).execute();

                    if (response.isSuccessful()) {
                        return response.body();
                    }else {
                        callback.failure(new RuntimeException(response.message()));
                        return null;
                    }
            }});
        new Thread(task).start();

        try {
            callback.success(task.get());
        } catch (InterruptedException e) {
            callback.failure(new RuntimeException(e));
            return;
        } catch (ExecutionException e) {
            callback.failure(new RuntimeException(e));
            return;
        } catch (IOException e) {
            callback.failure(new RuntimeException(e));
            return;
        }
    }

    public interface ResultCallback<T>{
        public void success(T res)throws IOException;
        public void failure(RuntimeException e);
    }

}
