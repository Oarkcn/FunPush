package cn.oark.coder.basecode;

public class EncodeBase64 {
    final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8','9',
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            '+','/' };
    final static Long numMask = 0x3fL;
    public static String numToString(Long num){
        if (num == null) return "";
        if (num == 0  ) return  "0";
        char[]newWord = new char[11];
        String string = "0";
        byte flag = 10;
        while(num!=0){
            newWord[flag] = digits[(int) (num & numMask)];
            num = num >>> 6;
            flag --;
        }
        string = String.valueOf(newWord);
        return string;
    }
    public static String hexToString(String s){
        if (s == null) return "";
        s=s.toLowerCase();
        int num=0,opnum = 0,opnum2 = s.length()*2/3+1,flag = s.length()-1;
        char[] newWord = new char[opnum2];
        try {
            opnum2--;
            while(flag >=0){
                num = (num ) | ((Integer.parseInt(s.substring(flag,flag+1),16))<<opnum);
                opnum+=4;
                flag-=1;
                if(opnum>=6){
                    newWord[opnum2]=digits[(int)(num & numMask)];
                    num = num >>> 6;
                    opnum2--;
                    opnum-=6;
                }
            }
            if(opnum >0) newWord[opnum2]=digits[(int)(num & numMask)];
        }catch (Exception exception){
            System.out.println("Error!" + exception.toString());
        }
        return String.valueOf(newWord);
    }
}
