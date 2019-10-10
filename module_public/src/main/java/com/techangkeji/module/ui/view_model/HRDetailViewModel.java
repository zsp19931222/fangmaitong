package com.techangkeji.module.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import com.goldze.base.utils.ParameterLogUtil;
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseBannerBean;
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseSizeBean;
import com.techangkeji.model_mine.ui.bean.SelectFriendBean;
import com.techangkeji.model_mine.ui.m_enum.HouseTypePriceEnum;
import com.techangkeji.model_mine.ui.m_enum.HouseTypeSizeEnum;
import com.techangkeji.module.ui.adapter.CommentStateAdapter;
import com.techangkeji.module.ui.adapter.HRDCommentAdapter;
import com.techangkeji.module.ui.adapter.HRDRecommendAdapter;
import com.techangkeji.module.ui.adapter.HRDStateAdapter;
import com.techangkeji.module.ui.adapter.HRDetailAdapter;

import java.util.HashMap;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.CommentListBody;
import me.goldze.mvvmhabit.http.net.body.TcReviewsListBody;
import me.goldze.mvvmhabit.http.net.entity.HouseResourceDetailEntity;
import me.goldze.mvvmhabit.http.net.entity.RecommendBuildingEntity;
import me.goldze.mvvmhabit.http.net.entity.ReviewListEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.information.CommentListEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ZLog;

public class HRDetailViewModel extends BaseViewModel {
    public ObservableField<RecyclerView> recyclerView = new ObservableField<>();
    public ObservableField<HRDetailAdapter> adapterObservableField = new ObservableField<>();
    public ObservableField<HRDRecommendAdapter> recommendAdapter = new ObservableField<>();//推荐房源
    public ObservableField<HRDStateAdapter> stateAdapter = new ObservableField<>();//楼盘动态

    public ObservableList<HouseResourceReleaseBannerBean> bannerPathList = new ObservableArrayList<>();
    public ObservableList<HouseResourceReleaseSizeBean> sizeList = new ObservableArrayList<>();//楼盘户型图
    public ObservableList<String> labelList = new ObservableArrayList<>();
    public ObservableList<SelectFriendBean> linkManList = new ObservableArrayList<>();//联系人数据
    public ObservableList<RecommendBuildingEntity.DataBean> recommendBuildingList = new ObservableArrayList<>();//推荐房源
    public ObservableList<CommentListEntity.DataBean> stateList = new ObservableArrayList<>();//楼盘动态


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
    public ObservableField<String> propertyCompany = new ObservableField<>("");
    public ObservableField<String> propertyMoney = new ObservableField<>("");
    public ObservableField<String> propertyYear = new ObservableField<>("");
    public ObservableField<String> resident = new ObservableField<>("");
    public ObservableField<String> volumeRate = new ObservableField<>("");
    public ObservableField<String> noticeId = new ObservableField<>("");//规则ID修改时候用
    public ObservableField<String> baiduImageUrl = new ObservableField<>("");//百度地图静态图

    public int id;

