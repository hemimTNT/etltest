package cn.hemim.common;

/**
 * @author Hemim
 */
public enum KpiType {
    /**
     * stats_user表
     */
    STATS_USER("new_install_user"),
    /**
     * browser表
     */
    STATS_DEVICE_BROWSER("stats_device_browser");

    public String kpiName;

    KpiType(String kpiName){
        this.kpiName = kpiName;
    }

    public static KpiType valueOfKpiType(String kpiName){
        for (KpiType kpiType : values()){
            if (kpiType.kpiName.equals(kpiName)){
                return kpiType;
            }
        }
        return null;
    }
}
