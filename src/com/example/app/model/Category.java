package com.example.app.model;


import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("OfflineCategoryID")
    private int OfflineCategoryID;
    @SerializedName("Name")
    private String Name;
    @SerializedName("ParentCategoryID")
    private String ParentCategoryID;
    @SerializedName("CategoryType")
    private String CategoryType;

    /*"OfflineCategoryID": "32",
            "Name": "Continental",
            "ParentCategoryID": "1",
            "CategoryType": "Cuisine"*/

    public int getOfflineCategoryID() {
        return this.OfflineCategoryID;
    }

    public void setOfflineCategoryID(int OfflineCategoryID) {
        this.OfflineCategoryID = OfflineCategoryID;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getParentCategoryID() {
        return this.ParentCategoryID;
    }

    public void setParentCategoryID(String ParentCategoryID) {
        this.ParentCategoryID = ParentCategoryID;
    }

    public String getCategoryType() {
        return this.CategoryType;
    }

    public void setCategoryType(String CategoryType) {
        this.CategoryType = CategoryType;
    }

}
