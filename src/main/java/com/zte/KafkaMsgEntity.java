package com.zte;

public class KafkaMsgEntity extends ParserEntity {
    private String measType;
    private String measValue;

    public String getMeasType() {
        return measType;
    }

    public void setMeasType(String measType) {
        this.measType = measType;
    }

    public String getMeasValue() {
        return measValue;
    }

    public void setMeasValue(String measValue) {
        this.measValue = measValue;
    }
}
