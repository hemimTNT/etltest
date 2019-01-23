package cn.hemim.etl;

import cn.hemim.bean.LogWritable;
import cn.hemim.util.HttpClient;
import com.alibaba.fastjson.JSONObject;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.net.URLDecoder;

public class EtlMapper extends Mapper<LongWritable, Text, LogWritable, NullWritable> {
    private final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php?ip=";
    private final String DELIMITER = "\\^A";

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 解码
        String line = URLDecoder.decode(value.toString(), "UTF-8");
        StringBuffer sb = new StringBuffer();
        // 按照'|'来分割字符串
        String[] keys = line.split("\\^A");
        // 第一个是ip，利用taobao的api来解析
        JSONObject obj = JSONObject.parseObject(HttpClient.doGet(IP_URL + keys[0]));
        // 如果结果正常，则接收
        if ("0".equals(obj.getString("code"))) {
            JSONObject dataObj = obj.getJSONObject("data");
            // 国家
            sb.append(dataObj.getString("country") + DELIMITER);
            // 省份
            sb.append(dataObj.getString("region") + DELIMITER);
            // 城市
            sb.append(dataObj.getString("city") + DELIMITER);
        } else {
            // 占位
            sb.append(DELIMITER);
            sb.append(DELIMITER);
            sb.append(DELIMITER);
        }
            /*
            处理后面的参数
             */
            String[] params = keys[3].split("&");
            // user-agent
            UserAgent userAgent = UserAgent.parseUserAgentString(handleKv(params[11]));
            //  浏览器名称
            sb.append(userAgent.getBrowser().getName() + DELIMITER);
            // 浏览器版本
            sb.append(userAgent.getBrowserVersion() + DELIMITER);
            // 系统名称
            sb.append(userAgent.getOperatingSystem().getName() + DELIMITER);
            // TODO 系统版本
            // s_time，去掉.
            sb.append(handleKv(params[9]).replaceAll(".", "") + DELIMITER);
            // 拼接原字符串
            sb.append(line);
    }

    private String handleKv(String kv) {
        return kv.split("=")[1];
    }
}
