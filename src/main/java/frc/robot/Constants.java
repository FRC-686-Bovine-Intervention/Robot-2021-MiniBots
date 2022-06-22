package frc.robot;

public final class Constants {
    private Constants(){}

    public static final int kControllerPort = 0; // controller goes to USB 0 on PC

    public static final int kLeftMotorID = 1; // left motor ID is 1
    public static final int kRightMotorID = 2; // right motor ID is 2

    public static final int kTalonPidIDx = 0; // "0 for Primary closed-loop. 1 for auxiliary closed-loop." Something to do with configuring the feedback sensor for the motor
    public static final int kTalonTimeoutMs = 5; // "Timeout value in ms. If nonzero, function will wait for config success and report an error if it times out. If zero, no blocking or checking is performed." Something to do with configuring the feedback sensor for the motor

    private static final double kGearRatio = 16.0 * 48.0/12.0;  // 16 in gearbox, 48t:12t sprockets
    private static final double kEncoderUnitsPerRev = 1024 * kGearRatio; // 1024 encoder units per revolution
    private static final double kEncoderUnitsPerDeg = kEncoderUnitsPerRev/360.0; // encoder units per revolution --> degrees per revolution

    public static int degreesToEncoderUnits(double _degrees) {return (int)(_degrees * kEncoderUnitsPerDeg);}
    public static double encoderUnitsToDegrees(double _encoderUnits) {return (double)(_encoderUnits / kEncoderUnitsPerDeg);}
    public static double degreesToTraveledInches(double _degrees) {return ((_degrees * Math.PI * 2)/180);}
}