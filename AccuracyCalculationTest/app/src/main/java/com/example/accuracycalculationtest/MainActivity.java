package com.example.accuracycalculationtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6, radioButton7, radioButton8;
    private TextView textView;
    private EditText edit, editText2;
    private int scale = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edit = findViewById(R.id.edit);
        editText2 = findViewById(R.id.edit2);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radio1);
        radioButton2 = findViewById(R.id.radio2);
        radioButton3 = findViewById(R.id.radio3);
        radioButton4 = findViewById(R.id.radio4);
        radioButton5 = findViewById(R.id.radio5);
        radioButton6 = findViewById(R.id.radio6);
        radioButton7 = findViewById(R.id.radio7);
        radioButton8 = findViewById(R.id.radio8);
        textView = findViewById(R.id.text);


        findViewById(R.id.test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, new BigDecimal("3.1").toString(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, new BigDecimal(3.1).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.test3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, new BigDecimal(BigInteger.valueOf(31), 1).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        doTestBigDecimal();

        findViewById(R.id.format).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberFormat currency = NumberFormat.getCurrencyInstance(); //建立货币格式化引用
                NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
                percent.setMaximumFractionDigits(3); //百分比小数点最多3位

                BigDecimal loanAmount = new BigDecimal("15000.48"); //贷款金额
                BigDecimal interestRate = new BigDecimal("0.008"); //利率
                BigDecimal interest = loanAmount.multiply(interestRate); //相乘


                String message = "";
                Toast.makeText(MainActivity.this,
                        "贷款金额:\t" + currency.format(loanAmount) + "\n" +
                                "利率:\t" + percent.format(interestRate) + "\n" +
                                "利息:\t" + currency.format(interest),
                        Toast.LENGTH_LONG).show();


            }
        });

        findViewById(R.id.ulpBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = ((EditText) findViewById(R.id.ulpEdit)).getText().toString();
                if (TextUtils.isEmpty(input)) {
                    input = "0";
                }
                Toast.makeText(MainActivity.this, new BigDecimal(input).ulp().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getInputStr() {
        String inputStr = edit.getText().toString();
        scale = Integer.parseInt(editText2.getText().toString());
        if (TextUtils.isEmpty(inputStr)) {
            inputStr = "1.1449";
        } else {
            if (inputStr.matches("-?[0-9]+.*[0-9]*")) {
            } else {
                Toast.makeText(MainActivity.this, "输入数字错误！", Toast.LENGTH_SHORT).show();
            }
        }
        return inputStr;
    }

    private void doTestBigDecimal() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio1:
                        BigDecimal b1 = new BigDecimal(getInputStr());
                        textView.setText(b1.setScale(scale, BigDecimal.ROUND_HALF_UP).toString());
                        break;
                    case R.id.radio2:
                        BigDecimal b2 = new BigDecimal(getInputStr());
                        textView.setText(b2.setScale(scale, BigDecimal.ROUND_HALF_DOWN).toString());
                        break;
                    case R.id.radio3:
                        BigDecimal b3 = new BigDecimal(getInputStr());
                        textView.setText(b3.setScale(scale, BigDecimal.ROUND_HALF_EVEN).toString());
                        break;
                    case R.id.radio4:
                        BigDecimal b4 = new BigDecimal(getInputStr());
                        textView.setText(b4.setScale(scale, BigDecimal.ROUND_UP).toString());
                        break;
                    case R.id.radio5:
                        BigDecimal b5 = new BigDecimal(getInputStr());
                        textView.setText(b5.setScale(scale, BigDecimal.ROUND_DOWN).toString());
                        break;
                    case R.id.radio6:
                        BigDecimal b6 = new BigDecimal(getInputStr());
                        textView.setText(b6.setScale(scale, BigDecimal.ROUND_CEILING).toString());
                        break;
                    case R.id.radio7:
                        BigDecimal b7 = new BigDecimal(getInputStr());
                        textView.setText(b7.setScale(scale, BigDecimal.ROUND_FLOOR).toString());
                        break;
                    case R.id.radio8:
                        BigDecimal b8 = new BigDecimal(getInputStr());
                        textView.setText(b8.setScale(scale, BigDecimal.ROUND_UNNECESSARY).toString());
                        break;
                }
            }
        });
    }
}
