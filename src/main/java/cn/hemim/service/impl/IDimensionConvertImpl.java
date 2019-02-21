package cn.hemim.service.impl;

import cn.hemim.parse.model.dim.DateDimension;
import cn.hemim.parse.model.dim.PlatformDimension;
import cn.hemim.parse.model.dim.base.BaseDimension;
import cn.hemim.service.IDimensionConvert;
import cn.hemim.util.JdbcUtil;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description: xxx</ br>
 * @author: Hemim
 * @date: 2019-02-12
 */
public class IDimensionConvertImpl implements IDimensionConvert {

    private Map<String, Integer> cache = new LinkedHashMap<>();

    @Override
    public int getDimensionByValue(BaseDimension dimension) throws Exception {
        String[] sqls = null;
        Connection conn = null;
        conn = JdbcUtil.getConnection();
        if (dimension instanceof DateDimension) {
            // 返回DateDimension维度的id
            sqls = buildDateSql();
        } else if (dimension instanceof PlatformDimension) {
            sqls = buildPlatformSqls();
        } else {
            throw new Exception("没写对应的维度id convert");
        }

        return handle(sqls, conn, dimension);
    }

    private String[] buildDateSql() {
        String query = "select id from `dimension_date` where `year` = ? and `season` = ? and `month` = ?" +
                " and `week` = ? and `day` = ? and `calendar` = ? and `type` = ?";
        String insert = "insert into `dimension_date`(`year` ,`season` ,`month` ,`week` ,`day` ,`calendar` ,`type`) " +
                "values(?,?,?,?,?,?,?)";
        return new String[]{query, insert};
    }

    private String[] buildPlatformSqls() {
        String selectSql = "select id from `dimension_platform` where `platform_name` = ?";
        String insertSql = "insert into `dimension_platform`(`platform_name`) values(?)";
        return new String[]{selectSql, insertSql};
    }

    private int handle(String[] sqls, Connection conn, BaseDimension dimension) {
        try {
            PreparedStatement ps = conn.prepareStatement(sqls[0]);
            setArgs(dimension, ps);
            ResultSet rs = ps.executeQuery();
            // 查找是否存在id
            if (rs.next()) {
                return rs.getInt(1);
            }
            ps = conn.prepareStatement(sqls[1], Statement.RETURN_GENERATED_KEYS);
            setArgs(dimension, ps);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 设置ps参数
     *
     * @param dimension
     * @param ps
     * @throws SQLException
     */
    private void setArgs(BaseDimension dimension, PreparedStatement ps) throws SQLException {
        int i = 1;
        if (dimension instanceof DateDimension) {
            DateDimension dateDimension = (DateDimension) dimension;
            ps.setInt(i++, dateDimension.getYear());
            ps.setInt(i++, dateDimension.getSeason());
            ps.setInt(i++, dateDimension.getMonth());
            ps.setInt(i++, dateDimension.getWeek());
            ps.setInt(i++, dateDimension.getDay());
            ps.setDate(i++, new java.sql.Date(dateDimension.getCalendar().getTime()));
            ps.setString(i++, dateDimension.getType());
        } else if (dimension instanceof PlatformDimension) {
            PlatformDimension platformDimension = (PlatformDimension) dimension;
            ps.setString(i++, platformDimension.getPlatformName());
        }
    }
}
