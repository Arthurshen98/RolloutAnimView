package com.arthur.simple;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.arthur.rollout.activity.RolloutBaseActivity;
import com.arthur.rollout.adapter.RListViewAdapter;
import com.arthur.rollout.model.RolloutBDInfo;
import com.arthur.rollout.model.RolloutInfo;
import com.arthur.rollout.tools.RGlideUtil;

import java.util.ArrayList;

/**
 * listView的实例代码
 */
public class ListViewActivity extends RolloutBaseActivity {

    public ListView listView;
    //继承library里的adapter
    private RListViewAdapter adapter;
    //library里RolloutInfo
    private ArrayList<RolloutInfo> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        init();
        listener();
        handler();
    }

    private void init() {
        listView = (ListView)findViewById(R.id.list_view);
    }
    private void listener() {
    }

    private void handler() {
        data = new ArrayList<RolloutInfo>();
        for (int i = 0 ; i < 5; i++) {
            RolloutInfo model = new RolloutInfo();
            model.url = "https://a-ssl.duitang.com/uploads/item/201408/31/20140831220442_RAeKU.thumb.700_0.jpeg";
            model.width = 1280;
            model.height = 720;
            data.add(model);
            RolloutInfo model1 = new RolloutInfo();
            model1.url = "http://v1.qzone.cc/pic/201603/18/17/18/56ebc7eb74384954.jpeg!600x600.jpg";
            model1.width = 1280;
            model1.height = 720;
            data.add(model1);
            RolloutInfo model2 = new RolloutInfo();
            model2.url = "http://imgsrc.baidu.com/forum/pic/item/8e230cf3d7ca7bcb3acde5a2be096b63f724a8b2.jpg";
            model2.width = 1280;
            model2.height = 720;
            data.add(model2);
            RolloutInfo model3 = new RolloutInfo();
            model3.url = "http://pic0.mofang.com/2014/0626/20140626052559887.jpg";
            model3.width = 1280;
            model3.height = 720;
            data.add(model3);
            RolloutInfo model4 = new RolloutInfo();
            model4.url = "http://img5.duitang.com/uploads/item/201404/11/20140411214939_XswXa.jpeg";
            model4.width = 1280;
            model4.height = 720;
            data.add(model4);
        }
        //要把listview传入
        adapter = new RListViewAdapter(this,listView,data);
        listView.setAdapter(adapter);
    }
}
