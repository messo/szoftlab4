/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logsim;

import logsim.model.component.AbstractComponent;
import logsim.model.component.impl.Toggle;

/**
 *
 * @author Gabor
 */
public interface Viewable {
    public void Run();
    public void writeDetails(AbstractComponent ac);
    public void writeToggle(Toggle t);

}
