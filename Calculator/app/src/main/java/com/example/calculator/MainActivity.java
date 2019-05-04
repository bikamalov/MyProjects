package com.example.calculator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {
    double lastNum=0.0,result=0.0;
    String lastOperation = "=";
    String trigonometricOp = "";
    TextView display=null,smallDisplay=null;
    boolean justNumPressed = false;
    Button buttondot,buttonc,buttonsqrt,buttonAidana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (TextView)findViewById(R.id.display);
        smallDisplay = (TextView)findViewById(R.id.smallDisplay);
        buttondot = (Button)findViewById(R.id.buttondot);
        buttonc = (Button)findViewById(R.id.buttonc);
        buttonsqrt = (Button)findViewById(R.id.buttonsqrt);
        /*buttonsqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (display.getText().equals("0")) {
                    display.setText(display.getText());
                } else {
                    display.setText(valueOf(Math.pow(Double.parseDouble(valueOf(display.getText())), 0.5)));
                }
            }
        });*/

        buttondot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (display.getText().equals("NaN")){
                    display.setText("");
                }
                if (display.getText().equals("Infinity")){
                    display.setText("");                }
                if (display.getText().equals("-Infinity")){
                    display.setText("");
                }
                if (display.getText().equals("")) {
                    display.setText("0.");
                }
                if (valueOf(display.getText()).contains(".")) {
                    display.setText(display.getText());
                } else {
                    display.setText(display.getText() + ".");
                }
            }
        });
        buttonc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (display.getText().equals("0")){
                    display.setText(display.getText());
                }else{
                    display.setText(String.valueOf(display.getText()).substring(0,display.getText().length()-1));
                    if (display.getText().equals("")){
                        display.setText("0");
                    }
                }
            }
        });

        buttonc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                display.setText("0");
                return true;
            }
        });

    }

    public void numberPressed(View view) {
        Button target = (Button) view;
        String current_text = display.getText().toString();
        if (current_text.equals("0")) {
            current_text = "";
        }
        if (current_text.equals("NaN")){
            current_text = "";
        }
        if (current_text.equals("Infinity")){
            current_text = "";
        }
        if (current_text.equals("-Infinity")){
            current_text = "";
        }
        String text = current_text + target.getText().toString();
        justNumPressed = true;
        display.setText(text);
    }
    public void operationPressed(View view){
        try {
            Button target = (Button) view;
            String operation = target.getText().toString();
            lastNum = Double.parseDouble(display.getText().toString());
            display.setText("0");
            result = calculate(result,lastNum,lastOperation);
            if (result == (int) result) {
                smallDisplay.setText(String.valueOf((int) result)+operation);
            } else {
                smallDisplay.setText(String.valueOf(result)+operation);
            }
            if (operation.equals("=")){
                if (result == (int) result) {
                    display.setText(String.valueOf((int) result));
                    lastNum=0;
                    result=0;
                    smallDisplay.setText("0");
                } else {
                    display.setText(String.valueOf(result));
                    lastNum=0;
                    result=0;
                    smallDisplay.setText("0");
                }
            }
            lastOperation = operation;
            justNumPressed = false;
        }catch (Exception ex){
            Toast toast = Toast.makeText(getApplicationContext(), "Неправильное значение", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
            toast.show();
        }

    }


    public double calculate(double result,double lastNum,String operation){
        if (operation.equals("+")){
                return result+lastNum;
        }else if (operation.equals("-")){
            return result-lastNum;
        }else if (operation.equals("×")){
            if (justNumPressed) {
                return result*lastNum;
            }
            return result;
        }else if (operation.equals("÷")){
            if (justNumPressed) {
                return result/lastNum;
            }
            return result;
        }
        return lastNum;
    }
    public void trigonometricPressed(View view){
        Button target = (Button) view;
        trigonometricOp = target.getText().toString();
        if (trigonometricOp.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "Введите что нибудь!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
            toast.show();
        }
        if (trigonometricOp.equals("sin")){
            result = Math.sin(Double.parseDouble(String.valueOf(display.getText()))*Math.PI/180);
            display.setText(String.valueOf(result));
        }
        if (trigonometricOp.equals("cos")){
            result = Math.cos(Double.parseDouble(String.valueOf(display.getText()))*Math.PI/180);
            display.setText(String.valueOf(result));
        }
        if (trigonometricOp.equals("tang")){
            result = Math.tan(Double.parseDouble(String.valueOf(display.getText()))*Math.PI/180);
            display.setText(String.valueOf(result));
        }
        if (trigonometricOp.equals("√")){
            display.setText(valueOf(Math.pow(Double.parseDouble(valueOf(display.getText())), 0.5)));
        }


    }
}
