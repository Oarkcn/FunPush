package cn.oark.utility.message;

public class LogoFactory implements Provider{

    @Override
    public Message get() {
        return new LogoMsg();
    }
}
