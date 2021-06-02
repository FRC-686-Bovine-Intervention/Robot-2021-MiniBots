package frc.robot.auto;

import java.util.ArrayList;
import java.util.List;

import frc.robot.auto.actions.Action;
import frc.robot.auto.modes.Mode;

public class AutoManager
{
    private static AutoManager instance = null;
    public static AutoManager getInstance()
    {
        if(instance == null) {instance = new AutoManager();}
        return instance;
    }
    private List<Action> actions = new ArrayList<Action>();
    private int actionIndex = 0;
    private int prevActionIndex = 0;
    public boolean loop = false;

    public void setMode(Mode mode)
    {
        setActions(mode.getActions());
        loop = mode.loop();
    }
    
    public void setActions(List<Action> actions) {this.actions = actions; actionIndex = 0; prevActionIndex = -1;}

    public void runAuto()
    {
        if (actionIndex >= actions.size())
        {
            if (!loop) {return;}
            else {actionIndex = 0; prevActionIndex = -1;}
        }
        if (prevActionIndex != actionIndex) {actions.get(actionIndex).start();} //if changed action, start new action
        else {actions.get(actionIndex).update();}   //otherwise run update
        prevActionIndex = actionIndex;
        if (actions.get(actionIndex).isFinished()) {actions.get(actionIndex).done(); actionIndex += 1;} //run done when finised and increment action index
    }
}
