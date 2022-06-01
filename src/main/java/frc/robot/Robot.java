// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.auto.AutoManager;
import frc.robot.auto.actions.Action;

import static frc.robot.Constants.*;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.XboxController; // import xbox controller class
import edu.wpi.first.wpilibj.GenericHID.Hand; // import hand class

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */

// Hello!

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private MyJoystick mJoystick = new MyJoystick(kJoystickPort);
  private Drivetrain drivetrain = Drivetrain.getInstance();
  private AutoManager autoManager = AutoManager.getInstance();

  private int direction;
  private double autoTimer;
  private double prevTime;

  private XboxController xbox; // this is the name of our xbox controller I think

  private List<Action> actions = new ArrayList<Action>();

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

    xbox = new XboxController(0); // creating a new object of XboxController
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
    SmartDashboard.putNumber("Left Joystick X", xbox.getX(Hand.kLeft)); // when you move the left joystick you get the x coordinates I believe    
    System.out.println("The controller's axis count is: " + xbox.getAxisCount());
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
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
    direction = 0;
    autoTimer = 1;
    prevTime = 0;

    autoManager.setActions(actions);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    /*switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
    switch (direction)
    {
      case 0:
        drivetrain.setPower(0.125, 0.125);
      break;
      case 1:
        drivetrain.setPower(-0.125, 0.125);
      break;
      case 2:
        drivetrain.setPower(-0.125, -0.125);
      break;
      case 3:
        drivetrain.setPower(0.125, -0.125);
      break;
      default:
        drivetrain.setPower(0, 0);
      break;
    }
    if (autoTimer > 0)
    {
      autoTimer -= (System.currentTimeMillis()/1000-prevTime);
    }
    else
    {
      direction = (direction+1)%4;
      autoTimer = 1;
    }
    prevTime = System.currentTimeMillis()/1000;
    System.out.println(drivetrain.wheelsToAngle(drivetrain.degreesToDistance(drivetrain.encoderUnitsToDegrees(drivetrain.leftMotor.getSelectedSensorVelocity()/10)), drivetrain.degreesToDistance(drivetrain.encoderUnitsToDegrees(drivetrain.rightMotor.getSelectedSensorVelocity()/10))));
    */
    autoManager.runAuto();
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
    System.out.println("Left Motor " + drivetrain.encoderUnitsToDistance(drivetrain.leftMotor.getSelectedSensorPosition()) + " Right Motor " + drivetrain.encoderUnitsToDistance(drivetrain.rightMotor.getSelectedSensorPosition()));
    //System.out.println("Robot Angle " + drivetrain.wheelsToAngle(drivetrain.encoderUnitsToDistance(drivetrain.leftMotor.getSelectedSensorPosition()), drivetrain.encoderUnitsToDistance(drivetrain.rightMotor.getSelectedSensorPosition())));
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
