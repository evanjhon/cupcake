package com.evan.cupcake.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.evan.cupcake.R;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;


/**
 * Created by Evan on 2018/9/5.
 * des imageLoader 工具类
 */

public class ImageLoaderUtils {

    /**
     * 初始化配置文件
     *
     * @param context
     */
    public static void init(Context context) {
        //自定义的方式
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800)//内存缓存文件的最大长宽
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)//设置当前线程的优先级
                .tasksProcessingOrder(QueueProcessingType.FIFO)//设置任务的处理顺序
                .denyCacheImageMultipleSizesInMemory()//防止内存中图片重复
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))//设置自己的内存缓存大小   2M
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13)//内存缓存百分比
                .imageDownloader(new BaseImageDownloader(context))
                .imageDecoder(new BaseImageDecoder(true))//图片解码
                .defaultDisplayImageOptions(getOption())//是否使用默认的图片加载配置，null表示不使用
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(configuration);
    }

    /**
     * 设置option
     *
     * @return
     */

    public static DisplayImageOptions getOption() {
        DisplayImageOptions option = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher_round)//设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher_round)//设置图片uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher_round)//设置图片加载或解码过程中发生错误显示的图片
                .resetViewBeforeLoading(false)//设置图片在加载前是否重置、复位
                //.delayBeforeLoading(1000)//下载前的延迟时间
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在sd卡中
                .considerExifParams(false)//思考可交换的参数
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)//设置图片的显示比例
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .displayer(new FadeInBitmapDisplayer(1000))//设置图片显示的透明度过程的时间
                .build();

        return option;
    }

    /**
     * 设置option
     *
     * @return
     */

    public static DisplayImageOptions getTransparencyOption() {
        DisplayImageOptions option = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.color.transparent)//设置图片下载期间显示的图片
                .showImageForEmptyUri(R.color.transparent)//设置图片uri为空或是错误的时候显示的图片
                .showImageOnFail(R.color.transparent)//设置图片加载或解码过程中发生错误显示的图片
                .resetViewBeforeLoading(false)//设置图片在加载前是否重置、复位
                //.delayBeforeLoading(1000)//下载前的延迟时间
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在sd卡中
                .considerExifParams(false)//思考可交换的参数
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)//设置图片的显示比例
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .displayer(new FadeInBitmapDisplayer(1000))//设置图片显示的透明度过程的时间
                .build();

        return option;
    }

    /**
     * 设置图片
     *
     * @param url
     * @param imageView
     */
    public static void setImage(String url, ImageView imageView) {
        ImageLoader.getInstance().displayImage(url, imageView, getOption());
    }

    /**
     * 设置图片 加载背景为空白
     * @param url
     * @param imageView
     */
    public static void setTransparentBgImage(String url, ImageView imageView) {
        ImageLoader.getInstance().displayImage(url, imageView, getTransparencyOption());
    }

    /**
     * 设置小图片
     *
     * @param url
     * @param imageView
     */
    @SuppressLint("DefaultLocale")
    public static void setSmallImage(String url, ImageView imageView) {
        String format = "%s?x-oss-process=image/resize,w_%d/quality,Q_%d";
        url = String.format(format, url, 400, 100);
        ImageLoader.getInstance().displayImage(url, imageView, getOption());
    }

    /**
     * 设置图片
     *
     * @param url
     * @param imageView
     */
    public static void setImage(String url, ImageView imageView, DisplayImageOptions options) {
        ImageLoader.getInstance().displayImage(url, imageView, options);
    }

    /**
     * 设置option
     *
     * @param radius 圆角角度
     * @return
     */

    public static DisplayImageOptions getOption(int radius) {
        DisplayImageOptions option = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher_round)//设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher_round)//设置图片uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher_round)//设置图片加载或解码过程中发生错误显示的图片
                .resetViewBeforeLoading(false)//设置图片在加载前是否重置、复位
                //.delayBeforeLoading(1000)//下载前的延迟时间
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在sd卡中
                .considerExifParams(false)//思考可交换的参数
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)//设置图片的显示比例
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .displayer(new RoundedBitmapDisplayer(radius))//设置图片的圆角半径
//                .displayer(new FadeInBitmapDisplayer(1000))//设置图片显示的透明度过程的时间
                .build();

        return option;
    }

}