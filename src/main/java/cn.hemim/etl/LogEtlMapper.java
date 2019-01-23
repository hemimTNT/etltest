package cn.hemim.etl;

import cn.hemim.bean.LogWritable;
import cn.hemim.util.LogUtil;
import org.apache.avro.JsonProperties;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;

public class LogEtlMapper extends Mapper<LongWritable, Text, LogWritable, NullWritable> {
    private final Logger logger = Logger.getLogger(LogEtlMapper.class);
    private LogWritable k;
    private int inputRecords, filterRecords, outputRecords;

    @Override
    protected void map(LongWritable key, Text value, Context context){
        inputRecords++;
        if (value == null){
            filterRecords++;
            return;
        }

        String line = value.toString();
        // 调用解析日志的方法
        Map<String, String> info = LogUtil.parseLog(line);
        k = new LogWritable();
        this.k.setEn(info.get("en"));
        this.k.setVer(info.get("ver"));
        this.k.setPl(info.get("pl"));
        this.k.setSdk(info.get("sdk"));
        this.k.setB_rst(info.get("b_rst"));
        this.k.setB_iev(info.get("b_iev"));
        this.k.setU_ud(info.get("u_ud"));
        this.k.setL(info.get("l"));
        this.k.setU_mid(info.get("u_mid"));
        this.k.setU_sd(info.get("u_sd"));
        this.k.setC_time(info.get("c_time"));
        this.k.setP_url(info.get("p_url"));
        this.k.setP_ref(info.get("p_ref"));
        this.k.setTt(info.get("tt"));
        this.k.setCa(info.get("ca"));
        this.k.setAc(info.get("ac"));
        this.k.setKv(info.get("kv"));
        this.k.setDu(info.get("du"));
        this.k.setOid(info.get("oid"));
        this.k.setOn(info.get("on"));
        this.k.setCua(info.get("cua"));
        this.k.setCut(info.get("cut"));
        this.k.setPt(info.get("pt"));
        this.k.setCountry(info.get("country"));
        this.k.setProvince(info.get("province"));
        this.k.setCity(info.get("city"));
        this.k.setBrowsername(info.get("browsername"));
        this.k.setBrowserversion(info.get("browserversion"));
        this.k.setOsname(info.get("osname"));
        this.k.setOsversion(info.get("osversion"));
        try {
            context.write(k, NullWritable.get());
            outputRecords++;
        } catch (IOException | InterruptedException e) {
            filterRecords++;
            logger.warn("mapper异常");
            e.printStackTrace();
        }
    }
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        logger.info("=============inputRecords:"+inputRecords+" filterRecords:"+filterRecords+" outputRecords:"+outputRecords);
    }

}
