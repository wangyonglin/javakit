package javakit;


import com.fasterxml.jackson.annotation.JsonProperty;
import javakit.result.JavaKitClientResponseCallback;
import javakit.util.JavaKitClientResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class application {
    public static void main(String args[])throws Exception{
        String uri="http://localhost:8888/user/auth";
        String data="Mq+6QUMfEuOfWEKKdzbBWqQnAcHbN4RdkZeJh5detKO6liszkiotDpJpupb6UxlE+6ql518MVo1g\n" +
                "VXGqxjp+BRRLLKY2m7fpCm1seeBBfVbN8ISXfNPPWrpMpsypnYIJZ0pdkMku/alvwfXtXAQtA7X8\n" +
                "3f5Mpy+3IPBd1HoDm14=";
        User user= new User();
        user.setUser("wangyong");
        user.setData(data);
        JavaKitClientResponse.postRawJson(uri,user,new JavaKitClientResponseCallback<String>() {
            @Override
            public void success(String res) {
                System.out.println(res);
            }

            @Override
            public void failure(Exception e) {
                e.printStackTrace();
            }

        });
    }
    static class User {
        @JsonProperty
        private String user;
        @JsonProperty
        private String data;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

}
