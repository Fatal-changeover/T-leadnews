package com.heima.utils.common;

import com.heima.model.wemedia.pojos.WmUser;

/**
 * ClassName: WmThreadLocalUtil
 * Package: com.heima.utils.common
 * Description:
 *
 * @Author R
 * @Create 2024/5/14 7:53
 * @Version 1.0
 */
public class WmThreadLocalUtil {
    private final  static ThreadLocal<WmUser> WM_USER_THREAD_LOCAL = new ThreadLocal<>();
    public static void setUser(WmUser wmUser) {
        WM_USER_THREAD_LOCAL.set(wmUser);
    }

    public static  WmUser getUser() {
        return WM_USER_THREAD_LOCAL.get();
    }

    public static void clear() {
        WM_USER_THREAD_LOCAL.remove();
    }
}
