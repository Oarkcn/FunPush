package cn.oark.utility.message;

import com.alibaba.fastjson.annotation.JSONField;

public class LogoMsg implements Message{
    @JSONField(name="msgId", ordinal = 1)
    String msgId="";
    @JSONField(name="msgTime", ordinal = 2)
    Long msgTime=0L;
    @JSONField(name="msgTitle", ordinal = 3)
    String msgTitle="";
    @JSONField(name="msgContext", ordinal = 4)
    String msgContext="";  //消息正文与标题 限制长度64+256 多余截取
    @JSONField(name="msgLogo", ordinal = 6)
    String msgLogo="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAAEBUlEQVQ4jV1UPWwcRRh987t7u/bdOfic2E5ClBghsDBNJIgECBANQkg0RNQgCkRPRU+BIlFQUYFoKCJBRZMKOdCkcEIgRYQAEZzLneXz3e6eb3/mB83MHSF81e7MfN+87733Ddl59pnrwsRpPW0Go6NDevLkKTuejEk2ybC+sY7+/b7lgiOOYtwb3oOEgBQRhBRENcpubz+NbmfF3rn769q4HDXk4vZzd596Z3PDnCsm5WFDhJTQSkFpDSkl6roGIQSUUlRVBUoICKV+zVqLJElgW8qemK0nu5/d7vNiNB10n0yeoM/r1IxSyDYDpQyUUTRVgybXsMbCWAshuoi7EoTCF3NFJ8MCuc1xIdnErS/+usXLekbKoxpyZHG8X2J6q4QPCxBGkK7GPtFFaRsMfj6C0W4zHEtWIpA2R57PwBihvG5qaG2QrMQ4uJnj6nu7IQEGyyeXcPmrF8FbzCc3M4XvP7qBrJ8D4AA03vr8BZx6JQEOgDRNwdvLHcI5g7Hat2G0DvAAqMoEGHM0LnRtFl/+nMuB68ACTdOAd9odMCZgbDOvY/9NNsog7kjwhPt/Jih0sygYztnFcQJwzsGnx1NrjHYdgnKKpV4KrRzhBmkvQbY/BYsXLWu0NxJQYQHLYK0GlxRWB5HGkwm4U9DdNhvVSHsx3v7yJX9b2mthcq/A1XevoykbX5AL5vdXzi2jOJjB6qB0MZx65Z3N+PBoQOp6Cy3mvGUhljioU7cXocpqzMa1YzNwWmpEHYGkF0NV2rfv1rSx3hHdTgf8sBhBKeX7L60CoQTTgxLfffATyqwCjxisph61Q/Pt+z8CzOLSh9u48PI68mPluTUGGAwG4OvL65BSQKn6EeL7tw/mbUbe5EFR4PCPsV+vxhXE3E7OGVQQnDl7FjRuxdaNlZ3L5Th1KKO0BYD6IkYb701rAMZkuFgyGBVy3BRRStDr9cBbrRj/LbiIMB0WWlWPrkPg/+Ho0lrj5t4e+ObmJpEywqwZe0SLKIsKTAi8+vGORxNMrbF75Q7KrJkbemHJ8O0eFj4ajaB1F4wxGFgvf/CsU5Fg5/J5yDSgqqYNfvj0l7npAzWhXrBPHMfg+/v7dqc6jYgzNFCo8gYiYXjjyiUvxtGfxcNEY/H6Jxf9TJ8430benwUa5k9ZlmXgy2lb2EhhJjPU0nFmwFoUj795AlppFP1pGC9HvGTYeK3tJ6oYHiMvSoBbHPMMRiokccL4TE1pz55GV11ATRVIFASyfwflLH04rNbJ3A9o19x7IOAFVaLCWnMGk+kNyRtZXdv7+rc6+SZdGo1H5epjqzbPM5IXBdbW1jAcDq1TMZIS/QcPIDgP5HNOtNZ2a2sL7aU23/39WjSY3L/7DyhKDvYCAPhyAAAAAElFTkSuQmCC";     //消息内嵌一个logo logo使用base64编码后存储在此，图片大小限制在32K以内，否则logo恢复为空
    @JSONField(name="msgType", ordinal = 5)
    String msgType="Logo";
    @Override
    public String getMsgId() {
        return msgId;
    }
    public void setMsgId(String msgId){
        this.msgId = msgId;
    }
    public Long getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(Long msgTime) {
        this.msgTime = msgTime;
    }

    public String getMsgContext() {
        return msgContext;
    }

    public void setMsgContext(String msgContext) {
        this.msgContext = msgContext;
    }

    public String getMsgLogo() {
        return msgLogo;
    }

    public void setMsgLogo(String msgLogo) {
        this.msgLogo = msgLogo;
    }
    public String getMsgType() {
        return msgType;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }
}
