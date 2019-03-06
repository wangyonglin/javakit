package javakit.security;


import org.apache.commons.codec.binary.Base64;
import java.security.KeyPair;
import static javakit.security.RSAUtils.getKeyPair;

public class RSAKeys {
    public static String RsaPrivateKey;
    public static String RsaPublicKey;
    public static String getRsaPrivateKey() {
        return RsaPrivateKey;
    }
    public static void setRsaPrivateKey(String rsaPrivateKey) {
        RsaPrivateKey = rsaPrivateKey;
    }
    public static String getRsaPublicKey() {
        return RsaPublicKey;
    }

    public static void setRsaPublicKey(String rsaPublicKey) {
        RsaPublicKey = rsaPublicKey;
    }

    public static RSAKeys make()throws Exception{
        // 生成密钥对
        KeyPair keyPair = getKeyPair();
        setRsaPrivateKey(new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded())));
        setRsaPublicKey(new String(Base64.encodeBase64(keyPair.getPublic().getEncoded())));
        return new RSAKeys();
    }
}
