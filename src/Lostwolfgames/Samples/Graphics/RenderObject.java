/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lostwolfgames.Samples.Graphics;

import Lostwolfgames.Samples.SortAPI.SortHandler;

/**
 *
 * @author Navnik
 */
public class RenderObject {
    public static final int UNMARKED = 0;
    public static final int TARGETED = 1;
    public static final int INDEXED = 3;
    public static final int SORTED = 2;
    private int code, value;
    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }
    
    public void setValue(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
    /**
     * 
     * @param obj objects to be compared
     * @return if a negative number is returned, than the object belongs before this object, if the object is greater, than ahead, if 0, than they are 
     * value equal
     */
    public int compare(RenderObject obj){
        SortHandler.Comparisions();
        return obj.getValue() - getValue();
    }
}
