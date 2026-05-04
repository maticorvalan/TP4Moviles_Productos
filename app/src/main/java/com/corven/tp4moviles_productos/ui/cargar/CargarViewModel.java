package com.corven.tp4moviles_productos.ui.cargar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.corven.tp4moviles_productos.MainActivity;
import com.corven.tp4moviles_productos.modelo.Producto;

import java.util.ArrayList;

public class CargarViewModel extends AndroidViewModel {

    private MutableLiveData<String> mensaje;

    public CargarViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMensaje() {
        if (mensaje == null){
            mensaje = new MutableLiveData<>();
        }
        return mensaje;
    }


    public void validarYGuardar(String cod, String desc, String prec){
        if(cod == null || desc == null || prec == null || cod.isEmpty() || desc.isEmpty() || prec.isEmpty()){
            mensaje.setValue("Debe completar todos los campos");
            return;
        }
        int codigo = Integer.parseInt(cod);
        double precio = Double.parseDouble(prec);
        if(existente(codigo)){
            mensaje.setValue("El producto ya existe");
        }else{
            Producto p = new Producto(codigo, desc, precio);
            MainActivity.productos.add(p);
            mensaje.setValue("Producto agregado con exito");
        }
    }

    private boolean existente(int cod) {
        for (Producto producto : MainActivity.productos) {
            if (producto.getCodigo() == cod) {
                return true;
            }
        }
        return false;
    }

    public void limpiarMensaje(){
        mensaje.setValue(null);
    }
}