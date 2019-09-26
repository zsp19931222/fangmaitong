package com.techangkeji.module.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.techangkeji.module.R;

import java.util.List;

public class HRDDetailVerticalAdapter extends BaseQuickAdapter<HRDDetailVerticalAdapter.HRDDetailVerticalBean, BaseViewHolder> {
    public HRDDetailVerticalAdapter(int layoutResId, @Nullable List<HRDDetailVerticalBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HRDDetailVerticalBean item) {
        helper.setText(R.id.tv_hdv_1, item.getName1());
        helper.setText(R.id.tv_hdv_2, item.getName2());
    }

    public static class HRDDetailVerticalBean {
        private String name1;
        private String name2;

        public HRDDetailVerticalBean(String name1, String name2) {
            this.name1 = name1;
            this.name2 = name2;
        }

        public String getName1() {
            return name1;
        }

        public void setName1(String name1) {
            this.name1 = name1;
        }

        public String getName2() {
            return name2;
        }

        public void setName2(String name2) {
            this.name2 = name2;
        }
    }

}
