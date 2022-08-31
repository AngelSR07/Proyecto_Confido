package com.grupo3.confido.usercase.recomendations.search_help;

public class Help {

    private String nomHelp;
    private String description;
    private int imgHelp;
    private int idHelp;
    public Help() { }

    public Help(String nomHelp, int imgHelp, String description, int idHelp) {
        this.nomHelp = nomHelp;
        this.description = description;
        this.imgHelp = imgHelp;
        this.idHelp = idHelp;
    }

    public String getNomHelp() {
        return nomHelp;
    }
    public void setNomHelp(String nomHelp) { this.nomHelp = nomHelp;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public int getImgHelp() {
        return imgHelp;
    }
    public void setImgHelp(int imgHelp) {
        this.imgHelp = imgHelp;
    }

    public int getIdHelp() {
        return idHelp;
    }
    public void setIdHelp(int idHelp) {
        this.idHelp = idHelp;
    }


}
