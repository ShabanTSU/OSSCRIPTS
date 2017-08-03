package scripts;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import scripts.tasks.Drop;
import scripts.tasks.Mine;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="QuickMiner", description="Test", properties="client=4; author=Shaban; topic=999;")

public class QuickMiner extends PollingScript<ClientContext>{

    List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start(){
        taskList.add(new Drop(ctx));
        taskList.add(new Mine(ctx));
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
