package com.wiecny.todoapp.repository.cache;

import com.wiecny.todoapp.model.MyEntity;

public interface Cache {

    MyEntity getById(Integer id);

    MyEntity removeById(Integer id);
}
