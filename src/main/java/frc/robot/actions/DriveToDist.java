package frc.robot.actions;

import frc.robot.Drivetrain;

public class DriveToDist extends Action{
    Drivetrain drivetrain = Drivetrain.getInstance();
    double inch, power;
    double startDist;
    boolean finished;

    public DriveToDist(double inch, double power) {
        this.inch = inch;
        this.power = power;
    }

    @Override
    void onStart() {
        startDist = drivetrain.getRightDistance();
        drivetrain.setPower(power, power);
    }

    @Override
    void onLoop() {
       if ((drivetrain.getRightDistance()-startDist) >= inch){
           finished = true;
       }
    }


    @Override
    void onStop() {
        drivetrain.setPower(0, 0);

    }


    @Override
    boolean finished() {

        return finished;
    }
    
}