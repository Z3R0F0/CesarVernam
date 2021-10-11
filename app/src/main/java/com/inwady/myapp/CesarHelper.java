package com.inwady.myapp;

public class CesarHelper {

    private static final String alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    private static int index;
    private static int updated_index;
    private static int final_index;
    private static String finalTxt;


    // Функция расшифровки
    public static String decrypt(String ciphertext, int decryptionKey) {
        if (decryptionKey == 0) {
            decryptionKey++;
        }
        reset();
        ciphertext = ciphertext.toUpperCase();
        for (index = 0; index < ciphertext.length(); index++) {
            if (ciphertext.charAt(index) != ' ') {
                updated_index = alphabet.indexOf(ciphertext.charAt(index)) - decryptionKey;
                if (updated_index < 0) {
                    final_index = updated_index + alphabet.length();
                } else
                    final_index = updated_index;
                String plainTxt = alphabet.substring(final_index, final_index + 1);
                finalTxt += plainTxt;
            }
        }
        return finalTxt;
    }

    // Функция шифровки :О
    public static String encrypt(String message, int shiftkey) {
        message = message.toLowerCase();
        String cipherText = "";
        for (int i = 0; i < message.length(); i++) {
            int charPosition = Cesar.alphabetString.indexOf(message.charAt(i));
            int keyval = (shiftkey + charPosition) % 33;
            char replaceVAL = Cesar.alphabetString.charAt(keyval);
            cipherText += replaceVAL;
            Cesar.screen_output.setText(cipherText);
            Cesar.cipher.setText(cipherText);
        }

        return cipherText;

    }


    private static void reset() {
        finalTxt = "";
    }
}

