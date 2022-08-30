package com.example.concesionario.ui;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.concesionario.R;

import com.example.myapplication.capaEntidad.CE_Carro;

public class Home extends Fragment {
    View view;
    EditText edt1,edt2,edt3,edt4;
    CheckBox cbx;
    Button btn1,btn2,btn3,btn4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_home, container, false);


        edt1 = view.findViewById(R.id.TxtPlacaCarro);
        edt2 = view.findViewById(R.id.TxtMarcaCarro);
        edt3 = view.findViewById(R.id.TxtModeloCarro);
        edt4 = view.findViewById(R.id.TxtValorCarro);

        cbx = view.findViewById(R.id.cbxActivoCarro);


        btn1 = view.findViewById(R.id.BtnBuscarCarro);
        btn2 = view.findViewById(R.id.BtnGuardarCarro);
        btn3 = view.findViewById(R.id.BtnAnularCarro);
        btn4 = view.findViewById(R.id.BtnLimpiarCarro);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buscar();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guardar();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Limpiar();
            }
        });
        return  view;
    }

    public void Guardar(){
        String Placa,Marca,Modelo,Valor;
        int Activo,Respuesta;

        CE_Carro Carro =new CE_Carro(getContext());

        Placa = edt1.getText().toString();
        Marca = edt2.getText().toString();
        Modelo = edt3.getText().toString();
        Valor = edt4.getText().toString();
        if(cbx.isChecked())
            Activo = 1;
        else
            Activo = 0;

        Respuesta = Carro.Guardar(Placa,Marca,Modelo,Valor,Activo);

        switch (Respuesta){
            case 1 :
                Toast.makeText(getContext(), "No dejar el campo de la Placa vacio", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getContext(), "No dejar el campo de la marca vacio", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getContext(), "No dejar el campo del modelo vacio", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getContext(), "No dejar el campo del valor vacio", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(getContext(), "Guardado ", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(getContext(), "error al guardar", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                Toast.makeText(getContext(), "Actulizado", Toast.LENGTH_SHORT).show();
                break;
            case 8:
                Toast.makeText(getContext(), "error al actualizar", Toast.LENGTH_SHORT).show();
                break;
        }
        try{
            Limpiar();
        }catch (Exception e){
            e.toString();
        }
        Limpiar();
    }
    public void Buscar (){
        CE_Carro CAR =new CE_Carro(getContext());
        String Placa,Activo1;
        Placa = edt1.getText().toString();
        Cursor cursor;
        cursor = CAR.buscar(Placa);
        if(cursor.moveToNext()) {
            try{
                edt2.setText(cursor.getString(1));
                edt3.setText(cursor.getString(2));
                edt4.setText(cursor.getString(3));
                Activo1 = cursor.getString(4);
                if(Activo1.equals("1"))
                    cbx.setChecked(true);
                else
                    cbx.setChecked(false);
            }catch (Exception e){
                e.toString();
            }
        }
    }
    public void Eliminar(){
        CE_Carro Carro =new CE_Carro(getContext());
        String Placa;
        int eliminar;
        Placa = edt1.getText().toString();
        eliminar = Carro.anulado(Placa);
        switch (eliminar){
            case 0:
                Toast.makeText(getContext(), "Error app", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getContext(), "Desactivado carro", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getContext(), "Error al desactivar", Toast.LENGTH_SHORT).show();
        }
        Limpiar();
    }
    public void Limpiar(){
        edt1.setText("");
        edt2.setText("");
        edt3.setText("");
        edt4.setText("");
        cbx.setChecked(false);
    }
}