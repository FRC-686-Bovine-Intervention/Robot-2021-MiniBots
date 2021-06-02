package frc.robot.auto.modes;

import java.util.ArrayList;
import java.util.List;

import frc.robot.auto.actions.Action;
import frc.robot.auto.actions.TestDriveAction;

public class TestMode implements Mode{
    @Override
    public List<Action> getActions()
    {
        List<Action> actions = new ArrayList<Action>();
        actions.add(new TestDriveAction(1));
        return actions;
    }
    
    @Override
    public boolean loop()
    {
        return true;
    }
}
