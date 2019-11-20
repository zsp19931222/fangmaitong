package com.techangkeji.model_mine.ui.viewModel;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.fragment.app.FragmentActivity;

import com.goldze.base.bean.FeaturedLabelBean;
import com.goldze.base.utils.ParameterLogUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.techangkeji.model_mine.ui.adapter.HSRDAdapter;
import com.techangkeji.model_mine.ui.adapter.HouseResourceReleaseAdapter;
import com.techangkeji.model_mine.ui.adapter.HouseResourceReleaseBannerAdapter;
import com.techangkeji.model_mine.ui.adapter.HouseResourceReleaseSizeAdapter;
import com.techangkeji.model_mine.ui.adapter.LinkManAdapter;
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseBannerBean;
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseSizeBean;
import com.techangkeji.model_mine.ui.bean.SelectFriendBean;
import com.techangkeji.model_mine.ui.data.HouseResourceReleaseSizeData;
import com.techangkeji.model_mine.ui.m_enum.HouseTypePriceEnum;
import com.techangkeji.model_mine.ui.m_enum.HouseTypeSizeEnum;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.http.net.entity.FeaturedLabelEntity;
import me.goldze.mvvmhabit.http.net.entity.HouseResourceDetailEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.UpLoadImageEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtil;
import me.goldze.mvvmhabit.utils.ZLog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.goldze.base.constant.TipsConstants.GET_PERMISSIONS_FAILED;
import static com.luck.picture.lib.config.PictureConfig.CHOOSE_REQUEST;

/**
 * description:
 * author:created by Andy on 2019/9/12 0012 09:25
 * email:zsp872126510@gmail.com
 */
public class HouseResourceReleaseViewModel extends BaseViewModel {
    public ObservableField<HouseResourceReleaseAdapter> adapterObservableField = new ObservableField<>();
    public ObservableField<HouseResourceReleaseSizeAdapter> houseResourceReleaseSizeAdapter = new ObservableField<>();
    public ObservableField<HouseResourceReleaseBannerAdapter> bannerAdapter = new ObservableField<>();
    public ObservableField<com.techangkeji.model_mine.ui.adapter.LinkManAdapter> linkManAdapter = new ObservableField<>();
    public ObservableField<HSRDAdapter> hsrdAdapter = new ObservableField<>();
    public ObservableField<TextView> addressTextView = new ObservableField<>();
    public ObservableField<TextView> officeAddressTextView = new ObservableField<>();

    public ObservableList<HouseResourceReleaseBannerBean> bannerPathList = new ObservableArrayList<>();
    public ObservableList<FeaturedLabelBean> featuredLabelList = new ObservableArrayList<>();
    public ObservableList<FeaturedLabelBean> featuredLabelListConfirm = new ObservableArrayList<>();
    public ObservableList<String> labelList = new ObservableArrayList<>();
    public ObservableList<SelectFriendBean> linkManList = new ObservableArrayList<>();//联系人数据


    public ObservableField<StringBuilder> bannerStringBuilder = new ObservableField<>(new StringBuilder());

    public ObservableField<StringBuilder> typeImgUrlStringBuilder = new ObservableField<>(new StringBuilder());
    public ObservableField<StringBuilder> areaStringBuilder = new ObservableField<>(new StringBuilder());
    public ObservableField<StringBuilder> typeStringBuilder = new ObservableField<>(new StringBuilder());
    public ObservableField<StringBuilder> houseAveragePriceStringBuilder = new ObservableField<>(new StringBuilder());
    public ObservableField<StringBuilder> housePriceTypeStringBuilder = new ObservableField<>(new StringBuilder());
    public ObservableField<StringBuilder> houseAreaTypeStringBuilder = new ObservableField<>(new StringBuilder());
    public ObservableField<StringBuilder> friendIdStringBuilder = new ObservableField<>(new StringBuilder());

    public ObservableField<StringBuilder> labelIdsStringBuilder = new ObservableField<>(new StringBuilder());
    public ObservableField<StringBuilder> labelNameStringBuilder = new ObservableField<>(new StringBuilder());//标签名

