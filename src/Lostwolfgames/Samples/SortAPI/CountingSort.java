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
public class CountingSort implements BaseSortableObject{
    public static final int STAGE_INDEX = 0;
    public static final int STAGE_SWITCHING = 1;
    public static final int STAGE_COMPLETE  = 2;
    private int index = 0;
    private int stage = STAGE_INDEX;
    
    private RenderObject[] render;
    private int locations[];
    
    public CountingSort(RenderObject[] render){
        this.render = render;
        locations = new int[render.length];
    }

    @Override
    public RenderObject[] getRenderPoints() {
        return this.render;
    }

    @Override
    public boolean update() {
        switch(stage){
            case STAGE_INDEX:
                locations[index] =render[index].getValue();
                render[index].setCode(RenderObject.INDEXED);
                render[index].setCode(RenderObject.INDEXED);
                index++;
                if(index == render.length){
                    stage = STAGE_SWITCHING;
                    index = 0;
                    render[0].setCode(RenderObject.TARGETED);
                }
                else{
                    render[index].setCode(RenderObject.TARGETED);
                }
                break;
            case STAGE_SWITCHING:
                
                int locTemp = locations[index];
                RenderObject renderTemp = render[index];
                
                locations[index] = locations[locTemp];
                render[index] = render[locTemp];
                
                locations[locTemp] = locTemp;
                render[locTemp] = renderTemp;
                renderTemp.setCode(RenderObject.SORTED);
                while(index != render.length && render[index].getCode()==RenderObject.SORTED){
                    index++;
                }
                if(index == render.length){
                    stage = STAGE_COMPLETE;
                }
                else{
                    render[index].setCode(RenderObject.TARGETED);
                }
                break;
            case STAGE_COMPLETE:
                return true;
        }
        return false;
    }

    @Override
    public void set(RenderObject[] obj) {
        this.render = obj;
        index = 0;
        this.stage = STAGE_INDEX;
        locations = new int[render.length];
        render[0].setCode(RenderObject.TARGETED);
    }
    
}
