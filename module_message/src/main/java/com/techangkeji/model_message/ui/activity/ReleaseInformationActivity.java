package com.techangkeji.model_message.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.goldze.base.utils.glide.GlideLoadUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.techangkeji.model_message.BR;
import com.techangkeji.model_message.R;
import com.techangkeji.model_message.databinding.ActivityReleaseInformationBinding;
import com.techangkeji.model_message.ui.adapter.ReleaseInformationAdapter;
import com.techangkeji.model_message.ui.bean.ReleaseInformationBean;
import com.techangkeji.model_message.ui.view_model.ReleaseInformationViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DefaultObserver;
import me.goldze.mvvmhabit.base.AppManager;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.ToastUtil;
import me.goldze.mvvmhabit.utils.ZLog;

import static com.goldze.base.constant.RxBusMessageEventConstants.OPEN_GALLERY;
import static com.goldze.base.constant.TipsConstants.GET_PERMISSIONS_FAILED;
import static com.luck.picture.lib.config.PictureConfig.CHOOSE_REQUEST;

/**
 * description: 发布状态
 * author: Andy
 * date: 2019/9/16  21:48
 */
@Route(path = ARouterPath.Message.ReleaseInformationActivity)
public class ReleaseInformationActivity extends BaseActivity<ActivityReleaseInformationBinding, ReleaseInformationViewModel> {
    private List<ReleaseInformationBean> imageList = new ArrayList<>();
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
        imageList.add(new ReleaseInformationBean(true, R.mipmap.add_image));
        informationAdapter = new ReleaseInformationAdapter(R.layout.item_release_information, imageList);
        binding.rv.setLayoutManager(new GridLayoutManager(this, 4));
        binding.rv.setAdapter(informationAdapter);
        RxSubscriptions.add(RxBus.getDefault().toObservable(Object.class).subscribe(obj -> {
            if (obj instanceof String) {//权限获取
                String s = (String) obj;
                switch (s) {
                    case OPEN_GALLERY:
                        openGallery();
                        break;
                }
            }
        }));
    }

    /**
     * description: 打开相册
     * author: Andy
     * date: 2019/9/12 0012 9:46
     */
    private void openGallery() {
        PictureSelector.create(ReleaseInformationActivity.this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(9)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .enableCrop(false)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(false)// 是否显示gif图片 true or false
                .freeStyleCropEnabled(false)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .forResult(CHOOSE_REQUEST);//结果回调onActivityResult code
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
                        imageList.add(0, new ReleaseInformationBean(false, PictureSelector.obtainMultipleResult(data).get(i).getCompressPath()));
                    }
                    informationAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }
}
