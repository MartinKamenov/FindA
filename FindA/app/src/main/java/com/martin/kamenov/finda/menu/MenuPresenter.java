package com.martin.kamenov.finda.menu;

import com.martin.kamenov.finda.base.BaseContracts.View;

/**
 * Created by Martin on 3.10.2017 г..
 */

public class MenuPresenter implements MenuContracts.IMenuPresenter {
    private View view;

    @Override
    public void subscribe(View view) {
        this.view = view;
    }

    @Override
    public void unsubscribe() {
        this.view = null;
    }
}
