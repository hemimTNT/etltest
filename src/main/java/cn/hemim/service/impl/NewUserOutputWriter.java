package cn.hemim.service.impl;

import cn.hemim.common.UserConstants;
import cn.hemim.parse.model.dim.StatsUserDimension;
import cn.hemim.parse.model.value.reduce.ReduceOutputWritable;
import cn.hemim.service.IDimensionConvert;
import cn.hemim.service.IOutputWriter;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

/**
 * Description: 该类的作用是往数据库插入id和统计值</br>
 * Author: Hemim
 * Date: 2019-02-13
 */
public class NewUserOutputWriter implements IOutputWriter {

    private IDimensionConvertImpl iDimensionConvert = new IDimensionConvertImpl();

    @Override
    public void writer(PreparedStatement ps, StatsUserDimension key, ReduceOutputWritable value) throws Exception {
        int i = 1;
        int newUser = 0;
        MapWritable valueMap = value.getValueMap();
        newUser = ((IntWritable) valueMap.get(new IntWritable(UserConstants.NEW_USER))).get();
        ps.setInt(i++, iDimensionConvert.getDimensionByValue(key.getStatsCommonDimension().getDateDimension()));
        ps.setInt(i++, iDimensionConvert.getDimensionByValue(key.getStatsCommonDimension().getPlatformDimension()));
        ps.setInt(i++, newUser);
        ps.setDate(i++, new java.sql.Date(System.currentTimeMillis()));
        ps.setInt(i++, newUser);
        ps.addBatch();
    }
}
