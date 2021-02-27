package cn.oark.coder.msg;

import cn.oark.utility.message.LogoMsg;
import cn.oark.utility.message.Message;
import cn.oark.utility.message.TextFactory;
import cn.oark.utility.postal.Postal;
import cn.oark.utility.postal.PostalFactory;
import cn.oark.utility.postal.PostalType;
import cn.oark.utility.postal.SinglePostal;

public class TestMsg {
    public static void main(String[] args) {
        String msg = new MsgEncoder().EncodeMsg(new LogoMsg());
        SinglePostal pos = (SinglePostal) PostalFactory.getPostal(PostalType.Single);
        pos.setPostalMsg(msg);
        String pos1 = new MsgEncoder().EncodeMsg(pos);
        System.out.println(pos1);
        Postal msg1 = new MsgDecoder().DecodePostal(pos1);
        System.out.println(msg1.toString());
    }
}
