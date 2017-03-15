package com.arthur.simple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.arthur.simple.costom.CustomListViewActivity;

/**
 * 站在牛人的肩膀上
 * library里面已经引用的包有：
 * compile 'com.android.support:appcompat-v7:25.1.1' //可以换成v4或降低版本，等等
 * compile 'com.github.chrisbanes.photoview:library:1.2.3' 图片放大缩小
 * compile 'com.facebook.rebound:rebound:0.3.8' //facebook的弹性动画
 * compile 'com.github.bumptech.glide:glide:3.7.0' //图片加载工具
 * compile 'com.nineoldandroids:library:2.4.0' //大神JakeWharton的动画库
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.single_view).setOnClickListener(this);
        findViewById(R.id.list_view).setOnClickListener(this);
        findViewById(R.id.grid_view).setOnClickListener(this);
        findViewById(R.id.custom_listview).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.single_view:
                startActivity(new Intent(this,SingleViewActivity.class));
                break;
            case R.id.list_view:
                startActivity(new Intent(this,ListViewActivity.class));
                break;
            case R.id.grid_view:
                startActivity(new Intent(this,GridViewActivity.class));
                break;
            case R.id.custom_listview:
                startActivity(new Intent(this, CustomListViewActivity.class));
                break;
        }
    }
}
