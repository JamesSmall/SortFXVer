/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lostwolfgames.Samples.SortAPI.Sorts;

import Lostwolfgames.Samples.Graphics.RenderObject;
import Lostwolfgames.Samples.SortAPI.BaseSortableObject;
import java.util.Random;

/**
 *
 * @author James
 */
public class BogoSort implements BaseSortableObject{
    private static Random rand = new Random();
    public static final int STAGE_SWITCHING = 0;
    public static final int STAGE_SHIFTING = 1;
    public static final int STAGE_CHECK = 2;
    public static final int COMPLETE = 3;
    private RenderObject[] objects;
    private int stage;
    private int switchingCounter;
    public BogoSort(RenderObject[] obj){
        this.objects = obj;
    }
    @Override
    public RenderObject[] getRenderPoints() {
        return objects;
    }
    private void clearMarkings(){
        for(RenderObject o:objects){
            o.setCode(RenderObject.UNMARKED);
        }
    }
    @Override
    public boolean update() {
        switch(stage){
            case STAGE_SWITCHING:
                this.clearMarkings();
                int random = Math.abs(rand.nextInt())%objects.length;
                RenderObject temp = this.objects[switchingCounter];
                objects[switchingCounter] = objects[random];
                objects[random] = temp;
                objects[switchingCounter].setCode(RenderObject.INDEXED);
                objects[random].setCode(RenderObject.TARGETED);
                stage = STAGE_SHIFTING;
                break;
            case STAGE_SHIFTING:
                this.clearMarkings();
                if(switchingCounter < objects.length-1){
                    switchingCounter++;
                    objects[switchingCounter].setCode(RenderObject.INDEXED);
                    stage = STAGE_SWITCHING;
                }
                else{
                    stage = STAGE_CHECK;
                    switchingCounter = 0;
                    objects[0].setCode(RenderObject.SORTED);
                }
                break;
            case STAGE_CHECK:
                if(switchingCounter <= objects.length-2){
                    if(objects[switchingCounter].compare(objects[switchingCounter+1]) < 0){
                        switchingCounter = 0;
                        stage = STAGE_SWITCHING;
                        objects[switchingCounter+1].setCode(RenderObject.TARGETED);
                    }
                    else{
                        objects[switchingCounter+1].setCode(RenderObject.SORTED);
                        switchingCounter++;
                    }
                }
                else{
                    stage = COMPLETE;
                }
                break;
            case COMPLETE:
                objects[objects.length-1].setCode(RenderObject.SORTED);
                return true;
        }
        return false;
    }

    @Override
    public void set(RenderObject[] objs) {
        this.objects = objs;
        this.stage = 0;
        this.switchingCounter = 0;
    }
    
}
