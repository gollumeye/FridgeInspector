package com.example.fridgeinspector.ui.recipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridgeinspector.CategoryRecyclerviewAdapter;
import com.example.fridgeinspector.Receipe;
import com.example.fridgeinspector.RecipesRecyclerviewAdapter;
import com.example.fridgeinspector.databinding.FragmentRecipesBinding;

import java.util.ArrayList;

public class RecipesFragment extends Fragment {

    private FragmentRecipesBinding binding;
    private ArrayList<Receipe> receipes;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RecipesViewModel recipesViewModel =
                new ViewModelProvider(this).get(RecipesViewModel.class);

        binding = FragmentRecipesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.receipesRecyclerView.setLayoutManager(linearLayoutManager);

        //TODO: get Receipe Data From File:
        receipes = new ArrayList<>();
        ArrayList<String> ingridients1 = new ArrayList<>();
        ingridients1.add("Mehl");
        ingridients1.add("Milch");
        ingridients1.add("Butter");
        receipes.add(new Receipe("Pfannkuchen", ingridients1, "Eier mit Milch, Zucker, Salz, Mehl und Mineralwasser zu einem glatten Teig rühren. Bei Bedarf noch etwas Mehl oder Wasser hinzugeben, um die gewünschte Konsistenz zu erreichen.\nEine beschichtete Pfanne mit etwas Speiseöl erhitzen. Mit einer Schöpfkelle eine Kelle Teig in die Pfanne geben und die Pfanne kurz in jede Richtung schwenken um den Teig zu verteilen. Den Pfannkuchen von beiden Seiten etwa 1-2 Minuten bräunlich ausbacken. Warm genießen." +
                "\n"));
        ArrayList<String> ingridients2 = new ArrayList<>();
        ingridients1.add("Nudeln");
        ingridients1.add("Tomatensoße");
        receipes.add(new Receipe("Nudeln mit Tomatensoße", ingridients2, "Nudeln kochen. Tomatensoße erhitzen und mit Nudeln vermischen"));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.receipesRecyclerView.getContext(), linearLayoutManager.getOrientation());
        binding.receipesRecyclerView.addItemDecoration(dividerItemDecoration);

        RecyclerView.Adapter adapter = new RecipesRecyclerviewAdapter(getContext(), receipes);
        binding.receipesRecyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}