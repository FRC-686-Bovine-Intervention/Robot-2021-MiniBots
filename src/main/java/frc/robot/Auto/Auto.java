// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


package frc.robot.Auto;
import edu.wpi.first.hal.HALUtil;
import frc.robot.Robot;

/**
 * Contains auto movements.
 */

public class Auto{
    long millisecondsToRun;
    
    /**
     * Drives forward at power percent and time duration (in seconds).
     */
    public void driveForward(int power, int time){
        millisecondsToRun = time*1000;
        long initTime = HALUtil.getFPGATime();
        while (HALUtil.getFPGATime() - initTime <= millisecondsToRun){
            Robot.drivetrain.setPower(power, power);
        }
    }
    
    // TO DO: Find correct time and speed to turn exactly 90 deg
    public void turnRightNinetyDeg(){
        long initTime = HALUtil.getFPGATime();
        while (HALUtil.getFPGATime() - initTime <= 1000){
            Robot.drivetrain.setPower(0.4, -0.4);
        }
    }
    
    public void turnLeftNinetyDeg(){
        long initTime = HALUtil.getFPGATime();
        while (HALUtil.getFPGATime() - initTime <= 1000){
            Robot.drivetrain.setPower(-0.4, 0.4);
        }
    }
    
    public void runAuto(){
        autoMode.runAction()
    }
        
        // TO DO: Find out how to run Auto.java
        public static void run(){}
    }
    