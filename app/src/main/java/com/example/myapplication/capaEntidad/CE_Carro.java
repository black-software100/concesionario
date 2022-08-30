package com.example.myapplication.capaEntidad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.myapplication.CapaDatos.CN_Carro;

public class CE_Carro {

    public CE_Carro(Context context) {
        this.context = context;
    }

    Context context;

    public Cursor buscar (String placa){
        Cursor cursor = null;
        try{
            CN_Carro Carro= new CN_Carro(context);
            cursor = Carro.search(placa);
        }catch (Exception e){
            e.toString();
        }
        return cursor;
    }

    public int Guardar(String placa,String marca, String modelo, String valor, int Activo){
        int Actualizar =0;
        long Guardar = 0;
        int check = Integer.parseInt(valor);
        try{
            if(placa.isEmpty())
                return 1;
            if(marca.isEmpty())
                return 2;
            if(modelo.isEmpty())
                return 3;
            if(valor.isEmpty())
                return 4;
           Cursor cursor;
            CN_Carro Carro= new CN_Carro(context);
            cursor = Carro.search(placa);
            if(cursor.getCount()>0){
                Actualizar = Carro.Actualizar(placa,marca,modelo,check,Activo);
                if (Actualizar !=0)
                    return 5;
                else
                    return 6;
            }else{
                Guardar = Carro.Guardar(placa,marca,modelo,check);
                if(Guardar !=0)
                    return 7;
                else
                    return 8;
            }
        }catch (Exception e){
            return 0;
        }
    }

    public int anulado(String placa){
        Cursor cursor;
        try{
            CN_Carro Carro= new CN_Carro(context);
            cursor = Carro.search(placa);
            if(cursor.getCount()>0){
                Carro.Anular(placa);
                return 1;
            }else {
                return 2;
            }
        }catch (Exception e){

        }
        return 0;
    }
}
