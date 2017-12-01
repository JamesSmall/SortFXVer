/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortdata;

import Lostwolfgames.Samples.Graphics.RenderObject;
import Lostwolfgames.Samples.SortAPI.BubbleSort;
import Lostwolfgames.Samples.SortAPI.Shuffler;
import java.util.Arrays;

/**
 *
 * @author Navnik
 */
public class SortData {

    /**
     * @param args the command line arguments
     *//*
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        /*RenderObjectFactory factory = new RenderObjectFactory(){
            @Override
            public RenderObject createRenderObject() {
                return new RenderObject() {
                    private int code;
                    private int value;
                    @Override
                    public void setCode(int code) {
                        this.code = code;
                    }

                    @Override
                    public int getCode() {
                        return code;
                    }

                    @Override
                    public void setValue(int value) {
                        this.value = value;
                    }

                    @Override
                    public int getValue() {
                        return value;
                    }
                    @Override
                    public String toString(){
                        return (code==RenderObject.SORTED?"#":code==RenderObject.TARGETED?"T":"")+value;
                    }
                };
            }
        };/*
        RenderObject[] array = new RenderObject[100];
        for(int i = 0; i < array.length;i++){
            array[i] = factory.createRenderObject();
            array[i].setValue(i);
        }
        Shuffler.fullRandomShuffle(array);
        BubbleSort sort = new BubbleSort(array);
        
        while(!sort.update()){
            System.out.println(Arrays.asList(array));
            //Thread.sleep(1);
        }
    }
 */   
}
