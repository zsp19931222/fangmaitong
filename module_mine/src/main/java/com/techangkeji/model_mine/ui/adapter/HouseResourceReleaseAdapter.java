package com.techangkeji.model_mine.ui.adapter;

import android.Manifest;
import android.content.Context;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.goldze.base.utils.PermissionsUtils;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseBean;
import com.techangkeji.model_mine.ui.popup.HRSDLabelPopupwindow;
import com.techangkeji.model_mine.ui.popup.TimePickerPopupwindow;
import com.techangkeji.model_mine.ui.viewModel.HouseResourceReleaseViewModel;

import java.util.List;

import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.ZLog;

import static com.goldze.base.constant.RxBusMessageEventConstants.OPEN_GALLERY;

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
                break;
            case HouseResourceReleaseBean.Information:
                initInformation(helper);
                break;
            case HouseResourceReleaseBean.Linkman:
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
        tv_vhb_add.setOnClickListener(view -> PermissionsUtils.getInstance().getPermissionsWithFragmentActivity((FragmentActivity) context, OPEN_GALLERY, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE));
        if (IsNullUtil.getInstance().isEmpty(bannerAdapter)) {
            RecyclerView bannerRecyclerView = helper.getView(R.id.rv_vhb);
            bannerAdapter = new HouseResourceReleaseBannerAdapter(R.layout.item_hsr_banner, viewModel.bannerPathList);
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

    private void initLinkman() {

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
        et_vhd_households = helper.getView(R.id.et_vhd_households);
        et_vhd_plot_ratio = helper.getView(R.id.et_vhd_plot_ratio);
        et_vhd_stall_num = helper.getView(R.id.et_vhd_stall_num);
        et_vhd_licence = helper.getView(R.id.et_vhd_licence);
        et_vhd_property_company = helper.getView(R.id.et_vhd_property_company);
        et_vhd_property_fee = helper.getView(R.id.et_vhd_property_fee);
        //添加标签
        if (IsNullUtil.getInstance().isEmpty(hsrdAdapter)) {
            viewModel.labelList.add("+");
            HSRDAdapter hsrdAdapter = new HSRDAdapter(R.layout.item_hsrd, viewModel.labelList, viewModel);
            LinearLayoutManager gridLayoutManager = new LinearLayoutManager(context);
            gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rv_vhd.setLayoutManager(gridLayoutManager);
            rv_vhd.setAdapter(hsrdAdapter);
        } else {
            hsrdAdapter.notifyDataSetChanged();
        }
        //添加开盘时间
        iv_vhd_open_time.setOnClickListener(view -> {
            TimePickerPopupwindow timePickerPopupwindow=new TimePickerPopupwindow(context);
            timePickerPopupwindow.setOnSelectTimeListener(time -> tv_vhd_open_time.setText(time));
            timePickerPopupwindow.showPopupWindow();
        });
        //添加交房时间
        iv_vhd_delivery_time.setOnClickListener(view -> {
            TimePickerPopupwindow timePickerPopupwindow=new TimePickerPopupwindow(context);
            timePickerPopupwindow.setOnSelectTimeListener(time -> tv_vhd_delivery_time.setText(time));
            timePickerPopupwindow.showPopupWindow();
        });
    }
}
