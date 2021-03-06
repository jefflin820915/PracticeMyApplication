package provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.example.myapplicationcontentprovider.db.UserDataBaseHelper;
import com.example.myapplicationcontentprovider.utils.Constant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserProvider  extends ContentProvider {

    private UserDataBaseHelper mUserDataBaseHelper = null;
    private static UriMatcher sUriMatcher = new UriMatcher( UriMatcher.NO_MATCH );
    private static final  int USER_MATCH_CODE = 1;

    static {
        sUriMatcher.addURI( "com.example.myapplicationcontentprovider",null,USER_MATCH_CODE );
    }

    @Override
    public boolean onCreate() {

        mUserDataBaseHelper = new UserDataBaseHelper( getContext() );


        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int result = sUriMatcher.match( uri );
         if (result==USER_MATCH_CODE){
             //說明這裡是匹配

             SQLiteDatabase db = mUserDataBaseHelper.getReadableDatabase();
             Cursor cursor = db.query( Constant.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder );

             return cursor;

         }else{
             //不匹配規則

             throw new IllegalArgumentException( "參數錯誤" );
         }

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        int result = sUriMatcher.match( uri );

        if (result==USER_MATCH_CODE){

            SQLiteDatabase db = mUserDataBaseHelper.getWritableDatabase();
            long id = db.insert( Constant.TABLE_NAME, null, values );
            Uri resultUri = Uri.parse( "content://com.example.myapplicationcontentprovider/user/" + id );

            Log.v(  "brad","insert user result ---> " + id );

            //插入數據成功,數據已經變化,所以通知其他地方(誰監聽就通知誰)
            getContext().getContentResolver().notifyChange( resultUri,null );

            return resultUri;
        }else{

            throw new IllegalArgumentException( "參數錯誤" );
        }

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
