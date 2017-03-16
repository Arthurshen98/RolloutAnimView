package com.arthur.rollout.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.arthur.rollout.model.RolloutBDInfo;
import com.arthur.rollout.model.RolloutInfo;
import com.arthur.rollout.rolloutanimview.R;
import com.arthur.rollout.tools.RCommonUtil;
import com.arthur.rollout.tools.RGlideUtil;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;
import com.facebook.rebound.SpringUtil;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * 站在牛人的肩膀上
 * 引用的包：
 * compile 'com.android.support:appcompat-v7:25.1.1' //可以换成v4或降低版本
 * compile 'com.github.chrisbanes.photoview:library:1.2.3' 图片放大缩小
 * compile 'com.facebook.rebound:rebound:0.3.8' //facebook的弹性动画
 * compile 'com.github.bumptech.glide:glide:3.7.0' //图片加载工具
 * compile 'com.nineoldandroids:library:2.4.0' //大神JakeWharton的动画库
 */
public class RolloutBaseActivity extends Activity {

    // 屏幕宽度
    public float Width;
    // 屏幕高度
    public float Height;
    //整个动画过程显示的图片
    protected ImageView showimg;
    //弹性库：拉力，摩擦力
    private final Spring mSpring = SpringSystem
            .create()
            .createSpring()
            .addListener(new ExampleSpringListener());

    private RelativeLayout MainView;

    protected RolloutBDInfo bdInfo;
    protected RolloutInfo imageInfo;
    //用于动画的数值
    private float size, size_h;

    //关于imageView想要有多宽
    private float img_w;
    private float img_h;

    //原图高
    private float y_img_h;
    //退出时候图片飞向的坐标xy
    protected float to_x = 0;
    protected float to_y = 0;
    //打开时图片的坐标
    private float tx;
    private float ty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Width = RCommonUtil.getScreenWidth(this);
        Height = RCommonUtil.getScreenHeight(this);
    }


    /**
     * 获取资源
     */
    protected void findID() {
        MainView = (RelativeLayout) findViewById(R.id.main_show_view);
    }

    /**
     * 监听
     */
    protected void Listener() {
    }

    /**
     * 初始
     */
    public void InData() {
    }


    protected void EndSoring() {

    }

    protected void EndMove() {

    }

    /**
     * 获取相应的数据
     */
    protected void getValue() {
        showimg = new ImageView(this);
        showimg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        
        RGlideUtil.setImage(this, imageInfo.url, showimg);
        //关于imageView想要有多宽
        img_w = bdInfo.width;
        img_h = bdInfo.height;
        //动画变化值
        size = Width / img_w;
        y_img_h = imageInfo.height * Width / imageInfo.width;
        size_h = y_img_h / img_h;

        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams((int) bdInfo.width, (int) bdInfo.height);
        showimg.setLayoutParams(p);
        p.setMargins((int) bdInfo.x, (int) bdInfo.y, (int) (Width - (bdInfo.x + bdInfo.width)), (int) (Height - (bdInfo.y + bdInfo.height)));

        MainView.addView(showimg);

        showimg.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                setShowimage();
            }
        }, 300);
    }

    /**
     * 根据数据设置展示图
     */
    protected void setShowimage() {
        if (mSpring.getEndValue() == 0) {
            mSpring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(170, 5));
            tx = Width / 2 - (bdInfo.x + img_w / 2);
            ty = Height / 2 - (bdInfo.y + img_h / 2);
            MoveView();
            return;
        }
        mSpring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(1, 5));
        mSpring.setEndValue(0);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                //execute the task
                MoveBackView();
            }
        }, 300);

    }

    private class ExampleSpringListener implements SpringListener {

        @Override
        public void onSpringUpdate(Spring spring) {
            double CurrentValue = spring.getCurrentValue();
            float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(CurrentValue, 0, 1, 1, size);
            float mapy = (float) SpringUtil.mapValueFromRangeToRange(CurrentValue, 0, 1, 1, size_h);
            showimg.setScaleX(mappedValue);
            showimg.setScaleY(mapy);
            if (CurrentValue == 1) {
                EndSoring();
            }
        }

        @Override
        public void onSpringAtRest(Spring spring) {

        }

        @Override
        public void onSpringActivate(Spring spring) {

        }

        @Override
        public void onSpringEndStateChange(Spring spring) {

        }
    }

    /**
     * 图片展示前动画
     */
    private void MoveView() {
        //属性动画
        ObjectAnimator.ofFloat(MainView, "alpha", 0.8f).setDuration(0).start();
        MainView.setVisibility(View.VISIBLE);
        //属性动画集合
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(showimg, "translationX", tx).setDuration(200),
                ObjectAnimator.ofFloat(showimg, "translationY", ty).setDuration(200),
                ObjectAnimator.ofFloat(MainView, "alpha", 1).setDuration(200)

        );
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                showimg.setScaleType(ImageView.ScaleType.FIT_XY);
                mSpring.setEndValue(1);
                MainView.setBackgroundResource(R.color.rollout_preview_bg);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.start();

    }
    /**
     * 图片退出动画
     */
    private void MoveBackView() {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(showimg, "translationX", to_x).setDuration(200),
                ObjectAnimator.ofFloat(showimg, "translationY", to_y).setDuration(200)
        );
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                showimg.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                EndMove();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.start();
    }

    /**
     * 对跳转的动画可以重新自己定义
     *
     * @param intent
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.activity_in, 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.activity_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.activity_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RGlideUtil.clearMemory(this);
    }
}
