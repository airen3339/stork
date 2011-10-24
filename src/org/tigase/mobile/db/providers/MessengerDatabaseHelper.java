package org.tigase.mobile.db.providers;

import org.tigase.mobile.db.ChatTableMetaData;
import org.tigase.mobile.db.OpenChatsTableMetaData;
import org.tigase.mobile.db.RosterCacheTableMetaData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class MessengerDatabaseHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "mobile_messenger.db";

	public static final Integer DATABASE_VERSION = 4;

	private static final boolean DEBUG = false;

	private static final String TAG = "tigase";

	public MessengerDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql;

		sql = "CREATE TABLE " + ChatTableMetaData.TABLE_NAME + " (";
		sql += ChatTableMetaData.FIELD_ID + " INTEGER PRIMARY KEY, ";
		sql += ChatTableMetaData.FIELD_TYPE + " INTEGER, ";
		sql += ChatTableMetaData.FIELD_JID + " TEXT, ";
		sql += ChatTableMetaData.FIELD_TIMESTAMP + " DATETIME, ";
		sql += ChatTableMetaData.FIELD_THREAD_ID + " TEXT, ";
		sql += ChatTableMetaData.FIELD_BODY + " TEXT, ";
		sql += ChatTableMetaData.FIELD_STATE + " INTEGER";
		sql += ");";
		db.execSQL(sql);

		sql = "CREATE TABLE " + OpenChatsTableMetaData.TABLE_NAME + " (";
		sql += OpenChatsTableMetaData.FIELD_ID + " INTEGER PRIMARY KEY, ";
		sql += OpenChatsTableMetaData.FIELD_JID + " TEXT, ";
		sql += OpenChatsTableMetaData.FIELD_RESOURCE + " TEXT, ";
		sql += OpenChatsTableMetaData.FIELD_TIMESTAMP + " DATETIME, ";
		sql += OpenChatsTableMetaData.FIELD_THREAD_ID + " TEXT";
		sql += ");";
		db.execSQL(sql);

		sql = "CREATE TABLE " + RosterCacheTableMetaData.TABLE_NAME + " (";
		sql += RosterCacheTableMetaData.FIELD_ID + " INTEGER PRIMARY KEY, ";
		sql += RosterCacheTableMetaData.FIELD_JID + " TEXT, ";
		sql += RosterCacheTableMetaData.FIELD_NAME + " TEXT, ";
		sql += RosterCacheTableMetaData.FIELD_GROUP_NAME + " DATETIME, ";
		sql += RosterCacheTableMetaData.FIELD_ASK + " BOOLEAN, ";
		sql += RosterCacheTableMetaData.FIELD_SUBSCRIPTION + " TEXT";
		sql += ");";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(TAG, "Database upgrade from version " + oldVersion + " to " + newVersion);
		db.execSQL("DROP TABLE IF EXISTS " + OpenChatsTableMetaData.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + ChatTableMetaData.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + RosterCacheTableMetaData.TABLE_NAME);
		onCreate(db);
	}

}
