package scripts.tasks;

import org.powerbot.script.rt4.ClientContext;
import scripts.Task;

public class Unstuck extends Task {

    private final static int CHOCID = 1973;
    private final static int DUSTID = 1975; //ref
    private final static int KNIFEID= 946;

    public Unstuck(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ((ctx.inventory.select().count() < 28 && ctx.inventory.id(CHOCID).count() > 0) ||
                 ctx.inventory.select().count() == 1 ||
                (ctx.inventory.select().count() < 28 && ctx.inventory.id(DUSTID).count() > 0) ||
                 ctx.inventory.id(DUSTID).count() == 28 ||
                 ctx.inventory.id(CHOCID).count() == 28 ||
                 ctx.inventory.id(CHOCID).count() > 0 && ctx.inventory.id(CHOCID).count() < 28||
                 ctx.inventory.id(DUSTID).count() > 0 && ctx.inventory.id(DUSTID).count() < 28||
                 ctx.inventory.id(KNIFEID).count() == 1);
    }

    //test adding this stuff

    @Override
    public void execute() {
        ctx.bank.open();
        ctx.bank.depositInventory();
    }
}