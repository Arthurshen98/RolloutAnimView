package com.arthur.rollout.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.arthur.rollout.model.RolloutBDInfo;
import com.arthur.rollout.model.RolloutInfo;
import com.arthur.rollout.rolloutanimview.R;
import com.arthur.rollout.tools.RCommonUtil;
import com.arthur.rollout.tools.RGlideUtil;
import com.arthur.rollout.view.RolloutViewPager;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class RolloutPreviewActivity extends RolloutBaseActivity implements ViewPager.OnPageChangeListener {

    private int index = 0;
    private int selectIndex = -1;

    private RelativeLayout main_show_view;

    private ViewPager viewpager;
    private SamplePagerAdapter pagerAdapter;

    private ArrayList<RolloutInfo> ImgList;

    private float moveheight;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rollout_preview);
        findID();
        Listener();
        InData();
        getValue();
    }

    @Override
    public void findID() {
        super.findID();
        viewpager = (RolloutViewPager) findViewById(R.id.bi_viewpager);
        main_show_view = (RelativeLayout) findViewById(R.id.main_show_view);
    }

    @Override
    public void Listener() {
        super.Listener();
        viewpager.setOnPageChangeListener(this);
    }

    @Override
    public void InData() {
        super.InData();

        index = getIntent().getIntExtra("index", 0);
        type = getIntent().getIntExtra("type", 0);
        ImgList = (ArrayList<RolloutInfo>) getIntent().getSerializableExtra("data");

        Log.e("1", ImgList.size() + "数量");

        imageInfo = ImgList.get(index);
        bdInfo = (RolloutBDInfo) getIntent().getSerializableExtra("bdinfo");

        pagerAdapter = new SamplePagerAdapter();
        viewpager.setAdapter(pagerAdapter);
        viewpager.setCurrentItem(index);

        if (type == 1) {
            moveheight = RCommonUtil.dip2px(this, 70);
        } else if (type == 2) {
            moveheight = (Width - 3 * RCommonUtil.dip2px(this, 2)) / 3;
        } else if (type == 3) {
            moveheight = (Width - RCommonUtil.dip2px(this, 80) - RCommonUtil.dip2px(this, 2)) / 3;
        }
    }


    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        if (showimg == null) {
            return;
        }
        RolloutInfo info = ImgList.get(arg0);
        //单张
        if (type == 0) {
            RGlideUtil.setImage(RolloutPreviewActivity.this, info.url, showimg);
        } else if (type == 1) {//listview
            selectIndex = arg0;
            int move_index = arg0 - index;
            to_y = move_index * moveheight;
        } else if (type == 2) {//gridview，计算图片原始的位置，某行某列
            selectIndex = arg0;
            int a = index / 3;
            int b = index % 3;
            int a1 = arg0 / 3;
            int b1 = arg0 % 3;
            to_y = (a1 - a) * moveheight + (a1 - a) * RCommonUtil.dip2px(this, 2);
            to_x = (b1 - b) * moveheight + (b1 - b) * RCommonUtil.dip2px(this, 2);
        } else if (type == 3) {//类似与朋友圈
            selectIndex = arg0;
            int a = index / 3;
            int b = index % 3;
            int a1 = arg0 / 3;
            int b1 = arg0 % 3;
            to_y = (a1 - a) * moveheight + (a1 - a) * RCommonUtil.dip2px(this, 1);
            to_x = (b1 - b) * moveheight + (b1 - b) * RCommonUtil.dip2px(this, 1);
        }
    }

    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return ImgList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            String path = ImgList.get(position).url;
            Glide.with(RolloutPreviewActivity.this).load(path).into(photoView);
            // Now just add PhotoView to ViewPager and return it
            photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {

                @Override
                public void onViewTap(View arg0, float arg1, float arg2) {
                    viewpager.setVisibility(View.GONE);
                    showimg.setVisibility(View.VISIBLE);
                    if (selectIndex != -1) {
                        RGlideUtil.setImage(RolloutPreviewActivity.this, ImgList.get(selectIndex).url, showimg);
                    }
                    setShowimage();
                }
            });
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            viewpager.setVisibility(View.GONE);
            showimg.setVisibility(View.VISIBLE);
            setShowimage();
        }
        return true;
    }

    @Override
    protected void EndSoring() {
        super.EndSoring();
        viewpager.setVisibility(View.VISIBLE);
        showimg.setVisibility(View.GONE);
    }

    @Override
    protected void EndMove() {
        super.EndMove();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (selectIndex != -1) {
            selectIndex = -1;
        }
        RGlideUtil.clearMemory(this);
    }
}
