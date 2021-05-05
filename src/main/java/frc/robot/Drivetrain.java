package frc.robot;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;



public class Drivetrain {

    private static Drivetrain instance = null;
    public static Drivetrain getInstance(){
        if(instance == null){
            instance = new Drivetrain();
        }
        return instance;
    }

    private MyJoystick joystick;

    private TalonSRX leftMotor, rightMotor;
    private static final boolean kLeftInversion = true;
    private static final boolean kLeftSensorPhase = false;
    private static final boolean kRightInversion = false;
    private static final boolean kRightSensorPhase = true;

    private static final double kTicksPerRev = 11800;


    private Drivetrain(){
        leftMotor = new TalonSRX(kLeftMotorID);
        leftMotor.configFactoryDefault();
        leftMotor.setInverted(kLeftInversion);
        leftMotor.setNeutralMode(NeutralMode.Coast);
        leftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kTalonPidIDx, kTalonTimeoutMs);
        leftMotor.setSensorPhase(kLeftSensorPhase);

        rightMotor = new TalonSRX(kRightMotorID);
        rightMotor.configFactoryDefault();
        rightMotor.setInverted(kRightInversion);
        rightMotor.setNeutralMode(NeutralMode.Coast);
        rightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kTalonPidIDx, kTalonTimeoutMs);
        rightMotor.setSensorPhase(kRightSensorPhase);
        rightMotor.setSelectedSensorPosition(0);
    }

    public void init(){
        setPower(0, 0);
    }

    public void onLoop(){
        double xInput = joystick.getXAxis();
        double yInput = joystick.getYAxis();
        double leftPower = (yInput+xInput)/2;
        double rightPower = (yInput-xInput)/2;
        leftPower*=joystick.getSliderAxis();
        rightPower*=joystick.getSliderAxis();
        System.out.println(rightPower);
        setPower(leftPower, rightPower);
    }

    public void onStop(){
        setPower(0, 0);
    }


    public void setPower(double leftPower, double rightPower){
        leftMotor.set(ControlMode.PercentOutput, leftPower);
        rightMotor.set(ControlMode.PercentOutput, rightPower);
    }

    public void setJoystick(MyJoystick joystick){
        this.joystick = joystick;
    }

    public double getEncoderTicks(){
        return rightMotor.getSelectedSensorPosition();
    }
    public double getDistance(){
        return rightMotor.getSelectedSensorPosition()/11800.0*4.0*Math.PI;
    }
}