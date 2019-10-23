package com.techangkeji.model_mine.ui.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.goldze.base.bean.FeaturedLabelBean;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.ui.activity.AddSizeActivity;
import com.techangkeji.model_mine.ui.activity.SelectFriendActivity;
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseBean;
import com.techangkeji.model_mine.ui.data.HouseResourceReleaseSizeData;
import com.techangkeji.model_mine.ui.popup.ArchitectTypePopupwindow;
import com.techangkeji.model_mine.ui.popup.DecorationStatePopupwindow;
import com.techangkeji.model_mine.ui.popup.HRSDLabelPopupwindow;
import com.techangkeji.model_mine.ui.popup.PropertyTypePopupwindow;
import com.techangkeji.model_mine.ui.popup.TimePickerPopupwindow;
import com.techangkeji.model_mine.ui.viewModel.HouseResourceReleaseViewModel;

import java.util.List;

import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.ZLog;
import me.goldze.mvvmhabit.view.shape.RadiusTextView;

public class HouseResourceReleaseAdapter extends BaseQuickAdapter<HouseResourceReleaseBean, BaseViewHolder> {
    private Context context;
    private HouseResourceReleaseViewModel viewModel;

    public HouseResourceReleaseAdapter(@Nullable List<HouseResourceReleaseBean> data, Context context, HouseResourceReleaseViewModel viewModel) {
        super(data);
        this.context = context;
        this.viewModel = viewModel;
        setMultiTypeDelegate(new MultiTypeDelegate<HouseResourceReleaseBean>() {
            @Override
            protected int getItemType(HouseResourceReleaseBean homeAdapterBean) {
                //根据你的实体类来判断布局类型
                return homeAdapterBean.getType();
            }
        });
        getMultiTypeDelegate()
                .registerItemType(HouseResourceReleaseBean.Banner, R.layout.view_hsr_banner)
                .registerItemType(HouseResourceReleaseBean.Detail, R.layout.view_hsr_detail)
                .registerItemType(HouseResourceReleaseBean.Size, R.layout.view_hsr_size)
                .registerItemType(HouseResourceReleaseBean.Information, R.layout.view_hsr_information)
                .registerItemType(HouseResourceReleaseBean.Linkman, R.layout.view_hsr_linkman)
        ;
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseResourceReleaseBean item) {
        switch (helper.getItemViewType()) {
            case HouseResourceReleaseBean.Banner:
                initBanner(helper);
                break;
            case HouseResourceReleaseBean.Detail:
                initDetail(helper);
                break;
            case HouseResourceReleaseBean.Size:
                initSize(helper);
                break;
            case HouseResourceReleaseBean.Information:
                initInformation(helper);
                break;
            case HouseResourceReleaseBean.Linkman:
                initLinkman(helper);
                break;
        }
    }

    /**
     * description:banner模块
     * author: Andy
     * date: 2019/9/12 0012 9:18
     */
    private HouseResourceReleaseBannerAdapter bannerAdapter;
    private TextView tv_vhb_add;

    private void initBanner(BaseViewHolder helper) {
        tv_vhb_add = helper.getView(R.id.tv_vhb_add);
        tv_vhb_add.setOnClickListener(view -> viewModel.selectImage(helper.itemView.getContext(), 9));
        if (IsNullUtil.getInstance().isEmpty(bannerAdapter)) {
            RecyclerView bannerRecyclerView = helper.getView(R.id.rv_vhb);
            bannerAdapter = new HouseResourceReleaseBannerAdapter(R.layout.item_hsr_banner, viewModel.bannerPathList);
            viewModel.bannerAdapter.set(bannerAdapter);
            bannerRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            bannerRecyclerView.setAdapter(bannerAdapter);
        } else {
            ZLog.d(viewModel.bannerPathList);
            bannerAdapter.notifyDataSetChanged();
        }
    }

    /**
     * description:信息模块
     * author: Andy
     * date: 2019/9/12 0012 10:33
     */
    private EditText et_vhf_name;
    private EditText et_vhf_price;
    private TextView tv_vhf_address;
    private EditText et_vhf_dn;
    private EditText et_vhf_commission;
    private EditText et_vhf_look;
    private CheckBox cb_vhf_square;
    private CheckBox cb_vhf_suit;

