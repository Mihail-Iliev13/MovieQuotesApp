package com.example.demo.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private int id;

    @Column(name = "character_name", nullable = false)
    private String characterName;

    @OneToMany(mappedBy = "quotedCharacter")
    private List<Quote> quoteList;

    public Character(){

    }

    public Character(String characterName) {
        this.characterName = characterName;
    }

    public int getId() {
        return id;
    }

    public String getCharacterName() {
        return characterName;
    }


    @JsonIgnore
    public List<Quote> getQuoteList() {
        return quoteList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public void setQuoteList(List<Quote> quoteList) {
        this.quoteList = quoteList;
    }
}
