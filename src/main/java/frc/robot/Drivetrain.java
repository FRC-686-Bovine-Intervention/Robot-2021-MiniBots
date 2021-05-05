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

    public TalonSRX leftMotor, rightMotor;
    private static final boolean kLeftInversion = false;
    private static final boolean kLeftSensorPhase = false;
    private static final boolean kRightInversion = false;
    private static final boolean kRightSensorPhase = false;


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

    public void init(){ //british be like
        setPower(0, 0);
    }

    public void onLoop(){
        double xInput = joystick.getXAxis();
        double yInput = joystick.getYAxis();
        System.out.println(xInput + " " + yInput);
        double leftPower = (yInput+xInput)/-2;
        double rightPower = (yInput-xInput)/2;
        leftPower *= 1+1*joystick.getBigBooostButton();
        rightPower *= 1+1*joystick.getBigBooostButton();
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

    public double encoderUnitsToDegrees(double encoderUnits)
    {
        return encoderUnits/(2048/120);
    }

    public double degreesToDeath(double degrees)
    {
        return (Math.PI*degrees)/90;
    }

    public double wheelsToAngle(double leftWheel, double rightWheel)
    {
        double difference = leftWheel-rightWheel;
        return Math.toDegrees(Math.atan2(difference, 6.65));
    }
}
