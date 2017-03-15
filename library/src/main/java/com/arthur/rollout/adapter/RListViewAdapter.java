package com.arthur.rollout.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;


import com.arthur.rollout.activity.RolloutPreviewActivity;
import com.arthur.rollout.model.RolloutBDInfo;
import com.arthur.rollout.model.RolloutInfo;
import com.arthur.rollout.rolloutanimview.R;
import com.arthur.rollout.tools.RCommonUtil;
import com.arthur.rollout.tools.RGlideUtil;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * arthur list 适配器
 */
public class RListViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<RolloutInfo> data;
    private RolloutBDInfo bdInfo;
    private ListView mListView;

    public RListViewAdapter(Context context, ListView listView, ArrayList<RolloutInfo> data) {
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
    public View getView(int position, View convertView, ViewGroup arg2) {
        RolloutInfo info = data.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.rollout_list_view, null);
            holder.list_img = (ImageView) convertView.findViewById(R.id.list_img);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RGlideUtil.setImage(context, info.url, holder.list_img);
        holder.list_img.setOnClickListener(new ImageOnclick(position, holder.list_img));

        return convertView;
    }

    public class ViewHolder {
        ImageView list_img;
    }

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
            intent.putExtra("type", 1);
            context.startActivity(intent);
        }
    }
}
