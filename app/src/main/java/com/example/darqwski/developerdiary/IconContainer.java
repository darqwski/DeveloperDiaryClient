package com.example.darqwski.developerdiary;

import java.util.ArrayList;

/**
 * Created by Darqwski on 2018-11-27.
 */

public class IconContainer {

    private String containerName;
    private ArrayList<NoteEvent> events;
    private String containerCategory;

    public IconContainer(String containerCategory) {
        this.containerCategory = containerCategory;
        this.events=new ArrayList<NoteEvent>();
        switch (containerCategory){
            case "Work":
                this.containerName = "Praca";
                break;
            case "Cooking":
                this.containerName = "Gotowanie";
                break;
            case "Social":
                this.containerName = "Towarzyskie";
                break;
            case "Money":
                this.containerName = "Pieniądze";
                break;
            case "Activity":
                this.containerName = "Sport";
                break;
            case "Entertainment":
                this.containerName = "Rozrywka";
                break;
            case "Games":
                this.containerName = "Pogrywanie";
                break;
            case "Organisation":
                this.containerName = "Organizacja";
                break;
            case "Home":
                this.containerName = "Domowe";
                break;
            case "Learning":
                this.containerName = "Nauka";
                break;
            case "Other":
                this.containerName = "Inne";
                break;
            case "Programming":
                this.containerName = "Programowanie";
                break;
            case "Transport":
                this.containerName = "Podróżowanie";
                break;
            case "Weather":
                this.containerName = "Pogoda";
                break;
            case "Music":
                this.containerName = "Muzyka";
                break;
            default:
                this.containerName = containerCategory;
                break;
        }
    }

    public String getContainerCategory() {
        return containerCategory;
    }

    public void setContainerCategory(String containerCategory) {
        this.containerCategory = containerCategory;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public ArrayList<NoteEvent> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<NoteEvent> events) {
        this.events = events;
    }
    public void addEvent(NoteEvent event) {
        events.add(event);
    }
}
