package javakit.security;

import lombok.Data;

import java.util.Map;
@Data
public class RSAKeys {
    private String publicKey;
    private String privateKey;

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
    public static RSAKeys make()throws Exception{
        RSAKeys keys= new RSAKeys();
        Map<String, Object> keyMap = RSAUtils.genKeyPair();
        keys.setPublicKey(RSAUtils.getPublicKey(keyMap));
        keys.setPrivateKey(RSAUtils.getPrivateKey(keyMap));
        return keys;
    }
}
