package baway.com.mypropertyanimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageView image_logoo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image_logoo = (ImageView) findViewById(R.id.image_logo);
        Animator animator = AnimatorInflater.loadAnimator(MainActivity.this, R.anim.myanim);
        animator.setTarget(image_logoo);
        animator.start();
        //实例化timer
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                //开始跳转
                Intent intent=new Intent(MainActivity.this,MyActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);




    }
}
