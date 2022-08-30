package com.example.myapplication.CapaDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class CN_Factura extends Conexion{
    Context context;
    public CN_Factura(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public Cursor search_Factura(String Codigo){
        Cursor cursor = null;
        try{
            Conexion conexion = new Conexion(context);
            SQLiteDatabase db = conexion.getReadableDatabase();
            cursor = db.rawQuery("select * from Factura where Codigo ='"+Codigo+"'",null);
        }catch (Exception e){
            e.toString();
        }
        return cursor;
    }

    public Cursor search_placa_Activo(String Placa){
        Cursor cursor = null;
        try{
            Conexion conexion = new Conexion(context);
            SQLiteDatabase db = conexion.getReadableDatabase();
            cursor = db.rawQuery("select * from Carro where Placa ='"+Placa+"' and Activo='"+1+"'",null);
        }catch (Exception e){
            e.toString();
        }
        return cursor;
    }

    public Cursor search_placa(String Placa){
        Cursor cursor = null;
        try{
            Conexion conexion = new Conexion(context);
            SQLiteDatabase db = conexion.getReadableDatabase();
            cursor = db.rawQuery("select * from Carro where Placa ='"+Placa+"'",null);
        }catch (Exception e){
            e.toString();
        }
        return cursor;
    }

    public long Guardar(String placa,String Fecha,  String codigo){
        long Gurdado =0;
        int Anulado =0;
        try{
            Conexion conexion = new Conexion(context);
            SQLiteDatabase db = conexion.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Codigo",codigo);
            contentValues.put("Fecha",Fecha);
            contentValues.put("Placa",placa);
            Gurdado = db.insert("Factura",null,contentValues);
            ContentValues values= new ContentValues();
            values.put("Activo",0);
            db.update("Carro",values,"Placa ='"+placa+"'",null);
        }catch (Exception e){
            e.toString();
        }
        return Gurdado;
    }

    public int Actualizar(String placa,String Fecha, String Codigo){
        int Actulizado =0;
        int Anulado =0;
        try{
            Conexion conexion = new Conexion(context);
            SQLiteDatabase db = conexion.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Codigo",Codigo);
            contentValues.put("Fecha",Fecha);
            contentValues.put("Placa",placa);
            Actulizado = db.update("Factura",contentValues,"Codigo ='"+Codigo+"'",null);
            ContentValues values= new ContentValues();
            values.put("Activo",Anulado);
            db.update("Carro",values,"Placa ='"+placa+"'",null);
        }catch (Exception e){
            e.toString();
        }
        return Actulizado;
    }

    public int Anular (String Codigo){
        int Anulado =0;
        try{
            Conexion conexion = new Conexion(context);
            SQLiteDatabase db = conexion.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Activo",Anulado);
            Anulado = db.update("Factura",contentValues,"Codigo ='"+Codigo+"'",null);
        }catch (Exception e){
            e.toString();
        }
        return Anulado;
    }
}
