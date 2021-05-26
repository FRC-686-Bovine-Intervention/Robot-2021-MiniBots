package frc.robot.auto;

import java.util.ArrayList;
import java.util.List;

import frc.robot.auto.actions.Action;

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
    
    public void setActions(List<Action> actions) {this.actions = actions; actionIndex = 0; prevActionIndex = 0;}

    public void runAuto()
    {
        if (actionIndex > actions.size()) {return;}
        if (prevActionIndex != actionIndex) {actions.get(actionIndex).start();}
        else {actions.get(actionIndex).update();}
        prevActionIndex = actionIndex;
        if (actions.get(actionIndex).isFinished()) {actionIndex += 1;}
    }
}
