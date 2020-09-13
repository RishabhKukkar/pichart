package com.assignment.ui.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import io.reactivex.disposables.CompositeDisposable;

import static com.assignment.utils.ConstantUtils.ADD_FRAGMENT;
import static com.assignment.utils.ConstantUtils.REPLACE_FRAGMENT;

public class BaseFragment extends Fragment {
    //Objects
    public CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void changeFragment(FragmentManager fragmentManager, int container, Fragment fragment,
                               int action, boolean addToBackStack, String TAG) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (action == ADD_FRAGMENT)
            fragmentTransaction.add(container, fragment);
        else if (action == REPLACE_FRAGMENT) {
            fragmentTransaction.replace(container, fragment);
        } else
            return;
        if (addToBackStack)
            fragmentTransaction.addToBackStack(TAG);
        fragmentTransaction.commit();
    }
}
