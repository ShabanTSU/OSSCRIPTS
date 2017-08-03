package scripts;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import scripts.tasks.Bank;
import scripts.tasks.DepositDust;
import scripts.tasks.Dust;
import scripts.tasks.Unstuck;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="ChocolateDuster", description="Dusts Choc at Lumb Bank", properties="client=4; author=Shaban; topic=999;")

public class ChocDuster extends PollingScript<ClientContext>{

    List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start(){
        taskList.add(new Bank(ctx));
        taskList.add(new Dust(ctx));
        taskList.add(new DepositDust(ctx));
        taskList.add(new Unstuck(ctx));
    }

    @Override
    public void poll(){

        for(Task task : taskList){

            if (ctx.controller.isStopping()){
                break;
            }

            if (task.activate()){
                task.execute();
                break;
            }

        }

    }
}
