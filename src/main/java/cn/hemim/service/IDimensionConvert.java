package cn.hemim.service;

import cn.hemim.parse.model.dim.base.BaseDimension;

/**
 * Description: xxx</br>
 * Author: Hemim
 * Date: 2019-02-12
 */
public interface IDimensionConvert {
    /**
     * 该方法根据传入的dimension来获取表中的id
     * @param dimension
     * @return 返回相应维度的id
     */
    int getDimensionByValue(BaseDimension dimension) throws Exception;
}
