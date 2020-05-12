package com.fengtoos.ppgraduate.auth.util;

import com.fengtoos.ppgraduate.auth.exception.FengtoosException;

public class FengtoosUtil {

    public static void null2Entity(Object entity, String msg){
        if(entity == null){
            throw new FengtoosException(500, msg);
        }
    }

    public static void null2Entity(Object entity){
        null2Entity(entity, "对象不能为空");
    }
}
