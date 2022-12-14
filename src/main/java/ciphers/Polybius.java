package ciphers;

import java.util.Scanner;

public class Polybius implements Cipher {

    private char[][] table = new char[5][5];

    private void createTable()
    {
        for(int i = 0; i < 2; i++ )
            for(int j = 0; j< 5; j++) {
                if(i*5+j+65!=74)
                    table[i][j]= (char)(i*5+j+65);
            }
        table[1][4]= 'K';
        for(int i = 2; i < 5 ; i++)
            for(int j = 0; j < 5 ; j++)
                table[i][j]=(char)(i*5+j+65+1);
    }

    @Override
    public String encrypt(String text, int key) {
        String encpt = "";
        createTable();
        text = text.toUpperCase();
        char[] encpt1 = text.toCharArray();
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < 5; j++)
                for (int k = 0; k < 5; k++) {
                    if (encpt1[i] == 'J') {
                        encpt = encpt + "24";
                        break;
                    }
                    if (table[j][k] == encpt1[i]) {
                        encpt = encpt + Integer.toString(j + 1) + Integer.toString(k + 1);
                        break;
                    }
                }
        }
        return encpt;
    }

    @Override
    public String decrypt(String text, int key) {
     String decpt = "";
    createTable();
        char[] decpt1 = text.toCharArray();
        for(int i = 0; i < text.length(); i = i + 2) {
            int row = Integer.parseInt(String.valueOf(decpt1[i]));
            int col = Integer.parseInt(String.valueOf(decpt1[i+1]));
            decpt = decpt + Character.toString(table[row-1][col-1]);
        }
        return decpt;
    }

    @Override
    public String getName() {
        return "Polybius";
    }

    @Override
    public boolean requiredKey() {
        return false;
    }
}
