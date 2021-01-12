package cn.oark.utility.postal;

import cn.oark.utility.message.Message;

import java.util.ArrayList;
import java.util.List;

public class MultiPostal implements Postal{
    Long postalID;      //雪花算法生成的报文
    String postalFrom;  //报文发送方
    ArrayList<String> postalTo;    //报文接收方
    PostalType postalType=PostalType.Multi;  //该邮包类型
    Message postalMsg;  //该报文的信息正文
    @Override
    public Long getID() {
        return postalID;
    }
}
