package com.dnd.model;

import java.util.LinkedList;
import com.dnd.dao.ModelDOA;

public class Model {
    LinkedList<String> stringAttributes;
    LinkedList<Integer> numericAttributes;
    ModelDOA modelDAO;

    public Model() {
        stringAttributes = new LinkedList<>();
        numericAttributes = new LinkedList<>();
    }

    public Model setModelDAO(ModelDOA modelDAO) {
        this.modelDAO = modelDAO;
        return this;
    }

    public void addStringAttribute(String attribute) {
        stringAttributes.add(attribute);
    }

    public void addNumericAttribute(int attribute) {
        numericAttributes.add(attribute);
    }

    
}
