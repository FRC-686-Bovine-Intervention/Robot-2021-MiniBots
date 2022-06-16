package frc.robot;

public final class Constants {
    private Constants(){}

    public static final int kControllerPort = 0; // controller goes to USB 0 on PC

    public static final int kLeftMotorID = 1; // left motor ID is 1
    public static final int kRightMotorID = 2; // right motor ID is 2

    public static final int kTalonPidIDx = 0; // "0 for Primary closed-loop. 1 for auxiliary closed-loop." Something to do with configuring the feedback sensor for the motor
    public static final int kTalonTimeoutMs = 5; // "Timeout value in ms. If nonzero, function will wait for config success and report an error if it times out. If zero, no blocking or checking is performed." Something to do with configuring the feedback sensor for the motor
}