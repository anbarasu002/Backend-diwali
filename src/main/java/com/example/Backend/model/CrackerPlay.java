package com.example.Backend.model;

import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** One "light a cracker" event from the Play with Crackers page. */
@Document(collection = "cracker_plays")
public class CrackerPlay {

    @Id
    private String id;
    private String crackerId;
    private String colorId;
    private Instant playedAt;

    public CrackerPlay() {}

    public CrackerPlay(String crackerId, String colorId) {
        this.crackerId = crackerId;
        this.colorId = colorId;
        this.playedAt = Instant.now();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCrackerId() { return crackerId; }
    public void setCrackerId(String crackerId) { this.crackerId = crackerId; }
    public String getColorId() { return colorId; }
    public void setColorId(String colorId) { this.colorId = colorId; }
    public Instant getPlayedAt() { return playedAt; }
    public void setPlayedAt(Instant playedAt) { this.playedAt = playedAt; }
}
