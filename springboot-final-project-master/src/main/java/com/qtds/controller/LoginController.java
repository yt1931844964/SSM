package com.qtds.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qtds.util.SysAdminUtil;
import com.qtds.entity.SysAdmin;
import com.qtds.entity.VO.R;
import com.qtds.service.SysAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 登录页面等 前端控制器
 * </p>
 * @author 姚廷
 * @since 2023-07-01
 */
@Controller
public class LoginController {
    @Resource
    private SysAdminService sysAdminService;

    /**
     * 返回index页面
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 服务器主页
     * @return
     */
    @RequestMapping("/")
    public String welcome() {
        return "login";
    }
    @RequestMapping("/loginPage")
    public String loginPage() {
        return "login";
    }

    /**
     * 返回登录页面
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    /**
     * 用户登录
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public R login(SysAdmin sysAdmin, HttpSession session) {
        SysAdmin login = sysAdminService.getOne(Wrappers.<SysAdmin>query()
                .eq("login_name", sysAdmin.getLoginName())
                .eq("password", sysAdmin.getPassword()));
        if (login != null && login.getLoginName() != null) {
            SysAdminUtil.setLoginUser(session, login);
            return R.ok("登录成功！");
        } else {
            return R.error("登录失败！");
        }
    }
}
