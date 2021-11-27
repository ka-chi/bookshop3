package com.example.bookshop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther: shu'ai
 * @Date: 2021/10/12 10:12
 * @Description: 权限枚举
 */
@Getter
@AllArgsConstructor
public enum authenticEnum {

    SUPERMAN(0,"超级管理员"),
    CUSTOMER(1,"顾客"),
    SHOPPER(2,"商家");

    /**
     *
     */
    private Integer auth;
    /**
     *
     */
    private String value;

    /**
     * 根据类型获取枚举
     * @param type 类型
     * @return 枚举
     */
    public static authenticEnum getChatType(Integer type) {
        for (authenticEnum chatType : authenticEnum.values()) {
            if (chatType.getAuth().equals(type)) {
                return chatType;
            }
        }
        return null;
    }
}
