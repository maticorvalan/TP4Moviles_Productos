package com.corven.tp4moviles_productos.ui.listar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.corven.tp4moviles_productos.databinding.FragmentListarBinding;
import com.corven.tp4moviles_productos.databinding.ItemListarBinding;
import com.corven.tp4moviles_productos.modelo.Producto;

/**
 * Fragment that demonstrates a responsive layout pattern where the format of the content
 * transforms depending on the size of the screen. Specifically this Fragment shows items in
 * the [RecyclerView] using LinearLayoutManager in a small screen
 * and shows items using GridLayoutManager in a large screen.
 */
public class ListarFragment extends Fragment {

    private FragmentListarBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ListarViewModel listarViewModel =
                new ViewModelProvider(this).get(ListarViewModel.class);

        binding = FragmentListarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.recyclerviewCargar;
        ListAdapter<Producto, ListarViewHolder> adapter = new ListarAdapter();
        recyclerView.setAdapter(adapter);
        listarViewModel.getProductos().observe(getViewLifecycleOwner(), adapter::submitList);
        listarViewModel.recuperarProductos();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private static class ListarAdapter extends ListAdapter<Producto, ListarViewHolder> {

        protected ListarAdapter() {
            super(new DiffUtil.ItemCallback<Producto>() {
                @Override
                public boolean areItemsTheSame(@NonNull Producto oldItem, @NonNull Producto newItem) {
                    return oldItem.getCodigo() == newItem.getCodigo();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Producto oldItem, @NonNull Producto newItem) {
                    return oldItem.getDescripcion().equals(newItem.getDescripcion()) &&
                            oldItem.getPrecio() == newItem.getPrecio();
                }
            });
        }

        @NonNull
        @Override
        public ListarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemListarBinding binding = ItemListarBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ListarViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ListarViewHolder holder, int position) {

            Producto p = getItem(position);

            holder.tvCodigo.setText("Código: " + p.getCodigo());
            holder.tvDescripcion.setText(p.getDescripcion());
            holder.tvPrecio.setText("$ " + p.getPrecio());
        }
    }

    private static class ListarViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvCodigo;
        private final TextView tvDescripcion;
        private final TextView tvPrecio;

        public ListarViewHolder(ItemListarBinding binding) {
            super(binding.getRoot());
            tvCodigo = binding.tvCodigo;
            tvDescripcion = binding.tvDescripcion;
            tvPrecio = binding.tvPrecio;
        }
    }
}
