package com.techangkeji.module_friend_circle.ui.activity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.goldze.base.router.ARouterPath;
import com.kcrason.highperformancefriendscircle.others.DataCenter;
import com.kcrason.highperformancefriendscircle.others.GlideSimpleTarget;
import com.techangkeji.module_friend_circle.BR;
import com.techangkeji.module_friend_circle.R;
import com.techangkeji.module_friend_circle.databinding.ActivityCardBinding;
import com.techangkeji.module_friend_circle.ui.adapter.MyStateAdapter;

import ch.ielse.view.imagewatcher.ImageWatcher;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * description:名片详情
 * author:created by Andy on 2019/9/16 0016 16:36
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPath.FriendCircle.CardActivity)
public class CardActivity extends BaseActivity<ActivityCardBinding, BaseViewModel> implements ImageWatcher.OnPictureLongPressListener, ImageWatcher.Loader{
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_card;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
    @Override
    public void initData() {
        binding.title.setTitle("好友动态");
        binding.emojiPanelView.initEmojiPanel(DataCenter.emojiDataSources);

        MyStateAdapter myStateAdapter = new MyStateAdapter(R.layout.item_my_state, DataCenter.makeFriendCircleBeans(this), binding.imageWatcher, binding.emojiPanelView);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(myStateAdapter);
    }

    @Override
    public void load(Context context, String url, ImageWatcher.LoadCallback lc) {
        Glide.with(context).asBitmap().load(url).into(new GlideSimpleTarget(lc));
    }

    @Override
    public void onPictureLongPress(ImageView v, String url, int pos) {

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (isSoftShowing()) {
                binding.emojiPanelView.dismiss();
            } else {
                finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onBackPressed() {
        if (!binding.imageWatcher.handleBackPressed()) {
            super.onBackPressed();
        }
    }

    //软键盘是否显示
    private boolean isSoftShowing() {
        //获取当前屏幕内容的高度
        int screenHeight = getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        //DecorView即为activity的顶级view
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        //考虑到虚拟导航栏的情况（虚拟导航栏情况下：screenHeight = rect.bottom + 虚拟导航栏高度）
        //选取screenHeight*2/3进行判断
        return screenHeight * 2 / 3 > rect.bottom;
    }
}
