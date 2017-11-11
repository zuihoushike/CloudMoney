package name.hd.cloudmoney.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import name.hd.cloudmoney.bean.User;

/**
 * Created by AkiNobunaga on 2017/10/23.
 */

public class UserDB {
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
            long id = db.insert("personInfo", null, values);
            if (id > 0) {// 插入成功
                Log.v("srsj", "插入成功" + id);
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
        Log.v("srsj", "插入失败");
        return false;
    }
    /**
     * @Description: 修改
     */
    public boolean perfectUser(Context context, String name, String sex,
                               String age) {
        CreateDB helper = new CreateDB(context, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            // 要修改的数据
            ContentValues values = new ContentValues();
            values.put("sex", sex);
            values.put("age", age);
            // 修改数据库（表名，要修改值，判断条件，条件值）
            long id = db.update("personInfo", values, "name=?",
                    new String[] { name });
            if (id > 0) {
                Log.v("srsj", "修改成功");
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
        Log.v("srsj", "修改失败");
        return false;
    }
    /**
     *
     * @Description: 查询所有用户名
     */
    public List<User> getAllUserName(Context context){
        List<User> users =new ArrayList<>();
        //建库建表
        CreateDB helper =new CreateDB(context, 1);
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=null;
        try {
            String sql="select name from personInfo";
            cursor=db.rawQuery(sql, null);
            if(cursor!=null){
                while(cursor.moveToNext()){//一条接一条取
                    User user=new User();
                    user.setName(cursor.getString(cursor.getColumnIndex("name")));
                    users.add(user);//将取出的对象放入集合中
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(cursor!=null){
                cursor.close();
            }
            if(db!=null){
                db.close();
            }
        }
        Log.v("srsj", users.toString());
        return users;
    }
    /**
     * 查询
     */
    public boolean see(Context context,String name){
        CreateDB helper =new CreateDB(context, 1);
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=null;
        try{
            String sql="select*from personInfo where name=?";
            cursor=db.rawQuery(sql, new String[]{name});
            if(cursor!=null){
                if(cursor.getCount()>0){
                    Log.v("srsj", "查询成功");
                    return true;
                }
            }
        }catch(Exception e){

        }finally{
            if(cursor!=null){
                cursor.close();
            }
            if(db!=null){
                db.close();
            }
        }
        Log.v("srsj", "查询失败");
        return false;
    }
    /**
     * 删除
      */
    public boolean delete(Context context, String name) {
        CreateDB helper = new CreateDB(context, 1);
        // 获取数据库实例
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            // 从数据库中删除数据
            long id = db.delete("personInfo", "name=?", new String[]{name});
            if (id > 0) {
                Log.v("srsj", "删除成功" + id);
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
        Log.v("srsj", "插入失败");
        return false;
    }
}
