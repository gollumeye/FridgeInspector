package com.example.fridgeinspector.ui.recipes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridgeinspector.CategoryRecyclerviewAdapter;
import com.example.fridgeinspector.R;
import com.example.fridgeinspector.Receipe;
import com.example.fridgeinspector.RecipesRecyclerviewAdapter;
import com.example.fridgeinspector.databinding.FragmentRecipesBinding;
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
        receipes = new ArrayList<>(); //just some sample data
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

        binding.closeRecipeDetailsButton.setOnClickListener( e->{
            setDetailsFragmentInVisible();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setDetailsFragmentVisible(){
        binding.RecipeDetailsFragmentView.bringToFront();
        //bindingRecipes.recipeDetailsFragmentView.setAlpha(1f);
        binding.RecipeDetailsFragmentView.setVisibility(View.VISIBLE);

        binding.closeRecipeDetailsButton.bringToFront();
        //bindingRecipes.closeRecipeDetailsButton.setAlpha(1f);
        binding.closeRecipeDetailsButton.setVisibility(View.VISIBLE);
    }

    public void setDetailsFragmentInVisible(){
        binding.RecipeDetailsFragmentView.setVisibility(View.GONE);
        binding.closeRecipeDetailsButton.setVisibility(View.GONE);
    }



    public class RecipesRecyclerviewAdapter extends RecyclerView.Adapter<RecipesRecyclerviewAdapter.ViewHolder> {

        private ArrayList<Receipe> list_items;
        private LayoutInflater layoutInflater;
        private View view;
        private FragmentRecipesBinding bindingRecipes;

        public RecipesRecyclerviewAdapter(Context context, ArrayList<Receipe> list_items) {
            this.layoutInflater = LayoutInflater.from(context);
            this.list_items = list_items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            view = layoutInflater.inflate(R.layout.recycler_view_receipes_item_layout, viewGroup, false);
            view.setOnClickListener(e->{
                //TODO: open new Fragment with Recipe information
                setDetailsFragmentVisible();
            });
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            String name = list_items.get(position).getName();
            viewHolder.name.setText(name);
        }

        @Override
        public int getItemCount() {
            return list_items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView name;;

            ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.recyclerViewRecipeName);
            }
        }
    }
}