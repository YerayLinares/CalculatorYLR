package com.example.calculatorylr;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    //buttons and textbox definitions
    TextView etnumbers;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button btnplus, btnminus, btnmultiply, btndivide, btnequals, delete;

    //vars i am going to use
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String operator = "";
    private boolean isOperatorSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        etnumbers = findViewById(R.id.numbersbox);
        btn0 = findViewById(R.id.button0);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        btnplus = findViewById(R.id.plus);
        btnminus = findViewById(R.id.minus);
        btnmultiply = findViewById(R.id.multiply);
        btndivide = findViewById(R.id.btndivide);
        btnequals = findViewById(R.id.equals);
        delete = findViewById(R.id.delete);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadComponent();
    }

    //method to load the operations buttons and numbers buttons functionality
    protected void loadComponent() {
        btnplus.setOnClickListener(v -> setOperator("+"));
        btnminus.setOnClickListener(v -> setOperator("-"));
        btnmultiply.setOnClickListener(v -> setOperator("*"));
        btndivide.setOnClickListener(v -> setOperator("/"));

        delete.setOnClickListener(v -> {
            setOperator("");
            etnumbers.setText("");
            firstNumber = 0;
            secondNumber = 0;
        });

        btnequals.setOnClickListener(view -> calculateResult());

        loadNumbers();
    }

    protected void loadNumbers() {
        btn0.setOnClickListener(view -> addNumber("0"));
        btn1.setOnClickListener(view -> addNumber("1"));
        btn2.setOnClickListener(view -> addNumber("2"));
        btn3.setOnClickListener(view -> addNumber("3"));
        btn4.setOnClickListener(view -> addNumber("4"));
        btn5.setOnClickListener(view -> addNumber("5"));
        btn6.setOnClickListener(view -> addNumber("6"));
        btn7.setOnClickListener(view -> addNumber("7"));
        btn8.setOnClickListener(view -> addNumber("8"));
        btn9.setOnClickListener(view -> addNumber("9"));
    }

    //method to add operators to the TextView
    protected void setOperator(String operator) {
        firstNumber = Double.parseDouble(etnumbers.getText().toString());
        this.operator = operator;
        isOperatorSelected = true;
    }

    //method to add numbers to the TextView
    private void addNumber(String number) {
        if (isOperatorSelected) {
            etnumbers.setText("");
            isOperatorSelected = false;
        }
        etnumbers.append(number);
    }

    //method to do the calculations and set the result on the screen
    private void calculateResult() {
        secondNumber = Double.parseDouble(etnumbers.getText().toString());

        double result = 0;
        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                } else {
                    etnumbers.setText("Error");
                    return;
                }
                break;
        }

        etnumbers.setText(String.valueOf(result));
    }
}
