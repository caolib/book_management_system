package com.clb.constant;

import java.util.Date;

public class Jwt {
    public static final Long EXPIRE_TIME = 2 * 60 * 60 * 1000L;
    public static final Date EXPIRE = new Date(System.currentTimeMillis() + EXPIRE_TIME); //令牌到期时间
    public static final String SIGNKEY = "caolibin";//签名密钥
}
