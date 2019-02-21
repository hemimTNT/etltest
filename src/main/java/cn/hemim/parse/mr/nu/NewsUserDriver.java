package cn.hemim.parse.mr.nu;

import cn.hemim.parse.model.dim.StatsUserDimension;
import cn.hemim.parse.model.value.map.MapOutputWritable;
import cn.hemim.parse.model.value.reduce.ReduceOutputWritable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * @description: xxx</ br>
 * @author: Hemim
 * @date: 2019-02-15
 */
public class NewsUserDriver{

    private static final Logger logger = Logger.getLogger(NewsUserDriver.class);
    private static Configuration conf;

    public static void main(String[] args) {

//        if (args == null || args.length < 2){
//            logger.warn("缺少inputpath outputpath参数");
//            return ;
//        }

        Configuration conf = new Configuration();
        Job job = null;
        try {
            conf.addResource("output-mapping.xml");
            conf.addResource("output-writter.xml");

            job = Job.getInstance(conf, "NewUser");
            job.setJarByClass(NewsUserDriver.class);

            job.setMapperClass(NewUserMapper.class);
            job.setMapOutputKeyClass(StatsUserDimension.class);
            job.setMapOutputValueClass(MapOutputWritable.class);

            job.setReducerClass(NewUserReducer.class);
            job.setOutputKeyClass(StatsUserDimension.class);
            job.setOutputValueClass(ReduceOutputWritable.class);
            job.setOutputFormatClass(IOutputFormat.class);

            // 设置输入路径
            FileInputFormat.setInputPaths(job, new Path("/ods/month=01/day=21/part-m-00000"));

            System.exit(job.waitForCompletion(true) ? 0 : 1);
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            logger.warn("新增用户出错");
            e.printStackTrace();
        }

    }


}
