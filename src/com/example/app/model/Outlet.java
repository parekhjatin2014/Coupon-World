package com.example.app.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Outlet {

    @SerializedName("SubFranchiseID")
    private int SubFranchiseID;
    @SerializedName("OutletID")
    private int OutletID;
    @SerializedName("OutletName")
    private String OutletName;
    @SerializedName("BrandID")
    private int BrandID;
    @SerializedName("Address")
    private String Address;
    @SerializedName("NeighbourhoodID")
    private int NeighbourhoodID;
    @SerializedName("CityID")
    private int CityID;
    @SerializedName("Email")
    private String Email;
    @SerializedName("Timings")
    private String Timings;
    @SerializedName("CityRank")
    private String CityRank;
    @SerializedName("Latitude")
    private double Latitude;
    @SerializedName("Longitude")
    private double Longitude;
    @SerializedName("Pincode")
    private String Pincode;
    @SerializedName("Landmark")
    private String Landmark;
    @SerializedName("Streetname")
    private String Streetname;
    @SerializedName("BrandName")
    private String BrandName;
    @SerializedName("OutletURL")
    private String OutletURL;
    @SerializedName("NumCoupons")
    private int NumCoupons;
    @SerializedName("NeighbourhoodName")
    private String NeighbourhoodName;
    @SerializedName("PhoneNumber")
    private String PhoneNumber;
    @SerializedName("CityName")
    private String CityName;
    @SerializedName("Distance")
    private float Distance;
    @SerializedName("Categories")
    private List<Category> Categories;
    @SerializedName("LogoURL")
    private String LogoURL;
    @SerializedName("CoverURL")
    private String CoverURL;

    private double distanceFromCurrent = -1;

    private long mLocalDbOutletRowId = -1;

    /*"SubFranchiseID": "0",
            "OutletID": "607",
            "OutletName": "Spill ",
            "BrandID": "403",
            "Address": "J-349, JP Road, Opp. Apna Bazar, JP Rd, D.N.Nagar, Andheri West, Mumbai, Maharashtra, India",
            "NeighbourhoodID": "1",
            "CityID": "1",
            "Email": null,
            "Timings": "Everyday: 6 pm to 1:30 am.",
            "CityRank": null,
            "Latitude": "19.127473",
            "Longitude": "72.832545",
            "Pincode": null,
            "Landmark": null,
            "Streetname": null,
            "BrandName": "Spill ",
            "OutletURL": "https://plus.google.com/111539701326240643109/about?hl=en-US",
            "NumCoupons": 1,
            "NeighbourhoodName": "Andheri West",
            "PhoneNumber": "+91 22 2642 5895",
            "CityName": "Mumbai",
            "Distance": 8205.2235,*/

    public int getSubFranchiseID() {
        return this.SubFranchiseID;
    }

    public void setSubFranchiseID(int SubFranchiseID) {
        this.SubFranchiseID = SubFranchiseID;
    }

    public int getOutletID() {
        return this.OutletID;
    }

    public void setOutletID(int OutletID) {
        this.OutletID = OutletID;
    }

    public String getOutletName() {
        return this.OutletName;
    }

    public void setOutletName(String OutletName) {
        this.OutletName = OutletName;
    }

    public int getBrandID() {
        return this.BrandID;
    }

    public void setBrandID(int BrandID) {
        this.BrandID = BrandID;
    }

    public String getAddress() {
        return this.Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getNeighbourhoodID() {
        return this.NeighbourhoodID;
    }

    public void setNeighbourhoodID(int NeighbourhoodID) {
        this.NeighbourhoodID = NeighbourhoodID;
    }

    public int getCityID() {
        return this.CityID;
    }

    public void setCityID(int CityID) {
        this.CityID = CityID;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }


    public String getTimings() {
        return this.Timings;
    }

    public void setTimings(String Timings) {
        this.Timings = Timings;
    }


    public String getCityRank() {
        return this.CityRank;
    }

    public void setCityRank(String CityRank) {
        this.CityRank = CityRank;
    }

    public double getLatitude() {
        return this.Latitude;
    }

    public void setLatitude(double Latitude) {
        this.Latitude = Latitude;
    }

    public double getLongitude() {
        return this.Longitude;
    }

    public void setLongitude(double Longitude) {
        this.Longitude = Longitude;
    }

    public String getPincode() {
        return this.Pincode;
    }

    public void setPincode(String Pincode) {
        this.Pincode = Pincode;
    }

    public String getLandmark() {
        return this.Landmark;
    }

    public void setLandmark(String Landmark) {
        this.Landmark = Landmark;
    }

    public String getStreetname() {
        return this.Streetname;
    }

    public void setStreetname(String Streetname) {
        this.Streetname = Streetname;
    }

    public String getBrandName() {
        return this.BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public String getOutletURL() {
        return this.OutletURL;
    }

    public void setOutletURL(String OutletURL) {
        this.OutletURL = OutletURL;
    }

    public int getNumCoupons() {
        return this.NumCoupons;
    }

    public void setNumCoupons(int NumCoupons) {
        this.NumCoupons = NumCoupons;
    }

    public String getNeighbourhoodName() {
        return this.NeighbourhoodName;
    }

    public void setNeighbourhoodName(String NeighbourhoodName) {
        this.NeighbourhoodName = NeighbourhoodName;
    }

    public String getPhoneNumber() {
        return this.PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getCityName() {
        return this.CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    public float getDistance() {
        return this.Distance;
    }

    public void setDistance(float Distance) {
        this.Distance = Distance;
    }

    public List<Category> getCategories() {
        return this.Categories;
    }

    public void addCategory(Category category) {
        if (Categories == null) {
            Categories = new ArrayList<Category>();
        }
        Categories.add(category);
    }

    public String getLogoURL() {
        return this.LogoURL;
    }

    public void setLogoURL(String LogoURL) {
        this.LogoURL = LogoURL;
    }

    public String getCoverURL() {
        return this.CoverURL;
    }

    public void setCoverURL(String CoverURL) {
        this.CoverURL = CoverURL;
    }

    public long getLocalDbOutletRowId() {
        return mLocalDbOutletRowId;
    }

    public void setLocalDbOutletRowId(long localDbOutletRowId) {
        this.mLocalDbOutletRowId = localDbOutletRowId;
    }

    public double getDistanceFromCurrent() {
        return distanceFromCurrent;
    }

    public void setDistanceFromCurrent(double distanceFromCurrent) {
        this.distanceFromCurrent = distanceFromCurrent;
    }
}
