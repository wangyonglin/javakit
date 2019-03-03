package javakit;


import javakit.security.RSAKeys;
import javakit.security.RSAUtils;


public class application {
    public static void main(String args[]) {
        System.out.println("Hello World!");
        try {
            String str ="wangyonglin and wangxiaoli";
            RSAKeys keys= RSAKeys.make();
            System.out.println("getPrivateKey:"+keys.getPrivateKey());
            System.out.println("########################");
            System.out.println("getPublicKey:"+keys.getPublicKey());

           String in = RSAUtils.encryptedDataOnJava(str,keys.getPublicKey());

            System.out.println("encryptedDataOnJava:"+in);
            String out = RSAUtils.decryptDataOnJava(in,keys.getPrivateKey());
            System.out.println("decryptDataOnJava:"+out);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
