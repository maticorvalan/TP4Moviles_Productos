package com.corven.tp4moviles_productos.ui.listar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.corven.tp4moviles_productos.MainActivity;
import com.corven.tp4moviles_productos.modelo.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListarViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Producto>> mProductos;
    public ListarViewModel() {
        mProductos = new MutableLiveData<>();

        recuperarProductos();

    }

    public LiveData<ArrayList<Producto>> getProductos() {
        return mProductos;
    }

    public void recuperarProductos() {
        ArrayList<Producto> listaOrdenada = new ArrayList<>(MainActivity.productos);
        Collections.sort(listaOrdenada, (o1, o2) -> o1.getDescripcion().compareToIgnoreCase(o2.getDescripcion()));
        mProductos.setValue(listaOrdenada);

    }
}