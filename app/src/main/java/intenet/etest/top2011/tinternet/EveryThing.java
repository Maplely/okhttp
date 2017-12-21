package intenet.etest.top2011.tinternet;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


import com.alibaba.fastjson.JSON;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by top2011 on 2017/12/19.
 */

public class EveryThing implements WhatDO {
    Context ctx;
    String res;
    private ResultBean resultBean;
    private static final String TAG = "nnnnnnnnnnn";
    private Thread t1;
    List<String> nums;
    private int count;
    private static  final int REQUESTCONT=10;

    public EveryThing(Context ct) {
        ctx = ct;
    }

    Boolean flag = false;
    //  private static final String URL = "https://appapis.dmall.com/app/card/bindWumartCard.jsonp?callback=jQuery223045917888773069837_1513485346569&token=52e79283-2729-4736-a5ab-03c65239e99e&code="+code+"&_=1513485346574";

    @Override
    public void start() {
        initSetting();
        final Runnable requestInternet = new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    //设置URL
                    if (count < 0) {
                        flag = false;
                        count = REQUESTCONT-1;
                       initDatas();
                    }

                    String URL = "http://www.baidu.com";
                    //网络请求
                    requestMore(URL);
                    --count;
                }
            }
        };
        t1 = new Thread(requestInternet);
        t1.start();
    }

    private void initSetting() {

        nums = new ArrayList<String>();
        flag = true;
        count = REQUESTCONT-1;
        initDatas();
    }

    //初始化数据
    private void initDatas() {
        nums.clear();
        StringBuilder ranCode = new StringBuilder();
        Random random = new Random();
        while (nums.size() < REQUESTCONT) {
            ranCode.delete(0,ranCode.length());
            for (int i = 0; i < 16; i++) {
                ranCode.append(random.nextInt(10));
            }
            Log.i(TAG, "initDatas: "+ranCode.toString());
            nums.add(ranCode.toString());
        }
        flag=true;
    }

    private void requestMore(final String URL) {
        //网络请求
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(20, TimeUnit.SECONDS);
        Request request = new Request.Builder()
                .url(URL)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Response response) throws IOException {
                res = response.body().string();
                res = handlerRes(res);
                resultBean = JSON.parseObject(res, ResultBean.class);
                Intent intent=new Intent();
                intent.putExtra("data",resultBean);
                intent.setAction("com.intent.receive");
                ctx.sendBroadcast(intent);
            }
        });
    }

    //处理json模型
    private String handlerRes(String res) {
        int i = res.indexOf('{');
        res = res.substring(i, res.length() - 1);
        return res;
    }

    @Override
    public void stop() {
        flag = false;
        if (t1 != null && !t1.isInterrupted()) {
            t1.interrupt();
        }
    }

}
