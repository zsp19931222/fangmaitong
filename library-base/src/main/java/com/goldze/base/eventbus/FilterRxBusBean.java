package com.goldze.base.eventbus;

/**
 * description:
 * author:created by Andy on 2019/9/24 0024 15:34
 * email:zsp872126510@gmail.com
 */
public class FilterRxBusBean {
  private String decoration;
  private String specialLabel;
  private String propertyType;
  private String areaMin;
  private String areaMax;
  private String openType;

    public FilterRxBusBean(String decoration, String specialLabel, String propertyType, String areaMin, String areaMax, String openType) {
        this.decoration = decoration;
        this.specialLabel = specialLabel;
        this.propertyType = propertyType;
        this.areaMin = areaMin;
        this.areaMax = areaMax;
        this.openType = openType;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }

    public String getDecoration() {
        return decoration;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }

    public String getSpecialLabel() {
        return specialLabel;
    }

    public void setSpecialLabel(String specialLabel) {
        this.specialLabel = specialLabel;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getAreaMin() {
        return areaMin;
    }

    public void setAreaMin(String areaMin) {
        this.areaMin = areaMin;
    }

    public String getAreaMax() {
        return areaMax;
    }

    public void setAreaMax(String areaMax) {
        this.areaMax = areaMax;
    }
}
