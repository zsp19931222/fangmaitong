package com.techangkeji.model_message.ui.bean;

/**
 * description:
 * author:created by Andy on 2019/9/16 21:38
 * email:zsp872126510@gmail.com
 */
public class ReleaseInformationBean {
    private boolean canAdd;
    private Object image;

    public ReleaseInformationBean(boolean canAdd, Object image) {
        this.canAdd = canAdd;
        this.image = image;
    }

    public boolean isCanAdd() {
        return canAdd;
    }

    public void setCanAdd(boolean canAdd) {
        this.canAdd = canAdd;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }
}
