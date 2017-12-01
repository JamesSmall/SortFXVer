/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lostwolfgames.Samples.SortAPI;

import Lostwolfgames.Samples.Graphics.RenderObject;

/**
 *
 * @author James
 */
public class InsertionSort implements BaseSortableObject{
    public static final int STAGE_INIT = -1;
    public static final int STAGE_GRAB = 0;
    public static final int STAGE_SEARCH_COMPARE = 1;
    public static final int STAGE_SEARCH_SWAP = 2;
    public static final int STAGE_SEARCH_SHIFT = 3;
    
    private int stage = STAGE_INIT;
    private int currentInsertIndex = 0;
    private int currentEndOfList = 0;
    private RenderObject[] render;
    private boolean swap = false;
    
    public InsertionSort(RenderObject[] obj){
        this.render = obj;
    }
    @Override
    public RenderObject[] getRenderPoints() {
        return render;
    }

    @Override
    public boolean update() {
       switch(stage){
           case STAGE_INIT:
               //render[0].setCode(RenderObject.SORTED);
               stage = STAGE_GRAB;
               currentInsertIndex = 0;
               currentEndOfList = 0;
               //render[1].setCode(RenderObject.TARGETED);
               break;
           case STAGE_GRAB:
               render[currentInsertIndex].setCode(RenderObject.SORTED);
               if(currentInsertIndex != 0){
                   render[currentInsertIndex-1].setCode(RenderObject.SORTED);
               }
               if(currentEndOfList == render.length-1){
                   //we are done here
                   return true;
               }
               render[currentEndOfList+1].setCode(RenderObject.TARGETED);
               currentEndOfList++;
               if(currentEndOfList != render.length){
                   //render[currentEndOfList].setCode(RenderObject.TARGETED);
               }
               currentInsertIndex = currentEndOfList;
               stage = STAGE_SEARCH_COMPARE;
               
               break;
           case STAGE_SEARCH_COMPARE:
               if(currentInsertIndex!=0&&render[currentInsertIndex].compare(render[currentInsertIndex-1]) > 0){
                    stage = STAGE_SEARCH_SWAP;
                    render[currentInsertIndex-1].setCode(RenderObject.INDEXED);
               }
               else if(currentInsertIndex != 0){
                   stage = STAGE_GRAB;
                   render[currentInsertIndex-1].setCode(RenderObject.INDEXED);
                   //render[currentInsertIndex].setCode(RenderObject.SORTED);
               }
               else{
                   stage = STAGE_GRAB;
                   //render[currentInsertIndex].setCode(RenderObject.SORTED);
               }
               break;
           case STAGE_SEARCH_SWAP:
                RenderObject temp = render[currentInsertIndex];
                render[currentInsertIndex] = render[currentInsertIndex-1];
                render[currentInsertIndex-1] = temp;
                render[currentInsertIndex].setCode(RenderObject.SORTED);
                currentInsertIndex--;
                stage = STAGE_SEARCH_COMPARE;
                break;
       }
       return false;
    }

    @Override
    public void set(RenderObject[] obj) {
        this.render = obj;
        this.stage = STAGE_INIT;
    }
    
}
