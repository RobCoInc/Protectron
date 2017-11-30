package tech.secure.protectron.database_helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import tech.secure.protectron.objects.Arrest;
import tech.secure.protectron.objects.Description;
import tech.secure.protectron.objects.Location;
import tech.secure.protectron.objects.Shift;
import tech.secure.protectron.objects.Tatoo;
import tech.secure.protectron.objects.User;

/**
 * Created by Morgan on 2017-11-12.
 */

public class DBHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static String DATABASE_NAME = "Protectron.db";

    // Table names
    private static final String TABLE_USER = "User_Master";
    private static final String TABLE_LOCATION = "User_Locations";
    private static final String TABLE_SHIFTS = "User_Shifts";
    private static final String TABLE_ARRESTS = "Arrest_Master";
    private static final String TABLE_DESCRIPTION = "Perpetrator_Description";
    private static final String TABLE_TATOO = "Tatoo_Master";
    private static final String TABLE_USER_LOCATIONS = "User_Location_Table";
    private static final String TABLE_USER_SHIFT = "User_Shift_Table";

    // User Table Column names
    private static final String COLUMN_USER_ID = "_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_SECURE_NUM = "user_secure_number";
    private static final String COLUMN_USER_IS_ADMIN = "user_is_admin";
    private static final String COLUMN_USER_ID_FK = "user_id";

    // Location Table Column names
    private static final String COLUMN_LOCATION_ID = "_id";
    private static final String COLUMN_LOCATION_NAME = "location_name";
    private static final String COLUMN_LOCATION_ADDRESS = "location_address";
    private static final String COLUMN_LOCATION_CITY = "location_city";
    private static final String COLUMN_LOCATION_ID_FK = "location_id";

    // UserLocation Table Column names
    private static final String COLUMN_USER_LOCATION_ID = "_id";

    // Shift Table Column names
    private static final String COLUMN_SHIFT_ID = "_id";
    private static final String COLUMN_SHIFT_DATE = "shift_date";
    private static final String COLUMN_SHIFT_TIME = "shift_start_time";
    private static final String COLUMN_SHIFT_END_TIME = "shift_end_time";
    private static final String COLUMN_SHIFT_AVAILABLITY = "shift_availabilty";
    private static final String COLUMN_SHIFT_ID_FK = "shift_id";

    // UserShifts Table Column names
    private static final String COLUMN_USER_SHIFT_ID = "_user_shift_id";
    private static final String COLUMN_USER_SHIFT_COMPLETED = "user_shift_completed";

    // Arrest Table Column names
    private static final String COLUMN_ARREST_ID = "_id";
    private static final String COLUMN_ARREST_DATE = "arrest_date";
    private static final String COLUMN_ARREST_TIME = "arrest_time";
    private static final String COLUMN_ARREST_TYPE = "arrest_type";
    private static final String COLUMN_ARREST_ID_FK = "arrest_id";

    // Description Table Column names
    private static final String COLUMN_DESCRIPTION_ID = "_id";
    private static final String COLUMN_DESCRIPTION_NAME = "description_name";
    private static final String COLUMN_DESCRIPTION_HEIGHT = "description_height";
    private static final String COLUMN_DESCRIPTION_WEIGHT = "description_weight";
    private static final String COLUMN_DESCRIPTION_HAIR_COLOR = "description_haircolor";
    private static final String COLUMN_DESCRIPTION_EYE_COLOR = "description_eyecolor";
    private static final String COLUMN_DESCRIPTION_SKIN_COLOR = "description_skincolor";
    private static final String COLUMN_DESCRIPTION_HAS_TATOO = "description_tatoo";
    private static final String COLUMN_DESCRIPTION_ID_FK = "description_id";

    // Tatoo/Mark Table Column names
    private static final String COLUMN_TATOO_ID = "_id";
    private static final String COLUMN_TATOO_LOCATION = "tatoo_location";
    private static final String COLUMN_TATOO_SIZE = "tatoo_size";
    private static final String COLUMN_TATOO_DESCRIPTION = "tatoo_description";
    private static final String COLUMN_TATOO_COLOR_PRIMARY = "tatoo_primary";
    private static final String COLUMN_TATOO_COLOR_SECONDARY = "tatoo_secondary";
    private static final String COLUMN_TATOO_ID_FK = "tatoo_id";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_USER +
                        "(" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                        "," + COLUMN_USER_NAME + " TEXT" +
                        "," + COLUMN_USER_EMAIL + " TEXT" +
                        "," + COLUMN_USER_PASSWORD + " TEXT" +
                        "," + COLUMN_USER_IS_ADMIN + " TEXT" +
                        "," + COLUMN_USER_SECURE_NUM + " INTEGER)");
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_LOCATION +
                        "(" + COLUMN_LOCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                        "," + COLUMN_LOCATION_NAME + " TEXT" +
                        "," + COLUMN_LOCATION_ADDRESS + " TEXT" +
                        "," + COLUMN_LOCATION_CITY + " TEXT)");
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_USER_LOCATIONS +
                        "(" + COLUMN_USER_LOCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                        "," + COLUMN_USER_ID_FK + " INTEGER" +
                        "," + COLUMN_LOCATION_ID_FK + " INTEGER" +
                        ",FOREIGN KEY (" + COLUMN_USER_ID_FK + ") " +
                        "REFERENCES " + TABLE_USER + "(" + COLUMN_USER_ID + ")" +
                        ",FOREIGN KEY (" + COLUMN_LOCATION_ID_FK + ") " +
                        "REFERENCES " + TABLE_LOCATION + "(" + COLUMN_LOCATION_ID + "))");
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_SHIFTS +
                        "(" + COLUMN_SHIFT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                        "," + COLUMN_SHIFT_DATE + " TEXT" +
                        "," + COLUMN_SHIFT_TIME + " TEXT" +
                        "," + COLUMN_SHIFT_END_TIME + " TEXT" +
                        "," + COLUMN_SHIFT_AVAILABLITY + " TEXT" +
                        "," + COLUMN_LOCATION_ID_FK + " INTEGER" +
                        ",FOREIGN KEY (" + COLUMN_LOCATION_ID_FK + ") " +
                        "REFERENCES " + TABLE_LOCATION + " (" + COLUMN_LOCATION_ID + "))");
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_USER_SHIFT +
                        "(" + COLUMN_USER_SHIFT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                        "," + COLUMN_USER_SHIFT_COMPLETED + " TEXT" +
                        "," + COLUMN_SHIFT_ID_FK + " INTEGER" +
                        "," + COLUMN_USER_ID_FK + " INTEGER" +
                        ",FOREIGN KEY (" + COLUMN_SHIFT_ID_FK + ") " +
                        "REFERENCES " + TABLE_SHIFTS + " (" + COLUMN_SHIFT_ID + ")" +
                        ",FOREIGN KEY (" + COLUMN_USER_ID_FK + ") " +
                        "REFERENCES " + TABLE_USER + " (" + COLUMN_USER_ID + "))");
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_ARRESTS +
                        "(" + COLUMN_ARREST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                        "," + COLUMN_ARREST_DATE + " TEXT" +
                        "," + COLUMN_ARREST_TIME + " TEXT" +
                        "," + COLUMN_ARREST_TYPE + " TEXT" +
                        "," + COLUMN_LOCATION_ID_FK + " INTEGER" +
                        "," + COLUMN_USER_ID_FK + " INTEGER" +
                        ",FOREIGN KEY (" + COLUMN_LOCATION_ID_FK + ") " +
                        "REFERENCES " + TABLE_LOCATION + " (" + COLUMN_LOCATION_ID + ")" +
                        ",FOREIGN KEY (" + COLUMN_USER_ID_FK + ") " +
                        "REFERENCES " + TABLE_USER + " (" + COLUMN_USER_ID + "))");
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_DESCRIPTION +
                        "(" + COLUMN_DESCRIPTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                        "," + COLUMN_DESCRIPTION_NAME + " TEXT" +
                        "," + COLUMN_DESCRIPTION_HEIGHT + " TEXT" +
                        "," + COLUMN_DESCRIPTION_WEIGHT + " TEXT" +
                        "," + COLUMN_DESCRIPTION_HAIR_COLOR + " TEXT" +
                        "," + COLUMN_DESCRIPTION_EYE_COLOR + " TEXT" +
                        "," + COLUMN_DESCRIPTION_SKIN_COLOR + " TEXT" +
                        "," + COLUMN_DESCRIPTION_HAS_TATOO + " TEXT" +
                        "," + COLUMN_ARREST_ID_FK + " INTEGER" +
                        ",FOREIGN KEY (" + COLUMN_ARREST_ID_FK + ") " +
                        "REFERENCES " + TABLE_ARRESTS + " (" + COLUMN_ARREST_ID + "))");
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_TATOO +
                        "(" + COLUMN_TATOO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" +
                        "," + COLUMN_TATOO_SIZE + " TEXT" +
                        "," + COLUMN_TATOO_LOCATION + " TEXT" +
                        "," + COLUMN_TATOO_DESCRIPTION + " TEXT" +
                        "," + COLUMN_TATOO_COLOR_PRIMARY + " TEXT" +
                        "," + COLUMN_TATOO_COLOR_SECONDARY + " TEXT" +
                        "," + COLUMN_DESCRIPTION_ID_FK + " INTEGER" +
                        ",FOREIGN KEY (" + COLUMN_DESCRIPTION_ID_FK + ") " +
                        "REFERENCES " + TABLE_DESCRIPTION + " (" + COLUMN_DESCRIPTION_ID + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    public boolean isAdmin(String userEmail)
    {
        User user;
        Long user_id;

        user_id = getUserIdByEmail(userEmail);
        user = getUserById(user_id);

        if(user.getIsAdmin().equals("YES"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void seedAdmin()
    {
        SQLiteDatabase rdb = this.getReadableDatabase();
        Cursor c;

        c = rdb.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_ID + " ='" + 0 + "'", null);

        if(!c.moveToFirst())
        {
            SQLiteDatabase wdb = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_NAME, "Admin");
            values.put(COLUMN_USER_EMAIL, "admin@burn.ca");
            values.put(COLUMN_USER_PASSWORD, "1234");
            values.put(COLUMN_USER_SECURE_NUM, "123456");
            values.put(COLUMN_USER_IS_ADMIN, "YES");
            wdb.insert(TABLE_USER, null, values);
            wdb.close();
        }
        else
        {

        }
    }

    public Cursor searchArrestLikeText(String text)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_DESCRIPTION + " WHERE " + COLUMN_DESCRIPTION_NAME + " LIKE '%" + text + "%'", null);
    }

    public Cursor searchArrestLikeDescription(Description description)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_DESCRIPTION + " WHERE " + "" + " LIKE '%" + description.getName() + "%'", null);
    }

    public Cursor readAllLocations() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_LOCATION + "", null);
    }

    public Cursor readAvailableShifts(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_SHIFTS + " WHERE " + COLUMN_SHIFT_AVAILABLITY + "='YES' AND " + COLUMN_LOCATION_ID_FK + "='" + id + "'", null);
    }

    public ArrayList<String> getAllLocations() {
        ArrayList<String> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_LOCATION + "", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            array_list.add(res.getString(res.getColumnIndex(COLUMN_LOCATION_NAME)));
            res.moveToNext();
        }
        res.close();
        return array_list;
    }

    public Cursor readAllUserShifts(long id) {
        ArrayList<Long> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_USER_SHIFT + " WHERE " + COLUMN_USER_ID_FK + "='" + id + "' AND " + COLUMN_USER_SHIFT_COMPLETED + "='NO'", null);

        if(res.moveToFirst())
        {
            while (!res.isAfterLast()) {
                array_list.add(res.getLong(res.getColumnIndex(COLUMN_SHIFT_ID_FK)));
                res.moveToNext();
            }
            res.close();

            String where_clause = "";
            for (int i = 0; i < array_list.size(); i++) {
                where_clause = where_clause + COLUMN_SHIFT_ID + "='" + array_list.get(i) + "'";
                if (i != (array_list.size() - 1))
                    where_clause = where_clause + " OR ";
            }

            return db.rawQuery("SELECT * FROM " + TABLE_SHIFTS + " WHERE " + where_clause + "", null);
        }

        return null;
    }

    public void newUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_SECURE_NUM, user.getSecureNum());
        values.put(COLUMN_USER_IS_ADMIN, "NO");
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void newLocation(Location location)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LOCATION_CITY, location.getCity());
        values.put(COLUMN_LOCATION_ADDRESS, location.getAddress());
        values.put(COLUMN_LOCATION_NAME, location.getName());
        db.insert(TABLE_LOCATION, null, values);
        db.close();
    }

    public void newShift(Shift shift)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SHIFT_DATE, shift.getDate());
        values.put(COLUMN_SHIFT_TIME, shift.getTime());
        values.put(COLUMN_SHIFT_END_TIME, shift.getEndTime());
        values.put(COLUMN_SHIFT_AVAILABLITY, "YES");
        values.put(COLUMN_LOCATION_ID_FK, shift.getLocation_id());
        db.insert(TABLE_SHIFTS, null, values);
        db.close();
    }

    public void reserveShift(long id)
    {
        Shift shift = getShiftById(id);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SHIFT_DATE, shift.getDate());
        values.put(COLUMN_SHIFT_TIME, shift.getTime());
        values.put(COLUMN_SHIFT_END_TIME, shift.getEndTime());
        values.put(COLUMN_SHIFT_AVAILABLITY, "NO");
        values.put(COLUMN_LOCATION_ID_FK, shift.getLocation_id());

        db.update(TABLE_SHIFTS, values, COLUMN_SHIFT_ID + " = ?",
                new String[]{String.valueOf(shift.getId())});
        db.close();
    }

    public void newUserShift(long shift_id, long user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_SHIFT_COMPLETED, "NO");
        values.put(COLUMN_USER_ID_FK, user_id);
        values.put(COLUMN_SHIFT_ID_FK, shift_id);

        db.insert(TABLE_USER_SHIFT, null, values);
        db.close();
    }

    public void finishUserShift(long shift_id, long user_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_SHIFT_COMPLETED, "YES");
        values.put(COLUMN_USER_ID_FK, user_id);
        values.put(COLUMN_SHIFT_ID_FK, shift_id);

        db.update(TABLE_USER_SHIFT, values, COLUMN_SHIFT_ID_FK + " = ?",
                new String[]{String.valueOf(shift_id)});
        db.close();
    }

    void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, user.getId());
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_SECURE_NUM, user.getSecureNum());
        values.put(COLUMN_USER_IS_ADMIN, "NO");
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    //Checks if the user exists based on their email
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        SQLiteDatabase db = this.getReadableDatabase();

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    //Checks if the user exists based on their email and password
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public Location getLocationById(long id)
    {
        Location location = new Location();
        Cursor c;

        c = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_LOCATION + " WHERE " + COLUMN_LOCATION_ID + "='" + id +"'", null);

        c.moveToFirst();
        location.setId(id);
        location.setName(c.getString(c.getColumnIndex(COLUMN_LOCATION_NAME)));
        location.setCity(c.getString(c.getColumnIndex(COLUMN_LOCATION_CITY)));
        location.setAddress(c.getString(c.getColumnIndex(COLUMN_LOCATION_ADDRESS)));

        c.close();
        return location;
    }

    public User getUserById(long id)
    {
        User user = new User();
        Cursor c;

        c = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_ID + "='" + id +"'", null);

        c.moveToFirst();
        user.setId(id);
        user.setName(c.getString(c.getColumnIndex(COLUMN_USER_NAME)));
        user.setEmail(c.getString(c.getColumnIndex(COLUMN_USER_EMAIL)));
        user.setIsAdmin(c.getString(c.getColumnIndex(COLUMN_USER_IS_ADMIN)));
        user.setSecureNum(c.getInt(c.getColumnIndex(COLUMN_USER_SECURE_NUM)));

        c.close();
        return user;
    }

    public Shift getShiftById(long id)
    {
        Shift shift = new Shift();
        Cursor c;

        c = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_SHIFTS + " WHERE " + COLUMN_SHIFT_ID + "='" + id +"'", null);

        c.moveToFirst();
        shift.setDate(c.getString(c.getColumnIndex(COLUMN_SHIFT_DATE)));
        shift.setTime(c.getString(c.getColumnIndex(COLUMN_SHIFT_TIME)));
        shift.setEndTime(c.getString(c.getColumnIndex(COLUMN_SHIFT_END_TIME)));
        shift.setId(c.getLong(c.getColumnIndex(COLUMN_SHIFT_ID)));
        shift.setLocation_id(c.getLong(c.getColumnIndex(COLUMN_LOCATION_ID_FK)));

        c.close();
        return shift;
    }

    public Description getDescriptionById(long id)
    {
        Description description = new Description();
        Cursor c;

        c = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_DESCRIPTION + " WHERE " + COLUMN_DESCRIPTION_ID + "='" + id +"'", null);

        c.moveToFirst();
        description.setDescriptionId(id);
        description.setName(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION_NAME)));
        description.setHeight(c.getInt(c.getColumnIndex(COLUMN_DESCRIPTION_HEIGHT)));
        description.setWeight(c.getInt(c.getColumnIndex(COLUMN_DESCRIPTION_WEIGHT)));
        description.setHairColor(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION_HAIR_COLOR)));
        description.setEyeColor(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION_EYE_COLOR)));
        description.setSkinColor(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION_SKIN_COLOR)));
        description.setHasTatoo(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION_HAS_TATOO)));
        description.setArrestId(c.getLong(c.getColumnIndex(COLUMN_ARREST_ID_FK)));

        c.close();
        return description;
    }

    public Arrest getArrestById(long id)
    {
        Arrest arrest = new Arrest();
        Cursor c;

        c = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_ARRESTS + " WHERE " + COLUMN_ARREST_ID + "='" + id +"'", null);

        c.moveToFirst();
        arrest.setArrestID(id);
        arrest.setDate(c.getString(c.getColumnIndex(COLUMN_ARREST_DATE)));
        arrest.setTime(c.getString(c.getColumnIndex(COLUMN_ARREST_TIME)));
        arrest.setType(c.getString(c.getColumnIndex(COLUMN_ARREST_TYPE)));
        arrest.setLocationID(c.getLong(c.getColumnIndex(COLUMN_LOCATION_ID_FK)));
        arrest.setUserID(c.getLong(c.getColumnIndex(COLUMN_USER_ID_FK)));

        c.close();
        return arrest;
    }

    public String getUsernameByEmail(String email)
    {
        String Username = "";
        Cursor c;

        c = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_EMAIL + "='" + email + "'", null);
        if (c.moveToFirst())
        {
            Username = (c.getString(c.getColumnIndex(COLUMN_USER_NAME)));
        }
        c.close();

        return Username;
    }

    public long getUserIdByEmail(String email)
    {
        long user_id = 0;
        Cursor c;

        c = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_EMAIL + "='" + email + "'", null);
        if (c.moveToFirst())
        {
            user_id = (c.getLong(c.getColumnIndex(COLUMN_USER_ID)));
        }
        c.close();

        return user_id;
    }

    public void newArrest(Arrest arrest, Description description) {
        long arrestId;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ARREST_DATE, arrest.getDate());
        values.put(COLUMN_ARREST_TIME, arrest.getTime());
        values.put(COLUMN_ARREST_TYPE, arrest.getType());
        values.put(COLUMN_LOCATION_ID_FK, arrest.getLocationID());
        values.put(COLUMN_USER_ID_FK, arrest.getUserID());

        db.insert(TABLE_ARRESTS, null, values);
        db.close();

        //Dumb hack....
        arrestId = getArrestIdByTime(arrest.getTime());
        description.setArrestId(arrestId);

        newDescription(description, arrestId);
    }

    Long getArrestIdByTime(String time)
    {
        long arrest_id = 0;
        Cursor c;

        c = this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_ARRESTS + " WHERE " + COLUMN_ARREST_TIME + "='" + time + "'", null);
        if (c.moveToFirst())
        {
            arrest_id = (c.getLong(c.getColumnIndex(COLUMN_USER_ID)));
        }
        c.close();

        return arrest_id;
    }

    public void newDescription(Description description, long arrestId)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION_NAME, description.getName());
        values.put(COLUMN_DESCRIPTION_HEIGHT, description.getHeight());
        values.put(COLUMN_DESCRIPTION_WEIGHT, description.getWeight());
        values.put(COLUMN_DESCRIPTION_HAIR_COLOR, description.getHairColor());
        values.put(COLUMN_DESCRIPTION_EYE_COLOR, description.getEyeColor());
        values.put(COLUMN_DESCRIPTION_SKIN_COLOR, description.getSkinColor());
        values.put(COLUMN_DESCRIPTION_HAS_TATOO, description.getHasTatoo());
        values.put(COLUMN_ARREST_ID_FK, description.getArrestId());
        db.insert(TABLE_DESCRIPTION, null, values);
        db.close();
    }

    public void newTatoo(Tatoo tatoo)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TATOO_LOCATION, tatoo.getLocation());
        values.put(COLUMN_TATOO_SIZE, tatoo.getSize());
        values.put(COLUMN_TATOO_DESCRIPTION, tatoo.getDescription());
        values.put(COLUMN_TATOO_COLOR_PRIMARY, tatoo.getPrimary());
        values.put(COLUMN_TATOO_COLOR_SECONDARY, tatoo.getSecondary());
        values.put(COLUMN_DESCRIPTION_ID_FK, tatoo.getDescriptionId());
        db.insert(TABLE_TATOO, null, values);
        db.close();
    }

    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_NAME,
                COLUMN_USER_EMAIL,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_SECURE_NUM
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                user.setSecureNum(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_SECURE_NUM))));

                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }
}
