package com.android.haule.androidachitecture.mvvm.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.android.haule.androidachitecture.R;
import com.android.haule.androidachitecture.databinding.ActivityMvvmAnswerListBinding;
import com.android.haule.androidachitecture.mvvm.viewmodels.MvvmAnswerListViewModel;

public class MvvmAnswerListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void bindingView() {
        final ActivityMvvmAnswerListBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_mvvm_answer_list);
        binding.setViewModel(new MvvmAnswerListViewModel(this, binding));
        binding.executePendingBindings();
    }
}
