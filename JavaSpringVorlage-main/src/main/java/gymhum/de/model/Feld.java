package gymhum.de.model;

public class Feld {
    
    Boolean istFrei;
    Boolean zustand; //true = x, false = o 

    public Feld(){
        setIstFrei(true);
    }

    public void setIstFrei(Boolean istFrei) {
        this.istFrei = istFrei;
    }
    public Boolean getIstFrei() {
        return istFrei;
    }
    public void setZustand(Boolean zustand) {
        this.zustand = zustand;
    }
    public Boolean getZustand() {
        return zustand;
    }

}
