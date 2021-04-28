package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class MyJoystick extends Joystick{
    public MyJoystick(int port){
        super(port);
    }

    private static final int kXAxisID = 0;
    private static final int kYAxisID = 1;
    private static final int kSliderAxisID=3;
    private static final int kTriggerID=1;
    //Next three are on top of the joystick
    private static final int kDownThumbButtonID=2;
    private static final int kLeftThumbButtonID=3;
    private static final int kRightThumbButtonID=4;
    //these are the six buttons on the right of the joystick, but i labeled the joystick going left to right divided between top and bottom rows(E.X Top6 would be the top row, sixth button going right).
    private static final int kTop6ID=5;
    private static final int kTop5ID=6;
    private static final int kTop4ID=7;
    private static final int kBottom4ID=8;
    private static final int kBottom5ID=9;
    private static final int kBottom6ID=10;
    //these next six are the buttons on the left of the joystick
    private static final int kTop1ID=11;
    private static final int kTop2ID=12;
    private static final int kTop3ID=13;
    private static final int kBottom3ID=14;
    private static final int kBottom2ID=15;
    private static final int kBottom1ID=16;

    public double getXAxis(){
        return getRawAxis(kXAxisID);
    }

    public double getYAxis(){
        return -getRawAxis(kYAxisID);
    }

    public double getSliderAxis(){
        return -getRawAxis(kSliderAxisID);
    }
}
