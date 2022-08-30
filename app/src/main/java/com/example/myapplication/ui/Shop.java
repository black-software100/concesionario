package com.example.myapplication.ui;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.capaEntidad.CE_Carro;
import com.example.myapplication.capaEntidad.CE_Factura;

import java.util.Calendar;


public class Shop extends Fragment {
    View view;
    EditText edtFactura,edtFecha,edtplaca,edtMarca,edtModelo,edtValor;
    CheckBox cbx;
    Button btnConsultar,btnBuscar,btnEliminar,BtnGuardar,BtnLimpiar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_shop, container, false);
        edtFactura = view.findViewById(R.id.editfactura);
        edtFecha = view.findViewById(R.id.ediFecha);
        edtplaca = view.findViewById(R.id.editPlaca);
        edtMarca = view.findViewById(R.id.editMarca);
        edtModelo = view.findViewById(R.id.editModelo);
        edtValor = view.findViewById(R.id.editvalor);

        cbx = view.findViewById(R.id.cbxAxtivo);

        btnConsultar = view.findViewById(R.id.btnConsultar);
        btnBuscar = view.findViewById(R.id.btnBuscar_s);
        btnEliminar = view.findViewById(R.id.btnAnular_s);
        BtnGuardar = view.findViewById(R.id.btnGuardar_s);
        BtnLimpiar = view.findViewById(R.id.btnLimpiar_s);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buscar();
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultar();
            }
        });
        BtnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Guardar();
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar();
            }
        });
        BtnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Limpiar();
            }
        });

        edtFecha.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                int dia;
                int mes;
                int ano;
                final Calendar c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                ano = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtFecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                }
                        ,dia,mes,ano);
                datePickerDialog.show();
            }
        });
        return view;
    }

    public void Guardar(){
        String Codigo,Fecha,Placa;
        int Respuesta = 0;

        CE_Factura Factura =new CE_Factura(getContext());
        Codigo = edtFactura.getText().toString();
        Fecha = edtFecha.getText().toString();
        Placa = edtplaca.getText().toString();

        Respuesta = Factura.Guardar(Placa,Fecha,Codigo);

        switch (Respuesta){
            case 0 :
                Toast.makeText(getContext(), "Error de la app", Toast.LENGTH_SHORT).show();
                break;
            case 1 :
                Toast.makeText(getContext(), "No dejar el campo del codigo de la factura vacio", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getContext(), "No dejar el campo de la fecha vacio", Toast.LENGTH_SHORT).show();
                break;
            case 3 : Toast.makeText(getContext(), "No dejar el campo de la placa vacio", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getContext(), "Guardado ", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(getContext(), "error al guardar", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(getContext(), "Actulizado", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                Toast.makeText(getContext(), "error al actualizar", Toast.LENGTH_SHORT).show();
                break;
            case 8:
                Toast.makeText(getContext(), "No se puede vender este carro por que ya esta comprado", Toast.LENGTH_SHORT).show();
                break;
        }
        Limpiar();
    }
    public void Buscar (){
        CE_Factura CAR =new CE_Factura(getContext());
        String Placa;
        Placa = edtplaca.getText().toString();
        Cursor cursor;
        cursor = CAR.buscar(Placa);
        if(cursor.moveToNext()) {
            try{
                edtMarca.setText(cursor.getString(1));
                edtModelo.setText(cursor.getString(2));
                edtValor.setText(cursor.getString(3));
            }catch (Exception e){
                e.toString();
            }
        }
    }
    public void  consultar(){
        CE_Factura CAR =new CE_Factura(getContext());
        String Codigo,Activo1;
        Codigo = edtFactura.getText().toString();
        Cursor cursor;
        cursor = CAR.Consultar(Codigo);
        if(cursor.moveToNext()) {
            try{
                edtFecha.setText(cursor.getString(1));
                edtplaca.setText(cursor.getString(2));
                Activo1 =cursor.getString(3);
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
        CE_Factura Carro =new CE_Factura(getContext());
        String Codigo;
        int eliminar;
        Codigo = edtFactura.getText().toString();
        eliminar = Carro.anulado(Codigo);
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
        edtFactura.setText("");
        edtFecha.setText("");
        edtplaca.setText("");
        edtMarca.setText("");
        edtModelo.setText("");
        edtValor.setText("");
       cbx.setChecked(false);
    }
}