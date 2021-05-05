// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.Constants.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private MyJoystick mJoystick = new MyJoystick(kJoystickPort);
  private Drivetrain drivetrain = Drivetrain.getInstance();

  public enum States {
    State0,
    State1,
    State2,
    State3, State4;
  }

  public States cState = States.State1;
  public long startTime;
  public States pState = States.State0;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    drivetrain.init();

    drivetrain.setJoystick(mJoystick);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    //Hello!
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    System.out.println(drivetrain.getDistance());
    switch(cState){
      case State1:
        if (cState != pState){
          pState=cState;
          startTime=System.currentTimeMillis();
          drivetrain.setPower(.1,.1);

        }
        if (drivetrain.getDistance()>=12){
          cState=States.State2;
          drivetrain.setPower(0,0);
        }
        break;
      case State2:
        if (cState != pState){
          pState=cState;
          startTime=System.currentTimeMillis();
          drivetrain.setPower(0,.1);

        }
        if (drivetrain.getDistance()>=26){
          cState=States.State1;
          drivetrain.setPower(0,0);
        }
        break;
      case State3:
        if (cState != pState){
          pState=cState;
          startTime=System.currentTimeMillis();
          drivetrain.setPower(.125,.125);

        }
        if (System.currentTimeMillis()-startTime>=3000){
          cState=States.State4;
          drivetrain.setPower(0,0);
        }
        break;
      case State4:
        break;
      default:
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    drivetrain.init();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    drivetrain.onLoop();
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
