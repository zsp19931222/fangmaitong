package com.techangkeji.model_mine.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.goldze.base.utils.glide.GlideLoadUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityAddSizeBinding;
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseSizeBean;
import com.techangkeji.model_mine.ui.data.HouseResourceReleaseSizeData;
import com.techangkeji.model_mine.ui.m_enum.HouseTypePriceEnum;
import com.techangkeji.model_mine.ui.m_enum.HouseTypeSizeEnum;

import io.reactivex.observers.DefaultObserver;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.ToastUtil;

import static com.goldze.base.constant.RxBusMessageEventConstants.OPEN_GALLERY;
import static com.goldze.base.constant.TipsConstants.GET_PERMISSIONS_FAILED;
import static com.luck.picture.lib.config.PictureConfig.CHOOSE_REQUEST;

public class AddSizeActivity extends BaseActivity<ActivityAddSizeBinding, BaseViewModel> {
    private boolean fromEditState = false;//是否是编辑状态
    private int position;
    private String imagePath = "";

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_add_size;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
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
        init();
        selectImage();
    }

    private void init() {
        binding.title.setTitle("添加户型图");
        try {
            fromEditState = true;
            position = getIntent().getExtras().getInt("position");//点击编辑
            HouseResourceReleaseSizeBean sizeBean = HouseResourceReleaseSizeData.getInstance().getList().get(position);
            imagePath=sizeBean.getImagePath();
            GlideLoadUtils.getInstance().glideLoad(this, imagePath, binding.ivAs, 0);
            binding.et1.setText(sizeBean.getRoomNum());
            binding.et2.setText(sizeBean.getHallNum());
            binding.et3.setText(sizeBean.getCookNum());
            binding.et4.setText(sizeBean.getToiletNum());
            binding.etSize.setText(sizeBean.getSize());
            binding.etAsPrice.setText(sizeBean.getPrice());
            switch (sizeBean.getHouseTypePriceEnum()) {
                case Square:
                    binding.cbPrice1.setChecked(true);
                    break;
                case ASuitOf:
                    binding.cbPrice2.setChecked(true);
                    break;
                case Undetermined:
                    binding.cbPrice3.setChecked(true);
                    break;
            }
            switch (sizeBean.getHouseTypeSizeEnum()) {
                case BuildingSurface:
                    binding.cbSize1.setChecked(true);
                    break;
                case Comprising:
                    binding.cbSize2.setChecked(true);
                    break;
            }
        } catch (Exception e) {//点击添加
            fromEditState = false;
        }

        binding.cbPrice1.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                binding.cbPrice2.setChecked(false);
                binding.cbPrice3.setChecked(false);
            }
        });
        binding.cbPrice2.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                binding.cbPrice1.setChecked(false);
                binding.cbPrice3.setChecked(false);
            }
        });
        binding.cbPrice3.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                binding.cbPrice2.setChecked(false);
                binding.cbPrice1.setChecked(false);
            }
        });
        binding.cbSize1.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                binding.cbSize2.setChecked(false);
            }
        });
        binding.cbSize1.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                binding.cbSize2.setChecked(false);
            }
        });
        binding.cbSize2.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                binding.cbSize1.setChecked(false);
            }
        });
        binding.tvCancel.setOnClickListener(view -> finish());
        binding.tvConfirm.setOnClickListener(view -> {
            HouseResourceReleaseSizeBean sizeBean;
            if (fromEditState) {
                sizeBean = HouseResourceReleaseSizeData.getInstance().getList().get(position);
                sizeBean.setImagePath(imagePath);
                sizeBean.setRoomNum(binding.et1.getText().toString());
                sizeBean.setHallNum(binding.et2.getText().toString());
                sizeBean.setCookNum(binding.et3.getText().toString());
                sizeBean.setToiletNum(binding.et4.getText().toString());
                sizeBean.setSize(binding.etSize.getText().toString());
                sizeBean.setPrice(binding.etAsPrice.getText().toString());
                if (binding.cbSize1.isChecked()) {
                    sizeBean.setHouseTypeSizeEnum(HouseTypeSizeEnum.BuildingSurface);
                } else if (binding.cbSize2.isChecked()) {
                    sizeBean.setHouseTypeSizeEnum(HouseTypeSizeEnum.Comprising);
                }
                if (binding.cbPrice1.isChecked()) {
                    sizeBean.setHouseTypePriceEnum(HouseTypePriceEnum.Square);
                } else if (binding.cbPrice2.isChecked()) {
                    sizeBean.setHouseTypePriceEnum(HouseTypePriceEnum.ASuitOf);
                } else if (binding.cbPrice3.isChecked()) {
                    sizeBean.setHouseTypePriceEnum(HouseTypePriceEnum.Undetermined);
                }
            } else {
                sizeBean = new HouseResourceReleaseSizeBean();
                sizeBean.setImagePath(imagePath);
                sizeBean.setRoomNum(binding.et1.getText().toString());
                sizeBean.setHallNum(binding.et2.getText().toString());
                sizeBean.setCookNum(binding.et3.getText().toString());
                sizeBean.setToiletNum(binding.et4.getText().toString());
                sizeBean.setSize(binding.etSize.getText().toString());
                sizeBean.setPrice(binding.etAsPrice.getText().toString());
                if (binding.cbSize1.isChecked()) {
                    sizeBean.setHouseTypeSizeEnum(HouseTypeSizeEnum.BuildingSurface);
                } else if (binding.cbSize2.isChecked()) {
                    sizeBean.setHouseTypeSizeEnum(HouseTypeSizeEnum.Comprising);
                }
                if (binding.cbPrice1.isChecked()) {
                    sizeBean.setHouseTypePriceEnum(HouseTypePriceEnum.Square);
                } else if (binding.cbPrice2.isChecked()) {
                    sizeBean.setHouseTypePriceEnum(HouseTypePriceEnum.ASuitOf);
                } else if (binding.cbPrice3.isChecked()) {
                    sizeBean.setHouseTypePriceEnum(HouseTypePriceEnum.Undetermined);
                }
                HouseResourceReleaseSizeData.getInstance().getList().add(sizeBean);
            }
            finish();
        });
    }

    private void selectImage() {
        binding.ivAs.setOnClickListener(view -> new RxPermissions(this)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(new DefaultObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            openGallery();
                        } else {
                            ToastUtil.errorToast(AddSizeActivity.this, GET_PERMISSIONS_FAILED, false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.errorToast(AddSizeActivity.this, GET_PERMISSIONS_FAILED, false);
                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );
    }

    /**
     * description: 打开相册
     * author: Andy
     * date: 2019/9/12 0012 9:46
     */
    private void openGallery() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(1)// 最大图片选择数量 int
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
                        imagePath = PictureSelector.obtainMultipleResult(data).get(position).getPath();
                        GlideLoadUtils.getInstance().glideLoad(this, imagePath, binding.ivAs, 0);
                    }
                    break;
            }
        }
    }
}
