package com.example.zagar.task4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class BMICalculatorActivity extends AppCompatActivity {

    @BindView(R.id.etHeight) EditText etHeight;
    @BindView(R.id.etWeight) EditText etWeight;
    @BindView(R.id.tvBMIResult) TextView tvBMIResult;
    @BindView(R.id.tvResultTitle) TextView tvResultTitle;
    @BindView(R.id.tvResultSummary) TextView tvResultSummary;
    @BindView(R.id.tvTargetWeight) TextView tvTargetWeight;
    @BindView(R.id.btnCalculate) Button btnCalculate;
    @BindView(R.id.ivResultImage) ImageView ivResultImage;

    Double result, targetWeight, healthyBMI = 23.0;

    DecimalFormat decimalFormat = new DecimalFormat("##.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);
        ButterKnife.bind(this);
    }

    @OnTextChanged(R.id.etWeight)
    public void checkWeight(){

        if(etWeight.getText().toString().equals("") || etWeight.getText().toString().equals(".")) {
            return;
        }

        Double weightValue = Double.parseDouble(etWeight.getText().toString());
        if(weightValue >= 350) {
            Toast.makeText(this, R.string.warningWeightMessage, Toast.LENGTH_SHORT).show();
        }
        checkParameters();
    }

    @OnTextChanged(R.id.etHeight)
    public void checkHeight(){

        if(etHeight.getText().toString().equals("") || etHeight.getText().toString().equals(".")) {
            return;
        }
        Double heightValue = Double.parseDouble(etHeight.getText().toString());

        if(heightValue >= 2.5) {
            Toast.makeText(this, R.string.warningHeightMessage, Toast.LENGTH_SHORT).show();
        }
        checkParameters();
    }

    @OnClick(R.id.btnCalculate)
    public void calculateBMI(){

        Double weightValue = Double.parseDouble(etWeight.getText().toString());
        Double heightValue = Double.parseDouble(etHeight.getText().toString());

        result =  weightValue / ( heightValue * heightValue );
        targetWeight = healthyBMI * heightValue * heightValue;
        tvBMIResult.setText(decimalFormat.format(result));
        tvTargetWeight.setText(getString(R.string.targetWeightText) + " " + decimalFormat.format(targetWeight) + " kg");

       if(result < 16){
            tvResultTitle.setText(R.string.anorexiaCategory3Message);
            ivResultImage.setBackground(getResources().getDrawable(R.drawable.anorexiacategory3));
            tvResultSummary.setText(R.string.anorexiaCategory3Summary);
        }
        else if(result >= 16 && result < 17){
           tvResultTitle.setText(R.string.anorexiaCategory2Message);
           ivResultImage.setBackground(getResources().getDrawable(R.drawable.anorexiacategory2));
           tvResultSummary.setText(R.string.anorexiaCategory2Summary);
       }
       else if(result >= 17 && result < 18.5){
           tvResultTitle.setText(R.string.anorexiaCategory1Message);
           ivResultImage.setBackground(getResources().getDrawable(R.drawable.anorexiacategory1));
           tvResultSummary.setText(R.string.anorexiaCategory1Summary);
       }
       else if(result >= 18.5 && result < 25){
           tvResultTitle.setText(R.string.normalMessage);
           ivResultImage.setBackground(getResources().getDrawable(R.drawable.normalweight));
           tvResultSummary.setText(R.string.normalWeightSummary);

       }
       else if(result >= 25 && result < 30){
           tvResultTitle.setText(R.string.overweightMessage);
           ivResultImage.setBackground(getResources().getDrawable(R.drawable.overweight));
           tvResultSummary.setText(R.string.overweightSummary);
       }
       else if(result >= 30 && result < 35){
           tvResultTitle.setText(R.string.obeseCategory1Message);
           ivResultImage.setBackground(getResources().getDrawable(R.drawable.obesecategory1));
           tvResultSummary.setText(R.string.obeseCategory1Summary);
       }
       else if(result >= 35 && result < 40){
           tvResultTitle.setText(R.string.obeseCategory2Message);
           ivResultImage.setBackground(getResources().getDrawable(R.drawable.obesecategory2));
           tvResultSummary.setText(R.string.obeseCategory2Summary);
       }
       else{
           tvResultTitle.setText(R.string.obeseCategory3Message);
           ivResultImage.setBackground(getResources().getDrawable(R.drawable.obesecategory3));
           tvResultSummary.setText(R.string.obeseCategory2Summary);
       }

    }

    private void checkParameters(){
        if(etHeight.getText().toString().equals("") || etHeight.getText().toString().equals(".")) {
            return;
        }
        if(etWeight.getText().toString().equals("") || etWeight.getText().toString().equals(".")) {
            return;
        }
        Double weightValue = Double.parseDouble(etWeight.getText().toString());
        Double heightValue = Double.parseDouble(etHeight.getText().toString());

        if(weightValue <350 && weightValue >30 && heightValue >1.3 && heightValue < 2.5){
            btnCalculate.setEnabled(true);
        }
        else {
            btnCalculate.setEnabled(false);
        }

    }

}
