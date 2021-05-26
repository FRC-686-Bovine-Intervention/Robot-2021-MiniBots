package frc.robot.actions;

import java.util.List;

public class AutoManager {
    int count=0, pCount=-1;
    List<Action> actions;

    public void setActions(List<Action> actions){
        this.actions = actions;
    }

    public void runAuto(){
        if(count >= actions.size()){
            return;
        }
        Action cAction = actions.get(count);
        
        if(count != pCount){
            cAction.onStart();
            pCount=count;
            return;
        }
        cAction.onLoop();
        if(cAction.finished()){
            cAction.onStop();
            count++;
        }
    }

    public void reset(){
        count = 0;
        pCount = -1;
    }
}