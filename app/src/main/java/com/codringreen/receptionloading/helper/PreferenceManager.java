package com.codringreen.receptionloading.helper;

import android.content.Context;

public enum PreferenceManager {

    INSTANCE;

    private static final String KEY_ACCESSTOKEN = "accesstoken";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_CONTACTNO = "contactno";
    private static final String KEY_DBMIGRATION = "DB_MIGRATION";
    private static final String KEY_EMAILID = "emailid";
    private static final String KEY_FIREBASE_TOKEN = "fb_token";
    private static final String KEY_INSTANCEID = "instanceid";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static final String KEY_LOGINEXPIRY = "loginexpiry";
    private static final String KEY_NAME = "name";
    private static final String KEY_ORIGINID = "originid";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PHOTO = "photo";
    private static final String KEY_REFRESHTOKEN = "refreshtoken";
    private static final String KEY_USERID = "userid";
    private static final String KEY_USER_NAME = "user_name";
    private static final String LAST_TEMP_RECEPTION_ID = "LAST_TEMP_RECEPTION_ID";
    public static final String PREF_NAME = "TTK_RECEPTION_PREF";
    private UserPreferences preferenceHandle;

    public void createPreferences(Context context) {
        this.preferenceHandle = new UserPreferences(context, PREF_NAME);
    }

    public String getKeyInstanceid() {
        return this.preferenceHandle.getString(KEY_INSTANCEID, "");
    }

    public void setKeyInstanceid(String instanceid) {
        this.preferenceHandle.setString(KEY_INSTANCEID, instanceid);
    }

    public String getKeyPhoto() {
        return this.preferenceHandle.getString(KEY_PHOTO, "");
    }

    public void setKeyPhoto(String photo) {
        this.preferenceHandle.setString(KEY_PHOTO, photo);
    }

    public String getKeyAddress() {
        return this.preferenceHandle.getString(KEY_ADDRESS, "");
    }

    public void setKeyAddress(String address) {
        this.preferenceHandle.setString(KEY_ADDRESS, address);
    }

    public String getKeyContactno() {
        return this.preferenceHandle.getString(KEY_CONTACTNO, "");
    }

    public void setKeyContactno(String contactno) {
        this.preferenceHandle.setString(KEY_CONTACTNO, contactno);
    }

    public String getKeyEmailid() {
        return this.preferenceHandle.getString(KEY_EMAILID, "");
    }

    public void setKeyEmailid(String emailid) {
        this.preferenceHandle.setString(KEY_EMAILID, emailid);
    }

    public int getKeyUserid() {
        return this.preferenceHandle.getInt(KEY_USERID, 0);
    }

    public void setKeyUserid(int userid) {
        this.preferenceHandle.setInt(KEY_USERID, userid);
    }

    public int getKeyOriginId() {
        return this.preferenceHandle.getInt(KEY_ORIGINID, 0);
    }

    public void setKeyOriginId(int originid) {
        this.preferenceHandle.setInt(KEY_ORIGINID, originid);
    }

    public String getKeyName() {
        return this.preferenceHandle.getString(KEY_NAME, "");
    }

    public void setKeyName(String name) {
        this.preferenceHandle.setString(KEY_NAME, name);
    }

    public Long getKeyLoginexpiry() {
        return this.preferenceHandle.getLong(KEY_LOGINEXPIRY, 0L);
    }

    public void setKeyLoginexpiry(Long loginexpiry) {
        this.preferenceHandle.setLong(KEY_LOGINEXPIRY, loginexpiry);
    }

    public String getKeyRefreshtoken() {
        return this.preferenceHandle.getString(KEY_REFRESHTOKEN, "");
    }

    public void setKeyRefreshtoken(String refreshtoken) {
        this.preferenceHandle.setString(KEY_REFRESHTOKEN, refreshtoken);
    }

    public String getKeyFirebaseToken() {
        return this.preferenceHandle.getString(KEY_FIREBASE_TOKEN, "");
    }

    public void setKeyFirebaseToken(String firebaseToken) {
        this.preferenceHandle.setString(KEY_FIREBASE_TOKEN, firebaseToken);
    }

    public String getKeyUserName() {
        return this.preferenceHandle.getString(KEY_USER_NAME, "");
    }

    public void setKeyUserName(String userName) {
        this.preferenceHandle.setString(KEY_USER_NAME, userName);
    }

    public String getKeyPassword() {
        return this.preferenceHandle.getString(KEY_PASSWORD, "");
    }

    public void setKeyPassword(String password) {
        this.preferenceHandle.setString(KEY_PASSWORD, password);
    }

    public String getAccessToken() {
        return this.preferenceHandle.getString(KEY_ACCESSTOKEN, "");
    }

    public void setAccessToken(String accessToken) {
        this.preferenceHandle.setString(KEY_ACCESSTOKEN, accessToken);
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.preferenceHandle.setBoolean(KEY_IS_LOGGED_IN, loggedIn);
    }

    public Boolean isLoggedIn() {
        return this.preferenceHandle.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void clearLoginDetails() {
        this.preferenceHandle.removePreferenceWithKey(KEY_LOGINEXPIRY);
        this.preferenceHandle.removePreferenceWithKey(KEY_ACCESSTOKEN);
        this.preferenceHandle.removePreferenceWithKey(KEY_REFRESHTOKEN);
        this.preferenceHandle.removePreferenceWithKey(KEY_FIREBASE_TOKEN);
        this.preferenceHandle.removePreferenceWithKey(KEY_IS_LOGGED_IN);
        this.preferenceHandle.removePreferenceWithKey(KEY_USER_NAME);
        this.preferenceHandle.removePreferenceWithKey(KEY_PASSWORD);
        this.preferenceHandle.removePreferenceWithKey(KEY_NAME);
        this.preferenceHandle.removePreferenceWithKey(KEY_USERID);
        this.preferenceHandle.removePreferenceWithKey(KEY_EMAILID);
        this.preferenceHandle.removePreferenceWithKey(KEY_CONTACTNO);
        this.preferenceHandle.removePreferenceWithKey(KEY_ADDRESS);
        this.preferenceHandle.removePreferenceWithKey(KEY_PHOTO);
        this.preferenceHandle.removePreferenceWithKey(KEY_IS_LOGGED_IN);
    }

    public void setKeyDbMigration(Boolean value) {
        this.preferenceHandle.setBoolean(KEY_DBMIGRATION, value);
    }

    public Boolean getKeyDbMigration() {
        return this.preferenceHandle.getBoolean(KEY_DBMIGRATION, false);
    }

    public void setLastTempReceptionId(String value) {
        this.preferenceHandle.setString(LAST_TEMP_RECEPTION_ID, value);
    }

    public String getLastTempReceptionId() {
        return this.preferenceHandle.getString(LAST_TEMP_RECEPTION_ID, "");
    }
}