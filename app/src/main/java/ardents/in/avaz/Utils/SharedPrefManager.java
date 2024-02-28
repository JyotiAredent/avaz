package ardents.in.avaz.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import ardents.in.avaz.models.LoginModel;
import ardents.in.avaz.models.RegisterModel;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME_USER="user";
    private static final String SHARED_PREF_NAME_TOKEN="token";
    private static final String USER_NAME="name";
    private static final String USER_EMAIL="email";
    private static final String USER_AGE="age";
    private static final String USER_UPDATED_AT="updated_at";
    private static final String USER_CREATED_AT="created_at";
    private static final String USER_ID="id";
    private static final String MESSAGE="message";
    private static final String LOGIN_TOKEN="token";
    public static SharedPrefManager mInstance;
    public static Context mCtx;

    public SharedPrefManager() {
    }
    public SharedPrefManager(Context context) {
        mCtx=context;
    }


    public static synchronized SharedPrefManager getInstance(Context context){
        if (mInstance==null){
            mInstance=new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void setUser(RegisterModel registerModel){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_USER,mCtx.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString(USER_NAME,registerModel.getUser().getName());
        editor.putString(USER_EMAIL,registerModel.getUser().getEmail());
        editor.putString(USER_AGE,registerModel.getUser().getAge());
        editor.putString(USER_CREATED_AT,registerModel.getUser().getCreatedAt());
        editor.putString(USER_UPDATED_AT,registerModel.getUser().getUpdatedAt());
        editor.putString(USER_ID,registerModel.getUser().getId());
        editor.putString(MESSAGE,registerModel.getMessage());
        editor.apply();

    }
    public void setToken(String token){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_TOKEN,mCtx.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(LOGIN_TOKEN,token);
    }
    public boolean userisLoggedIn(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_TOKEN,mCtx.MODE_PRIVATE);
        return sharedPreferences.getString(LOGIN_TOKEN,null)!=null;
    }

    public String getToken(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_TOKEN,mCtx.MODE_PRIVATE);
        return sharedPreferences.getString(LOGIN_TOKEN,null);
    }

    public void logout(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_TOKEN,mCtx.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
