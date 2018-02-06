package com.dayu.singapp.activeandroid;
import java.util.Collection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.util.LruCache;

public final class Cache {
	public static final int DEFAULT_CACHE_SIZE = 1024;
	private static Context sContext;
	private static ModelInfo sModelInfo;
	private static DatabaseHelper sDatabaseHelper;
	private static LruCache<String, Model> sEntities;
	private static boolean sIsInitialized = false;
	private Cache() {
	}
	public static synchronized void initialize(Configuration configuration) {
		if (sIsInitialized) {
			Log.v("ActiveAndroid already initialized.");
			return;
		}

		sContext = configuration.getContext();
		sModelInfo = new ModelInfo(configuration);
		sDatabaseHelper = new DatabaseHelper(configuration);

		// TODO: It would be nice to override sizeOf here and calculate the memory
		// actually used, however at this point it seems like the reflection
		// required would be too costly to be of any benefit. We'll just set a max
		// object size instead.
		sEntities = new LruCache<String, Model>(configuration.getCacheSize());

		openDatabase();

		sIsInitialized = true;

		Log.v("ActiveAndroid initialized successfully.");
	}

	public static synchronized void clear() {
		sEntities.evictAll();
		Log.v("Cache cleared.");
	}

	public static synchronized void dispose() {
		closeDatabase();

		sEntities = null;
		sModelInfo = null;
		sDatabaseHelper = null;

		sIsInitialized = false;

		Log.v("ActiveAndroid disposed. Call initialize to use library.");
	}

	// Database access
	
	public static boolean isInitialized() {
		return sIsInitialized;
	}

	public static synchronized SQLiteDatabase openDatabase() {
		return sDatabaseHelper.getWritableDatabase();
	}

	//SQLiteDatabase getReadableDatabase( )和getWritableDatabase( )  这两种方法又什么区别：
    //    共同点：
    //    打开数据库，如果数据库不存在，先创建再打开
    //            返回操作数据库的SQLiteDatabase对象
    //    都是以读写方式（创建并）打开
    //    区别：
    //    如果磁盘满了，getWritableDatabase 会出错 但是getReadableDatabase不会出错 但是打开数据库是只能读不能写

	public static synchronized void closeDatabase() {
		sDatabaseHelper.close();
	}

	// Context access

	public static Context getContext() {
		return sContext;
	}

	// Entity cache

	public static String getIdentifier(Class<? extends Model> type, Long id) {
		return getTableName(type) + "@" + id;
	}

	public static String getIdentifier(Model entity) {
		return getIdentifier(entity.getClass(), entity.getId());
	}

	//entity 实体 统一体
	public static synchronized void addEntity(Model entity) {
		sEntities.put(getIdentifier(entity), entity);
	}

	public static synchronized Model getEntity(Class<? extends Model> type, long id) {
		return sEntities.get(getIdentifier(type, id));
	}

	public static synchronized void removeEntity(Model entity) {
		sEntities.remove(getIdentifier(entity));
	}

	// Model cache

	public static synchronized Collection<TableInfo> getTableInfos() {
		return sModelInfo.getTableInfos();
	}

	public static synchronized TableInfo getTableInfo(Class<? extends Model> type) {
		return sModelInfo.getTableInfo(type);
	}

	public static synchronized TypeSerializer getParserForType(Class<?> type) {
		return sModelInfo.getTypeSerializer(type);
	}

	public static synchronized String getTableName(Class<? extends Model> type) {
		return sModelInfo.getTableInfo(type).getTableName();
	}
}
