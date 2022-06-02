package sg.edu.np.mad.week2activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "users.db";
    public static String USERS = "Users";
    public static String COLUMN_USERNAME = "Username";
    public static String COLUMN_DESCRIPTION = "Description";
    public static String COLUMN_ID = "Id";
    public static String COLUMN_FOLLOWED = "Followed";
    public static int DATABASE_VERSION = 1;

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //Create new Database
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //SQL Creation Statement
        String CREATE_USER_TABLE = "CREATE TABLE " + USERS +
                "(" + COLUMN_USERNAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_FOLLOWED + " BOOLEAN" + ")";

        //Execute SQL
        db.execSQL(CREATE_USER_TABLE);
    }

    //Update Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        onCreate(db);
    }

    //Add new User
    public void addUser(User user)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        //values.put(COLUMN_ID, user.getId());
        values.put(COLUMN_FOLLOWED, user.isFollowed());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(USERS, null, values);
        db.close();
    }

    //Get Users
    public ArrayList<User> getUsers()
    {
        //SQL String
        String query = "SELECT * FROM " + USERS;

        //Empty userList
        ArrayList<User> userList = new ArrayList<>();

        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //Looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do
            {
                User user = new User();
                user.setName(cursor.getString(0));
                user.setDescription(cursor.getString(1));
                user.setId(Integer.parseInt(cursor.getString(2)));
                user.setFollowed(Boolean.parseBoolean(cursor.getString(3)));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        //Close Database
        db.close();

        //Return userList
        return userList;
    }

    //Update User
    public void updateUser(User user)
    {
        String query = "SELECT * FROM " + USERS + " WHERE " + COLUMN_ID + "=\"" + user.getId() + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery(query, null);

        //Check if User ID can be found in Database
        if (cursor.moveToFirst())
        {
            //Check if user.isFollowed is False
            if (!user.isFollowed())
            {
                user.setFollowed(true);
            }
            else
            {
                user.setFollowed(false);
            }
            cursor.close();
        }
        else
        {
            //if no matching username found.
            user = null;
        }

        //Close Database
        db.close();
    }

}
