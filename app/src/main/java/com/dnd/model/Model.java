package com.dnd.model;

import java.util.LinkedList;
import com.dnd.dao.ModelDAO;

public class Model {
    LinkedList<String> stringAttributes;
    LinkedList<Integer> numericAttributes;


    public Model() {
        stringAttributes = new LinkedList<>();
        numericAttributes = new LinkedList<>();
    }


    public void addStringAttribute(String attribute) {
        stringAttributes.add(attribute);
    }

    public void addNumericAttribute(int attribute) {
        numericAttributes.add(attribute);
    }

    
}
