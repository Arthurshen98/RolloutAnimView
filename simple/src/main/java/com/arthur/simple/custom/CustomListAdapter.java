package com.arthur.simple.custom;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.arthur.rollout.activity.RolloutPreviewActivity;
import com.arthur.rollout.model.RolloutBDInfo;
import com.arthur.rollout.tools.RCommonUtil;
import com.arthur.rollout.tools.RGlideUtil;
import com.arthur.simple.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by arthur on 2017/3/14.
 * 可自己根据需要定义的adapter
 */

public class CustomListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CustomImageInfo> data;
    //改属性基本不改，保留
    private RolloutBDInfo bdInfo;
    private ListView mListView;

    public CustomListAdapter(Context context, ListView listView, ArrayList<CustomImageInfo> data) {
        this.context = context;
        this.data = data;
        this.mListView = listView;
        bdInfo = new RolloutBDInfo();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data == null ? null : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomImageInfo info = data.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context,R.layout.custom_list_item, null);
            holder.list_img = (ImageView) convertView.findViewById(R.id.custom_list_img);
            holder.text = (TextView) convertView.findViewById(R.id.custom_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RGlideUtil.setImage(context, info.url, holder.list_img);
        holder.list_img.setOnClickListener(new ImageOnclick(position, holder.list_img));
        holder.text.setText(info.text);

        return convertView;
    }

    public class ViewHolder {
        ImageView list_img;
        TextView text;
    }


    //上面基本都一样的，重点就是这里面要传的东西
    private class ImageOnclick implements View.OnClickListener {

        private int index;
        private ImageView imageView;

        public ImageOnclick(int index, ImageView imageView) {
            this.index = index;
            this.imageView = imageView;
        }

        @Override
        public void onClick(View v) {
            View c = mListView.getChildAt(0);
            //相对于其父视图的顶部位置
            int top = c.getTop();
            //返回适配器的数据集中显示在屏幕上的第一个项目的位置。
            int firstVisiblePosition = mListView.getFirstVisiblePosition();
            //Left position of this view relative to its parent.
            bdInfo.x = imageView.getLeft();
            bdInfo.y = RCommonUtil.dip2px(context, 7) + (index - firstVisiblePosition) * RCommonUtil.dip2px(context, 70) + top + mListView.getTop();

            //关于imageView想要有多宽
            bdInfo.width = imageView.getLayoutParams().width;
            bdInfo.height = imageView.getLayoutParams().height;

            Intent intent = new Intent(context, RolloutPreviewActivity.class);
            intent.putExtra("data", (Serializable) data);
            intent.putExtra("bdinfo", bdInfo);
            intent.putExtra("index", index);
            intent.putExtra("type", 1);//list传1
            context.startActivity(intent);
        }
    }
}
