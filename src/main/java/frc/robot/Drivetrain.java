// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

  private static DriveTrain instance = null;
  public static DriveTrain getInstance(){
      if(instance == null){
          instance = new DriveTrain();
      }
      return instance;
  }

  /** Creates a new Drivetrain. */
  private PS4Controller driverController = new PS4Controller(Constants.DRIVER_CONTROLLER_PORT);
  
  public static TalonSRX LeftMotor = new TalonSRX(Constants.LEFT_MOTOR_ID);
  public static TalonSRX RightMotor = new TalonSRX(Constants.RIGHT_MOTOR_ID);
 
  public void init(){
    setPower(0,0);
  }

  public void onLoop(){
    double yPower = driverController.getLeftY()/4;
    double xPower = driverController.getLeftX()/4;
  
    double leftPower = yPower-xPower;
    double rightPower = yPower+xPower;

    setPower(leftPower,rightPower);
  }
  public void setPower(double LeftPower, double RightPower){
    LeftMotor.set(ControlMode.PercentOutput, LeftPower);
    RightMotor.set(ControlMode.PercentOutput, RightPower);
  }

  public void stopMotor(){
    setPower(0,0);
  }
  
}
