package com.arthur.simple.costom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.arthur.rollout.activity.RolloutBaseActivity;
import com.arthur.rollout.adapter.RListViewAdapter;
import com.arthur.rollout.model.RolloutInfo;
import com.arthur.simple.R;

import java.util.ArrayList;

/**
 * 这个是可以自定义的adapter，item布局和info去实现的listView,
 * 备注：gridview里面与RGridViewAdapter类似，看了源码可以改造
 */
public class CustomListViewActivity extends RolloutBaseActivity {

    public ListView listView;
    private ArrayList<CustomImageInfo> data;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);

        init();
        listener();
        handler();
    }

    private void init() {
        listView = (ListView)findViewById(R.id.custom_list);
    }
    private void listener() {
    }

    private void handler() {
        data = new ArrayList<CustomImageInfo>();
        for (int i = 0 ; i < 5; i++) {
            CustomImageInfo model = new CustomImageInfo();
            model.url = "http://v1.qzone.cc/pic/201603/18/17/18/56ebc7eb74384954.jpeg!600x600.jpg";
            model.width = 1280;
            model.height = 720;
            model.text = "杀生丸";
            data.add(model);
            CustomImageInfo model1 = new CustomImageInfo();
            model1.url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490086858&di=f8938d348ad720fb839c39068f7a915b&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.zgqydx.cn%2Ftupian%2Fbd64135.jpg.jpg";
            model1.width = 1280;
            model1.height = 720;
            model1.text = "汉库克";
            data.add(model1);
            CustomImageInfo model2 = new CustomImageInfo();
            model2.url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489492371312&di=ddd981235ef8a164dec438b3c1b7d86e&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Ff9198618367adab4f3ad7d5289d4b31c8701e4a6.jpg";
            model2.width = 1280;
            model2.height = 720;
            model2.text = "罗宾";
            data.add(model2);
            CustomImageInfo model3 = new CustomImageInfo();
            model3.url = "https://a-ssl.duitang.com/uploads/item/201408/31/20140831220442_RAeKU.thumb.700_0.jpeg";
            model3.width = 1280;
            model3.height = 720;
            model3.text = "娜美";
            data.add(model3);
            CustomImageInfo model4 = new CustomImageInfo();
            model4.url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489492189656&di=9e3ad450917dc9652951e3c9a16106c3&imgtype=0&src=http%3A%2F%2Fimg4q.duitang.com%2Fuploads%2Fitem%2F201502%2F20%2F20150220100612_Yy4cn.png";
            model4.width = 1280;
            model4.height = 720;
            model4.text = "索隆";
            data.add(model4);
        }
        //要把listview传入
        adapter = new CustomListAdapter(this,listView,data);
        listView.setAdapter(adapter);
    }
}
