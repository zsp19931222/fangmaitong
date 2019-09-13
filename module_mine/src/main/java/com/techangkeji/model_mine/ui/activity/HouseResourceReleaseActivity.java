package com.techangkeji.model_mine.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.SPUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityHouseResourceReleaseBinding;
import com.techangkeji.model_mine.ui.adapter.HouseResourceReleaseAdapter;
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseBannerBean;
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseBean;
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseSizeBean;
import com.techangkeji.model_mine.ui.post_bean.HouseResourceReleaseBannerPostBean;
import com.techangkeji.model_mine.ui.viewModel.HouseResourceReleaseViewModel;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.ZLog;

import static com.goldze.base.constant.RxBusMessageEventConstants.OPEN_GALLERY;
import static com.luck.picture.lib.config.PictureConfig.CHOOSE_REQUEST;

/**
 * description:
 * author:created by Andy on 2019/9/12 0012 09:24
 * email:zsp872126510@gmail.com
 */
public class HouseResourceReleaseActivity extends BaseActivity<ActivityHouseResourceReleaseBinding, HouseResourceReleaseViewModel> {
    private HouseResourceReleaseAdapter releaseAdapter;
    private List<HouseResourceReleaseBean> data = new ArrayList<>();

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_house_resource_release;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!IsNullUtil.getInstance().isEmpty(viewModel.adapterObservableField.get())) {
            viewModel.adapterObservableField.get().notifyDataSetChanged();
        }
    }

    @Override
    public void initData() {
        binding.title.setTitle("发布房源");
        releaseAdapter = new HouseResourceReleaseAdapter(data, this, viewModel);
        viewModel.adapterObservableField.set(releaseAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(releaseAdapter);
        initBanner();
        initInformation();
        initLinkman();
        initDetail();
        initSize();
        releaseAdapter.notifyDataSetChanged();
        RxSubscriptions.add(RxBus.getDefault().toObservable(Object.class).subscribe(obj -> {
            if (obj instanceof String) {//权限获取
                String s = (String) obj;
                switch (s) {
                    case OPEN_GALLERY:
                        openGallery();
                        break;
                }
            } else if (obj instanceof HouseResourceReleaseBannerPostBean) {//删除banner图片
                HouseResourceReleaseBannerPostBean postBean = (HouseResourceReleaseBannerPostBean) obj;
                viewModel.bannerPathList.remove(postBean.getPosition());
                releaseAdapter.notifyDataSetChanged();
            }
        }));
    }

    /**
     * description: 设置banner
     * author: Andy
     * date: 2019/9/12 0012 10:29
     */
    private void initBanner() {
        HouseResourceReleaseBean bannerReleaseBean = new HouseResourceReleaseBean();
        bannerReleaseBean.setType(HouseResourceReleaseBean.Banner);
        data.add(bannerReleaseBean);
    }

    /**
     * description:信息
     * author: Andy
     * date: 2019/9/12 0012 10:29
     */
    private void initInformation() {
        HouseResourceReleaseBean informationReleaseBean = new HouseResourceReleaseBean();
        informationReleaseBean.setType(HouseResourceReleaseBean.Information);
        data.add(informationReleaseBean);
    }

    /**
     * description:联系人
     * author: Andy
     * date: 2019/9/12 0012 10:29
     */
    private void initLinkman() {
        HouseResourceReleaseBean LinkmanReleaseBean = new HouseResourceReleaseBean();
        LinkmanReleaseBean.setType(HouseResourceReleaseBean.Linkman);
        data.add(LinkmanReleaseBean);
    }

    /**
     * description:详情
     * author: Andy
     * date: 2019/9/12 0012 10:29
     */
    private void initDetail() {
        HouseResourceReleaseBean detailReleaseBean = new HouseResourceReleaseBean();
        detailReleaseBean.setType(HouseResourceReleaseBean.Detail);
        data.add(detailReleaseBean);
    }

    /**
     * description:户型
     * author: Andy
     * date: 2019/9/12 0012 10:29
     */
    private void initSize() {
        HouseResourceReleaseBean sizeReleaseBean = new HouseResourceReleaseBean();
        sizeReleaseBean.setType(HouseResourceReleaseBean.Size);
        data.add(sizeReleaseBean);
    }


    /**
     * description: 打开相册
     * author: Andy
     * date: 2019/9/12 0012 9:46
     */
    private void openGallery() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(9)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(false)// 是否显示拍照按钮 true or false
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
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    viewModel.bannerPathList.clear();
                    for (int i = 0; i < PictureSelector.obtainMultipleResult(data).size(); i++) {
                        viewModel.bannerPathList.add(new HouseResourceReleaseBannerBean(PictureSelector.obtainMultipleResult(data).get(i).getPath(), ""));
                    }
                    ZLog.d(viewModel.bannerPathList);
                    releaseAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

}
