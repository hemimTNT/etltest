package cn.hemim.parse.mr.nu;

import cn.hemim.common.KpiType;
import cn.hemim.common.UserConstants;
import cn.hemim.parse.model.dim.StatsUserDimension;
import cn.hemim.parse.model.value.map.MapOutputWritable;
import cn.hemim.parse.model.value.reduce.ReduceOutputWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class NewUserReducer extends Reducer<StatsUserDimension, MapOutputWritable, StatsUserDimension, ReduceOutputWritable> {
    private ReduceOutputWritable v;
    private MapWritable mapWritable;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        v = new ReduceOutputWritable();
        mapWritable = new MapWritable();
    }

    @Override
    protected void reduce(StatsUserDimension key, Iterable<MapOutputWritable> values, Context context) throws IOException, InterruptedException {
        mapWritable.clear();
        int n = 0;
        KpiType kpiType = KpiType.valueOfKpiType(key.getStatsCommonDimension().getKpiDimension().getKpiName());
        for (MapOutputWritable outputWritable : values){
            n++;
        }
        // 设置v
        mapWritable.put(new IntWritable(UserConstants.NEW_USER), new IntWritable(n));
        v.setKpiType(kpiType);
        v.setValueMap(mapWritable);
        context.write(key, v);
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        super.cleanup(context);
    }
}
