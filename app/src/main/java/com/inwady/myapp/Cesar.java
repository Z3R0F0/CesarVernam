package com.inwady.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Cesar extends AppCompatActivity {
    private Button button;


    // Декларация переменных
    private Button encrypt, decrypt; // Функции шифрования и расшифрования
    public static EditText message, cipher, key; // Поля инпута текста начиная с верхнего
    public static TextView screen_output;
    public static final String alphabetString = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"; // Алфавит
    static int Key; // Статическая переменная, которая используется для манипуляции значением ключа


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация переменных
        encrypt = findViewById(R.id.btnencrypt);
        decrypt = findViewById(R.id.btndecrypt);
        screen_output = findViewById(R.id.tV1);
        message = findViewById(R.id.inputMessage);
        cipher = findViewById(R.id.ciphEdt);
        key = findViewById(R.id.key_dt);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }

        });


        // Слушатель кнопки "Зашифровать"
        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Key = Integer.parseInt(key.getText().toString());
                } catch (NumberFormatException ex) {
                    Key = 1;
                }
                Key = Math.min(Key, 33); // Максимальное значение равно длине алфавита, т.к. увеличение смещения не имеет смысла
                goToEncrypt(message.getText().toString(), Key);
            }
        });

        // Слушатель кнопки "Расшифровать"
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Key = Integer.parseInt(key.getText().toString());
                } catch (NumberFormatException ex) {
                    Key = 1;
                }
                Key = Math.min(Key, 33); // Максимальное значение равно длине алфавита, т.к. увеличение смещения не имеет смысла
                goToDecrypt(cipher.getText().toString(), Key);
            }
        });
    }

    // Открывалка 2 скрина
    public void openActivity2() {
        Intent intent = new Intent(this, Vernam.class);
        startActivity(intent);
    }

    // Функция, которая сразу закидывает результат шифрования из CesarHelper в окошко "Результат"
    public void goToEncrypt(String message, int shiftkey) {
        screen_output.setText((CesarHelper.encrypt(message, shiftkey).toLowerCase())); // Отправляемся в utility.java :)
    }
    // Функция, которая сразу закидывает результат дешифрованмия из CesarHelper в окошко "Результат"
    public void goToDecrypt(String cipher, int key) {
        screen_output.setText((CesarHelper.decrypt(cipher, key).toLowerCase())); // Отправляемся в utility.java :)
    }


}
