package bluemwall.mhussain96.com.bluemwall.Database.LocalDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import bluemwall.mhussain96.com.bluemwall.Database.Recents;

import static bluemwall.mhussain96.com.bluemwall.Database.LocalDatabase.LocalDatabase.DATABASE_VERSION;

/**
 * Created by Muhtamim Fuwad Nahid on 3/16/2018.
 */

@Database(entities = Recents.class, version = DATABASE_VERSION)
public abstract class LocalDatabase extends RoomDatabase {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "LiveWallpaper";

    public abstract RecentsDAO recentsDAO();

    public static LocalDatabase instance;

    public static LocalDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context, LocalDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
