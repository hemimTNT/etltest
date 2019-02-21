package cn.hemim.hive;

import cn.hemim.common.DateEnum;
import cn.hemim.parse.model.dim.DateDimension;
import cn.hemim.service.impl.IDimensionConvertImpl;
import cn.hemim.util.TimeUtil;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * @description: </br>
 * @author: Hemim
 * @date: 2019-02-19
 */
public class DateDimensionUdf extends UDF {
    public IntWritable evaluate(Text date){
        int id = -1;
        long timeStamp = TimeUtil.parseString2Long(date.toString());
        try {
            id = new IDimensionConvertImpl().getDimensionByValue(DateDimension.buildDate(timeStamp, DateEnum.DAY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new IntWritable(id);
    }

    public static void main(String[] args) {
//        System.out.println(new DateDimensionUdf().evaluate(new Text("2018-01-15")).get());
    }
}
