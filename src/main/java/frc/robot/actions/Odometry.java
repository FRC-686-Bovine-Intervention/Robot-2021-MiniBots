package frc.robot.actions;

import frc.robot.Drivetrain;

public class Odometry {
    Drivetrain drivetrain = Drivetrain.getInstance();
    Pose cPose = new Pose(0,0,0);
    double pLeftDist, pRightDist;

    public static double robotWidth = 13.4;

    public void update(){
        double leftDelta = drivetrain.getLeftDistance()-pLeftDist;
        double rightDelta = drivetrain.getRightDistance()-pRightDist;
        cPose = Odometry.updatePose(cPose, leftDelta, rightDelta, robotWidth);
        System.out.println("X: " + cPose.x + " Y: " + cPose.y + " Heading: " + Math.toDegrees(cPose.heading));

        pLeftDist = drivetrain.getLeftDistance();
        pRightDist = drivetrain.getRightDistance();
    }

    public static Pose updatePose(Pose pPose, double leftDelta, double rightDelta, double robotWidth){
        Pose output = new Pose(0, 0, 0);
        double theta = Math.abs((leftDelta - rightDelta) / robotWidth);
        double distance, angle, newHeading;
        if (theta == 0){
            distance = leftDelta;
            angle = pPose.heading;
            newHeading = pPose.heading;
        } else {
            double radius = Math.abs((robotWidth * (leftDelta + rightDelta)) / (2 *(rightDelta - leftDelta)));
            distance = Math.sqrt(Math.abs(2*radius*radius*(1 - Math.cos(theta))));
            double arclength = (leftDelta+rightDelta/2);
            
            if (leftDelta > rightDelta){
                angle = pPose.heading - theta / 2;
                newHeading = pPose.heading - theta;
            } else {
                angle = pPose.heading + theta / 2;
                newHeading = pPose.heading + theta;
            }

            if(arclength < 0){
                angle += Math.PI;
            }
        }
        output.x = pPose.x + distance * Math.cos(angle);
        output.y = pPose.y + distance * Math.sin(angle);
        output.heading = newHeading;

        return output;
    }
    
    public void setPose(double x, double y, double heading){
        cPose = new Pose(x, y, heading);
    }

    public void setPose(Pose pose){
        cPose = pose;
    }
}
