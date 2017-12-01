/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lostwolfgames.Samples.SortAPI;

import Lostwolfgames.Samples.Graphics.RenderObject;
import java.util.Random;

/**
 *
 * @author Navnik
 */
public class Shuffler {
    private static final Random random = new Random();
    public static void sideBySideShuffeler(RenderObject[] ref,int generations){
        
    }
    public static void pointMutationShuffles(RenderObject[] ref, int generations){
        for(int i = 0; i < generations;i++){
            int index0 = Math.abs(random.nextInt())%ref.length;
            int index1 = Math.abs(random.nextInt())%ref.length;
            RenderObject obj = ref[index0];
            ref[index0] = ref[index1];
            ref[index1] = obj;
        }
    }
    public static void fullRandomShuffle(RenderObject[] ref){
        pointMutationShuffles(ref,ref.length*ref.length);
    }
    public static void basicSwap(RenderObject[] ref, int x, int y){
        
    }
}
