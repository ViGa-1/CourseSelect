package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.AdminInfo;
import pojo.WxResultJson;
import service.AdminInfoService;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    AdminInfoService adminInfoService;

    @RequestMapping("/AdminLogin")
    @ResponseBody
    public String login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String psw = req.getParameter("pwd");
        String loginName = req.getParameter("count");
        AdminInfo select = adminInfoService.select();
        if( select.getLoginName().equals(loginName)&&select.getPsw().equals(psw)) {
            HttpSession session = req.getSession();
            session.setAttribute("isLogin","true");
            select.setLastLoginTime((select.getThisLoginTime()));
            select.setThisLoginTime(null);
            adminInfoService.update(select);
            resp.sendRedirect("index");
        }else
            resp.sendRedirect("SystemLogin");
        return "success";
    }

}
