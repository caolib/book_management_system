package com.clb.constant;

/**
 * 异常相关枚举字段
 */
public class Excep {
    public static final String UNKNOWN_ERROR = "未知的错误...";
    public static final String DELETE_BOOK_NOT_ALLOW = "借阅记录中存在该书籍信息，不允许删除！";
    public static final String DELETE_READER_NOT_ALLOW = "借阅记录中存在用户信息，不允许删除！";
    public static final String USER_NOT_EXIST = "用户不存在!";
    public static final String WRONG_PASSWORD = "密码错误!";
    public static final String NOT_LOGIN = "未登录!";
    public static final String TOKEN_NOT_EXIST = "令牌不存在!";
    public static final String TOKEN_ALREADY_EXPIRED = "令牌已过期!";
    public static final String USER_ALREADY_EXIST = "用户已存在！";
    public static final String DATE_FORMAT_ERROR = "日期格式错误！";
    public static final String DATE_IS_NULL = "日期不能为空！";
    public static final String RETURN_DATE_IS_NULL = "请先选择归还日期！";
    public static final String REGISTER_ERROR = "用户名、密码、电话不能为空！";
    public static final String TEL_ALREADY_EXIST = "电话号码已存在！";
    public static final String ISBN_ALREADY_EXIST = "isbn号已存在！";
    public static final String ISBN_IS_NULL = "isbn不能为空！";
    public static final String ISBN_NOT_EXIST = "isbn不存在！";
    public static final String TITLE_IS_NULL = "书名不能为空！";
    public static final String BOOK_NUMBER_ERROR = "书本库存量只能为非负数！";
    public static final String ARG_NOT_VALID = "方法参数非法！";

}
