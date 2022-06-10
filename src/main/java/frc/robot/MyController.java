package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class MyController extends XboxController {
    public MyController(int port){
        super(port);
    }

    private static final int kXAxisID = 0;
    private static final int kYAxisID = 5;


    public double getXAxis(){
        return getRawAxis(kXAxisID);
    }

    public double getYAxis(){
        return getRawAxis(kYAxisID);
    }
}
