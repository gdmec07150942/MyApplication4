package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends Activity {
    //计算按钮
    private Button calculatorButton;
    //体重输入框
    private TextView weightEditText;
    //男性选择框
    private CheckBox manCheckBox;
    //女性选择框
    private CheckBox womanCheckBox;
    // 显示结果
    private TextView resultTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //从main..xml页面布局中获得对应的UI控件
        calculatorButton = (Button) findViewById(R.id.calculator);
        weightEditText = (TextView) findViewById(R.id.weight);
        manCheckBox = (CheckBox) findViewById(R.id.man);
        womanCheckBox = (CheckBox) findViewById(R.id.woman);
        resultTextView = (TextView) findViewById(R.id.result);



    }

    protected void onStart() {
        super.onStart();
  registerEvent();

    }

    private void registerEvent() {
        //注册按钮事件
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //判断是否已填写体重数据
                if (!weightEditText.getText().toString().trim().equals("")) {
                    //判断是否已选择性别
                    if (manCheckBox.isChecked() || womanCheckBox.isChecked()) {
                        Double weight = Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("------评估结果-----\n");
                        if (manCheckBox.isChecked()) {
                            sb.append("男性标准身高:");
                            //执行运算
                            double result = evaluateHeight(weight, "男");
                            sb.append((int) result + "(厘米)");
                        }
                        if (womanCheckBox.isChecked()) {
                            sb.append("女性标准身高:");
                            //执行运算
                            double result = evaluateHeight(weight, "女");
                            sb.append((int) result + "(厘米)");
                        }
                        //输出页面显示结果
                        resultTextView.setText(sb.toString());
                    } else {
                        showMessage("请选择性别!");
                    }
                }else{
                    showMessage("请输入体重！");
                }

            }
        });


    }
    private  double evaluateHeight(double weight,String sex){
        double height;
        if(sex=="男") {
            height = 170 - (62 - weight) / 0.6;
        }else {
            height=158-(52-weight)/0.5;
        }
        return height;
    }

    private  void showMessage(String message){
        //提示框
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("系统消息");
        alert.setMessage(message);





    }
    public boolean onCreateOptionsItemMemu(Menu menu){
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        return  super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}