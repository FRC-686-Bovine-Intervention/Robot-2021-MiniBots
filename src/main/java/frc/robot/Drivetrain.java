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

    private MyController controller;

    private TalonSRX leftMotor, rightMotor;
    private static final boolean kLeftInversion = true;
    private static final boolean kLeftSensorPhase = false;
    private static final boolean kRightInversion = false;
    private static final boolean kRightSensorPhase = true;


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
    }

    public void init(){
        setPower(0, 0);
    }

    public void onLoop(){
        double xInput = controller.getXAxis();
        double yInput = controller.getYAxis();
        double leftPower = (yInput+xInput)/4;
        double rightPower = (yInput-xInput)/4;
        setPower(leftPower, rightPower);
    }

    public void onStop(){
        setPower(0, 0);
    }


    public void setPower(double leftPower, double rightPower){
        leftMotor.set(ControlMode.PercentOutput, leftPower);
        rightMotor.set(ControlMode.PercentOutput, rightPower);
    }

    public double getDistance(){
        // return (degreesToTraveledInches(encoderUnitsToDegrees(leftMotor.getSelectedSensorPosition())) // this will average the left and right motor,
        // +degreesToTraveledInches(encoderUnitsToDegrees(rightMotor.getSelectedSensorPosition())))/2;
        return leftMotor.getSelectedSensorPosition();
    }

    public void setController(MyController controller){
        this.controller = controller;
    }

    public void setSelectedSensorPos(double pos){
        leftMotor.setSelectedSensorPosition(pos);
        rightMotor.setSelectedSensorPosition(pos);
    }
}
