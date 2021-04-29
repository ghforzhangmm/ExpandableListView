package com.example.expandablelistview;

import java.util.ArrayList;

/**
 * 父类分组的实体
 */

public class ChildEntity {

    private int groupColor;
    private String groupName;
    private ArrayList<GrandsonEntity> childNames;


    /* ==========================================================
     * ======================= get method =======================
     * ========================================================== */

    public int getGroupColor() {
        return groupColor;
    }

    public String getGroupName() {
        return groupName;
    }

    public ArrayList<GrandsonEntity> getChildNames() {
        return childNames;
    }


    /* ==========================================================
     * ======================= set method =======================
     * ========================================================== */

    public void setGroupColor(int groupColor) {
        this.groupColor = groupColor;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setChildNames(ArrayList<GrandsonEntity> childNames) {
        this.childNames = childNames;
    }


}