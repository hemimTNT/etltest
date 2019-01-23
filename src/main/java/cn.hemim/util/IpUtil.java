package cn.hemim.util;

import org.apache.commons.lang.StringUtils;

public class IpUtil {

    static RegionInfo parseRegionFromIp(String str){
        RegionInfo info = new RegionInfo();
        if (StringUtils.isNotEmpty(str)){
            IPSeeker ipSeeker = IPSeeker.getInstance();
            String country = IPSeeker.getInstance().getCountry(str);
            if (StringUtils.isNotEmpty(country)){
                {
                    if(country.equals("局域网")){
                        info.setCountry("中国");
                        info.setProvince("广东");
                        info.setCity("深圳");
                    } else{
                        //定位country中是否有省
                        int index = country.indexOf("省");
                        if(index > 0){
                            info.setCountry("中国");
                            info.setProvince(country.substring(0,index+1));
                            int index2 = country.indexOf("市");
                            if(index2 > 0){
                                info.setCity(country.substring(index+1,index2+1));
                            }
                        } else {
                            //就是不是省，有直辖市和自治区和港澳台
                            String flag = country.substring(0,2);

                            switch (flag) {
                                case "北京":
                                case "天津":
                                case "重庆":
                                case "上海":
                                    country = country.substring(3);
                                    info.setCountry("中国");
                                    info.setProvince(flag+"市");
                                    index = country.indexOf("区");
                                    if(index > 0){
                                        char ch = country.charAt(index-1);
                                        if(ch != '小' && ch != '军' && ch != '校'){
                                            info.setCity(country.substring(0,index+1));
                                        }
                                    }

                                    index = country.indexOf("县");
                                    if(index > 0){
                                        info.setCity(country.substring(0,index+1));
                                    }

                                    break;
                                case "内蒙":
                                    info.setCountry("中国");
                                    info.setProvince(flag+"古");
                                    index = country.indexOf("市");
                                    country = country.substring(3);
                                    if(index > 0){
                                        info.setCity(country.substring(0,index+1));
                                    }
                                    break;

                                case "新疆":
                                case "西藏":
                                case "宁夏":
                                case "广西":
                                    info.setCountry("中国");
                                    info.setProvince(flag);
                                    country = country.substring(2);
                                    index = country.indexOf("市");
                                    if(index > 0){
                                        info.setCity(country.substring(0,index+1));
                                    }
                                    break;

                                case "香港":
                                case "澳门":
                                    info.setCountry("中国");
                                    info.setProvince(flag+"特别行政区");

                                case "台湾":
                                    info.setCountry("中国");
                                    info.setProvince(flag+"省");

                                default:
                                    info.setCountry(country);
                                    break;
                            }
                        }

                    }
                }
                return info;
            }
        }
        return null;
    }

    public static class RegionInfo{
        private final String DEFAULT = "unknown";
        private String country;
        private String province;
        private String city;

        RegionInfo(){
            country = DEFAULT;
            province = DEFAULT;
            city = DEFAULT;
        }

        public String getCountry() {
            return country;
        }

        void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        void setCity(String city) {
            this.city = city;
        }

        @Override
        public String toString() {
            return "RegionInfo{" +
                    "country='" + country + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
    }
}
