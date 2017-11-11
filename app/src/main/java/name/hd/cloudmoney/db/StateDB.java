package name.hd.cloudmoney.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by AkiNobunaga on 2017/10/28.
 */

public class StateDB {
    /**
     * 增加
     */
    public boolean add(Context context, String name) {
        CreateDB helper = new CreateDB(context, 1);
        // 获取数据库实例
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("name", name);
            // 往数据库中插入数据
            long id = db.insert("someState", null, values);
            if (id > 0) {// 插入成功
                Log.e("srsj", "插入成功" + id);
                return true;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        Log.e("srsj", "插入失败");
        return false;
    }

    /**
     * @Description: 修改
     */
    public boolean change(Context context, String name, int state) {
        CreateDB helper = new CreateDB(context, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            // 要修改的数据
            ContentValues values = new ContentValues();
            values.put("state", state);
            // 修改数据库（表名，要修改值，判断条件，条件值）
            long id = db.update("someState", values, "name=?",
                    new String[]{name});
            if (id > 0) {
                Log.e("srsj", "修改成功"+id);
                return true;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        Log.e("srsj", "修改失败");
        return false;
    }

    /**
     * 查询
     */
    public int get(Context context) {
        CreateDB helper = new CreateDB(context, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        try {
            String sql = "select state from someState where name=?";
            cursor = db.rawQuery(sql, null);
            if (cursor != null&&cursor.getCount()>0) {
                    int st=cursor.getInt(cursor.getColumnIndex("state"));
                    if(st==1){
                        Log.e("srsj", "返回1");
                        return 1;
                    }
            }
        } catch (Exception e) {

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        Log.e("srsj", "返回0");
        return 0;
    }
}
