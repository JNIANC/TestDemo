package com.example.jnc.testDemo;

import android.widget.Button;
import android.widget.EditText;


//extends AppCompatActivity implements View.OnClickListener
public class MainActivity{

    private Button loginBtn;
    private EditText editName,editpwd;
    private Object packageName;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       *//* TextView tv1 = (TextView) findViewById(R.id.textView);
        TextView tv2 = (TextView) findViewById(R.id.textView1);
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        tv1.setText("imei:" + tm.getDeviceSoftwareVersion());
        tv2.setText("imsi:" + tm.getSubscriberId());
*//*
        editName =(EditText) findViewById(R.id.editText3);
        editpwd =(EditText) findViewById(R.id.editText5);
        loginBtn =(Button) findViewById(R.id.button);
        loginBtn.setOnClickListener(this);
    }
        public boolean isCorrectInfo(String userName,String userPwd) {
            if ("test".equals(userName) && "123".equals(userPwd))
                return true;
            else
                return false;
        }

    @Override
    public void onClick(View view) {
        String userName = editName.getText() + "";
        String userPwd = editpwd.getText() + "";
        if (isCorrectInfo(userName,userPwd)){
            Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_LONG).show();
    }*/
}
