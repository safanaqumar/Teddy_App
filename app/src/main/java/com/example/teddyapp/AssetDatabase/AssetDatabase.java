package com.example.teddyapp.AssetDatabase;

public class AssetDatabase {
    String Serial_num,Asset_tag,Description,Deprt,Remark;


    public AssetDatabase(String id, String serial_num, String asset_tag, String description, String deprt, String remark) {

    }

    public AssetDatabase(String serial_num, String asset_tag, String description, String deprt, String remark) {

        Serial_num = serial_num;
        Asset_tag = asset_tag;
        Description = description;
        Deprt = deprt;
        Remark = remark;
    }


    public String getSerial_num() {
        return Serial_num;
    }

    public void setSerial_num(String serial_num) {
        Serial_num = serial_num;
    }

    public String getAsset_tag() {
        return Asset_tag;
    }

    public void setAsset_tag(String asset_tag) {
        Asset_tag = asset_tag;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDeprt() {
        return Deprt;
    }

    public void setDeprt(String deprt) {
        Deprt = deprt;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
