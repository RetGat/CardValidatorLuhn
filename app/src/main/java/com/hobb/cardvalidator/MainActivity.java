package com.hobb.cardvalidator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String input;
    private EditText cardNumber;
    private TextView info;
    private Button check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardNumber = findViewById(R.id.cardNumber);
        info = findViewById(R.id.infoText);
        check = findViewById(R.id.checkBtn);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cardNumber.getText().toString().equals("")) {
                    input = cardNumber.getText().toString();
                    Toast.makeText(MainActivity.this, "Tiene que agregar un numero de tarjeta", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), "Tienes que agregar un numero", Toast.LENGTH_SHORT).show();
                } else if (cardNumber.getText().toString().trim().length() < 16){
                    Toast.makeText(MainActivity.this, "Debe contener al menos 16 dígitos", Toast.LENGTH_SHORT).show();
                    
                } else {
                    info.setTextColor(Color.parseColor("#0356fc"));
                    info.setText("Verificando el numero...");

                    if (checkLuhn(cardNumber.getText().toString())) {

                        info.setTextColor(Color.parseColor("#52fc03"));
                        info.setText("Número de tarjeta Valido!");

                    } else {

                        info.setTextColor(Color.parseColor("#fc0703"));
                        info.setText("Número no valido");

                    }
                }

            }
        });


    }

    private boolean checkLuhn(String cardNo) {

        int Digits = cardNo.length();

        int Sum = 0;
        boolean isSecond = false;

        for (int i = Digits - 1; i >= 0; i--) {

            int d = cardNo.charAt(i) - '0';

            if (isSecond == true)
                d = d * 2;
            Sum += d / 10;
            Sum += d % 10;

            isSecond = !isSecond;
        }
        return (Sum % 10 == 0);

    }

    private void checkCard() {

        info.setTextColor(Color.parseColor("#0356fc"));
        info.setText("Verificando el numero...");

        int[] creditcardInt = new int[input.length()];

        for (int i = 0; i < input.length(); i++){

            creditcardInt[i] = Integer.parseInt(input.substring(i, i + 1));

        }

        for (int i = creditcardInt.length - 2; i >= 0; i = i - 2){

            int tempValue = creditcardInt[i];
            tempValue = tempValue * 2;
            if (tempValue > 9) {

                tempValue = tempValue % 10 + 1;

            }
            creditcardInt[i] = tempValue;

        }

        int total = 0;
        for (int i = 0; i < creditcardInt.length; i++) {

            total += creditcardInt[i];

        }

        if (total % 10 == 0) {
            info.setTextColor(Color.parseColor("#52fc03"));
            info.setText("Numero de tarjeta Valido!");

        } else {
            info.setTextColor(Color.parseColor("#fc0703"));
            info.setText("Numero no valido");
        }
    }


}