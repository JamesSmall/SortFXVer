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
public class SortHandler {
    private static int compares = 0;
    private static int access = 0;
    private static int inserts = 0;
    public static void Comparisions(){
        compares++;
    }
    public static void Access(){
        
    }
    public static void Inserts(){
        
    }
    public void ArraySwap(RenderObject[] objs, int i0, int i1){
        access += 2;
        inserts += 2;
        RenderObject temp = objs[i0];
        objs[i0] = objs[i1];
        objs[i1] = temp;
    }
    public void reset(){
        compares = 0;
        access = 0;
        inserts = 0;
    }
}
