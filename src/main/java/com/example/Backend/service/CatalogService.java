package com.example.Backend.service;

import java.util.List;
import org.springframework.stereotype.Service;

/** Mirrors src/data/crackers.js in the frontend so plays can be validated. */
@Service
public class CatalogService {

    public record Cracker(String id, String name, String sub) {}
    public record Color(String id, String name, String emoji, String rgb) {}

    private static final List<Cracker> CRACKERS = List.of(
            new Cracker("sparkler", "கம்பி மத்தாப்பு", "Sparkler"),
            new Cracker("chakram", "சங்கு சக்கரம்", "Ground Spinner"),
            new Cracker("pot", "புஸ் வானம்", "Fountain"),
            new Cracker("sky", "ஆகாய வெடி", "Aerial Fireworks"));

    private static final List<Color> COLORS = List.of(
            new Color("red", "Red", "❤️", "255,60,70"),
            new Color("green", "Green", "💚", "50,225,110"),
            new Color("orange", "Orange", "🧡", "255,140,30"),
            new Color("gold", "Golden", "✨", "255,205,80"),
            new Color("silver", "Silver", "🤍", "225,236,255"));

    public List<Cracker> crackers() { return CRACKERS; }
    public List<Color> colors() { return COLORS; }
    public boolean hasCracker(String id) { return CRACKERS.stream().anyMatch(c -> c.id().equals(id)); }
    public boolean hasColor(String id) { return COLORS.stream().anyMatch(c -> c.id().equals(id)); }
}
