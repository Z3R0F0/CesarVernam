package com.inwady.myapp;

import androidx.appcompat.app.AppCompatActivity;

import java.security.SecureRandom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;


public class Vernam extends AppCompatActivity {

    // Алфавит
    private final static char[] encryptionArr = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'};

    private Button button;

    private Button encrypt, decrypt, generateBtn; // Кнопки
    public static EditText message, cipher, keyField; // Поля инпута текста начиная с верхнего
    public static TextView screen_output; // Панелька с текстом
    public static String keyData; // Переменная для значения ключа
    public static char[] res; // Переменная для хранения результата шифрования


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Инициализация переменных
        keyData = "";
        encrypt = findViewById(R.id.btnencrypt);
        decrypt = findViewById(R.id.btndecrypt);
        screen_output = findViewById(R.id.tV1);
        message = findViewById(R.id.inputMessage);
        cipher = findViewById(R.id.ciphEdt);
        keyField = findViewById(R.id.key_dt);
        generateBtn = findViewById(R.id.button3);
        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }

        });

        generateBtn.setOnClickListener(new View.OnClickListener() { // Кнопка генерации ключа
            @Override
            public void onClick(View view) {
                generateKey(message.getText().toString());
            }
        });


        // Слушатель кнопки "Зашифровать"
        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyData = keyField.getText().toString();
                if (keyData == "" || keyData.length() != message.getText().toString().length()) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Инвалидный ключ :О", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    endecrypt(message.getText().toString(), true);
                }

            }
        });

        // Слушатель кнопки "Расшифровать"
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyData = keyField.getText().toString();
                if (keyData == "" || keyData.length() != message.getText().toString().length()) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Инвалидный ключ :О", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    endecrypt(message.getText().toString(), false);
                }

            }
        });

    }


    public void openMainActivity() { // Функция смены экрана
        Intent intent = new Intent(this, Cesar.class);
        startActivity(intent);
    }


    public void generateKey(String text) { // Генерация ключа
        int length = text.length();
        String key = "";
        SecureRandom secureRandom = new SecureRandom();
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < length; i++) {
            int randomValue = secureRandom.nextInt(33);
            key += encryptionArr[randomValue];
        }
        keyData = key;
        keyField.setText(key.toLowerCase());
    }

    // Функция шифрования и дешифрования
    public void endecrypt(String plainText, Boolean toDo) {
        char[] achText = plainText.toCharArray();
        char[] achKey = keyData.toCharArray();
        char[] achResult = new char[achText.length];
        if (toDo) {
            for (int i = 0; i < achText.length; i++) {
                achResult[i] = (char) (achText[i] ^ achKey[i]);
            }
            res = achResult;
            screen_output.setText(String.valueOf(achResult));
            cipher.setText(String.valueOf(achResult));
        } else {
            char[] achDecrypt = new char[achText.length];
            for (int i = 0; i < achText.length; i++) {
                achDecrypt[i] = (char) (res[i] ^ achKey[i]);
            }
            screen_output.setText(String.valueOf(achDecrypt));
        }


    }


}
