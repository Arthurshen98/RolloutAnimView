package com.arthur.simple;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import com.arthur.rollout.activity.RolloutBaseActivity;
import com.arthur.rollout.adapter.RGridViewAdapter;
import com.arthur.rollout.adapter.RListViewAdapter;
import com.arthur.rollout.model.RolloutInfo;

import java.util.ArrayList;

public class GridViewActivity extends RolloutBaseActivity {

    public GridView gridView;
    private RGridViewAdapter adapter;
    private ArrayList<RolloutInfo> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        init();
        listener();
        handler();
    }

    private void init() {
        gridView = (GridView)findViewById(R.id.gridview);
    }
    private void listener() {
    }

    private void handler() {
        data = new ArrayList<RolloutInfo>();
        for (int i = 0 ; i < 5; i++) {
            RolloutInfo model = new RolloutInfo();
            model.url = "http://img4q.duitang.com/uploads/item/201408/11/20140811141753_iNtAF.jpeg";
            model.width = 1280;
            model.height = 720;
            data.add(model);
            RolloutInfo model1 = new RolloutInfo();
            model1.url = "http://imgsrc.baidu.com/forum/pic/item/8b82b9014a90f603fa18d50f3912b31bb151edca.jpg";
            model1.width = 1280;
            model1.height = 720;
            data.add(model1);
            RolloutInfo model2 = new RolloutInfo();
            model2.url = "http://imgsrc.baidu.com/forum/pic/item/8e230cf3d7ca7bcb3acde5a2be096b63f724a8b2.jpg";
            model2.width = 1280;
            model2.height = 720;
            data.add(model2);
            RolloutInfo model3 = new RolloutInfo();
            model3.url = "http://att.bbs.duowan.com/forum/201210/20/210446opy9p5pghu015p9u.jpg";
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
        adapter = new RGridViewAdapter(this,gridView,data);
        gridView.setAdapter(adapter);
    }
}
