package cn.hemim.hive;

import cn.hemim.parse.model.dim.PlatformDimension;
import cn.hemim.service.impl.IDimensionConvertImpl;
import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * @description: </br>
 * @author: Hemim
 * @date: 2019-02-19
 */
public class PlatformDimensionUdf extends UDF {

    public int evaluate(String platform){
        int id = -1;
        IDimensionConvertImpl iDimensionConvert = new IDimensionConvertImpl();
        try {
            return iDimensionConvert.getDimensionByValue(new PlatformDimension(platform));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }


}
