package cn.oark.operator.test;

import cn.oark.coder.basecode.EncodeBase32;
import cn.oark.coder.basecode.EncodeBase64;
import cn.oark.operator.obid.UID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "testUUID", value = "/testuuid")
public class testDataBase extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //for(int i = 0;i<100000;i++){
        response.setContentType("text/html;charset=utf-8");
        String s = UID.getUUID32();
        response.getWriter().println(s);
        response.getWriter().println(EncodeBase32.hexToString(s));
        response.getWriter().println(EncodeBase64.hexToString(s));
        response.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
