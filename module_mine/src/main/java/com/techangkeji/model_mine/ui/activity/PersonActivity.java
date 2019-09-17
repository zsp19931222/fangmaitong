package com.techangkeji.model_mine.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.goldze.base.utils.glide.GlideLoadUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityPersonBinding;
import com.techangkeji.model_mine.ui.viewModel.PersonViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.ZLog;

public class PersonActivity extends BaseActivity<ActivityPersonBinding, PersonViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_person;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("个人资料");
        viewModel.context.set(this);
        GlideLoadUtils.getInstance().glideLoad(this, viewModel.headUrl.get(), binding.ivAp, 0, 19);
        RxSubscriptions.add(RxBus.getDefault().toObservable(Object.class).subscribe(obj -> {
            if (obj instanceof ChangeNickNameActivity.ChangeNameBean) {
                ChangeNickNameActivity.ChangeNameBean changeNameBean = (ChangeNickNameActivity.ChangeNameBean) obj;
                viewModel.nickName.set(changeNameBean.getName());
            }
        }));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        ZLog.d("onActivityResult", "onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    for (int i = 0; i < PictureSelector.obtainMultipleResult(data).size(); i++) {
                        viewModel.headUrl.set(PictureSelector.obtainMultipleResult(data).get(i).getCutPath());
                        GlideLoadUtils.getInstance().glideLoad(this, PictureSelector.obtainMultipleResult(data).get(i).getCutPath(), binding.ivAp, 0, 19);
                    }
                    break;
            }
        }
    }
}
