package javakit;


import javakit.network.HttpClientResponse;
import okhttp3.ResponseBody;
import java.io.IOException;


public class application {
    public static void main(String args[]) {
        System.out.println("Hello World!");
        HttpClientResponse.Request("http://api.eeob.com/cinemas/movie/title?title=我不是药神&page=0&size=5", new HttpClientResponse.ResultCallback<ResponseBody>() {
            @Override
            public void success(ResponseBody res) throws IOException  {

                    System.out.println("success:" +res.string());

            }

            @Override
            public void failure(Exception e) {
                System.out.println("failure:" +e.getMessage());
            }
        });


    }

}
