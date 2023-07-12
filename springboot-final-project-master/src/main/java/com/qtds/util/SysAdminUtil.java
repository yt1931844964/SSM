package com.qtds.util;


import com.qtds.entity.SysAdmin;

import javax.servlet.http.HttpSession;

public class SysAdminUtil {
    public static SysAdmin getLoginUser(HttpSession session) {
        return (SysAdmin) session.getAttribute("loginUser");
    }

    public static void setLoginUser(HttpSession session, SysAdmin user) {
        session.setAttribute("loginUser", user);
    }
}
