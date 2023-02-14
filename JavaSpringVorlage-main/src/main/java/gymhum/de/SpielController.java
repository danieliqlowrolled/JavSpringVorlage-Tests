package gymhum.de;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gymhum.de.model.Feld;
import gymhum.de.model.Spieler;

@Controller
public class SpielController {

    Feld[][] felder;
    Spieler p1;


    public SpielController(){
        setFelder(new Feld[6][7]);
        innitFeld();
        showTestfeld();
        setP1(new Spieler(false));
    }


    @GetMapping("/spiel")
    public String spiel (@RequestParam(name="activePage", required = false, defaultValue = "spiel") String activePage, Model model){
        
        boolean winTrue = pruefe(true);
        boolean winFalse = pruefe(false);

        if(winTrue){
            System.out.println("Spieler TRUE hat gewonnen!");
        }

        if(winFalse){
            System.out.println("Spiler FALSE hat gewonnen!");
        }
        
        model.addAttribute("activePage", "spiel");
        model.addAttribute("felder", getFelder());
        return "index.html";
    }

    private void innitFeld(){
        for(int i = 0; i < 6; i++){
            for(int k = 0; k < 7; k++){
                getFelder()[i][k] = new Feld();
            }
        }

        /* Demodaten 1. Reihe
        getFelder()[0][0].setZustand(true);
        getFelder()[1][0].setZustand(true);
        getFelder()[2][0].setZustand(true);
        getFelder()[3][0].setZustand(true);

        getFelder()[0][0].setIstFrei(false);
        getFelder()[1][0].setIstFrei(false);
        getFelder()[2][0].setIstFrei(false);
        getFelder()[3][0].setIstFrei(false);
        */
    }

    private void showTestfeld(){
        for(int i = 0; i < 6; i++){
            System.out.println(" ");
            for(int k = 0; k < 7; k++){
                System.out.print(getFelder()[i][k].getIstFrei()+ " ");
            }
        }     
    }

    @GetMapping("/addstein")
    public String addStein(@RequestParam(name="activePage", required = true, defaultValue = "spiel") String activePage, @RequestParam(name="id", required = true) int id, Model model){
        for(int hoehe = 5; hoehe >= 0; hoehe--) {
            if(getFelder()[hoehe][id].getIstFrei()) {
                if(p1.getActiveplayer() == true) {
                    getFelder()[hoehe][id].setIstFrei(false);
                    getFelder()[hoehe][id].setZustand(true);
                    p1.setActiveplayer(false);
                    System.out.println("Feld " + hoehe + " " + id +" wurde geändert in O");  
                    break;    
                } 
                else if(p1.getActiveplayer()== false) {
                    getFelder()[hoehe][id].setIstFrei(false);
                    getFelder()[hoehe][id].setZustand(false);
                    p1.setActiveplayer(true);   
                    System.out.println("Feld " + hoehe + " " + id +" wurde geändert in X");  
                    break;              
                }             
            } 
        }
        pruefe(false);
        pruefe(true);
        return "redirect:/spiel";
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
    public void setP1(Spieler p1) {
        this.p1 = p1;
    }
    public Spieler getP1() {
        return p1;
    }
}
