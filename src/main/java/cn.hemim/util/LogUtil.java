package cn.hemim.util;

import cn.hemim.common.LogConstant;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 解析每一行日志，将日志解析出来存储到map中
 */
public class LogUtil {
    static Map<String, String> info = new HashMap<String, String>();
    static final Logger logger = Logger.getLogger(LogUtil.class);
    /**
     *
     * @param log
     * @return {}
     */
    public static Map<String, String> parseLog(String log){
        // 每次使用前，清空历史数据
        info.clear();
        String[] fields = log.split(LogConstant.LOG_DELIMITER);
        if (fields != null && fields.length == 4){
            info.put(LogConstant.LOG_IP, fields[0]);
            info.put(LogConstant.LOG_S_TIME, fields[1].replace(".", ""));
            //解析参数列表
            parseParams(info, fields[3]);
            //解析user-agent
            parseUserAgent(info);

            // 解析ip
            parseIp(info);
            return info;
        }

        return null;
    }

    /**
     * 解析参数列表
     * @param info
     */
    private static void parseParams(Map<String, String> info, String str) {
        if(info.isEmpty())
            return;
        // 先解码
        try {
            str = URLDecoder.decode(str, "UTF-8");
            int index = str.indexOf("?");
            if (index > 0){
                String[] params = str.substring(index + 1).split("&");
                for (String param : params){
                    String[] kv = param.split("=");
                    info.put(kv[0], kv[1]);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析user-agent
     * @param info
     */
    private static void parseUserAgent(Map<String, String> info) {
//        if (info.get(LogConstant.LOG_B_IEV))
        UserAgent userAgent = UserAgent.parseUserAgentString(info.get(LogConstant.LOG_B_IEV));
        if (userAgent != null){
            info.put(LogConstant.LOG_BROWSERNAME, userAgent.getBrowserVersion().toString());
            info.put(LogConstant.LOG_BROWSERVERSION, userAgent.getBrowser().getName());
            info.put(LogConstant.LOG_OSNAME, userAgent.getOperatingSystem().getGroup().toString());
            info.put(LogConstant.LOG_OSVERSION, userAgent.getOperatingSystem().getName());
        }
    }

    /**
     *
     * @param info
     * @description 解析ip
     */
    private static void parseIp(Map<String, String> info){
        IpUtil.RegionInfo regionInfo = IpUtil.parseRegionFromIp(info.get(LogConstant.LOG_IP));
        if (regionInfo != null){
            info.put(LogConstant.LOG_COUNTRY, regionInfo.getCountry());
            info.put(LogConstant.LOG_PROVINCE, regionInfo.getProvince());
            info.put(LogConstant.LOG_CITY, regionInfo.getCity());
        }
    }
}
