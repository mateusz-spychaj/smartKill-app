package pl.pwr.smartkill.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class UserHelper {//TODO use sqlite to store data about users
	private static final String DEBUG_TAG = "SqLiteUserManager";
	 
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "database.db";
    private static final String DB_TABLE = "users";
    public static final String KEY_ID = "_id";
    public static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final int ID_COLUMN = 0;
    public static final String KEY_DESCRIPTION = "description";
    public static final String DESCRIPTION_OPTIONS = "TEXT NOT NULL";
    public static final int DESCRIPTION_COLUMN = 1;
    public static final String KEY_COMPLETED = "completed";
    public static final String COMPLETED_OPTIONS = "INTEGER DEFAULT 0";
    public static final int COMPLETED_COLUMN = 2;

    private static final String DB_CREATE_TABLE =
            "CREATE TABLE " + DB_TABLE + "( " +
            KEY_ID + " " + ID_OPTIONS + ", " +
            KEY_DESCRIPTION + " " + DESCRIPTION_OPTIONS + ", " +
            KEY_COMPLETED + " " + COMPLETED_OPTIONS +
            ");";
    private static final String DROP_TABLE =
            "DROP TABLE IF EXISTS " + DB_TABLE;
    private SQLiteDatabase db;
    private Context context;
    private DbHelper dbHelper;
    
	public UserHelper(Context context){
    	this.context = context;
    }
	public UserHelper open(){
	    dbHelper = new DbHelper(context, DB_NAME, null, DB_VERSION);
	    try {
	        db = dbHelper.getWritableDatabase();
	    } catch (SQLException e) {
	        db = dbHelper.getReadableDatabase();
	    }
	    return this;
	}
	
	public void close() {
	    dbHelper.close();
	}
	
	private static class DbHelper extends SQLiteOpenHelper {
		
		private static final String TAG = "db";
		private Context context;

	    public DbHelper(Context context, String name,
	            CursorFactory factory, int version) {
	        super(context, name, factory, version);
	    }

	    @Override
	    public void onCreate(SQLiteDatabase db) {
	        db.execSQL(DB_CREATE_TABLE);
	 
	        Log.d(TAG, "Database creating...");
	        Log.d(TAG, "Table " + DB_TABLE + " ver." + DB_VERSION + " created");
	    }
	 
	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//TODO rewrite on change version
	        db.execSQL(DROP_TABLE);
	 
	        Log.d(TAG, "Database updating...");
	        Log.d(TAG, "Table " + DB_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
	        Log.d(TAG, "All data is lost.");
	 
	        onCreate(db);
	    }
	    
	}
}
