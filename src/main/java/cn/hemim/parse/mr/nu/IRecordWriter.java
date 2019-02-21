package cn.hemim.parse.mr.nu;

import cn.hemim.common.GlobalConstants;
import cn.hemim.parse.model.dim.StatsUserDimension;
import cn.hemim.parse.model.value.reduce.ReduceOutputWritable;
import cn.hemim.service.IOutputWriter;
import cn.hemim.util.JdbcUtil;
import cn.hemim.util.XmlUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IRecordWriter extends RecordWriter<StatsUserDimension, ReduceOutputWritable> {

    private final Logger logger = Logger.getLogger(IRecordWriter.class);

    private Connection conn;

    private Configuration conf;

    /**
     * 保存不同kpi的ps
     */
    private Map<String, PreparedStatement> map;

    /**
     * 保存不同kpi的执行数
     */
    private Map<String, Integer> batch;

    private PreparedStatement ps = null;

    public IRecordWriter(Connection conn, Configuration conf) {
        this.conn = conn;
        this.conf = conf;
        map = new HashMap<>();
        batch = new HashMap<>();
    }


    /**
     * 该方法将结果输出到MySQL
     * 梳理业务逻辑：
     * reduce结束后，结果来到这里，StatsUserDimension是key，里面储存的是有关User的维度数据，ReduceOutputWritable作为
     * value，里面存储的是uuid
     *
     * @param key
     * @param value
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void write(StatsUserDimension key, ReduceOutputWritable value) throws IOException, InterruptedException {
        // 先做stats_user
        // 获取kpi的名字
        String kpiName = value.getKpiType().kpiName;
        // 根据kpiName来获取ps

        int count = 1;
        try {
            if ((ps = map.get(kpiName)) == null) {

                // 从conf中获取语句创建ps
                String psStr = XmlUtil.readSql(kpiName);
                ps = conn.prepareStatement(psStr);
                // 把ps加入到map中，供下次使用
                map.put(kpiName, ps);
                batch.put(kpiName, count);

            } else {
                ps = map.get(kpiName);
                count = batch.get(kpiName);
                count++;
            }
            batch.put(kpiName, count);

            String name = XmlUtil.readClassName(GlobalConstants.WRITER_PREFIX + kpiName);
            IOutputWriter iOutputWriter = (IOutputWriter) Class.forName(name).newInstance();
            iOutputWriter.writer(ps, key, value);

            /**
             * 达到阈值，执行语句
             */
            if (count % GlobalConstants.BATCH_THRESHOLD == 0) {
                ps.executeBatch();
                conn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("执行语句异常");
        }
    }

    @Override
    public void close(TaskAttemptContext context){
        // 执行完map里面的语句
        try {
            for (String key : map.keySet()){
                map.get(key).executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.warn("-------------执行异常");

        } finally {
            // 关闭
            try {
                for (String key : map.keySet()){
                    map.get(key).close();
                }
            } catch (SQLException e) {
                logger.warn("关闭ps异常");
                e.printStackTrace();
            }
            JdbcUtil.close(conn, null, null);
            map.clear();
        }

    }
}
