package com.arthur.simple.custom;

import com.arthur.rollout.model.RolloutInfo;

import java.io.Serializable;

/**
 * Created by arthur on 2017/3/14.
 * 重写info保留RolloutInfo里面的属性
 */

public class CustomImageInfo extends RolloutInfo implements Serializable {

    public int id;
    public String text;

}
