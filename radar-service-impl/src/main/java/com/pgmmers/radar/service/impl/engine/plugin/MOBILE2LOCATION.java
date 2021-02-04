package com.pgmmers.radar.service.impl.engine.plugin;

import com.pgmmers.radar.service.data.MobileInfoService;
import com.pgmmers.radar.service.engine.PluginServiceV2;
import com.pgmmers.radar.service.engine.vo.Location;
import com.pgmmers.radar.service.impl.util.BeanUtils;
import com.pgmmers.radar.vo.data.MobileInfoVO;
import com.pgmmers.radar.vo.model.PreItemVO;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * author: wangcheng Date: 2020/5/19 Time: 上午11:46 Description:
 */
public class MOBILE2LOCATION implements PluginServiceV2 {

    @Override
    public Integer key() {
        return 5;
    }

    @Override
    public String desc() {
        return "手机号码归属地";
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getMeta() {
        return "[{\"column\":\"country\", \"title\":\"国家\", \"type\":\"STRING\"},{\"column\":\"province\", \"title\":\"省份\", \"type\":\"STRING\"},{\"column\":\"city\", \"title\":\"城市\", \"type\":\"STRING\"}]";
    }

    @Override
    public Object handle(PreItemVO item, Map<String, Object> jsonInfo, String[] sourceField) {
        String mobile = jsonInfo.get(sourceField[0]).toString();
        if (!StringUtils.isEmpty(mobile) && mobile.length() == 11) {
            mobile = mobile.substring(0, 7);
        }
        MobileInfoVO vo = BeanUtils.getBean(MobileInfoService.class).getMobileInfoByMobile(mobile);
        Location location = new Location();
        if (vo != null) {
            location.setProvince(vo.getProvince());
            location.setCity(vo.getCity());
            location.setCountry("中国");
        }
        return location;
    }
}
