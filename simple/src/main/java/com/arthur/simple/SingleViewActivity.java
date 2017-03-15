package com.arthur.simple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.arthur.rollout.activity.RolloutBaseActivity;
import com.arthur.rollout.activity.RolloutPreviewActivity;
import com.arthur.rollout.model.RolloutBDInfo;
import com.arthur.rollout.model.RolloutInfo;
import com.arthur.rollout.tools.RGlideUtil;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 1，要继承库里面的Activity（因为某些限制，库里面包含activity）
 * 2，传数据到指定的activity
 */
public class SingleViewActivity extends RolloutBaseActivity implements View.OnClickListener {

    private ImageView show_img;
    private ArrayList<RolloutInfo> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view);

        init();
        listener();
        handler();
    }

    private void init() {
        show_img = (ImageView) findViewById(R.id.show_img);

    }
    private void listener() {
        show_img.setOnClickListener(this);
    }

    private void handler() {
        data = new ArrayList<RolloutInfo>();
        bdInfo = new RolloutBDInfo();
        imageInfo = new RolloutInfo();
        //图片的宽高可以自己去设定,也可以计算图片宽高
        imageInfo.width = 1280;
        imageInfo.height = 720;
        imageInfo.url = "https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1489482821&di=43da5087a3013c51d60e1f08d0af9a1e&src=http://imgsrc.baidu.com/forum/pic/item/f2a72fbd06bcb4b7a344df77.jpg";
        data.add(imageInfo);
        //显示图片
        RGlideUtil.setImage(this, imageInfo.url, show_img);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_img:
                //获取相对位置，左边和顶部
                bdInfo.x = show_img.getLeft();
                bdInfo.y = show_img.getTop();
                //视图布局的宽高
                bdInfo.width = show_img.getLayoutParams().width;
                bdInfo.height = show_img.getLayoutParams().height;
                //跳转和传数据都必须要
                Intent intent = new Intent(this, RolloutPreviewActivity.class);
                intent.putExtra("data", (Serializable) data);
                intent.putExtra("bdinfo",bdInfo);
                intent.putExtra("type", 0);//单图传0
                intent.putExtra("index",0);
                startActivity(intent);
                break;
        }
    }
}
