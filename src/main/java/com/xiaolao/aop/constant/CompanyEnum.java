package com.xiaolao.aop.constant;

/**
 * @Author: WuJianGuo
 * @Date: 2020/9/23 15:10
 * @Description:
 */
public enum CompanyEnum {
    SF("顺丰",1001),
    YT("圆通",1002),
    ST("申通",1003),
    ZT("中通",1004),
    YD("韵达",1005);

    private String name;

    private int code;

    CompanyEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static String getNameByCode(int code){
        CompanyEnum[] values = CompanyEnum.values();
        String name ="";
        for(CompanyEnum companyEnum:values){
            if(companyEnum.getCode()==code){
                name = companyEnum.getName();
            }
        }
        return name;
    }

    public static int getCodeByName(String name){
        CompanyEnum[] values = CompanyEnum.values();
        int code = 0;
        for(CompanyEnum companyEnum:values){
            if(companyEnum.getName().equals(name)){
                code = companyEnum.getCode();
            }
        }
        return code;
    }
}
