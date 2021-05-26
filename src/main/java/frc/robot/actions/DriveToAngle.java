package frc.robot.actions;

import frc.robot.Drivetrain;

public class DriveToAngle extends Action{
    double startLeftDist, startRightDist;
    Drivetrain drivetrain = Drivetrain.getInstance();
    double power,angle;
    boolean finished;

    public DriveToAngle(double angle, double power){
        this.angle = angle;
        this.power = power;

    }

    @Override
    void onStart() {
        double leftPower = 0, rightPower = 0;
        if(angle > 0){
            leftPower = 0;
            rightPower = power;
        } else if(angle < 0){
            leftPower = power;
            rightPower = 0;
        }
        startRightDist = drivetrain.getRightDistance();
        startLeftDist = drivetrain.getLeftDistance();
        drivetrain.setPower(leftPower, rightPower);

    }


    @Override
    void onLoop() {
        double rightDelta = drivetrain.getRightDistance()-startRightDist;
        double leftDelta = drivetrain.getLeftDistance()-startLeftDist;
        if (getCAngle(leftDelta,rightDelta, 13.4) >= Math.abs(angle)){
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

    double getCAngle(double leftDelta, double rightDelta, double robotWidth){
        double radius = Math.abs((robotWidth*(leftDelta + rightDelta)) / (2 * (rightDelta - leftDelta)));
        double arcLength = Math.abs((rightDelta + leftDelta)/2);
        return arcLength/radius;
    }

    
}
