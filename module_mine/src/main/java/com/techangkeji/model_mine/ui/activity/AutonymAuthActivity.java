package com.techangkeji.model_mine.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.luck.picture.lib.PictureSelector;
import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityAutonymAuthBinding;
import com.techangkeji.model_mine.ui.viewModel.AutonymAuthViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class AutonymAuthActivity extends BaseActivity<ActivityAutonymAuthBinding, AutonymAuthViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_autonym_auth;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.context.set(this);
        binding.title.setTitle("实名认证");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 10086:
                    // 图片、视频、音频选择结果回调
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    for (int i = 0; i < PictureSelector.obtainMultipleResult(data).size(); i++) {
                        viewModel.uploadpic(PictureSelector.obtainMultipleResult(data).get(i).getCompressPath(), 10086);
                    }
                    break;
                case 10087:
                    for (int i = 0; i < PictureSelector.obtainMultipleResult(data).size(); i++) {
                        viewModel.uploadpic(PictureSelector.obtainMultipleResult(data).get(i).getCompressPath(), 10087);
                    }
                    break;
            }
        }
    }
}
