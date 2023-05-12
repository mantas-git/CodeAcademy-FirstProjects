package Enums;

public enum Categories {
    FOOD("Maistas"),
    TRANSPORT("Transportas"),
    CLOTHING("Apranga"),
    ENTERTAIMENT("Pramogos"),
    HOUSEHOLD("Buitis"),
    HEALTH("Sveikata"),
    MAIN_JOB("Darbas"),
    EXTRA_JOB("Papildomas darbas"),
    SALES("Pardavimai"),
    OTHER("Kita");

    private String categorie;

    Categories(String categorie){
        this.categorie = categorie;
    }

    public String getCategorie(){
        return categorie;
    }

}
