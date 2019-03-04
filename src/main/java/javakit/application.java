package javakit;


import javakit.date.DateUtils;
import javakit.security.RSAKeys;
import javakit.security.RSAUtils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class application {
    public static void main(String args[])throws Exception{
       // RSAKeys rsaKeys=new RSAKeys();
      //  rsaKeys.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC87pN6rYFbS8bldjpvMnm31aF8RsPhcm8PsSBE\r\nr01LCLcrQNOg5gHrlGyonhEwLMRFQVcN8hmdbVlseyRKgJkcxY9DeHRy2AsMFBXTpLamnRYDdR8N\r\nY52wsTAGayc2jdaeJMezO5Q+NFv0LEFHx7wfthL6KJJ4sHMdZ84+OwywuQIDAQAB"),
       // rsaKeys.setPrivateKey("MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALzuk3qtgVtLxuV2Om8yebfVoXxG\r\nw+Fybw+xIESvTUsItytA06DmAeuUbKieETAsxEVBVw3yGZ1tWWx7JEqAmRzFj0N4dHLYCwwUFdOk\r\ntqadFgN1Hw1jnbCxMAZrJzaN1p4kx7M7lD40W/QsQUfHvB+2Evookniwcx1nzj47DLC5AgMBAAEC\r\ngYADplAvBH8FNneHIzuJvl3fkT7nWa6bAkzm7rMC4KgLlQWEzmHIL+lFlb5rm3IVjifUdqip2k1o\r\nXx5lHBPdZJKQlSE2FNEChivktG3bNZzH7tJo5pBwH9LX9CRnQi472LSIL3aYVrkvgEU3CkfvG6qB\r\nZ7oIjCRZ7rEeeLe77sxrAQJBAP8fF+v8gRZ9lSn6E/T2OTXDs4MRRme4/8Uxj8E5rZZ58GffdSGt\r\nMIvOshOcQVY0XRKhC7rmQonwTq9SWaRjMjECQQC9lSHWQeKNs7UhZqPjhRGuBr3AcYfXTKxGG7QE\r\nnxJHxAfs4DGzrfv5Sqrxn3ydsMt9I2r0kYEpiKN6+FLjbn0JAkAzwCzsE8Zjt4nAgBf4QtIME+yg\r\nE6sCc0Z+y7TrbzXFJmsbxvpNoIIOehkpXTLrt6eBleV5PigVLwfnDH9elzihAkBhg8op2S3dUWiD\r\ny7XVG+diKlmEGWGUHv4l+a35CpFulKi28QLft0h6jCutqSmCOyQQNB2ZuxZC9OOSJxULoxNxAkAa\r\nGziuKtsVF9fmmyVx6YG4uTJ5ICGQWgJ3NRKD+aQq7gZ3BcRl5zK4ngpeSf6e6oDPlMPULZuS1dCF\r\n72XnunHC");
        RSAKeys rsaKeys= RSAKeys.make();
        System.out.println("getPrivateKey \n"+rsaKeys.getPrivateKey());
        System.out.println("getPublicKey \n"+rsaKeys.getPublicKey());
        String data=    RSAUtils.encryptedDataOnJava("dfghjkl",rsaKeys.getPublicKey());
        System.out.println("encryptedDataOnJava\n"+data);
        System.out.println("decryptDataOnJava \n "+ RSAUtils.decryptDataOnJava(data,rsaKeys.getPrivateKey()));
    }

}
