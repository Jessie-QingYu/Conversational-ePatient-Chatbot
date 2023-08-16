package PatientManagement.Catalogs;

import java.util.HashMap;

public class VitalSignLimits {
    String name;
    HashMap<AgeGroup, Limits> limits;

    public VitalSignLimits(String name) {
        this.name = name;
        limits = new HashMap<AgeGroup, Limits>();
    }

    public void addLimits(AgeGroup a, int upper, int lower) {
        limits.put(a, new Limits(upper, lower));
    }

    public boolean isNormal(int age, int value) {
        boolean judge = false;
        for (AgeGroup a : limits.keySet()) {
            if (a.isInGroup(age)){
                //System.out.println(a.getName());
                judge = limits.get(a).isWithinLimits(value);
                break;
            }      
        }
        return judge;
    }

    public String isHighOrDown(int age, int value) {
        String analysis = "";
        for (AgeGroup a : limits.keySet()) {
            if (a.isInGroup(age)){
                //System.out.println(a.getName());
                analysis = limits.get(a).isHighOrLow(value);
                break;
            }
        }
        return analysis;
    }

    public String getRange(int age, String name){
        String range = "";
        for (AgeGroup a : limits.keySet()){
            if (a.isInGroup(age)){
                range = limits.get(a).getRange();
                break;
            }
        }
        return range;

    }

    public String getName() {
        return name;
    }

    public Limits getLimits(AgeGroup ag) {
        return limits.get(ag);
    }
}
