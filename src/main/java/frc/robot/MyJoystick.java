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
        return -1*getRawAxis(kYAxisID);
    }

    public int getBigBooostButton()
    {
        int r;
        if (getRawButton(1))
        {
            r = 1;
        }
        else
        {
            r = 0;
        }
        return r;
    }
}
