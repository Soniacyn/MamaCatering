package id.sch.smktelkom_mlg.project.xiirpl506162636.mamacatering;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Astri Fadilah on 18/11/2016.
 */
public class RegisterActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
        loginScreen.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0)
            {
                finish();
            }
        });
    }
}
