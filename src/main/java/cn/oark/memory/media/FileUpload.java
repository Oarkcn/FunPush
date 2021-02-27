package cn.oark.memory.media;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

@MultipartConfig
@WebServlet(name = "upload", value = "/uploadFile")
public class FileUpload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part part = request.getPart("uploadfile");
        String disposition = part.getHeader("Content-Disposition");
        String suffix = disposition.substring(disposition.lastIndexOf("."),disposition.length()-1);
        //随机的生存一个32的字符串
        String filename = ((new Random()).nextInt())+suffix;
        //获取上传的文件名
        InputStream is = part.getInputStream();
        //动态获取服务器的路径
        String serverpath = request.getServletContext().getRealPath("")+"\\mediaFile\\";
        File file = new File(serverpath+filename);
        FileOutputStream fos = new FileOutputStream(file,false);
        byte[] bty = new byte[1024];
        int length =0;
        while((length=is.read(bty))!=-1){
            fos.write(bty,0,length);
        }
        fos.close();
        is.close();
        File file1 = new File(serverpath,filename);
        String sha1 = getFileSha1(file1)+suffix;
        File file2 = new File(serverpath,sha1);
        if(!file2.exists()){
            if(file1.renameTo(file2)){
                response.getWriter().println("http://localhost:8080/FunPush/mediaFile/"+file2.getName());
            }else{
                response.getWriter().println("");
            }
        }else{
            response.getWriter().println("http://localhost:8080/FunPush/mediaFile/"+file2.getName());
        }

        file1.delete();
    }
    public static String getFileSha1(File file)
    {
        FileInputStream in = null;
        try
        {
            in = new FileInputStream(file);
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] buffer = new byte[1024 * 1024 * 10];

            int len = 0;
            while ((len = in.read(buffer)) > 0)
            {
                digest.update(buffer, 0, len);
            }
            String sha1 = new BigInteger(1, digest.digest()).toString(16);
            int length = 40 - sha1.length();
            if (length > 0)
            {
                for (int i = 0; i < length; i++)
                {
                    sha1 = "0" + sha1;
                }
            }
            return sha1;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        return "";
    }
}
