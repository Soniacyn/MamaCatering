package id.sch.smktelkom_mlg.project.xiirpl506162636.mamacatering;

/**
 * Created by PC on 11/24/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import id.sch.smktelkom_mlg.project.xiirpl506162636.mamacatering.activity.LoginActivity;

public class splash_screen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(splash_screen.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
