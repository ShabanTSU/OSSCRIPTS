package scripts.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import scripts.Task;

public class Dust extends Task {

    private final static int CHOCID = 1973;
    private final static int DUSTID = 1975;
    private final static int KNIFEID= 946;

    public Dust(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return (ctx.inventory.id(CHOCID).count() > 0);
    }

    @Override
    public void execute() {

        // final int inventCount = ctx.inventory.select().count(); //get inv count

        if (ctx.inventory.id(DUSTID).count() > 26) {
            ctx.bank.open();
            ctx.bank.depositInventory();
        }

        Item knife = ctx.inventory.select().id(KNIFEID).poll();
        knife.interact("Use");
        Item choc = ctx.inventory.select().id(CHOCID).poll();
        choc.interact("Use");

        Random rnd = new Random();
        Condition.sleep(rnd.nextInt(400,1000));

    }
}
