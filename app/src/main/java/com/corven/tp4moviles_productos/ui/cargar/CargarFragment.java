package com.corven.tp4moviles_productos.ui.cargar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.corven.tp4moviles_productos.databinding.FragmentCargarBinding;


public class CargarFragment extends Fragment {

    private FragmentCargarBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CargarViewModel cargarViewModel =
                new ViewModelProvider(this).get(CargarViewModel.class);

        binding = FragmentCargarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        cargarViewModel.getMensaje().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje != null){
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
                if (mensaje.contains("exito")) {
                    binding.etCodigo.setText("");
                    binding.etDesc.setText("");
                    binding.etPrecio.setText("");
                    binding.etCodigo.requestFocus();
                }
                cargarViewModel.limpiarMensaje();
            }

        });

        binding.btCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cod = binding.etCodigo.getText().toString();
                String desc = binding.etDesc.getText().toString();
                String prec = binding.etPrecio.getText().toString();
                cargarViewModel.validarYGuardar(cod, desc, prec);
            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}