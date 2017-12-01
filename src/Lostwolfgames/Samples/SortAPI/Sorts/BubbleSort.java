/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lostwolfgames.Samples.SortAPI.Sorts;

import Lostwolfgames.Samples.Graphics.RenderObject;
import Lostwolfgames.Samples.SortAPI.BaseSortableObject;

/**
 *
 * @author Navnik
 */
public class BubbleSort implements BaseSortableObject{
    public static final int INIT_COMPARE = 0;
    public static final int SWAP = 1;
    public static final int SHIFT_SELECTION = 2;
    
    public static final int AMOUNT_OF_STAGES = 3;
    
    private RenderObject[] objects;
    private int index;
    private int stage = 0;
    private boolean swap = false;
    
    public BubbleSort(RenderObject[] o){
        objects = o;
    }
    
    @Override
    public RenderObject[] getRenderPoints() {
        return  objects;
    }
    @Override
    public boolean update(){
        if(stage == -1){
            objects[0].setCode(RenderObject.TARGETED);
            index = 0;
            stage = 0;
            return false;
        }
        switch(stage%AMOUNT_OF_STAGES){
           case INIT_COMPARE:
               if(index < objects.length-1){
                    swap = objects[index].compare(objects[index+1]) < 0;
                    if(objects[index+1].getCode()!= RenderObject.SORTED){
                        objects[index+1].setCode(RenderObject.INDEXED);
                    }
               }
               else{
                   swap = false;
               }
               break;
           case SWAP:
               if(swap){
                   RenderObject temp = objects[index];
                   objects[index] = objects[index+1];
                   objects[index+1] = temp;
                   break;
               }
               stage++;
           case SHIFT_SELECTION:
               if(index == 0 && objects[index+1].getCode() == RenderObject.SORTED){
                   objects[0].setCode(RenderObject.SORTED);
                   return true;
               }
               else if(index == objects.length-1 || objects[index+1].getCode() == RenderObject.SORTED){
                   objects[index].setCode(RenderObject.SORTED);
                   index = 0;
                   objects[index].setCode(RenderObject.TARGETED);
               }
               
               else{
                   objects[index].setCode(RenderObject.UNMARKED);
                   index++;
                   objects[index].setCode(RenderObject.TARGETED);
               }
               break;
        }
        stage++;
        return false;
    }

    @Override
    public void set(RenderObject[] objs) {
        this.objects = objs;
        this.stage = -1;
    }
}