    public ObservableField<String> houseID = new ObservableField<>("");//房源ID
    public ObservableField<String> address = new ObservableField<>("");
    public ObservableField<String> officeAddress = new ObservableField<>("");
    public ObservableField<String> averagePrice = new ObservableField<>("");
    public ObservableField<String> buildType = new ObservableField<>("");
    public ObservableField<String> carNum = new ObservableField<>("");
    public ObservableField<String> commissionRule = new ObservableField<>("");
    public ObservableField<String> decorationType = new ObservableField<>("");
    public ObservableField<String> developerName = new ObservableField<>("");
    public ObservableField<String> handTime = new ObservableField<>("");
    //楼盘经纬度
    public ObservableField<String> lat = new ObservableField<>("");
    public ObservableField<String> lon = new ObservableField<>("");
    //售楼处经纬度
    public ObservableField<String> salesLat = new ObservableField<>("");
    public ObservableField<String> salesLon = new ObservableField<>("");
    public ObservableField<String> license = new ObservableField<>("");
    public ObservableField<String> listingName = new ObservableField<>("");
    public ObservableField<String> lookRule = new ObservableField<>("");
    public ObservableField<String> openTime = new ObservableField<>("");
    public ObservableField<String> priceType = new ObservableField<>("");
    public ObservableField<String> propertiesType = new ObservableField<>("");
    public ObservableField<String> propertiesTypeText = new ObservableField<>("");//物业类型
    public ObservableField<String> propertyCompany = new ObservableField<>("");
    public ObservableField<String> propertyMoney = new ObservableField<>("");
    public ObservableField<String> propertyYear = new ObservableField<>("");
    public ObservableField<String> resident = new ObservableField<>("");
    public ObservableField<String> volumeRate = new ObservableField<>("");
    public ObservableField<String> noticeId = new ObservableField<>("");//规则ID修改时候用


