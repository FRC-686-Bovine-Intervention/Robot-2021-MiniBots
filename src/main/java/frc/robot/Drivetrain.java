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
            instance = new Drivetrain(); // each time the code is deployed, create a new instance of Drivetrain
        }
        return instance;
    }

    private MyController controller; // controller field

    private TalonSRX leftMotor, rightMotor; // instance fields
    private static final boolean kLeftInversion = false;
    private static final boolean kLeftSensorPhase = false;
    private static final boolean kRightInversion = true;
    private static final boolean kRightSensorPhase = false;

    private Drivetrain(){ // Drivetrain constructor
        leftMotor = new TalonSRX(kLeftMotorID);
        leftMotor.configFactoryDefault();
        leftMotor.setInverted(kLeftInversion);
        leftMotor.setNeutralMode(NeutralMode.Coast);
        leftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kTalonPidIDx, kTalonTimeoutMs);
        leftMotor.setSensorPhase(kLeftSensorPhase);
        leftMotor.getSelectedSensorPosition();

        rightMotor = new TalonSRX(kRightMotorID);
        rightMotor.configFactoryDefault();
        rightMotor.setInverted(kRightInversion);
        rightMotor.setNeutralMode(NeutralMode.Coast);
        rightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kTalonPidIDx, kTalonTimeoutMs);
        rightMotor.setSensorPhase(kRightSensorPhase);
        rightMotor.getSelectedSensorPosition();
    }

    public void init(){
        setPower(0, 0);
    }

    public void onLoop(){
        double xInput = controller.getXAxis();
        double yInput = -controller.getYAxis();
        double leftPower = (yInput-xInput)/4;
        double rightPower = (yInput+xInput)/4;
        setPower(leftPower, rightPower);
    }

    public void onStop(){
        setPower(0, 0);
    }


    public void setPower(double leftPower, double rightPower){
        leftMotor.set(ControlMode.PercentOutput, leftPower);
        rightMotor.set(ControlMode.PercentOutput, rightPower);
    }

    public void setController(MyController controller){
        this.controller = controller;
    }

    public double getDistance(){
        // return rightMotor.getSelectedSensorPosition()*encoderUnitsToDegrees()*degreesToTraveledInches(); // What I thought
        
        return (degreesToTraveledInches(encoderUnitsToDegrees(leftMotor.getSelectedSensorPosition())) // this will average the left and right motor,
        +degreesToTraveledInches(encoderUnitsToDegrees(rightMotor.getSelectedSensorPosition())))/2; // so turning the robot will not effect the distance, unless it travels in an arc
    }
}