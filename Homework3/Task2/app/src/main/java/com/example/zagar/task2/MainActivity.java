package com.example.zagar.task2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etFirstNumber) EditText etFirstNumber;
    @BindView(R.id.etSecondNumber) EditText etSecondNumber;
    @BindView(R.id.btnAddition) Button btnAddition;
    @BindView(R.id.btnSubtraction) Button btnSubtraction;
    @BindView(R.id.btnMultiplication) Button btnMultiplication;
    @BindView(R.id.btnDivision) Button btnDivision;
    @BindView(R.id.tvResult) TextView tvResult;
    DecimalFormat decimalFormat = new DecimalFormat(".##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnAddition)
    void addition()
    {
        double firstNumber, secondNumber, result;

        try{
            firstNumber = Double.parseDouble(etFirstNumber.getText().toString());
        }
        catch (NumberFormatException e)
        {
            Toast.makeText(this, R.string.etFirstNumberException, Toast.LENGTH_LONG).show();
            return;
        }
        try{
            secondNumber = Double.parseDouble(etSecondNumber.getText().toString());
        }
        catch (NumberFormatException e)
        {
            Toast.makeText(this, R.string.etSecondNumberException, Toast.LENGTH_LONG).show();
            return;
        }

        result = firstNumber + secondNumber;
        tvResult.setText(decimalFormat.format(result));
    }

    @OnClick(R.id.btnSubtraction)
    void subtraction()
    {
        double firstNumber, secondNumber, result;

        try{
            firstNumber = Double.parseDouble(etFirstNumber.getText().toString());
        }
        catch (NumberFormatException e)
        {
            Toast.makeText(this, R.string.etFirstNumberException, Toast.LENGTH_LONG).show();
            return;
        }
        try{
            secondNumber = Double.parseDouble(etSecondNumber.getText().toString());
        }
        catch (NumberFormatException e)
        {
            Toast.makeText(this, R.string.etSecondNumberException, Toast.LENGTH_LONG).show();
            return;
        }

        result = firstNumber - secondNumber;
        tvResult.setText(decimalFormat.format(result));
    }

    @OnClick(R.id.btnMultiplication)
    void multiplication()
    {
        double firstNumber, secondNumber, result;

        try{
            firstNumber = Double.parseDouble(etFirstNumber.getText().toString());
        }
        catch (NumberFormatException e)
        {
            Toast.makeText(this, R.string.etFirstNumberException, Toast.LENGTH_LONG).show();
            return;
        }
        try{
            secondNumber = Double.parseDouble(etSecondNumber.getText().toString());
        }
        catch (NumberFormatException e)
        {
            Toast.makeText(this, R.string.etSecondNumberException, Toast.LENGTH_LONG).show();
            return;
        }

        result = firstNumber * secondNumber;
        tvResult.setText(decimalFormat.format(result));
    }

    @OnClick(R.id.btnDivision)
    void division()
    {
        double firstNumber, secondNumber, result;

        try{
            firstNumber = Double.parseDouble(etFirstNumber.getText().toString());
        }
        catch (NumberFormatException e)
        {
            Toast.makeText(this, R.string.etFirstNumberException, Toast.LENGTH_LONG).show();
            return;
        }
        try{
            secondNumber = Double.parseDouble(etSecondNumber.getText().toString());
        }
        catch (NumberFormatException e)
        {
            Toast.makeText(this, R.string.etSecondNumberException, Toast.LENGTH_LONG).show();
            return;
        }

        if(secondNumber == 0) {
            Toast.makeText(this, R.string.btnDivisionException, Toast.LENGTH_LONG).show();
            return;
        }
        result = firstNumber / secondNumber;
        tvResult.setText(decimalFormat.format(result));
    }

    /* In progress
    private double parseStringToDouble(EditText editText, String error)
    {
        double number;

        try{
            number = Double.parseDouble(editText.getText().toString());
        }
        catch (NumberFormatException e){
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            return 1;
        }
        return number;
    }*/
}
