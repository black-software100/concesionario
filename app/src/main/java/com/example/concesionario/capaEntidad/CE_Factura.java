package com.example.myapplication.capaEntidad;

import android.content.Context;
import android.database.Cursor;

import com.example.myapplication.CapaDatos.CN_Factura;


public class CE_Factura {
    Context context;

    public CE_Factura(Context context) {
        this.context = context;
    }

    public Cursor buscar (String placa){
        Cursor cursor = null;
        try{
            CN_Factura Carro= new CN_Factura(context);
            cursor = Carro.search_placa(placa);
        }catch (Exception e){
            e.toString();
        }
        return cursor;
    }

    public Cursor Consultar (String placa){
        Cursor cursor = null;
        try{
            CN_Factura Carro= new CN_Factura(context);
            cursor = Carro.search_Factura(placa);
        }catch (Exception e){
            e.toString();
        }
        return cursor;
    }

    public int Guardar(String placa, String Fecha, String Codigo){
        int Actualizar =0;
        long Guardar = 0;
        try{
            if(placa.isEmpty())
                return 1;
            if(Fecha.isEmpty())
                return 2;
            if(Codigo.isEmpty())
                return 3;
            Cursor cursor;
            CN_Factura Carro= new CN_Factura(context);
            cursor = Carro.search_placa_Activo(placa);
            if(cursor.moveToFirst()){
                cursor = Carro.search_Factura(Codigo);
                if(cursor.getCount()>0){
                    Actualizar = Carro.Actualizar(placa,Fecha,Codigo);
                    if (Actualizar !=0)
                        return 4;
                    else
                        return 5;
                }else{
                    Guardar = Carro.Guardar(placa,Fecha,Codigo);
                    if(Guardar !=0)
                        return 6;
                    else
                        return 7;
                }
            }else{
                return 8;
            }
        }catch (Exception e){
            return 0;
        }
    }

    public int anulado(String placa){
        Cursor cursor;
        try{
            CN_Factura Carro= new CN_Factura(context);
            cursor = Carro.search_Factura(placa);
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
