package com.dagger_2_mvvm.ui.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dagger_2_mvvm.R;
import com.dagger_2_mvvm.adapter.RecipeListAdapter;
import com.dagger_2_mvvm.databinding.FragmentCategoryBinding;
import com.dagger_2_mvvm.models.entity.RecipeModel;
import com.dagger_2_mvvm.util.Constants;
import com.dagger_2_mvvm.viewmodels.CategoryFragmentViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    private Context context;
    private FragmentCategoryBinding binding;

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(String categoryName) {
        CategoryFragment categoryFragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(Constants.CATEGORY_NAME, categoryName);
        categoryFragment.setArguments(args);
        return categoryFragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);

        String categoryName = getArguments() != null ? getArguments().getString(Constants.CATEGORY_NAME) : null;
        CategoryFragmentViewModel categoryFragmentViewModel = ViewModelProviders.of(this).get(CategoryFragmentViewModel.class);

        categoryFragmentViewModel.getRecipeList(categoryName).observe(this, new Observer<List<RecipeModel.Recipe>>() {
            @Override
            public void onChanged(@Nullable List<RecipeModel.Recipe> recipes) {
                setRecyclerView(recipes);
            }
        });

        categoryFragmentViewModel.getProgress().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean != null && aBoolean) {
                    binding.listRecipe.setVisibility(View.GONE);
                    binding.progress.setVisibility(View.VISIBLE);
                } else {
                    binding.listRecipe.setVisibility(View.VISIBLE);
                    binding.progress.setVisibility(View.GONE);
                }
            }
        });

        return binding.getRoot();
    }

    private void setRecyclerView(List<RecipeModel.Recipe> recipes) {

        RecipeListAdapter recipeListAdapter = new RecipeListAdapter(context, recipes);
        binding.listRecipe.setLayoutManager(new LinearLayoutManager(context));
        binding.listRecipe.setAdapter(recipeListAdapter);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
