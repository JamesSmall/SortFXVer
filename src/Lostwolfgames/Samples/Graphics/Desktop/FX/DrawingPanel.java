/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lostwolfgames.Samples.Graphics.Desktop.FX;

import Lostwolfgames.Samples.Graphics.RenderObject;
import Lostwolfgames.Samples.SortAPI.BaseSortableObject;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

/**
 *
 * @author Navnik
 */
public class DrawingPanel extends Canvas {
    private BaseSortableObject sort;
    private int waitTime;
    
    public void setSort(BaseSortableObject o,int time){
        this.sort = o;
        this.waitTime = time;
    }
    public int getWaitTime(){
        return waitTime;
    }
    private boolean render(GraphicsContext gc){
        
        boolean lastUpdate = sort.update();
        RenderObject[] obj = sort.getRenderPoints();
        int max = 0;
        for(RenderObject o:obj){
            max = Math.max(o.getValue(), max);
        }
        double width = getWidth()/obj.length;
        double height = getHeight()/(max+1);
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,getWidth(),getHeight());
        int x = 0;
        for(RenderObject o:obj){
            gc.setFill(o.getCode() == RenderObject.SORTED? Color.GREEN:
                o.getCode() == RenderObject.TARGETED? Color.RED:
                o.getCode() == RenderObject.INDEXED? Color.YELLOW:
                        Color.WHITE
            );
            gc.fillRect(x*(width),(max-o.getValue())*height,width, height*(o.getValue()+1));
            x++;
        }
        return lastUpdate;
    }
    public void update(){
        render(super.getGraphicsContext2D());
    }
}
