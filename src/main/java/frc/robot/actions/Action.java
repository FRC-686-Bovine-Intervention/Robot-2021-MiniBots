package frc.robot.actions;

public abstract class Action {
    abstract void onStart();
    abstract void onStop();
    abstract void onLoop();
    abstract boolean finished();
}