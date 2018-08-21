package com.zte;

public class CellRRCConn {
    private static String enodebID;
    private static String endTime;
    private static String vendorName;
    private String cellID;
    private String[] measTypes;
    private String[] measResults;

    public String getEnodebID() {
        return enodebID;
    }

    public void setEnodebID(String enodebID) {
        this.enodebID = enodebID;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCellID() {
        return cellID;
    }

    public void setCellID(String cellID) {
        this.cellID = cellID;
    }


    @Override
    public String toString() {
        return "endTime: " + this.endTime +
                "enodebID: " + this.enodebID + "cellID: " + this.cellID + "指标ID: " + this.measTypes + "指标Val: " + this.measResults;
    }

    public String[] getMeasTypes() {
        return measTypes;
    }

    public void setMeasTypes(String[] measTypes) {
        this.measTypes = measTypes;
    }

    public String[] getMeasResults() {
        return measResults;
    }

    public void setMeasResults(String[] measResults) {
        this.measResults = measResults;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String venderName) {
        this.vendorName = venderName;
    }
}

