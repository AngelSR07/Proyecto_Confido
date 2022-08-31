package com.grupo3.confido.util.sendMessage;

public class Contact {

    //Atributo
    private String nomContact;
    private int numContact;


    //Constructor
    public Contact() {
    }

    public Contact(String nomContact, int numContact) {
        this.nomContact = nomContact;
        this.numContact = numContact;
    }




    //Metodos Getter and Setter
    public String getNomContact() {
        return nomContact;
    }
    public void setNomContact(String nomContact) {
        this.nomContact = nomContact;
    }


    public int getNumContact() {
        return numContact;
    }
    public void setNumContact(int numContact) {
        this.numContact = numContact;
    }
}
