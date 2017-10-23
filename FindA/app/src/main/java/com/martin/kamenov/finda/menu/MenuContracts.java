package com.martin.kamenov.finda.menu;

import com.martin.kamenov.finda.base.BaseContracts;

/**
 * Created by Martin on 3.10.2017 г..
 */

public class MenuContracts {
    public interface IMenuPresenter<IMenuView> extends BaseContracts.Presenter {

    }

    public interface IMenuView<IMenuPresenter> extends  BaseContracts.View, BaseContracts.Navigator {
        void setListeners();
    }
}
