package com.dnd.dao;

import com.dnd.model.Model;

public abstract class ModelDOA {


    public abstract void insert(Model model);

    public abstract void update(Model model);

    public abstract void delete(Model model);


    public abstract Model get(Model model);
}
