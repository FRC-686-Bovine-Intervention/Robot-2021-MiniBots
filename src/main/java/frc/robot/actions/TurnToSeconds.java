package frc.robot.actions;

import frc.robot.Drivetrain;

public class TurnToSeconds extends Action {
    Drivetrain drivetrain = Drivetrain.getInstance();
    double seconds, power;
    long startTimeMS;
    boolean finished = false;

    public TurnToSeconds(double seconds, double power){
        this.seconds = seconds;
        this.power = power;
    }

    @Override
    void onStart() {
        startTimeMS = System.currentTimeMillis();
        drivetrain.setPower(-power, power);
    }

    @Override
    void onLoop() {
       if((System.currentTimeMillis()-startTimeMS)/1000 >= seconds){
            finished = true;
       }
    }

    @Override
    void onStop() {
        drivetrain.setPower(0,0);
    }

    @Override
    boolean finished() {
        
        return finished;
    }
    
}
