/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logsim;

import java.io.BufferedReader;

/**
 * Kontroller interf�sz. 
 *
 * @author Gabor
 */
public interface Controller {

    /**
     * Vez�rl�s elind�t�sa adott bemenetr�l.
     * 
     * @param input
     */
    public void run(BufferedReader input);
}
