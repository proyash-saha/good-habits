package comp3350.goodhabits.Persistence.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import comp3350.goodhabits.Objects.Profile;
import comp3350.goodhabits.Persistence.ProfileStorageI;


public class ProfileSQLite extends SQLiteOpenHelper implements ProfileStorageI {

    private Profile profile = null;

    public ProfileSQLite(Context context) {
        super(context, "Profile.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Profile(name TEXT, email TEXT primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Profile");
        onCreate(db);
    }

    public boolean addToProfileStorage(Profile profile) throws SQLException {
        boolean result = false;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("name", profile.getName());
            cv.put("email", profile.getEmail());
            db.insert("Profile", null, cv);
            db.close();
            result = true;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public Profile getProfileStorage() throws SQLException{
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cr = db.rawQuery("select * from Profile", null);
            while(cr.moveToNext()){
                String name = cr.getString(0);
                String email = cr.getString(1);
                profile = new Profile(name, email);
            }
            cr.close();
            db.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return profile;
    }

    public boolean makeProfileEmpty() throws SQLException{
        boolean result = false;
        if(profile !=null){
            try{
                SQLiteDatabase db = this.getWritableDatabase();
                db.execSQL("delete from Profile");
                profile = null;
                db.close();
                result = true;
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }
}
