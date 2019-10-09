package com.techangkeji.module.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.module.BR;
import com.techangkeji.module.R;
import com.techangkeji.module.databinding.ActivityWebBinding;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * description:
 * author:created by Andy on 2019/10/9 0009 10:22
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPath.Public.WebActivity)
public class WebActivity extends BaseActivity<ActivityWebBinding, BaseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_web;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        initWebViewSettings();
        binding.webView.setWebChromeClient(new WebChromeClient(){

            /**
             * title设置
             * */
            @Override
            public void onReceivedTitle(WebView webView, String s) {
                super.onReceivedTitle(webView, s);
                binding.title.setTitle(s);
            }
        });
        binding.webView.loadUrl(getIntent().getExtras().getString("url"));
    }

    private void initWebViewSettings() {
        WebSettings webSetting = binding.webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(false);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: 根据cache-control决定是否从网络上取数据。
        //LOAD_CACHE_NORMAL: API level 17中已经废弃, 从API level 11开始作用同LOAD_DEFAULT模式
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
        webSetting.setCacheMode(WebSettings.LOAD_DEFAULT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSetting.setMixedContentMode(WebSettings.LOAD_NO_CACHE);//webView 加载网页不显示图片问题
        }

    }

}
