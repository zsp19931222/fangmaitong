package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.goldze.base.utils.ParameterLogUtil;
import com.techangkeji.model_mine.ui.adapter.HouseResourceReleaseAdapter;
import com.techangkeji.model_mine.ui.bean.FeaturedLabelBean;
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseBannerBean;
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseSizeBean;
import com.techangkeji.model_mine.ui.bean.SelectFriendBean;
import com.techangkeji.model_mine.ui.data.HouseResourceReleaseSizeData;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.UpdateBody;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.http.net.entity.FeaturedLabelEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtil;
import me.goldze.mvvmhabit.utils.ZLog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * description:
 * author:created by Andy on 2019/9/12 0012 09:25
 * email:zsp872126510@gmail.com
 */
public class HouseResourceReleaseViewModel extends BaseViewModel {
    public ObservableList<HouseResourceReleaseBannerBean> bannerPathList = new ObservableArrayList<>();
    public ObservableList<FeaturedLabelBean> featuredLabelList = new ObservableArrayList<>();
    public ObservableList<FeaturedLabelBean> featuredLabelListConfirm = new ObservableArrayList<>();
    public ObservableList<String> labelList = new ObservableArrayList<>();
    public ObservableList<SelectFriendBean> linkManList = new ObservableArrayList<>();//联系人数据
    public ObservableField<HouseResourceReleaseAdapter> adapterObservableField = new ObservableField<>();
    public ObservableField<String> houseID = new ObservableField<>("");//房源ID


    public ObservableField<Integer> bannerPosition = new ObservableField<>(0);

    public ObservableField<Integer> typePosition = new ObservableField<>(0);

    public ObservableField<StringBuilder> bannerStringBuilder = new ObservableField<>(new StringBuilder());

    public ObservableField<StringBuilder> typeImgUrlStringBuilder = new ObservableField<>(new StringBuilder());
    public ObservableField<StringBuilder> areaStringBuilder = new ObservableField<>(new StringBuilder());
    public ObservableField<StringBuilder> typeStringBuilder = new ObservableField<>(new StringBuilder());
    public ObservableField<StringBuilder> houseAveragePriceStringBuilder = new ObservableField<>(new StringBuilder());
    public ObservableField<StringBuilder> housePriceTypeStringBuilder = new ObservableField<>(new StringBuilder());

    public ObservableField<StringBuilder> labelIdsStringBuilder = new ObservableField<>(new StringBuilder());

    public ObservableField<String> address = new ObservableField<>("重庆");
    public ObservableField<String> officeAddress = new ObservableField<>("重庆市沙坪坝区天星桥");
    public ObservableField<String> averagePrice = new ObservableField<>("50000");
    public ObservableField<String> buildType = new ObservableField<>("居住建筑");
    public ObservableField<String> carNum = new ObservableField<>("300");
    public ObservableField<String> commissionRule = new ObservableField<>("佣金规则");
    public ObservableField<String> decorationType = new ObservableField<>("装修状态");
    public ObservableField<String> developerName = new ObservableField<>("恒大地产");
    public ObservableField<String> handTime = new ObservableField<>("2018-09-02");
    public ObservableField<String> lat = new ObservableField<>("39.6114659222");
    public ObservableField<String> license = new ObservableField<>("ASDKTQNCZ54DW2");
    public ObservableField<String> listingName = new ObservableField<>("地勘大厦");
    public ObservableField<String> lon = new ObservableField<>("116.7253479027");
    public ObservableField<String> lookRule = new ObservableField<>("带看规则");
    public ObservableField<String> openTime = new ObservableField<>("2018-09-02");
    public ObservableField<String> priceType = new ObservableField<>("1");
    public ObservableField<String> propertiesType = new ObservableField<>("住宅");
    public ObservableField<String> propertyCompany = new ObservableField<>("恒大物业");
    public ObservableField<String> propertyMoney = new ObservableField<>("32.5");
    public ObservableField<String> propertyYear = new ObservableField<>("70");
    public ObservableField<String> resident = new ObservableField<>("36");
    public ObservableField<String> volumeRate = new ObservableField<>("54.2");


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
                            featuredLabelList.add(new FeaturedLabelBean(datum.getId(),datum.getLabel_name(),false));
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
        for (Map.Entry<String, Object> entry : parameter.entrySet()) {
            ZLog.d(entry.getKey() + ": " + entry.getValue() + "\n");
        }
        IdeaApi.getApiService()
                .addBuildingInfo(parameter)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity>(this) {
                    @Override
                    public void onSuccess(SuccessEntity response) {
                        ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "发布房源成功");
                        finish();
                        HouseResourceReleaseSizeData.getInstance().getList().clear();
                    }

                });
    }

    /**
     * description:上传banner图片
     * author: Andy
     * date: 2019/9/17  20:20
     */
    public void uploadBannerImage(int p) {
        if (bannerPathList.size() > 0) {
            bannerPosition.set(p);
            File file = new File(bannerPathList.get(p).getImagePath());
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);//表单类型
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);//表单类型
            builder.addFormDataPart("file", file.getName(), body); //添加图片数据，body创建的请求体
            List<MultipartBody.Part> parts = builder.build().parts();
            IdeaApi.getApiService()
                    .uploadpic(parts)
                    .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                    .compose(RxUtils.schedulersTransformer())
                    .subscribe(new DefaultObserver<SuccessEntity<String>>() {
                        @Override
                        public void onSuccess(SuccessEntity<String> response) {
                            bannerStringBuilder.get().append(response.getContent()).append(",");
                            ZLog.d(bannerStringBuilder.get().toString());
                            int position = bannerPosition.get();
                            position++;
                            if (position < bannerPathList.size()) {//角标小于图片集合上传图片
                                uploadBannerImage(position);
                            } else {//图片上传完成
                                bannerPosition.set(0);
                                uploadTypeImage(typePosition.get());
                            }
                        }
                    });
        } else {
            uploadTypeImage(typePosition.get());
        }

    }

    /**
     * description:上传户型图片
     * author: Andy
     * date: 2019/9/17  20:20
     */
    private void uploadTypeImage(int p) {
        if (HouseResourceReleaseSizeData.getInstance().getList().size() > 0) {
            typePosition.set(p);
            File file = new File(HouseResourceReleaseSizeData.getInstance().getList().get(p).getImagePath());
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);//表单类型
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);//表单类型
            builder.addFormDataPart("file", file.getName(), body); //添加图片数据，body创建的请求体
            List<MultipartBody.Part> parts = builder.build().parts();
            IdeaApi.getApiService()
                    .uploadpic(parts)
                    .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                    .compose(RxUtils.schedulersTransformer())
                    .subscribe(new DefaultObserver<SuccessEntity<String>>() {
                        @Override
                        public void onSuccess(SuccessEntity<String> response) {
                            typeImgUrlStringBuilder.get().append(response.getContent()).append(",");
                            int position = typePosition.get();
                            position++;
                            if (position < HouseResourceReleaseSizeData.getInstance().getList().size()) {//角标小于图片集合上传图片
                                uploadTypeImage(position);
                            } else {//图片上传完成
                                typePosition.set(0);
                                addBuildingInfo();
                            }
                        }
                    });
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
                .addBuildingInfo(parameter)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver() {
                    @Override
                    public void onSuccess(BaseEntity response) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }
}
