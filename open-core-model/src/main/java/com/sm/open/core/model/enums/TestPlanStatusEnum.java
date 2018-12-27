package com.sm.open.core.model.enums;

/**
 * @ClassName: TestPlanStatusEnum
 * @Description: 测试计划状态枚举
 * @Author yangtongbin
 * @Date 2018/11/16
 */
public enum TestPlanStatusEnum {

    PLAN("0", "计划"),
    EXEC("1", "正在执行"),
    FINISH("2", "执行完成");

    private String code;
    private String desc;

    TestPlanStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
