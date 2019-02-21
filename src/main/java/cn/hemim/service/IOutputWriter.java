package cn.hemim.service;

import cn.hemim.parse.model.dim.StatsUserDimension;
import cn.hemim.parse.model.value.reduce.ReduceOutputWritable;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Description: xxx</br>
 * Author: Hemim
 * Date: 2019-02-13
 */
public interface IOutputWriter {
    public void writer(PreparedStatement ps, StatsUserDimension key, ReduceOutputWritable value) throws Exception;
}
