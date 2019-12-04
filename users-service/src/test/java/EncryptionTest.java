
import org.junit.Test;
import utils.Aes;

import javax.crypto.Cipher;


public class EncryptionTest {

    @Test
    public void encryptionTest (){
        Aes aes256 = new Aes();
        String mes = "user";
            byte[] shifr = aes256.makeAes(mes.getBytes(), Cipher.ENCRYPT_MODE);
            System.out.println(new String(shifr));
            byte[] src = aes256.makeAes(shifr, Cipher.DECRYPT_MODE);
            System.out.println(new String(src));

    }

}
