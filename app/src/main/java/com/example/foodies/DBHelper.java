package com.example.foodies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodies.Models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME = "mydatabase.db";
    final static int DBVERSION = 2;

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);  //constructor
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table orders " +
                        "(id Integer primary key autoincrement," +
                        "name text," +
                        "phone text," +
                        "price int," +
                        "image int," +
                      //  "quantity int," +
                        "description text," +
                        "foodname text)"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP table if exists orders");
        onCreate(sqLiteDatabase);

    }

    //insert function
    public boolean insertOrder(String name, String phone, int price, int image, String desc, String foodName){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        /*
        id =0
        name =1
        phone =2
        price=3
        image=4
        desc=5
        foodName =6
        quantity = 7

         */
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", desc);
        values.put("foodname", foodName);
       // values.put("quantity", quantity);
        long id = database.insert("orders", null, values);
        if (id <=0){
            return false;
        }else{
            return true;
        }
    }

    //select query for show data in order activity
    public ArrayList<OrdersModel> getOrders(){
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,foodname,image,price from orders", null);
        if (cursor.moveToFirst()){
            while (cursor.moveToNext()){
                OrdersModel model = new OrdersModel();
                model.setOrderNumber(cursor.getInt(0) + "");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImaage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3) + "");
                orders.add(model);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    //show database detail in orderdetail
    public Cursor getOrderById(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders where id = "+id, null);
        if(cursor != null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    //update
    public boolean upadetOrder(String name, String phone, int price, int image, String desc, String foodName, int id){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        /*
        id =0
        name =1
        phone =2
        price=3
        image=4
        desc=5
        foodName =6
        quantity = 7

         */
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", desc);
        values.put("foodname", foodName);
        //values.put("quantity", quantity);
        long row = database.update("orders",  values, "id="+id, null);
        if (row <=0){
            return false;
        }else{
            return true;
        }
    }

    //delete data
    public int deleteOrder(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders", "id="+id, null);
    }
}
