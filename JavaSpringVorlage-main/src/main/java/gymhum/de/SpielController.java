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
    }

    private void showTestfeld(){
        for(int i = 0; i < 6; i++){
            System.out.println(" ");
            for(int k = 0; k < 7; k++){
                System.out.print(getFelder()[i][k].getIstFrei()+ " ");
            }
        }     
    }

    public void setFelder(Feld[][] felder) {
        this.felder = felder;
    }
    public Feld[][] getFelder() {
        return felder;
    }
}
