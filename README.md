Android RolloutAnimview
==
Introduction
--
这是一个图片浏览工具，点击图片开始位置开始平移动画到center展示动画，可回弹效果，可左滑右滑，双击放大。

Demonstration
--
单张图片的效果

![](https://github.com/Arthurshen98/RolloutAnimView/blob/master/gif/rollout_1.gif) 

ListView显示图片的效果

![](https://github.com/Arthurshen98/RolloutAnimView/blob/master/gif/rollout_7.gif) 

GridView显示图片的效果

![](https://github.com/Arthurshen98/RolloutAnimView/blob/master/gif/rollout_10.gif) 

Usage
--
simple里面有实现的方式，传递数据到指定的Activity即可。可分为单张显示，Listview显示，GridView显示等。数据处理大同小异。

没有gradle引用方式，你可以把library下载下来自己去更改代码，更改自己喜欢的风格。

library里面已经引用了这几个库，无需再次添加。  

 * compile 'com.android.support:appcompat-v7:25.1.1' //可以换成v4或降低版本
 
 * compile 'com.github.chrisbanes.photoview:library:1.2.3' 图片放大缩小
 
 * compile 'com.facebook.rebound:rebound:0.3.8' //facebook的弹性动画
 
 * compile 'com.github.bumptech.glide:glide:3.7.0' //图片加载工具
 
 * compile 'com.nineoldandroids:library:2.4.0' //大神JakeWharton的动画库
 
 可以自己定义listview的adapter进行图片数据的计算。在custom文件夹下就是自己定义item去显示。
 
 如果觉得我加入的图片可以的欢迎star ^ .^
