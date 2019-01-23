package cn.hemim.bean;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LogWritable implements Writable {
    private String country;
    private String province;
    private String city;
    private String browsername;
    private String browserversion;
    private String osname;
    private String osversion;
    private String s_time;
    private String en;
    private String ver;
    private String pl;
    private String sdk;
    private String b_rst;
    private String b_iev;
    private String u_ud;
    private String l;
    private String u_mid;
    private String u_sd;
    private String c_time;
    private String p_url;
    private String p_ref;
    private String tt;
    private String ca;
    private String ac;
    private String kv;
    private String du;
    private String oid;
    private String on;
    private String cua;
    private String cut;
    private String pt;
    private String ip;

    @Override
    public void write(DataOutput dataOutput) throws IOException {

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.country = in.readUTF();
        this.province = in.readUTF();
        this.city = in.readUTF();
        this.browsername = in.readUTF();
        this.browserversion = in.readUTF();
        this.osname = in.readUTF();
        this.osversion = in.readUTF();
        this.s_time = in.readUTF();
        this.en = in.readUTF();
        this.ver = in.readUTF();
        this.pl = in.readUTF();
        this.sdk = in.readUTF();
        this.b_rst = in.readUTF();
        this.b_iev = in.readUTF();
        this.u_ud = in.readUTF();
        this.l = in.readUTF();
        this.u_mid = in.readUTF();
        this.u_sd = in.readUTF();
        this.c_time = in.readUTF();
        this.p_url = in.readUTF();
        this.p_ref = in.readUTF();
        this.tt = in.readUTF();
        this.ca = in.readUTF();
        this.ac = in.readUTF();
        this.kv = in.readUTF();
        this.du = in.readUTF();
        this.oid = in.readUTF();
        this.on = in.readUTF();
        this.cua = in.readUTF();
        this.cut = in.readUTF();
        this.pt = in.readUTF();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBrowsername() {
        return browsername;
    }

    public void setBrowsername(String browsername) {
        this.browsername = browsername;
    }

    public String getBrowserversion() {
        return browserversion;
    }

    public void setBrowserversion(String browserversion) {
        this.browserversion = browserversion;
    }

    public String getOsname() {
        return osname;
    }

    public void setOsname(String osname) {
        this.osname = osname;
    }

    public String getOsversion() {
        return osversion;
    }

    public void setOsversion(String osversion) {
        this.osversion = osversion;
    }

    public String getS_time() {
        return s_time;
    }

    public void setS_time(String s_time) {
        this.s_time = s_time;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getPl() {
        return pl;
    }

    public void setPl(String pl) {
        this.pl = pl;
    }

    public String getSdk() {
        return sdk;
    }

    public void setSdk(String sdk) {
        this.sdk = sdk;
    }

    public String getB_rst() {
        return b_rst;
    }

    public void setB_rst(String b_rst) {
        this.b_rst = b_rst;
    }

    public String getB_iev() {
        return b_iev;
    }

    public void setB_iev(String b_iev) {
        this.b_iev = b_iev;
    }

    public String getU_ud() {
        return u_ud;
    }

    public void setU_ud(String u_ud) {
        this.u_ud = u_ud;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getU_mid() {
        return u_mid;
    }

    public void setU_mid(String u_mid) {
        this.u_mid = u_mid;
    }

    public String getU_sd() {
        return u_sd;
    }

    public void setU_sd(String u_sd) {
        this.u_sd = u_sd;
    }

    public String getC_time() {
        return c_time;
    }

    public void setC_time(String c_time) {
        this.c_time = c_time;
    }

    public String getP_url() {
        return p_url;
    }

    public void setP_url(String p_url) {
        this.p_url = p_url;
    }

    public String getP_ref() {
        return p_ref;
    }

    public void setP_ref(String p_ref) {
        this.p_ref = p_ref;
    }

    public String getTt() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getKv() {
        return kv;
    }

    public void setKv(String kv) {
        this.kv = kv;
    }

    public String getDu() {
        return du;
    }

    public void setDu(String du) {
        this.du = du;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
    }

    public String getCua() {
        return cua;
    }

    public void setCua(String cua) {
        this.cua = cua;
    }

    public String getCut() {
        return cut;
    }

    public void setCut(String cut) {
        this.cut = cut;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }



    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(country).append("\u0001").append(province).append("\u0001").append(city).append("\u0001").append(browsername).append("\u0001").append(browserversion).append("\u0001").append(osname).append("\u0001").append(osversion).append("\u0001").append(s_time).append("\u0001").append(en).append("\u0001").append(ver).append("\u0001").append(pl).append("\u0001").append(sdk).append("\u0001").append(b_rst).append("\u0001").append(b_iev).append("\u0001").append(u_ud).append("\u0001").append(l).append("\u0001").append(u_mid).append("\u0001").append(u_sd).append("\u0001").append(c_time).append("\u0001").append(p_url).append("\u0001").append(p_ref).append("\u0001").append(tt).append("\u0001").append(ca).append("\u0001").append(ac).append("\u0001").append(kv).append("\u0001").append(du).append("\u0001").append(oid).append("\u0001").append(on).append("\u0001").append(cua).append("\u0001").append(cut).append("\u0001").append(pt).append("\u0001").append(ip).append("\u0001").toString();
    }
}
