/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logsim;

import java.io.BufferedReader;

/**
 * Kontroller interfész. 
 *
 * @author Gabor
 */
public interface Controller {

    /**
     * Vezérlés elindítása adott bemenetrõl.
     * 
     * @param input
     */
    public void run(BufferedReader input);
}
