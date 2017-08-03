package scripts.tasks;


import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import scripts.Task;

import java.util.concurrent.Callable;

public class Bank extends Task {

    private final static int CHOCID = 1973;
    private final static int DUSTID = 1975; //ref
    private final static int KNIFEID= 946;

    public Bank(ClientContext ctx){
        super(ctx);
    }

    @Override
    public boolean activate() {
        return (ctx.inventory.select().count() == 0 && ctx.bank.nearest().tile().distanceTo(ctx.players.local())<6);
    }

    @Override
    public void execute() {
        if (ctx.bank.opened()) {

            final int inventCount = ctx.inventory.select().count(); //get inv count

            if (inventCount == 0) {
                if (ctx.bank.select().id(CHOCID).isEmpty()) {
                    ctx.controller.stop();
                }
                else
                    {
                        ctx.bank.open();
                        ctx.bank.withdraw(KNIFEID, 1);
                        ctx.bank.withdraw(CHOCID, 27);
                        ctx.bank.close();
                    }
                }

        }
            else
            {
                if (ctx.bank.inViewport())
                {
                    if (ctx.bank.open())
                    {
                        Condition.wait(new Callable<Boolean>() {
                            @Override
                            public Boolean call() throws Exception {
                                return ctx.bank.opened();
                            }
                        }, 250, 20);
                    }
                }
                else
                {
                    ctx.camera.turnTo(ctx.bank.nearest());
                    ctx.bank.open();
                }
            }

        }
    }
