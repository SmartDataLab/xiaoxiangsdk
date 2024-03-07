package com.xiangzhi.xxsdk.request;

public class GetProxyRequest {
    /**
     * 提取数量
     */
    private Integer cnt;
    /**
     * 响应体格式，json或text，默认值：json
     */
    private String wt;
    /**
     * 代理协议，http或s5（s5即socks5）,默认值：http
     */
    private String method;
    /**
     * 是否自动释放旧的IP，仅长效代理IP有效
     */
    private Boolean releaseAuto;
    /**
     * 待释放的代理IP，ip:port，多个以半角逗号分隔，仅长效代理IP有效
     */
    private String releaseProxy;

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public String getWt() {
        return wt;
    }

    public void setWt(String wt) {
        this.wt = wt;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Boolean getReleaseAuto() {
        return releaseAuto;
    }

    public void setReleaseAuto(Boolean releaseAuto) {
        this.releaseAuto = releaseAuto;
    }

    public String getReleaseProxy() {
        return releaseProxy;
    }

    public void setReleaseProxy(String releaseProxy) {
        this.releaseProxy = releaseProxy;
    }
}
