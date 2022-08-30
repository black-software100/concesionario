package com.example.myapplication.CapaDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexion extends SQLiteOpenHelper {

    static final String DATABASE = "concesionario";
    static final int DATAVERSION = 1;

    public Conexion(@Nullable Context context) {
        super(context,DATABASE,null,DATAVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Carro = "create table Carro(Placa text primary key,Marca text not null,Modelo text not null" +
                ",Valor integer not null,Activo integer default 1)";
        String Factura = "create table Factura(Codigo text primary key,fecha text not null,Placa text not null,Activo integer  default 1, foreign key (Placa)" +
                "references Carro(Placa))";
        db.execSQL(Carro);
        db.execSQL(Factura);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
