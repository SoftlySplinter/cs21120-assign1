package uk.ac.aber.adb9.cs21120.assignment_1.version._2._0.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Listener for the Control Panel.
 * <DL>
 * <DT><b>Created:</b></DT>
 * <DD>Oct 19, 2010</DD>
 * </DL>
 * @version 1
 * @author Alexander Brown
 */
class ControlListener
implements ActionListener
{
    /**
     * The Control Panel this is attached to.
     */
    private ControlPanel panel;

    /**
     * Creates a new Listener and attaches the Control Panel to it.
     * @param p The Control Panel to attach the Listener to.
     */
    public ControlListener(ControlPanel p)
    {
        panel = p;
    }

    /**
     * Gets an action performed by the Control Panel and calls a method depending on which action was performed.
     * @param ae The input action.
     *
     * @see ControlPanel#step()
     * @see ControlPanel#stop()
     * @see ControlPanel#run() 
     */
    public void actionPerformed(ActionEvent ae)
    {
        String command = ae.getActionCommand();

        if(command.equals("Step"))
        {
            panel.step();
        }
        if(command.equals("Run"))
        {
            panel.run();
        }
        if(command.equals("Stop"))
        {
            panel.stop();
        }
    }
    
}
