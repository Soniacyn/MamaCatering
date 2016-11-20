package id.sch.smktelkom_mlg.project.xiirpl506162636.mamacatering;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Astri Fadilah on 18/11/2016.
 */



public class LoginActivity extends Activity {

    private EditText editUsername;
    private EditText editPassword;
    private Button btnLogin;
    private TextView txtStatus;
    String username,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI(){
        editUsername = (EditText)findViewById(R.id.edtUsername);
        editUsername.getText();
        editPassword = (EditText)findViewById(R.id.edtPassword);
        editPassword.getText();
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(v == btnLogin){
                    if(editUsername.getText().toString().equals(username) && editPassword.getText().toString().equals(pass)){
                        txtStatus.setText("Login Berhasil");
                    }else{
                        txtStatus.setText("Login Gagal");
                    }
                }
            }
        });
        txtStatus = (TextView) findViewById(R.id.txtStatus);

        username = "astri";
        pass = "astri";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}