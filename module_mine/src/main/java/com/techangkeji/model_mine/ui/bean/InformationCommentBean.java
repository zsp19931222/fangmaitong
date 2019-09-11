package com.techangkeji.model_mine.ui.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

public class InformationCommentBean extends SectionEntity<String> {

    public InformationCommentBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public InformationCommentBean(String s) {
        super(s);
    }
}
