package com.example.teddyapp.AssetDatabase;

public class AssetDatabase {
   public static String Serial_num, Asset_tag,typeofasset, Description,location, Deprt,statusasset, Remark,Reader;



    public AssetDatabase() {


    }

    public  String getReader() {
        return Reader;
    }

    public void setReader(String reader) {
        this.Reader = reader;
    }

    public String getStatusasset() {
        return statusasset;
    }

    public void setStatusasset(String statusasset) {
        this.statusasset = statusasset;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public AssetDatabase(String serial_num, String asset_tag, String typeofasset, String description, String location, String deprt, String statusasset, String remark, String reader) {

        this.typeofasset = typeofasset;
       this.location = location;
        this.statusasset = statusasset;
        this.Serial_num = serial_num;
        this.Asset_tag = asset_tag;
        this.Description = description;
        this.Deprt = deprt;
        this.Remark = remark;
        this.Reader = reader;

    }

    public String getSerial_num() {
        return Serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.Serial_num = serial_num;
    }

    public String getAsset_tag() {
        return Asset_tag;
    }

    public void setAsset_tag(String asset_tag) {
        this.Asset_tag = asset_tag;
    }

    public String getTypeofasset() {
        return typeofasset;
    }

    public void setTypeofasset(String typeofasset) {
        this.typeofasset = typeofasset;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getDeprt() {
        return Deprt;
    }

    public void setDeprt(String deprt) {
        this.Deprt = deprt;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        this.Remark = remark;
    }
}