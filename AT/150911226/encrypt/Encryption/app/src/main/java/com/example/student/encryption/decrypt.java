package com.example.student.encryption;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class decrypt extends AppCompatActivity {
    private EditText res;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);
        res = (EditText) findViewById(R.id.etext);
        button = (Button)findViewById(R.id.Decrypt);
        final Bundle b;
        b = getIntent().getExtras();
        final String m = b.getString("enc");
        res.setText(m);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                String enc = b.getString("enc");

                byte[] bb = new byte[enc.length()];
                for (int i=0; i<enc.length(); i++) {
                    bb[i] = (byte) enc.charAt(i);
                }
                // decrypt the text
                    String key = "Bar12345Bar12345"; // 128 bit key
                    Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
                    Cipher cipher = Cipher.getInstance("AES");
                    // encrypt the text
                    cipher.init(Cipher.ENCRYPT_MODE, aesKey);
                cipher.init(Cipher.DECRYPT_MODE, aesKey);
                String decrypted = new String(cipher.doFinal(bb));
                Toast.makeText(getApplicationContext(), "decrypted:" + decrypted, Toast.LENGTH_SHORT).show();


                }catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
