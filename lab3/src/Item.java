import java.security.InvalidParameterException;

public class Item {
    private int value;
    private int stageOfProduction = 0;

    public Item(int val) {
        this.value = val;
    }

    public int getValue() {
        return value;
    }

    public int getStageOfProduction() {
        return stageOfProduction;
    }

    public synchronized void process() throws Exception{
        if(stageOfProduction == 0) {
            this.value += 5;
        } else if(stageOfProduction == 1) {
            this.value *= 6;
        } else if(stageOfProduction == 2) {
            this.value += 21;
        } else if(stageOfProduction == 3) {
            this.value -= 3;
        } else if(stageOfProduction == 4) {
            this.value /= 2;
        } else throw new Exception("Invalid stage of production");

        stageOfProduction++;
    }
}
