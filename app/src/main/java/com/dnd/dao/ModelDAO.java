package com.dnd.dao;

import com.dnd.model.Model;
import java.util.List;
public abstract class ModelDAO {


    public abstract void insert(Model model);

    public abstract void update(Model model);

    public abstract void delete(Model model);


    public abstract Model get(Model model);

    public abstract String getModelName();

    public abstract List<String> getAttributes();
}
