package gymhum.de;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gymhum.de.model.Feld;

@Controller
public class SpielController {

    Feld[][] felder;   

    public SpielController(){
        setFelder(new Feld[6][7]);
        innitFeld();
        showTestfeld();
    }


    @GetMapping("/spiel")
    public String spiel (@RequestParam(name="activePage", required = false, defaultValue = "spiel") String activePage, Model model){
        model.addAttribute("activePage", "spiel");
        model.addAttribute("spiel");


        return "index.html";
    }

    private void innitFeld(){
        for(int i = 0; i < 6; i++){
            for(int k = 0; k < 7; k++){
                getFelder()[i][k] = new Feld();
            }
        }

        // Demodaten 1. Reihe
        getFelder()[0][0].setZustand(true);
        getFelder()[1][0].setZustand(true);
        getFelder()[2][0].setZustand(true);
        getFelder()[3][0].setZustand(true);

        getFelder()[0][0].setIstFrei(false);
        getFelder()[1][0].setIstFrei(false);
        getFelder()[2][0].setIstFrei(false);
        getFelder()[3][0].setIstFrei(false);
    }

    private void showTestfeld(){
        for(int i = 0; i < 6; i++){
            System.out.println(" ");
            for(int k = 0; k < 7; k++){
                System.out.print(getFelder()[i][k].getIstFrei()+ " ");
            }
        }     
    }

    public boolean pruefe(boolean aufX){
        boolean pruefXo0 = aufX;
        boolean horizontal = false;
        boolean senkrecht = false;
        boolean diagonal = false;
        boolean windetected = false;

        for(int i = 0; i < 6; i++){
            for(int k = 0; k < 4; k++){
                if(!getFelder()[i][k].getIstFrei() && !getFelder()[i][k+1].getIstFrei() && !getFelder()[i][k+2].getIstFrei() && !getFelder()[i][k+3].getIstFrei()){
                    if(getFelder()[i][k].getZustand() == pruefXo0 && getFelder()[i][k+1].getZustand() == pruefXo0 && getFelder()[i][k+2].getZustand() == pruefXo0 && getFelder()[i][k+3].getZustand() == pruefXo0){
                        horizontal = true;
                    }
                }
            }
        }

        for(int i = 0; i < 3; i++){
            for(int k = 0; k < 7; k++){
                if(!getFelder()[i][k].getIstFrei() && !getFelder()[i+1][k].getIstFrei() && !getFelder()[i+2][k].getIstFrei() && !getFelder()[i+3][k].getIstFrei()){
                    if(getFelder()[i][k].getZustand() == pruefXo0 && getFelder()[i+1][k].getZustand() == pruefXo0 && getFelder()[i+2][k].getZustand() == pruefXo0 && getFelder()[i+3][k].getZustand() == pruefXo0){
                        senkrecht = true;
                    }
                }
            }
        }

        for(int i = 0; i < 3; i++){
            for(int k = 6; k > 4; k--){
                if(!getFelder()[i][k].getIstFrei() && !getFelder()[i+1][k-1].getIstFrei() && !getFelder()[i+2][k-2].getIstFrei() && !getFelder()[i+3][k-3].getIstFrei()){
                    if(getFelder()[i][k].getZustand() == pruefXo0 && getFelder()[i+1][k-1].getZustand() == pruefXo0 && getFelder()[i+2][k-2].getZustand() == pruefXo0 && getFelder()[i+3][k-3].getZustand() == pruefXo0){
                        diagonal = true;
                    }
                }
            }
        }

        for(int i = 0; i < 3; i++){
            for(int k = 0; k < 2; k++){
                if(!getFelder()[i][k].getIstFrei() && !getFelder()[i+1][k+1].getIstFrei() && !getFelder()[i+2][k+2].getIstFrei() && !getFelder()[i+3][k+3].getIstFrei()){
                    if(getFelder()[i][k].getZustand() == pruefXo0 && getFelder()[i+1][k+1].getZustand() == pruefXo0 && getFelder()[i+2][k+2].getZustand() == pruefXo0 && getFelder()[i+3][k+3].getZustand() == pruefXo0){
                        diagonal = true;
                    }
                }
            }
        }
        if(senkrecht || horizontal || diagonal){
            windetected = true;
        }
        return windetected;
    }


    public void setFelder(Feld[][] felder) {
        this.felder = felder;
    }
    public Feld[][] getFelder() {
        return felder;
    }
}
