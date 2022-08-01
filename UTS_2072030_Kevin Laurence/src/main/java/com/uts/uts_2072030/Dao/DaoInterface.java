package com.uts.uts_2072030.Dao;

import javafx.beans.Observable;
import javafx.collections.ObservableList;

public interface DaoInterface<T> {
    ObservableList<T> getData();
    int addData(T data);
    int delData(T data);
    int updateData(T data);
}
