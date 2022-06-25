package frc.robot;

public final class Constants {
    private Constants(){}

    public static final int kControllerPort = 0;

    public static final int kLeftMotorID = 1;
    public static final int kRightMotorID = 2;

    public static final int kTalonPidIDx = 0;
    public static final int kTalonTimeoutMs = 5;

    private static final double kGearRatio = 16.0/48.0;
    private static final double kEncoderUnitsPerRev = 4096 / kGearRatio; // 4096 encoder units per revolution
    private static final double kEncoderUnitsPerDeg = kEncoderUnitsPerRev/360.0; // encoder units per revolution --> degrees per revolution

    public static double degreesToEncoderUnits(double _degrees) {return (_degrees * kEncoderUnitsPerDeg);}
    public static double encoderUnitsToDegrees(double _encoderUnits) {return (_encoderUnits / kEncoderUnitsPerDeg);}
    public static double degreesToTraveledInches(double _degrees) {return ((_degrees * Math.PI * 2)/180);}

}
