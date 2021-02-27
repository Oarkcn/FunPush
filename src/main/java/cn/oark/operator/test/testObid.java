package cn.oark.operator.test;

import cn.oark.coder.basecode.EncodeBase32;
import cn.oark.operator.obid.SnowFlake;
import cn.oark.utility.config.SysPart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "testObid", value = "/testObid")
public class testObid extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SysPart.getReady();
        Long start = System.currentTimeMillis();
        SnowFlake snowFlake = SysPart.obidWorker;
        String result = snowFlake.give(1);
        response.setContentType("text/html;charset=utf-8");
        //response.getWriter().println("<p>"+result+"</p>");
        //for(int i = 0;i<100000;i++){
        try {
            response.getWriter().println(EncodeBase32.numToString(SysPart.obidList2.poll(10, TimeUnit.MINUTES)) + "");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //}
        //response.getWriter().println("<p>"+(System.currentTimeMillis()-start)+"</p>");
        response.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
