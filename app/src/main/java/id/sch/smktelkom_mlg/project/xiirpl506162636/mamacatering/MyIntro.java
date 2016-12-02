package id.sch.smktelkom_mlg.project.xiirpl506162636.mamacatering;

/**
 * Created by PC on 12/2/2016.
 */
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

import id.sch.smktelkom_mlg.project.xiirpl506162636.mamacatering.activity.LoginActivity;

public class MyIntro extends AppIntro {
    // Please DO NOT override onCreate. Use init
    @Override
    public void init(Bundle savedInstanceState) {

        // Add your slide's fragments here
        // AppIntro will automatically generate the dots indicator and buttons

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest
        //Here we are adding the four slides
        addSlide(SampleSlide.newInstance(R.layout.splash2));
        addSlide(SampleSlide.newInstance(R.layout.intro2));
        addSlide(SampleSlide.newInstance(R.layout.intro3));


        // Edit the color of the nav bar on Lollipop+ devices
        // setNavBarColor(Color.parseColor("#3F51B5"));

        // Hide Skip/Done button
        showSkipButton(true);
        showStatusBar(false);
        // Turn vibration on and set intensity
        // NOTE: you will probably need to ask VIBRATE permisssion in Manifest
        setVibrate(true);
        setVibrateIntensity(30);
        setFadeAnimation();
        // Animations -- use only one of the below. Using both could cause errors.
        //setFadeAnimation(); // OR
/*            setZoomAnimation();
            setFlowAnimation(); // OR
            setSlideOverAnimation(); // OR
            setDepthAnimation(); // OR
            setCustomTransformer(yourCustomTransformer);*/

        // Permissions -- takes a permission and slide number
        //askForPermissions(new String[]{Manifest.permission.CAMERA}, 3);
    }

    @Override
    public void onSkipPressed() {
        // Do something when users tap on Skip button.
        Toast.makeText(getApplicationContext(),
                getString(R.string.skip), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.
    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
        finish();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSlideChanged() {
        // Do something when slide is changed
    }
}