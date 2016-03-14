package com.cs160.joleary.catnip;

/**
 * Created by gabrielarreola on 3/11/16.
 */
public class Legislator {

    private String mBioguideId, mFirstName, mLastName, mParty, mTitle, mEmail, mPhone, mOffice, mZipcode, mWebsite, mTerm_end;

    public Legislator(String bioguideId, String firstName, String lastName, String party, String title, String email, String phone, String office, String zipcode ,String website, String term_end){
        mBioguideId = bioguideId;
        mFirstName = firstName;
        mLastName = lastName;
        mParty = party;
        mTitle = title;
        mEmail = email;
        mPhone = phone;
        mOffice = office;
        mZipcode = zipcode;
        mWebsite = website;
        mTerm_end = term_end;

    }

    public String getBioguideId() {
        return mBioguideId;
    }

    public void setBioguideId(String bioguideId) {
        mBioguideId = bioguideId;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getParty() {
        return mParty;
    }

    public void setParty(String party) {
        this.mParty = party;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getOffice() {
        return mOffice;
    }

    public String getZipcode() {
        return mZipcode;
    }

    public void setZipcode(String zipcode) {
        mZipcode = zipcode;
    }

    public void setOffice(String office) {
        mOffice = office;
    }



    public String getWebsite() {
        return mWebsite;
    }

    public void setWebsite(String website) {
        mWebsite = website;
    }

    public String getTerm_end() { return mTerm_end; }

    public void setTerm_end(String term_end) { mTerm_end = term_end; }
}
