package PatientManagement.Catalogs;

public class Limits {
    int upper;
    int lower;

    public Limits(int u, int l) {
        upper = u;
        lower = l;
    }

    public Boolean isWithinLimits(int value) {
        boolean judge = false;
        if ((value <= upper) && (value >= lower)){
            judge = true;
        }
        else judge = false;
        return judge;
    }

    public String isHighOrLow(int value) {
        String analysis = "\u263A";
        if ((value > upper)) analysis = "\u2191";
        else if ((value < lower)) analysis = "\u2193";
        return analysis;
    }

    public String getRange(){
        String range = lower + "-" + upper;
        return range;
    }
}
