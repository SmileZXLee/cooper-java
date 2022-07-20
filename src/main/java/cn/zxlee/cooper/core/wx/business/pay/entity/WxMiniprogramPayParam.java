package cn.zxlee.cooper.core.wx.business.pay.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Map;

/**
 * @program: cooper
 * @description:
 * @author: zxlee
 * @create: 2022-07-18 14:23
 **/
public class WxMiniprogramPayParam{
    /**
     * (设置无效，自动从application.properties中获取)应用ID，由微信生成的应用ID，全局唯一。请求基础下单接口时请注意APPID的应用属性，例如公众号场景下，需使用应用属性为公众号的服务号APPID
     */
    @JSONField(name="appid")
    private String appid;

    /**
     * (设置无效，自动从application.properties中获取)直连商户号，直连商户的商户号，由微信支付生成并下发
     */
    @JSONField(name="mchid")
    private String mchid;

    /**
     * (必填)商品描述
     */
    @JSONField(name="description")
    private String description;

    /**
     * (必填)商户订单号，商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一
     */
    @JSONField(name="out_trade_no")
    private String outTradeNo;

    /**
     * (选填)交易结束时间，订单失效时间，遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
     */
    @JSONField(name="time_expire")
    private String timeExpire;

    /**
     * (选填)附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用，实际情况下只有支付完成状态才会返回该字段
     */
    @JSONField(name="attach")
    private String attach;

    /**
     * (设置无效，自动从application.properties中获取)通知地址，异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。 公网域名必须为https，如果是走专线接入，使用专线NAT IP或者私有回调域名可使用http
     */
    @JSONField(name="notify_url")
    private String notifyUrl;

    /**
     * (选填)订单优惠标记
     */
    @JSONField(name="goods_tag")
    private String goodsTag;

    /**
     * (必填)订单金额信息，内部参数为{total:(金额，必填，int类型，单位为分),currency:(选填，String类型，默认CNY：人民币，境内商户号仅支持人民币)}
     */
    @JSONField(name="amount")
    private Map<String, String> amount;

    /**
     * (必填)支付者信息，内部参数为{openid:(用户openid，必填，String类型)}
     */
    @JSONField(name="payer")
    private Map<String, String> payer;

    /**
     * (选填)优惠功能，具体参数详见：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_5_1.shtml
     */
    @JSONField(name="detail")
    private Map<String, Object> detail;

    /**
     * (选填)支付场景描述，具体参数详见：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_5_1.shtml
     */
    @JSONField(name="scene_info")
    private Map<String, Object> sceneInfo;

    /**
     * (选填)结算信息，具体参数详见：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_5_1.shtml
     */
    @JSONField(name="settle_info")
    private Map<String, Object> settleInfo;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public Map<String, String> getAmount() {
        return amount;
    }

    public void setAmount(Map<String, String> amount) {
        this.amount = amount;
    }

    public Map<String, String> getPayer() {
        return payer;
    }

    public void setPayer(Map<String, String> payer) {
        this.payer = payer;
    }

    public Map<String, Object> getDetail() {
        return detail;
    }

    public void setDetail(Map<String, Object> detail) {
        this.detail = detail;
    }

    public Map<String, Object> getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(Map<String, Object> sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

    public Map<String, Object> getSettleInfo() {
        return settleInfo;
    }

    public void setSettleInfo(Map<String, Object> settleInfo) {
        this.settleInfo = settleInfo;
    }
}
