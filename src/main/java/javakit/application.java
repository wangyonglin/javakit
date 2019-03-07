package javakit;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import javakit.jackson.JacksonUtil;
import javakit.network.JavaKitClientResponse;
import javakit.network.JavaKitClientResponseCallback;
import javakit.security.RSAKeys;
import javakit.security.RSAUtils;

public class application {
    public static void main(String args[])throws Exception{
       RSAKeys rsaKeys= RSAKeys.make();
        System.out.println(rsaKeys.getRsaPublicKey());
        System.out.println(rsaKeys.getRsaPrivateKey());
        // RSA加密
        String data = "待加密的文字内容";
        String encryptData = RSAUtils.encrypt(data, RSAUtils.getPublicKey(rsaKeys.getRsaPublicKey()));
        System.out.println("加密后内容:" + encryptData);
        // RSA解密
        String decryptData = RSAUtils.decrypt(encryptData, RSAUtils.getPrivateKey(rsaKeys.getRsaPrivateKey()));
        System.out.println("解密后内容:" + decryptData);

        // RSA签名
        String sign = RSAUtils.sign(data, RSAUtils.getPrivateKey(rsaKeys.getRsaPrivateKey()));
        // RSA验签
        boolean result = RSAUtils.verify(data, RSAUtils.getPublicKey(rsaKeys.getRsaPublicKey()), sign);
        System.out.print("验签结果:" + result);

        User user = new User();
        user.setUser("wangyonglin");
        user.setData("ddd");

        JavaKitClientResponse.post("http://apis.eeob.com/user/auth",JacksonUtil.obj2json(user), new JavaKitClientResponseCallback<JsonNode>() {
            @Override
            public void success(JsonNode json, String res) {
                System.out.println(json);
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
