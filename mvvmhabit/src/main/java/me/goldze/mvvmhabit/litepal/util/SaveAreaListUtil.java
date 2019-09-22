package me.goldze.mvvmhabit.litepal.util;

import org.litepal.LitePal;

import me.goldze.mvvmhabit.http.net.entity.AreaListEntity;
import me.goldze.mvvmhabit.litepal.CityLitePal;
import me.goldze.mvvmhabit.litepal.DistrictLitePal;
import me.goldze.mvvmhabit.litepal.ProvinceLitePal;

/**
 * description:
 * author:created by Andy on 2019/9/22 17:25
 * email:zsp872126510@gmail.com
 */
public class SaveAreaListUtil {
    private static final SaveAreaListUtil ourInstance = new SaveAreaListUtil();

    public static SaveAreaListUtil getInstance() {
        return ourInstance;
    }

    private SaveAreaListUtil() {
    }
    public void save(AreaListEntity response){
        LitePal.deleteAll(ProvinceLitePal.class);
        LitePal.deleteAll(CityLitePal.class);
        LitePal.deleteAll(DistrictLitePal.class);

        for (AreaListEntity.DataBean datum : response.getData()) {
            ProvinceLitePal provinceLitePal = new ProvinceLitePal();
            provinceLitePal.setAreaName(datum.getAreaName());
            provinceLitePal.setProvinceid(datum.getId());
            provinceLitePal.save();

            for (AreaListEntity.DataBean.ChildBeanX childBeanX : datum.getChild()) {
                CityLitePal cityLitePal = new CityLitePal();
                cityLitePal.setCityId(childBeanX.getId());
                cityLitePal.setCityName(childBeanX.getAreaName());
                cityLitePal.setProvinceid(childBeanX.getParentId());
                cityLitePal.setProvinceName(datum.getAreaName());
                cityLitePal.save();

                for (AreaListEntity.DataBean.ChildBeanX.ChildBean childBean : childBeanX.getChild()) {
                    DistrictLitePal districtLitePal = new DistrictLitePal();
                    districtLitePal.setAreaName(childBean.getAreaName());
                    districtLitePal.setDistrictid(childBean.getId());
                    districtLitePal.setCityName(childBeanX.getAreaName());
                    districtLitePal.setCityId(childBean.getParentId());
                    districtLitePal.save();
                }
            }
        }
    }
}
