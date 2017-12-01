/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lostwolfgames.Samples.SortAPI;

import Lostwolfgames.Samples.Graphics.RenderObject;

/**
 *
 * @author Navnik
 */
public class SelectionSort implements BaseSortableObject{
    public static final int STAGE_NEW = -1;
    public static final int STAGE_COMPARE = 0;
    public static final int STAGE_SHIFT = 2;
    public static final int STAGE_SWAP = 1;
    public static final int STAGE_BACK = 3;
    
    private RenderObject[] objects;
    private int moveToIndex;
    private int selectedIndex;//current high
    private int targetIndex; //current lookat
    private int stage;
    
    public SelectionSort(RenderObject[] obj){
        objects = obj;
        stage = STAGE_NEW;
    }
    @Override
    public boolean update() {
        switch(stage){
            case STAGE_NEW:
                moveToIndex = objects.length;
                stage = STAGE_BACK;
                break;
            case STAGE_SHIFT:
                if(objects[targetIndex].getCode() == RenderObject.TARGETED){
                    objects[targetIndex].setCode(RenderObject.UNMARKED);
                }
                targetIndex--;
                objects[targetIndex].setCode(RenderObject.TARGETED);
                stage = STAGE_COMPARE;
                break;
            case STAGE_COMPARE:
                if(objects[selectedIndex].compare(objects[targetIndex])> 0){
                    objects[selectedIndex].setCode(RenderObject.UNMARKED);
                    objects[targetIndex].setCode(RenderObject.INDEXED);
                    selectedIndex = targetIndex;
                }
                if(targetIndex==0){
                    stage = STAGE_SWAP;
                }
                else{
                    stage = STAGE_SHIFT;
                }
                break;
            case STAGE_SWAP:
                RenderObject temp = objects[selectedIndex];
                objects[selectedIndex] = objects[moveToIndex];
                objects[moveToIndex] = temp;
                stage = STAGE_BACK;
                break;
            case STAGE_BACK:
                moveToIndex--;
                selectedIndex = moveToIndex;
                targetIndex = selectedIndex-1;
                if(moveToIndex <= 0){
                    objects[0].setCode(RenderObject.SORTED);
                    objects[1].setCode(RenderObject.SORTED);
                    return true;
                }
                else{
                    objects[0].setCode(RenderObject.UNMARKED);
                    if(moveToIndex+1 != objects.length){
                        objects[moveToIndex+1].setCode(RenderObject.SORTED);
                    }
                    objects[moveToIndex].setCode(RenderObject.INDEXED);
                    stage = STAGE_COMPARE;
                }
                break;
        }
        
        return false;
    }
    @Override
    public RenderObject[] getRenderPoints() {
       return objects;
    }


    @Override
    public void set(RenderObject[] objs) {
        objects = objs;
        stage = STAGE_NEW;
    }
}
