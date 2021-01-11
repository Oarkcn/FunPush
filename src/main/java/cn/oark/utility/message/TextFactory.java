package cn.oark.utility.message;

public class TextFactory implements Provider{

    @Override
    public Message get() {
        return new TextMsg();
    }
}
