package cn.hemim.parse.mr.nu;

import cn.hemim.util.JdbcUtil;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.db.DBRecordReader;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 重写outputformat类，代替默认的TextOutputFormat，参考DBOutputFormat类
 */
public class IOutputFormat extends OutputFormat {

//    DBOutputFormat

    /**
     * 返回RecordWriter对象，是主要要实现的类
     * @param taskAttemptContext
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public RecordWriter getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        return new IRecordWriter(JdbcUtil.getConnection(), taskAttemptContext.getConfiguration());
    }

    @Override
    public void checkOutputSpecs(JobContext jobContext) throws IOException, InterruptedException {
        // do nothing
    }

    @Override
    public OutputCommitter getOutputCommitter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        return new FileOutputCommitter(null, taskAttemptContext);
    }

}
