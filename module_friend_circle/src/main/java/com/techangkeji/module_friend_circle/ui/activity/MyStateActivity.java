package com.techangkeji.module_friend_circle.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.goldze.base.router.ARouterPath;
import com.kcrason.highperformancefriendscircle.interfaces.OnPraiseOrCommentClickListener;
import com.kcrason.highperformancefriendscircle.others.DataCenter;
import com.kcrason.highperformancefriendscircle.others.GlideSimpleTarget;
import com.techangkeji.module_friend_circle.BR;
import com.techangkeji.module_friend_circle.R;
import com.techangkeji.module_friend_circle.databinding.ActivityMyStateBinding;
import com.techangkeji.module_friend_circle.ui.adapter.MyStateAdapter;
import com.techangkeji.module_friend_circle.ui.viewModel.MyStateViewModel;

import java.util.ArrayList;
import java.util.List;

import ch.ielse.view.imagewatcher.ImageWatcher;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.http.net.body.CommentBody;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.ZLog;

@Route(path = ARouterPath.FriendCircle.MyStateActivity)
public class MyStateActivity extends BaseActivity<ActivityMyStateBinding, MyStateViewModel> implements ImageWatcher.OnPictureLongPressListener, ImageWatcher.Loader {
    private MyStateAdapter myStateAdapter;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_my_state;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("我的动态");
        binding.emojiPanelView.initEmojiPanel(DataCenter.emojiDataSources);

        myStateAdapter = new MyStateAdapter(R.layout.item_my_state, viewModel.friendCircleBeans, binding.imageWatcher, binding.emojiPanelView,viewModel);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(myStateAdapter);
        viewModel.myMovingList(this,myStateAdapter);

        RxSubscriptions.add(RxBus.getDefault().toObservable(CommentBody.class).subscribe(commentBody -> {
            ZLog.d(commentBody);
            viewModel.comment(commentBody);
        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(String.class).subscribe(s -> {
            CommentBody commentBody=new CommentBody("1234",2,6,  1,"梦佳姐",false);
            viewModel.comment(commentBody);
        }));
    }

    @Override
    public void load(Context context, String url, ImageWatcher.LoadCallback lc) {
        Glide.with(context).asBitmap().load(url).into(new GlideSimpleTarget(lc));
    }

    @Override
    public void onPictureLongPress(ImageView v, String url, int pos) {

    }

    @Override
    public void onBackPressed() {
        if (!binding.imageWatcher.handleBackPressed()) {
            super.onBackPressed();
        }
    }
}