    public HRDetailViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description:房源详情查询
     * author: Andy
     * date: 2019/9/22  17:52
     */
    public void buildingInfo(int id) {
        this.id = id;
        isCollection();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("id", id + "");
        ParameterLogUtil.getInstance().parameterLog(parameter);
        IdeaApi.getApiService()
                .getBuildingInfoById(parameter)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity<HouseResourceDetailEntity>>(this) {
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
                                    propertiesType.set("住宅");
                                    break;
                                case 4:
                                    propertiesType.set("别墅");
                                    break;
                                case 5:
                                    propertiesType.set("商办");
                                    break;
                                case 6:
                                    propertiesType.set("商铺");
                                    break;
                                case 7:
                                    propertiesType.set("写字楼");
                                    break;
                                case 8:
                                    propertiesType.set("商住");
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
                                sizeList.add(sizeBean);
                            }

                            for (String label : response.getContent().getLabels()) {
                                labelList.add(label);
                            }
                            address.set(response.getContent().getAddress());
                            getBaiduMapImage();
                            adapterObservableField.get().notifyDataSetChanged();
                        } catch (Exception e) {

                        }

                    }
                });


    }

    /**
     * description: 推荐房源
     * author: Andy
     * date: 2019/9/26  21:13
     */
    public void recommendBuilding(int id) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("id", id + "");
        IdeaApi.getApiService()
                .recommendBuilding(parameter)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<RecommendBuildingEntity>() {
                    @Override
                    public void onSuccess(RecommendBuildingEntity response) {
                        recommendBuildingList.addAll(response.getData());
                        recommendAdapter.get().notifyDataSetChanged();
                    }

                });
    }

    /**
     * description: 获取百度静态图
     * author: Andy
     * date: 2019/9/27 0027 10:42
     */
    public void getBaiduMapImage() {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("ak", "iPfMMzK53sLHUGkVifrTSyp68FWGuBEr");
        parameter.put("mcode", "49:69:DA:F4:52:70:A6:9C:FC:E9:1E:3A:1A:CE:0B:82:9A:A6:E5:2B;com.techangkeji.fangmaitong");
        parameter.put("center", lon.get() + "," + lat.get());
        parameter.put("zoom", "18");
        parameter.put("markers", lon.get() + "," + lat.get());
        parameter.put("&markerStyles", "m," + listingName.get() + ",0xFF0000");
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : parameter.entrySet()) {
            ZLog.d(entry.getKey() + ": " + entry.getValue() + "\n");
            stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        String image = "http://api.map.baidu.com/staticimage/v2?" + stringBuilder.toString();
        ZLog.d(image);
        baiduImageUrl.set(image);
    }

    /**
     * description: 获取动态列表
     * author: Andy
     * date: 2019/9/25  23:10
     */
    private void getCommentList() {
        stateList.clear();
        CommentListBody commentListBody = new CommentListBody(1, 4, 2, (long) id);
        IdeaApi.getApiService()
                .getCommentList(commentListBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<CommentListEntity>() {
                    @Override
                    public void onSuccess(CommentListEntity response) {
                        stateList.addAll(response.getData());
                        stateAdapter.get().notifyDataSetChanged();
                    }

                });
    }

    public boolean isCollect = false;

    /**
     * description: 是否收藏改房源
     * author: Andy
     * date: 2019/9/28  15:07
     */
    private void isCollection() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", LocalDataHelper.getInstance().getUserInfo().getUserId());
        map.put("listingId", id);
        IdeaApi.getApiService().isCollection(map)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<SuccessEntity<Integer>>() {
                    @Override
                    public void onSuccess(SuccessEntity<Integer> response) {
                        isCollect = response.getContent() != 0;
                        ZLog.d(isCollect);
                        stateAdapter.get().notifyDataSetChanged();
                    }
                });
    }

    /**
     * description:收藏
     * author: Andy
     * date: 2019/9/28  15:01
     */
    public void addCollection() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", LocalDataHelper.getInstance().getUserInfo().getUserId());
        map.put("listingId", id);
        IdeaApi.getApiService().addCollection(map)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<SuccessEntity>() {
                    @Override
                    public void onSuccess(SuccessEntity response) {
                        isCollect = true;
                        adapterObservableField.get().notifyDataSetChanged();
                    }
                });
    }

    /**
     * description: 取消收藏
     * author: Andy
     * date: 2019/9/28  15:09
     */
    public void deleteCollection() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", LocalDataHelper.getInstance().getUserInfo().getUserId());
        map.put("listingId", id);
        IdeaApi.getApiService().deleteCollection(map)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<SuccessEntity>() {
                    @Override
                    public void onSuccess(SuccessEntity response) {
                        isCollect = false;
                        adapterObservableField.get().notifyDataSetChanged();
                    }
                });
    }
    /**
     * description: 获取点评列表
     * author: Andy
     * date: 2019/10/10 0010 10:25
     */
    public int pageNum=1;
    public TcReviewsListBody tcReviewsListBody=new TcReviewsListBody();
    public ObservableList<ReviewListEntity.DataBean> dataBeans=new ObservableArrayList<>();
    public ObservableField<HRDCommentAdapter> hrdCommentAdapterObservableField=new ObservableField<>();
    public void tcReviewsList(){
        if (pageNum==1){
            dataBeans.clear();
        }
        tcReviewsListBody.setMax(4);
        tcReviewsListBody.setPage(pageNum);
        tcReviewsListBody.setEntityId(id);
        IdeaApi.getApiService()
                .tcReviewsList(tcReviewsListBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<ReviewListEntity>() {
                    @Override
                    public void onSuccess(ReviewListEntity response) {
                        dataBeans.addAll(response.getData());
                        hrdCommentAdapterObservableField.get().notifyDataSetChanged();
                    }

                });
    }
    @Override
    public void onResume() {
        super.onResume();
        getCommentList();
        tcReviewsList();
    }
}
