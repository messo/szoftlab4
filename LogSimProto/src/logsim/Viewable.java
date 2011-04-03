/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logsim;

import logsim.model.component.AbstractComponent;

/**
 *
 * @author Gabor
 */
public interface Viewable {
    public void Run();
    public void WriteDetails(AbstractComponent ac);
}