    private void initInformation(BaseViewHolder helper) {
        et_vhf_name = helper.getView(R.id.et_vhf_name);
        et_vhf_price = helper.getView(R.id.et_vhf_price);
        tv_vhf_address = helper.getView(R.id.tv_vhf_address);
        et_vhf_dn = helper.getView(R.id.et_vhf_dn);
        et_vhf_commission = helper.getView(R.id.et_vhf_commission);
        et_vhf_look = helper.getView(R.id.et_vhf_look);
        cb_vhf_square = helper.getView(R.id.cb_vhf_square);
        cb_vhf_suit = helper.getView(R.id.cb_vhf_suit);


        et_vhf_name.setText(viewModel.listingName.get());
        et_vhf_price.setText(viewModel.averagePrice.get());
        tv_vhf_address.setText(viewModel.address.get());
        et_vhf_dn.setText(viewModel.developerName.get());
        et_vhf_commission.setText(viewModel.commissionRule.get());
        et_vhf_look.setText(viewModel.lookRule.get());

        viewModel.addressTextView.set(tv_vhf_address);

        tv_vhf_address.setOnClickListener(v -> ARouter.getInstance().build(ARouterPath.Public.MoreAddressActivity).withInt("addressType", 0).withString("lat", viewModel.lat.get()).withString("lon", viewModel.lon.get()).navigation());
        cb_vhf_square.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                cb_vhf_suit.setChecked(false);
            }
        });
        cb_vhf_suit.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                cb_vhf_square.setChecked(false);
            }
        });
    }

    /**
     * description: 联系人模块
     * author: Andy
     * date: 2019/9/12 0012 10:51
     */
    private RecyclerView rv_vhl;
    private TextView rv_vhl_add;

    private void initLinkman(BaseViewHolder helper) {
        rv_vhl = helper.getView(R.id.rv_vhl);

        rv_vhl_add = helper.getView(R.id.rv_vhl_add);
        rv_vhl_add.setOnClickListener(v -> {
            viewModel.startActivity(SelectFriendActivity.class);
        });
        LinkManAdapter linkManAdapter = new LinkManAdapter(R.layout.item_mine_linkman, viewModel.linkManList);
        viewModel.linkManAdapter.set(linkManAdapter);
        linkManAdapter.setPopupSelectListener(position -> {
            viewModel.linkManList.remove(position);
            viewModel.linkManAdapter.get().notifyDataSetChanged();
        });
        rv_vhl.setLayoutManager(new LinearLayoutManager(context));
        rv_vhl.setAdapter(linkManAdapter);
    }

    /**
     * description: 楼盘名称模块
     * author: Andy
     * date: 2019/9/12 0012 10:53
     */
    private RecyclerView rv_vhd;
    private TextView tv_vhd_open_time;
    private TextView tv_vhd_delivery_time;
    private TextView tv_vhd_address;
    private TextView tv_vhd_property_type;
    private TextView tv_vhd_architecture_type;
    private TextView tv_vhd_decoration_state;
    private TextView tv_vhd_add;
    private ImageView iv_vhd_open_time;
    private ImageView iv_vhd_delivery_time;
    private EditText et_vhd_households;
    private EditText et_vhd_plot_ratio;
    private EditText et_vhd_stall_num;
    private EditText et_vhd_age_limit;
    private EditText et_vhd_licence;
    private EditText et_vhd_property_company;
    private EditText et_vhd_property_fee;
    private HSRDAdapter hsrdAdapter;

    private void initDetail(BaseViewHolder helper) {
        rv_vhd = helper.getView(R.id.rv_vhd);
        tv_vhd_open_time = helper.getView(R.id.tv_vhd_open_time);
        tv_vhd_delivery_time = helper.getView(R.id.tv_vhd_delivery_time);
        tv_vhd_address = helper.getView(R.id.tv_vhd_address);
        tv_vhd_property_type = helper.getView(R.id.tv_vhd_property_type);
        tv_vhd_architecture_type = helper.getView(R.id.tv_vhd_architecture_type);
        tv_vhd_decoration_state = helper.getView(R.id.tv_vhd_decoration_state);
        iv_vhd_open_time = helper.getView(R.id.iv_vhd_open_time);
        iv_vhd_delivery_time = helper.getView(R.id.iv_vhd_delivery_time);
        tv_vhd_add = helper.getView(R.id.tv_vhd_add);
        et_vhd_households = helper.getView(R.id.et_vhd_households);
        et_vhd_plot_ratio = helper.getView(R.id.et_vhd_plot_ratio);
        et_vhd_stall_num = helper.getView(R.id.et_vhd_stall_num);
        et_vhd_age_limit = helper.getView(R.id.et_vhd_age_limit);
        et_vhd_licence = helper.getView(R.id.et_vhd_licence);
        et_vhd_property_company = helper.getView(R.id.et_vhd_property_company);
        et_vhd_property_fee = helper.getView(R.id.et_vhd_property_fee);

        tv_vhd_open_time.setText(viewModel.openTime.get());
        tv_vhd_delivery_time.setText(viewModel.handTime.get());
        tv_vhd_address.setText(viewModel.officeAddress.get());
        tv_vhd_property_type.setText(viewModel.propertiesTypeText.get());
        tv_vhd_architecture_type.setText(viewModel.buildType.get());
        tv_vhd_decoration_state.setText(viewModel.decorationType.get());
        et_vhd_households.setText(viewModel.resident.get());
        et_vhd_plot_ratio.setText(viewModel.volumeRate.get());
        et_vhd_stall_num.setText(viewModel.carNum.get());
        et_vhd_age_limit.setText(viewModel.propertyYear.get());
        et_vhd_licence.setText(viewModel.license.get());
        et_vhd_property_company.setText(viewModel.propertyCompany.get());
        et_vhd_property_fee.setText(viewModel.propertyMoney.get());

        viewModel.officeAddressTextView.set(tv_vhd_address);

        tv_vhd_address.setOnClickListener(v -> ARouter.getInstance().build(ARouterPath.Public.MoreAddressActivity).withInt("addressType", 1).withString("lat", viewModel.salesLat.get()).withString("lon", viewModel.salesLon.get()).navigation());

        tv_vhd_add.setOnClickListener(v -> new HRSDLabelPopupwindow(helper.itemView.getContext(), viewModel).showPopupWindow());

        //添加标签
        if (IsNullUtil.getInstance().isEmpty(hsrdAdapter)) {
            hsrdAdapter = new HSRDAdapter(R.layout.item_hsrd, viewModel.labelList, viewModel);
            viewModel.hsrdAdapter.set(hsrdAdapter);
            LinearLayoutManager gridLayoutManager = new LinearLayoutManager(context);
            gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rv_vhd.setLayoutManager(gridLayoutManager);
            rv_vhd.setAdapter(hsrdAdapter);
        } else {
            hsrdAdapter.notifyDataSetChanged();
        }
        //添加开盘时间
        iv_vhd_open_time.setOnClickListener(view -> {
            TimePickerPopupwindow timePickerPopupwindow = new TimePickerPopupwindow(context);
            timePickerPopupwindow.setOnSelectTimeListener(time -> tv_vhd_open_time.setText(time));
            timePickerPopupwindow.showPopupWindow();
        });
        //添加交房时间
        iv_vhd_delivery_time.setOnClickListener(view -> {
            TimePickerPopupwindow timePickerPopupwindow = new TimePickerPopupwindow(context);
            timePickerPopupwindow.setOnSelectTimeListener(time -> tv_vhd_delivery_time.setText(time));
            timePickerPopupwindow.showPopupWindow();
        });
        //住宅选择
        tv_vhd_property_type.setOnClickListener(view -> {
            PropertyTypePopupwindow propertyTypePopupwindow = new PropertyTypePopupwindow(context, viewModel.buildLabeList);
            propertyTypePopupwindow.setSelectListener(position -> {
                for (FeaturedLabelBean featuredLabelBean : viewModel.buildLabeList) {
                    featuredLabelBean.setSelect(false);
                }
                viewModel.buildLabeList.get(position).setSelect(true);
                viewModel.propertiesType.set(viewModel.buildLabeList.get(position).getId()+"");
                viewModel.propertiesTypeText.set(viewModel.buildLabeList.get(position).getLabel_name());
                tv_vhd_property_type.setText(viewModel.propertiesTypeText.get());
            });
            propertyTypePopupwindow.showPopupWindow(tv_vhd_property_type);
        });
        //建筑类别
        tv_vhd_architecture_type.setOnClickListener(view -> {
            ArchitectTypePopupwindow architectTypePopupwindow = new ArchitectTypePopupwindow(context, tv_vhd_architecture_type.getText().toString());
            architectTypePopupwindow.setSelectListener(s -> tv_vhd_architecture_type.setText(s));
            architectTypePopupwindow.showPopupWindow(tv_vhd_architecture_type);
        });
        //装修状态
        tv_vhd_decoration_state.setOnClickListener(view -> {
            DecorationStatePopupwindow decorationStatePopupwindow = new DecorationStatePopupwindow(context, tv_vhd_decoration_state.getText().toString());
            decorationStatePopupwindow.setSelectListener(s -> tv_vhd_decoration_state.setText(s));
            decorationStatePopupwindow.showPopupWindow(tv_vhd_decoration_state);
        });
    }

    private TextView tv_vhs_add;
    private RecyclerView rv_vhs;
    private RadiusTextView tv_vhs_release;
    private HouseResourceReleaseSizeAdapter houseResourceReleaseSizeAdapter;

    private void initSize(BaseViewHolder helper) {
        tv_vhs_add = helper.getView(R.id.tv_vhs_add);
        rv_vhs = helper.getView(R.id.rv_vhs);
        tv_vhs_release = helper.getView(R.id.tv_vhs_release);
        tv_vhs_add.setOnClickListener(view -> viewModel.startActivity(AddSizeActivity.class));
        if (IsNullUtil.getInstance().isEmpty(houseResourceReleaseSizeAdapter)) {
            houseResourceReleaseSizeAdapter = new HouseResourceReleaseSizeAdapter(R.layout.item_hsr_size, HouseResourceReleaseSizeData.getInstance().getList(), viewModel);
            viewModel.houseResourceReleaseSizeAdapter.set(houseResourceReleaseSizeAdapter);
            rv_vhs.setLayoutManager(new LinearLayoutManager(context));
            rv_vhs.setAdapter(houseResourceReleaseSizeAdapter);
        } else {
            houseResourceReleaseSizeAdapter.notifyDataSetChanged();
        }
        tv_vhs_release.setOnClickListener(v -> {
            viewModel.averagePrice.set(et_vhf_price.getText().toString());
            String buildType;
            switch (tv_vhd_architecture_type.getText().toString()) {
                case "居住建筑":
                    buildType = "1";
                    break;
                case "商业建筑":
                    buildType = "2";
                    break;
                default:
                    buildType = "";
                    break;
            }
            viewModel.buildType.set(buildType);
            viewModel.carNum.set(et_vhd_stall_num.getText().toString());
            viewModel.commissionRule.set(et_vhf_commission.getText().toString());
            String decorationType;
            switch (tv_vhd_decoration_state.getText().toString()) {
                case "精装":
                    decorationType = "1";
                    break;
                case "简装":
                    decorationType = "2";
                    break;
                case "毛坯":
                    decorationType = "3";
                    break;
                default:
                    decorationType = "";
                    break;
            }
            viewModel.decorationType.set(decorationType);
            viewModel.developerName.set(et_vhf_dn.getText().toString());
            viewModel.handTime.set(tv_vhd_delivery_time.getText().toString());
            viewModel.license.set(et_vhd_licence.getText().toString());
            viewModel.listingName.set(et_vhf_name.getText().toString());
            viewModel.lookRule.set(et_vhf_look.getText().toString());
            viewModel.openTime.set(tv_vhd_open_time.getText().toString());
            if (cb_vhf_square.isChecked()) {
                viewModel.priceType.set("1");
            }
            if (cb_vhf_suit.isChecked()) {
                viewModel.priceType.set("2");
            }
//            String propertiesType = "";
//            switch (tv_vhd_property_type.getText().toString()) {
//                case "住宅":
//                    propertiesType = "3";
//                    break;
//                case "别墅":
//                    propertiesType = "4";
//                    break;
//                case "商办":
//                    propertiesType = "5";
//                    break;
//                case "商铺":
//                    propertiesType = "6";
//                    break;
//                case "写字楼":
//                    propertiesType = "7";
//                    break;
//                case "商住":
//                    propertiesType = "8";
//                    break;
//            }
            viewModel.propertyCompany.set(et_vhd_property_company.getText().toString());
            viewModel.propertyMoney.set(et_vhd_property_fee.getText().toString());
            viewModel.propertyYear.set(et_vhd_age_limit.getText().toString());
            viewModel.resident.set(et_vhd_households.getText().toString());
            viewModel.volumeRate.set(et_vhd_plot_ratio.getText().toString());
            viewModel.bannerStringBuilder.get().setLength(0);
            viewModel.typeImgUrlStringBuilder.get().setLength(0);
            viewModel.uploadBannerImage();
        });
    }
}
