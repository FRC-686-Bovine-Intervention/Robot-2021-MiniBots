package frc.robot.auto.actions;

import frc.robot.Drivetrain;

public class TestDriveAction implements Action {

    private double autoTimer;
    private double prevTime;
    private double duration;

    private Drivetrain drivetrain = Drivetrain.getInstance();

    public TestDriveAction(double duration)
    {
        this.duration = duration;
    }

    @Override
    public void start()
    {
        prevTime = System.currentTimeMillis()/1000.0;
        autoTimer = duration;
    }

    @Override
    public void update()
    {
        System.out.println(autoTimer);
        drivetrain.setPower(autoTimer/duration*0.25, autoTimer/duration*0.25);
        if (autoTimer > 0)
        {
            autoTimer -= (System.currentTimeMillis()/1000.0-prevTime);
        }
        prevTime = System.currentTimeMillis()/1000.0;
    }

    @Override
    public void done()
    {
        drivetrain.setPower(0, 0);
        System.out.println("done");
    }

    @Override
    public boolean isFinished()
    {
        return autoTimer <= 0;
    }
}