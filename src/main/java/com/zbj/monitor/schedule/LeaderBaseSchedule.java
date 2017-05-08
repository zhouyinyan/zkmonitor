package com.zbj.monitor.schedule;

import com.zbj.monitor.schedule.leaderselect.LeaderSelector;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhouyinyan on 17/5/5.
 */
public abstract class LeaderBaseSchedule {

    @Autowired
    LeaderSelector leaderSelector;
    
    public void schedule(){

        if(!leaderSelector.hasLeadership()){
            return;
        }
        
        doSchedule();
    }

    protected abstract void doSchedule();
}
