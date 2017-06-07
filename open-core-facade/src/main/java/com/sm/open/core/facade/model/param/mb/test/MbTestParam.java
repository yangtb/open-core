package com.sm.open.core.facade.model.param.mb.test;

import java.io.Serializable;

public class MbTestParam implements Serializable {

    private static final long serialVersionUID = -310053372064720675L;

    private Long    testId;
    private String  testName;

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MbTestParam{");
        sb.append("testId=").append(testId);
        sb.append(", testName='").append(testName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
