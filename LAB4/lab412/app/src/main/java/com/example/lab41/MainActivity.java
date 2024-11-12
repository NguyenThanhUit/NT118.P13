package com.example.lab41;

import static android.app.ProgressDialog.show;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnFadeInXml, btnFadeInCode, btnFadeOutXml, btnFadeOutCode,
            btnBlinkXml,
            btnBlinkCode, btnZoomInXml, btnZoomInCode, btnZoomOutXml,
            btnZoomOutCode, btnRotateXml,
            btnRotateCode, btnMoveXml, btnMoveCode, btnSlideUpXml, btnSlideUpCode,
            btnBounceXml,
            btnBounceCode, btnCombineXml, btnCombineCode;
    private ImageView ivUitLogo;
    private Animation.AnimationListener animationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        findViewsByIds();
        initVariables();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Thiet lap su kien cho tung loai AnimationXML
        HandleClickAnimationXML(btnFadeInXml, R.anim.anim_fade_in);
        HandleClickAnimationXML(btnFadeOutXml, R.anim.anim_fade_out);
        HandleClickAnimationXML(btnBlinkXml, R.anim.anim_blink);
        HandleClickAnimationXML(btnZoomInXml, R.anim.anim_zoom_in);
        HandleClickAnimationXML(btnZoomOutXml, R.anim.anim_zoom_out);
        HandleClickAnimationXML(btnRotateXml, R.anim.anim_rotate);
        HandleClickAnimationXML(btnMoveXml, R.anim.anim_move);
        HandleClickAnimationXML(btnSlideUpXml, R.anim.anim_slide_up);
        HandleClickAnimationXML(btnBounceXml, R.anim.anim_bounce);
        HandleClickAnimationXML(btnCombineXml, R.anim.anim_combine);


        //Thiet lap su kien cho tung loai AnimationCODE
        HandleClickAnimationCODE(btnFadeInCode, "fadeIn");
        HandleClickAnimationCODE(btnFadeOutCode, "fadeOut");
        HandleClickAnimationCODE(btnBlinkCode, "blink");
        HandleClickAnimationCODE(btnZoomInCode, "zoomIn");
        HandleClickAnimationCODE(btnZoomOutCode, "zoomOut");
        HandleClickAnimationCODE(btnRotateCode, "rotate");
        HandleClickAnimationCODE(btnMoveCode, "move");
        HandleClickAnimationCODE(btnSlideUpCode, "slideUp");
        HandleClickAnimationCODE(btnBounceCode, "bounce");
        HandleClickAnimationCODE(btnCombineCode, "combine");

        final Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_fade_in);
        animation.setAnimationListener(animationListener);
        btnFadeInXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivUitLogo.startAnimation(animation);
            }
        });


    }

    private void HandleClickAnimationXML(Button button, int typeOfAnimation) {
        final Animation animation = AnimationUtils.loadAnimation(MainActivity.this, typeOfAnimation);
        animation.setAnimationListener(animationListener);
        button.setOnClickListener(view -> ivUitLogo.startAnimation(animation));
    }
    private void HandleClickAnimationCODE(Button button, String typeOfAnimation) {
        Animation animation = null;
        final Animation finalAnimation;

        switch (typeOfAnimation) {
            case "fadeIn":
                animation = new AlphaAnimation(0.0f, 1.0f);
                animation.setDuration(1000);
                break;
            case "fadeOut":
                animation = new AlphaAnimation(1.0f, 0.0f);
                animation.setDuration(1000);
                break;
            case "blink":
                animation = new AlphaAnimation(0.0f, 1.0f);
                animation.setDuration(300);
                animation.setRepeatCount(3);
                animation.setRepeatMode(Animation.REVERSE);
                break;
            case "zoomIn":
                animation = new ScaleAnimation(1.0f, 3.0f, 1.0f, 3.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(1000);
                break;
            case "zoomOut":
                animation = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(1000);
                break;
            case "rotate":
                animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(600);
                animation.setRepeatMode(Animation.RESTART);
                animation.setRepeatCount(2);
                break;
            case "move":
                animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0.75f, Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0f);
                animation.setDuration(800);
                break;
            case "slideUp":
                animation = new ScaleAnimation(1.0f, 1.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.ABSOLUTE, 0.0f);
                animation.setDuration(500);
                animation.setFillAfter(true);
                break;
            case "bounce":
                animation = new ScaleAnimation(1.0f, 1.0f,0.0f , 1.0f, Animation.RELATIVE_TO_SELF, 0f);
                animation.setDuration(500);
                animation.setInterpolator(new BounceInterpolator());
                animation.setFillAfter(true);
                break;
            case "combine":

                Animation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(600);
                rotate.setRepeatCount(2);
                rotate.setRepeatMode(Animation.RESTART);

                Animation scale = new ScaleAnimation(1.0f, 3.0f, 1.0f, 3.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scale.setDuration(4000);

                AnimationSet animationSet = new AnimationSet(true); // true để giữ lại các thuộc tính của các animation con sau khi kết thúc
                animationSet.addAnimation(rotate);
                animationSet.addAnimation(scale);

                animation = animationSet;
                break;
        }

        if (animation != null) {
            finalAnimation = animation;
            animation.setAnimationListener(animationListener);
            button.setOnClickListener(view -> ivUitLogo.startAnimation(finalAnimation));
        }
    }


    private void findViewsByIds() {
        ivUitLogo = (ImageView) findViewById(R.id.iv_uit_logo);
        btnFadeInXml = (Button) findViewById(R.id.btn_fade_in_xml);
        btnFadeInCode = (Button) findViewById(R.id.btn_fade_in_code);
        btnFadeOutXml = (Button) findViewById(R.id.btn_fade_out_xml);
        btnFadeOutCode = (Button) findViewById(R.id.btn_fade_out_code);
        btnBlinkXml = (Button) findViewById(R.id.btn_blink_xml);
        btnBlinkCode = (Button) findViewById(R.id.btn_blink_code);
        btnZoomInXml = (Button) findViewById(R.id.btn_zoom_in_xml);
        btnZoomInCode = (Button) findViewById(R.id.btn_zoom_in_code);
        btnZoomOutXml = (Button) findViewById(R.id.btn_zoom_out_xml);
        btnZoomOutCode = (Button) findViewById(R.id.btn_zoom_out_code);
        btnRotateXml = (Button) findViewById(R.id.btn_rotate_xml);
        btnRotateCode = (Button) findViewById(R.id.btn_rotate_code);
        btnMoveXml = (Button) findViewById(R.id.btn_move_xml);
        btnMoveCode = (Button) findViewById(R.id.btn_move_code);
        btnSlideUpXml = (Button) findViewById(R.id.btn_slide_up_xml);
        btnSlideUpCode = (Button) findViewById(R.id.btn_slide_up_code);
        btnBounceXml = (Button) findViewById(R.id.btn_bounce_xml);
        btnBounceCode = (Button) findViewById(R.id.btn_bounce_code);
        btnCombineXml = (Button) findViewById(R.id.btn_combine_xml);
        btnCombineCode = (Button) findViewById(R.id.btn_combine_code);
    }

    private void initVariables() {
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(), "Animation Stopped",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        };
    }
}