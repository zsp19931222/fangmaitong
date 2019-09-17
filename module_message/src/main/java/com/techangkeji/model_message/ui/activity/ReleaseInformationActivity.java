package com.techangkeji.model_message.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.techangkeji.model_message.BR;
import com.techangkeji.model_message.R;
import com.techangkeji.model_message.databinding.ActivityReleaseInformationBinding;
import com.techangkeji.model_message.ui.adapter.ReleaseInformationAdapter;
import com.techangkeji.model_message.ui.bean.ReleaseInformationBean;
import com.techangkeji.model_message.ui.view_model.ReleaseInformationViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description: 发布状态
 * author: Andy
 * date: 2019/9/16  21:48
 */
@Route(path = ARouterPath.Message.ReleaseInformationActivity)
public class ReleaseInformationActivity extends BaseActivity<ActivityReleaseInformationBinding, ReleaseInformationViewModel> {
    private ReleaseInformationAdapter informationAdapter;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_release_information;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.imageUrls.add(new ReleaseInformationBean(true, R.mipmap.add_image));
        informationAdapter = new ReleaseInformationAdapter(R.layout.item_release_information, viewModel.imageUrls);
        binding.rv.setLayoutManager(new GridLayoutManager(this, 4));
        binding.rv.setAdapter(informationAdapter);
        RxSubscriptions.add(RxBus.getDefault().toObservable(Object.class).subscribe(obj -> {
            if (obj instanceof ReleaseInformationAdapter.ReleaseInformationAdapterRxBen) {//权限获取
                viewModel.openGallery(this);
            }
        }));
        viewModel.context.set(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ZLog.d("onActivityResult", "onActivityResult");
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
                        ZLog.d(PictureSelector.obtainMultipleResult(data).get(i).getCompressPath());
                        viewModel.imagePathList.add(PictureSelector.obtainMultipleResult(data).get(i).getCompressPath());
                        viewModel.imageUrls.add(0, new ReleaseInformationBean(false, PictureSelector.obtainMultipleResult(data).get(i).getCompressPath()));
                    }
                    informationAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }
}
