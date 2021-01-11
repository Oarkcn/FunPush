package cn.oark.utility.message;

public class LogoMsg implements Message{
    String msgId;
    Long msgTime;
    String msgTitle;
    String msgContext;  //消息正文与标题 限制长度64+256 多余截取
    String msgLogo;     //消息内嵌一个logo logo使用base64编码后存储在此，图片大小限制在32K以内，否则logo恢复为空
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
