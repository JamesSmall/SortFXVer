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
public interface BaseSortableObject {
    RenderObject[] getRenderPoints();
    boolean update();
    void set(RenderObject[] obj);
}
