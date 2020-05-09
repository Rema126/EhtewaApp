package com.example.ehtewa.itemData;


public class ItemData_save_new_user {

    private String UserId;
    private String UserEmail;
    private String UserProvider;


    public ItemData_save_new_user()
    {

    }

    public ItemData_save_new_user(String Userid, String Useremail , String Userproviderid) {
        UserId          = Userid;
        UserEmail       = Useremail;
        UserProvider    = Userproviderid;
    }

    public String getUserId() {
        return UserId;
    }

    public String getUserProvider() {
        return UserProvider;
    }

    public String getUserEmail() {
        return UserEmail;
    }

}

