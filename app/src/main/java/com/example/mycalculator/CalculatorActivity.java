package com.example.mycalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import	java.lang.Math;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {
    TextView resultTV;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        resultTV=findViewById(R.id.result_tx);

    }
    String result="";
    String operator="";
    public void onAC(View view){
        //Button ClickAC=(Button) view;
        resultTV.setText("");
        result="";
        operator="";
    }
    public void onDigitalClick(View view){
        //get text from button
        //append text to result text view
        //view -> button = polymorphism
        if(equal==true){
            resultTV.setText("");
            equal=false;
        }
        Button ClickButton=(Button) view;//casting
        resultTV.append(ClickButton.getText().toString());//جبنا textوحطنها ف result
    }
//الطريق دى تنفع لو انت جوا activity
    public void onOperateClick(View view) {//طريقه عمل lisenar لى اى ضغطه(text,button,liner اى حاجه)
        Button ClickOperator = (Button) view;//جبت button اللى ضغط عليها
        if (operator.isEmpty()) {//5+
            operator = ClickOperator.getText().toString();//بيعرفك انهى operator اتطغط عليه
            result = resultTV.getText().toString();
            resultTV.setText("");//فضى resultTV
        } else {//5+2-3=4
            String RHS=resultTV.getText().toString();// الرقم الجديد اللى اتكتب
            result=calculate(result,operator,RHS);
            operator = ClickOperator.getText().toString();
            resultTV.setText("");
        }
    }
    boolean equal=false;
    public void onEqualClick(View view) {
        equal=true;
        if (operator.equals("")) {
            resultTV.setText(result);
            result = "";
        } else {
            String RHS = resultTV.getText().toString();
            result = calculate(result, operator, RHS);
            resultTV.setText(result);
            operator = "";
            result = "";
        }
    }
    public void onDotClick(View view){
        if (resultTV.getText().toString().contains("."))
            return;
        resultTV.append(".");
    }
    private String calculate(String lhs, String operator, String rhs) {
        double res=0.0;
        try {
        if (lhs.isEmpty()){
            double n1=Double.parseDouble(rhs);
            if (operator.equals("√")){
                res=Math.sqrt(n1);
            }
            else if (operator.equals("sin")){
                res=Math.sin(n1);
            }
            else if (operator.equals("cos")){
                res=Math.cos(n1);
            }
        }
            else {
                double n1 = Double.parseDouble(lhs);
                double n2 = Double.parseDouble(rhs);
                if (operator.equals("+")) {
                    res = n1 + n2;
                } else if (operator.equals("-")) {
                    res = n1 - n2;
                } else if (operator.equals("*")) {
                    res = n1 * n2;
                } else if (operator.equals("/")) {
                    if(n2==0.0){
                        Toast.makeText(CalculatorActivity.this,"can not divide 0", Toast.LENGTH_LONG).show();
                        return n1+"";

                    }
                    res = n1 / n2;
                }
            }
        }
        catch (NumberFormatException exception){
            resultTV.setText("");
        }


        return res+"";
    }
}
