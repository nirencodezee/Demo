package com.pharma.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.pharma.app.scrolladapter.TopAppsPagerAdapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class ScreenActivity extends AppCompatActivity {

    private ScrollViewPager scrollViewPager;
    ImageView right_button;
    public static LinearLayout signin;
    public static TextView skip;
    public static int posi;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(this.getResources().getColor(R.color.blue));
        }

        right_button = findViewById(R.id.right_button);
        signin = findViewById(R.id.signin);
        skip = findViewById(R.id.skip);

        sharedPreferences = getSharedPreferences("splash", MODE_PRIVATE);
        editor = sharedPreferences.edit();


        scrollViewPager = (ScrollViewPager) findViewById(R.id.viewpager);
//        scrollViewPager.setInterval(3000);
//        scrollViewPager.startAutoScroll();
//        scrollViewPager.setCycle(true);
        scrollViewPager.setStopScrollWhenTouch(true);

        scrollViewPager.setAdapter(new TopAppsPagerAdapter(getSupportFragmentManager()));
        ((DotsIndicator) findViewById(R.id.dots_indicator)).setViewPager(scrollViewPager);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("value", 1);
                editor.commit();
                Intent intent = new Intent(ScreenActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        right_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (posi == 2) {
                    editor.putInt("value", 1);
                    editor.commit();
                    Intent intent = new Intent(ScreenActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    scrollViewPager.setCurrentItem(getItem(+1), true);
                }
//                Toast.makeText(ScreenActivity.this, "dfndsbfdsfdsfdfd", Toast.LENGTH_SHORT).show();

            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollViewPager.setCurrentItem(getItem(+1), true);
            }
        });


        scrollViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                posi = position;
//                Toast.makeText(ScreenActivity.this, "=====uyfu=====" + position, Toast.LENGTH_SHORT).show();
                scrollViewPager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private int getItem(int i) {
        return scrollViewPager.getCurrentItem() + i;
    }
}