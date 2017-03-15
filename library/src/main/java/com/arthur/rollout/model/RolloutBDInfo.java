package com.arthur.rollout.model;

import java.io.Serializable;

/**
 * Created by arthur on 2017/3/13.
 * 可以继承它
 */

public class RolloutBDInfo implements Serializable {
    //坐标
    public float x;
    public float y;
    //在容器中显示的image的宽 高
    public float width;
    public float height;
}
