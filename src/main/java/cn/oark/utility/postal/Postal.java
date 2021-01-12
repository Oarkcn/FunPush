package cn.oark.utility.postal;

public interface Postal {
    Long postalId = 0L;
    String postalFrom = "";  //报文发送方
    String postalTo = "";    //报文接收方
    PostalType postalType = null;  //该邮包类型
    byte postalWeight = 0;  //报文权重，默认为0  -128 ~ 127 之间
    Long getID();
}
