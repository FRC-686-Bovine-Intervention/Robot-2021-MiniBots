package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class MyJoystick extends Joystick{
    public MyJoystick(int port){
        super(port);
    }

    private static final int kXAxisID = 0;
    private static final int kYAxisID = 1;


    public double getXAxis(){
        return getRawAxis(kXAxisID);
    }

    public double getYAxis(){
        return getRawAxis(kYAxisID);
    }
}
