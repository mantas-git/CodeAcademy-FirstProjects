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
//    private int index;

//    Categories(int index, String categorie){
//        this.index = index;
//        this.categorie = categorie;
//    }

    Categories(String categorie){
        this.categorie = categorie;
    }

    public String getCategorie(){
        return categorie;
    }

//    public String getCategorie(int index){
//        return categorie;
//    }
}
