package scripts.tasks;

import org.powerbot.script.rt4.ClientContext;
import scripts.Task;


public class DepositDust extends Task {

    private final static int CHOCID = 1973;
    private final static int DUSTID = 1975; //ref
    private final static int KNIFEID= 946; //ref

    public DepositDust(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
            return (ctx.inventory.select().count() == 28 && ctx.inventory.id(CHOCID).count() == 0 );
        }

    @Override
    public void execute() {

        final int inventFull = ctx.inventory.select().count(); //get inv count

        if (inventFull == 28) {
            ctx.bank.open();
            ctx.bank.depositInventory();
        }
    }
}