    public HouseResourceReleaseViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 特色标签
     * author: Andy
     * date: 2019/9/21  23:56
     */
    public void getFeaturedLabel() {
        IdeaApi.getApiService()
                .getFeaturedLabel()
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<FeaturedLabelEntity>() {
                    @Override
                    public void onSuccess(FeaturedLabelEntity response) {
                        for (FeaturedLabelEntity.DataBean datum : response.getData()) {
                            featuredLabelList.add(new FeaturedLabelBean(datum.getId(), datum.getLabel_name(), false));
                        }
                    }

                });
    }

    /**
     * description:
     * 建筑类型标签
     * author: Andy
     * date: 2019/9/21  23:56
     */
    public ObservableList<FeaturedLabelBean> buildLabeList = new ObservableArrayList();//物业类型

    public void getBuildingTypeLabel() {
        IdeaApi.getApiService()
                .getBuildLabel()
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<FeaturedLabelEntity>() {
                    @Override
                    public void onSuccess(FeaturedLabelEntity response) {
                        for (FeaturedLabelEntity.DataBean datum : response.getData()) {
                            buildLabeList.add(new FeaturedLabelBean(datum.getId(), datum.getLabel_name(), false));
                        }
                    }

                });
    }

    /**
     * description:     房源发布
     * author: Andy
     * date: 2019/9/21  17:07
     */
    public void addBuildingInfo() {
        friendIdStringBuilder.get().setLength(0);
        areaStringBuilder.get().setLength(0);
        houseAveragePriceStringBuilder.get().setLength(0);
        typeStringBuilder.get().setLength(0);
        housePriceTypeStringBuilder.get().setLength(0);
        houseAreaTypeStringBuilder.get().setLength(0);

        for (HouseResourceReleaseSizeBean sizeBean : HouseResourceReleaseSizeData.getInstance().getList()) {
            areaStringBuilder.get().append(sizeBean.getSize()).append(",");
            houseAveragePriceStringBuilder.get().append(sizeBean.getPrice()).append(",");
            typeStringBuilder.get().append(sizeBean.getRoomNum() + "室" + sizeBean.getHallNum() + "厅" + sizeBean.getCookNum() + "厨" + sizeBean.getToiletNum() + "卫").append(",");
            switch (sizeBean.getHouseTypePriceEnum()) {
                case Square:
                    housePriceTypeStringBuilder.get().append("1").append(",");
                    break;
                case Undetermined:
                    housePriceTypeStringBuilder.get().append("3").append(",");
                    break;
                case ASuitOf:
                    housePriceTypeStringBuilder.get().append("2").append(",");
                    break;
            }
            switch (sizeBean.getHouseTypeSizeEnum()) {
                case BuildingSurface:
                    houseAreaTypeStringBuilder.get().append("1").append(",");
                    break;
                case Comprising:
                    houseAreaTypeStringBuilder.get().append("2").append(",");
                    break;
            }
        }
        for (SelectFriendBean selectFriendBean : linkManList) {
            friendIdStringBuilder.get().append(selectFriendBean.getId()).append(",");
        }
        ZLog.d(listingName.get());
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("address", address.get());
        parameter.put("area", areaStringBuilder.get());
        parameter.put("averagePrice", averagePrice.get());
        parameter.put("buildType", buildType.get());
        parameter.put("carNum", carNum.get());
        parameter.put("commissionRule", commissionRule.get());
        parameter.put("decorationType", decorationType.get());
        parameter.put("developerName", developerName.get());
        parameter.put("handTime", handTime.get());
        parameter.put("imgUrl", bannerStringBuilder.get().toString());
        parameter.put("labelIds", labelIdsStringBuilder.get().toString());
        parameter.put("lat", lat.get());
        parameter.put("license", license.get());
        parameter.put("listingName", listingName.get());
        parameter.put("lon", lon.get());
        parameter.put("lookRule", lookRule.get());
        parameter.put("openTime", openTime.get());
        parameter.put("priceType", priceType.get());
        parameter.put("propertiesType", propertiesType.get());
        parameter.put("propertyCompany", propertyCompany.get());
        parameter.put("propertyMoney", propertyMoney.get());
        parameter.put("propertyYear", propertyYear.get());
        parameter.put("resident", resident.get());
        parameter.put("salesAddress", officeAddress.get());
        parameter.put("type", typeStringBuilder.get().toString());
        parameter.put("typeImgUrl", typeImgUrlStringBuilder.get().toString());
        parameter.put("userId", LocalDataHelper.getInstance().getUserInfo().getUserId());
        parameter.put("volumeRate", volumeRate.get());
        parameter.put("id", houseID.get());
        parameter.put("houseAveragePrice", houseAveragePriceStringBuilder.get());
        parameter.put("housePriceType", housePriceTypeStringBuilder.get());
        parameter.put("friendId", friendIdStringBuilder.get().toString());
        parameter.put("areaType", houseAreaTypeStringBuilder.get().toString());
        parameter.put("noticeId", noticeId.get());
        parameter.put("salesLat", salesLat.get());
        parameter.put("salesLon", salesLon.get());
        parameter.put("labelName", labelNameStringBuilder.get().toString());
        parameter.put("properties", propertiesTypeText.get());
        parameter.put("build", buildType.get());
        parameter.put("decoration", decorationType.get());
        ParameterLogUtil.getInstance().parameterLog(parameter);
        IdeaApi.getApiService()
                .addBuildingInfo(parameter)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity>(this) {
                    @Override
                    public void onSuccess(SuccessEntity response) {
                        if (IsNullUtil.getInstance().isEmpty(houseID.get())) {
                            ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "发布房源成功");
                        } else {
                            ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "修改房源成功");
                        }
                        finish();
                    }

                });
    }

    /**
     * description:上传banner图片
     * author: Andy
     * date: 2019/9/17  20:20
     */
    private List<File> bannerFiles = new ArrayList<>();

    public void uploadBannerImage() {
        ZLog.d(bannerPathList.size());
        if (bannerPathList.size() > 0) {
            bannerFiles.clear();
            for (HouseResourceReleaseBannerBean houseResourceReleaseBannerBean : bannerPathList) {
                if (houseResourceReleaseBannerBean.getImagePath().startsWith("http")) {
                    bannerStringBuilder.get().append(houseResourceReleaseBannerBean.getImagePath()).append(",");
                } else {
                    bannerFiles.add(new File(houseResourceReleaseBannerBean.getImagePath()));
                }
            }
            if (bannerFiles.size() > 0) {
                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                for (File file : bannerFiles) {
                    //这里上传的是多图
                    builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
                }
                List<MultipartBody.Part> parts = builder.build().parts();
                IdeaApi.getApiService()
                        .uploadpic(parts)
                        .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                        .compose(RxUtils.schedulersTransformer())
                        .doOnSubscribe(disposable -> showDialog())
                        .subscribe(new DefaultObserver<UpLoadImageEntity>(this) {
                            @Override
                            public void onSuccess(UpLoadImageEntity response) {
                                for (String datum : response.getData()) {
                                    bannerStringBuilder.get().append(datum).append(",");
                                }
                                uploadTypeImage();
                            }
                        });
            } else {
                uploadTypeImage();
            }
        } else {
            uploadTypeImage();
        }

    }

    /**
     * description:上传户型图片
     * author: Andy
     * date: 2019/9/17  20:20
     */
    private List<File> typeFiles = new ArrayList<>();

    private void uploadTypeImage() {
        if (HouseResourceReleaseSizeData.getInstance().getList().size() > 0) {
            typeFiles.clear();
            for (HouseResourceReleaseSizeBean houseResourceReleaseSizeBean : HouseResourceReleaseSizeData.getInstance().getList()) {
                if (houseResourceReleaseSizeBean.getImagePath().startsWith("http")) {
                    typeImgUrlStringBuilder.get().append(houseResourceReleaseSizeBean.getImagePath()).append(",");
                } else {
                    typeFiles.add(new File(houseResourceReleaseSizeBean.getImagePath()));
                }
            }
            if (typeFiles.size() > 0) {
                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                for (File file : typeFiles) {
                    //这里上传的是多图
                    builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
                }
                List<MultipartBody.Part> parts = builder.build().parts();
                IdeaApi.getApiService()
                        .uploadpic(parts)
                        .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                        .compose(RxUtils.schedulersTransformer())
                        .doOnSubscribe(disposable -> showDialog())
                        .subscribe(new DefaultObserver<UpLoadImageEntity>(this) {
                            @Override
                            public void onSuccess(UpLoadImageEntity response) {
                                for (String datum : response.getData()) {
                                    typeImgUrlStringBuilder.get().append(datum).append(",");
                                }
                                addBuildingInfo();
                            }
                        });
            } else {
                addBuildingInfo();
            }
        } else {
            addBuildingInfo();
        }

    }


    /**
     * description:房源详情查询
     * author: Andy
     * date: 2019/9/22  17:52
     */
    public void buildingInfo() {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("id", houseID.get());
        ParameterLogUtil.getInstance().parameterLog(parameter);
        IdeaApi.getApiService()
                .getBuildingInfoById(parameter)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<SuccessEntity<HouseResourceDetailEntity>>() {
                    @Override
                    public void onSuccess(SuccessEntity<HouseResourceDetailEntity> response) {
                        try {
                            officeAddress.set(response.getContent().getSales_address());
                            averagePrice.set(response.getContent().getAverage_price() + "");
                            switch (response.getContent().getBuild_type()) {
                                case 1:
                                    buildType.set("居住建筑");
                                    break;
                                case 2:
                                    buildType.set("商业建筑");
                                    break;
                                default:
                                    buildType.set("");
                                    break;
                            }
                            carNum.set(response.getContent().getCar_num() + "");
                            switch (response.getContent().getDecoration_type()) {
                                case "1":
                                    decorationType.set("精装");
                                    break;
                                case "2":
                                    decorationType.set("简装");
                                    break;
                                case "3":
                                    decorationType.set("毛坯");
                                    break;
                            }
                            developerName.set(response.getContent().getDeveloper_name());
                            handTime.set(response.getContent().getHand_time());
                            lat.set(response.getContent().getLatitude());
                            license.set(response.getContent().getLicense());
                            listingName.set(response.getContent().getListing_name());
                            lon.set(response.getContent().getLongitude());
                            openTime.set(response.getContent().getOpen_time());
                            switch (response.getContent().getProperties_type()) {
                                case 3:
                                    propertiesTypeText.set("住宅");
                                    break;
                                case 4:
                                    propertiesTypeText.set("别墅");
                                    break;
                                case 5:
                                    propertiesTypeText.set("商办");
                                    break;
                                case 6:
                                    propertiesTypeText.set("商铺");
                                    break;
                                case 7:
                                    propertiesTypeText.set("写字楼");
                                    break;
                                case 8:
                                    propertiesTypeText.set("商住");
                                    break;
                            }
                            propertyCompany.set(response.getContent().getProperty_company());
                            propertyMoney.set(response.getContent().getProperty_money());
                            propertyYear.set(response.getContent().getProperty_year() + "");
                            resident.set(response.getContent().getResident() + "");
                            volumeRate.set(response.getContent().getVolume_rate());
                            for (HouseResourceDetailEntity.ImgsBean img : response.getContent().getImgs()) {
                                bannerPathList.add(new HouseResourceReleaseBannerBean(img.getImg_url(), ""));
                            }
                            for (HouseResourceDetailEntity.FriendBean friendBean : response.getContent().getFriend()) {
                                linkManList.add(new SelectFriendBean(friendBean.getHead_url(), friendBean.getPhone(), friendBean.getReal_name(), friendBean.getId(), true));
                            }
                            lookRule.set(response.getContent().getNotice().getLook_rule());
                            commissionRule.set(response.getContent().getNotice().getCommission_rule());
                            noticeId.set(response.getContent().getBroker_notice_id());
                            for (HouseResourceDetailEntity.TypeBean typeBean : response.getContent().getType()) {
                                HouseResourceReleaseSizeBean sizeBean = new HouseResourceReleaseSizeBean();
                                sizeBean.setPrice(typeBean.getAverage_price() + "");
                                sizeBean.setSize(typeBean.getArea());
                                sizeBean.setRoomNum(typeBean.getType().substring(0, 1));
                                sizeBean.setHallNum(typeBean.getType().substring(2, 3));
                                sizeBean.setCookNum(typeBean.getType().substring(4, 5));
                                sizeBean.setToiletNum(typeBean.getType().substring(6, 7));
                                if (typeBean.getPrice_type() == 1) {
                                    sizeBean.setHouseTypePriceEnum(HouseTypePriceEnum.Square);
                                } else if (typeBean.getPrice_type() == 2) {
                                    sizeBean.setHouseTypePriceEnum(HouseTypePriceEnum.ASuitOf);
                                } else {
                                    sizeBean.setHouseTypePriceEnum(HouseTypePriceEnum.Undetermined);
                                }
                                if (typeBean.getArea_type() == 1) {
                                    sizeBean.setHouseTypeSizeEnum(HouseTypeSizeEnum.BuildingSurface);
                                } else {
                                    sizeBean.setHouseTypeSizeEnum(HouseTypeSizeEnum.Comprising);
                                }
                                sizeBean.setImagePath(typeBean.getImg_url());
                                HouseResourceReleaseSizeData.getInstance().getList().add(sizeBean);
                            }

                            for (String label : response.getContent().getLabels()) {
                                labelList.add(label);
                            }
                            address.set(response.getContent().getAddress());
                            adapterObservableField.get().notifyDataSetChanged();
                        } catch (Exception e) {

                        }

                    }
                });
    }

    /**
     * description: 打开图片选择器
     * author: Andy
     * date: 2019/9/23  21:01
     */
    public void selectImage(Context context, int maxSelectNum) {
        new RxPermissions((FragmentActivity) context)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(new io.reactivex.observers.DefaultObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            openGallery(context, maxSelectNum);
                        } else {
                            ToastUtil.errorToast(context, GET_PERMISSIONS_FAILED, false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.errorToast(context, GET_PERMISSIONS_FAILED, false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * description: 打开相册
     * author: Andy
     * date: 2019/9/12 0012 9:46
     */
    private void openGallery(Context context, int maxSelectNum) {
        PictureSelector.create((Activity) context)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(maxSelectNum)// 最大图片选择数量 int
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
    public void onDestroy() {
        super.onDestroy();
        HouseResourceReleaseSizeData.getInstance().getList().clear();
    }
}
