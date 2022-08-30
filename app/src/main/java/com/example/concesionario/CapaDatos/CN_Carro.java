package com.example.myapplication.CapaDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CN_Carro extends Conexion{
    Context context;
    public CN_Carro(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public Cursor search(String Placa){
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
    public int Actualizar(String placa,String marca, String modelo, int valor, int Activo){
        int Actulizado =0;
        try{
            Conexion conexion = new Conexion(context);
            SQLiteDatabase db = conexion.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Placa",placa);
            contentValues.put("Marca",marca);
            contentValues.put("Modelo",modelo);
            contentValues.put("Valor",valor);
            contentValues.put("Activo",Activo);
            Actulizado = db.update("Carro",contentValues,"Placa ='"+placa+"'",null);
        }catch (Exception e){
            e.toString();
        }
        return Actulizado;
    }

    public long Guardar(String placa,String marca, String modelo, int valor){
        long Gurdado =0;
        try{
            Conexion conexion = new Conexion(context);
            SQLiteDatabase db = conexion.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Placa",placa);
            contentValues.put("Marca",marca);
            contentValues.put("Modelo",modelo);
            contentValues.put("Valor",valor);
            Gurdado = db.insert("Carro",null,contentValues);
        }catch (Exception e){
            e.toString();
        }
        return Gurdado;
    }

    public int Anular (String placa){
        int Anulado =0;
        try{
            Conexion conexion = new Conexion(context);
            SQLiteDatabase db = conexion.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Activo",Anulado);
            Anulado = db.update("Carro",contentValues,"Placa ='"+placa+"'",null);
        }catch (Exception e){
            e.toString();
        }
        return Anulado;
    }
}
