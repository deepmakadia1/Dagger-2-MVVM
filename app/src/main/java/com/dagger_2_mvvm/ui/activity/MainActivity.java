package com.dagger_2_mvvm.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dagger_2_mvvm.R;
import com.dagger_2_mvvm.adapter.RecipePagerAdapter;
import com.dagger_2_mvvm.databinding.ActivityMainBinding;
import com.dagger_2_mvvm.models.entity.CategoryModel;
import com.dagger_2_mvvm.ui.fragment.CategoryFragment;
import com.dagger_2_mvvm.viewmodels.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecipePagerAdapter pagerAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this;
        binding = DataBindingUtil.setContentView(activity, R.layout.activity_main);
        MainActivityViewModel mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        pagerAdapter = new RecipePagerAdapter(getSupportFragmentManager());

        mainActivityViewModel.getCategories().observe(this, new Observer<List<CategoryModel.Categories>>() {
            @Override
            public void onChanged(@Nullable List<CategoryModel.Categories> categories) {
                if (categories != null) {
                    setupViewPager(categories);
                }
            }
        });

        mainActivityViewModel.getProgress().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean != null && aBoolean) {
                    if (!progressDialog.isShowing())
                        progressDialog.show();
                } else {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                }
            }
        });

    }

    private void setupViewPager(List<CategoryModel.Categories> categories) {
        for (CategoryModel.Categories category : categories) {
            pagerAdapter.addFragment(CategoryFragment.newInstance(category.getStrCategory()), category.getStrCategory());
        }
        binding.pagerCategory.setAdapter(pagerAdapter);
        binding.tabCategory.setupWithViewPager(binding.pagerCategory);
    }

}