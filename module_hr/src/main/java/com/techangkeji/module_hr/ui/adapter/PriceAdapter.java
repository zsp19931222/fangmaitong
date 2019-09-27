package com.techangkeji.module_hr.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.eventbus.PriceRxBusBean;
import com.goldze.base.listener.PopupSelectListener;
import com.techangkeji.module_hr.R;

import java.util.List;

import me.goldze.mvvmhabit.bus.RxBus;

public class PriceAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public PriceAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_ia, item);
        PriceRxBusBean priceRxBusBean;
        switch (item) {
            case "不限":
                priceRxBusBean = new PriceRxBusBean("", "");
                break;
            case "100万以下":
                priceRxBusBean = new PriceRxBusBean("0", "100");
                break;
            case "100-200万":
                priceRxBusBean = new PriceRxBusBean("100", "200");
                break;
            case "200-300万":
                priceRxBusBean = new PriceRxBusBean("200", "300");
                break;
            case "300-400万":
                priceRxBusBean = new PriceRxBusBean("300", "400");
                break;
            case "400-500万":
                priceRxBusBean = new PriceRxBusBean("400", "500");
                break;
            case "面议":
                priceRxBusBean = new PriceRxBusBean("0", "0");
                break;
            case "10k以下":
                priceRxBusBean = new PriceRxBusBean("0", "10000");
                break;
            case "10k-15k":
                priceRxBusBean = new PriceRxBusBean("10000", "15000");
                break;
            case "15k-20k":
                priceRxBusBean = new PriceRxBusBean("15000", "20000");
                break;
            case "20k-25k":
                priceRxBusBean = new PriceRxBusBean("20000", "25000");
                break;
            case "25k-30k":
                priceRxBusBean = new PriceRxBusBean("25000", "30000");
                break;
            case "30k以上":
                priceRxBusBean = new PriceRxBusBean("30000", "0");
                break;
            default:
                priceRxBusBean = new PriceRxBusBean("500", "");
                break;

        }
        helper.itemView.setOnClickListener(view -> {
            popupSelectListener.select(helper.getAdapterPosition());
        });
    }

    private PopupSelectListener popupSelectListener;

    public void setPopupSelectListener(PopupSelectListener popupSelectListener) {
        this.popupSelectListener = popupSelectListener;
    }
}