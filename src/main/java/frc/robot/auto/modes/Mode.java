package frc.robot.auto.modes;

import java.util.List;

import frc.robot.auto.actions.Action;

public interface Mode 
{
    public List<Action> getActions();
    public boolean loop();
}
