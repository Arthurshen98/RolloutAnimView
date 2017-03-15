package com.arthur.rollout.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.arthur.rollout.activity.RolloutPreviewActivity;
import com.arthur.rollout.model.RolloutBDInfo;
import com.arthur.rollout.model.RolloutInfo;
import com.arthur.rollout.rolloutanimview.R;
import com.arthur.rollout.tools.RCommonUtil;
import com.arthur.rollout.tools.RGlideUtil;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * arthur GridView 适配器
 */
public class RGridViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<RolloutInfo> data;
    private RolloutBDInfo bdInfo;
    private GridView mGridView;

    public RGridViewAdapter(Context context, GridView gridView, ArrayList<RolloutInfo> data) {
        this.context = context;
        this.data = data;
        this.mGridView = gridView;
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
            convertView = View.inflate(context,
                    R.layout.rollout_grid_view, null);
            holder.gridimage = (ImageView) convertView.findViewById(R.id.grid_image);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        RGlideUtil.setImage(context, info.url, holder.gridimage);
        holder.gridimage.setOnClickListener(new ImageOnclick(position, holder.gridimage));

        return convertView;
    }

    public class ViewHolder {
        ImageView gridimage;
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

            View c = mGridView.getChildAt(0);
            int top = c.getTop();
            int firstVisiblePosition = mGridView.getFirstVisiblePosition() / 3;

            int a, b;
            //模拟行列划分3等分进行计算
            a = index / 3;
            b = index % 3;

            bdInfo.width = (RCommonUtil.getScreenWidth(context) - 3 * RCommonUtil.dip2px(context, 2)) / 3;
            bdInfo.height = bdInfo.width;
            //把屏幕划分成了行和列，采用行列估算方法，进行计算位置
            bdInfo.x = RCommonUtil.dip2px(context, 1) + b * bdInfo.width + b * RCommonUtil.dip2px(context, 2);
            bdInfo.y = RCommonUtil.dip2px(context, 1) + bdInfo.height * (a - firstVisiblePosition) + top + (a - firstVisiblePosition) * RCommonUtil.dip2px(context, 2) + mGridView.getTop() - RCommonUtil.dip2px(context, 1);

            Intent intent = new Intent(context, RolloutPreviewActivity.class);
            intent.putExtra("data", (Serializable) data);
            intent.putExtra("bdinfo", bdInfo);
            intent.putExtra("index", index);
            intent.putExtra("type", 2);
            context.startActivity(intent);
        }
    }

}
