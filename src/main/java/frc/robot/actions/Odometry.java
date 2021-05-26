package frc.robot.actions;

public class Odometry {
    public static Pose updatePose(Pose pPose, double leftDelta, double rightDelta, double robotWidth){
        Pose output = new Pose();

        double radius = Math.abs((robotWidth*(leftDelta + rightDelta)) / (2 * (rightDelta - leftDelta)));
        double arcLength = Math.abs((rightDelta + leftDelta)/2);
        double theta = arcLength/radius;
        
        double d = Math.abs((2*radius*radius) * (1 - Math.cos(theta)));
        double phi = Math.asin((radius/d)*Math.sin(theta));
        phi = pPose.heading + phi -Math.PI/2;

        output.x = pPose.x + d*Math.sin(phi);
        output.y = pPose.y + d*Math.cos(phi);

        if(leftDelta > rightDelta) {
            output.heading = pPose.heading - theta;
        } else {
            output.heading = pPose.heading + theta;
        }
        return pPose;
    }
    
}
