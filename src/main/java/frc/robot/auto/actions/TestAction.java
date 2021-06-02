package frc.robot.auto.actions;

public class TestAction implements Action{
    private int updateCount;

    @Override
    public void start()
    {
        updateCount = 0;
        System.out.println("Start");
    }

    @Override
    public void update()
    {
        updateCount += 1;
        System.out.println("Update" + updateCount);
    }

    @Override
    public void done()
    {
        System.out.println("Done");
    }

    @Override
    public boolean isFinished()
    {
        return updateCount >= 5;
    }
}
