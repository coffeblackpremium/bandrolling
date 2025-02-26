package com.bandrolling.bandrolling.entity.band;

import com.bandrolling.bandrolling.entity.UserBand;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.Instant;
import java.util.Set;

@Entity
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private String genre;
    private String location;
    private String members;
    @Column(name = "social_media")
    private String socialMedia;
    private String contact;
    private String image;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;
    @OneToMany(mappedBy = "band")
    private Set<UserBand> userBands;

    private Band(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.genre = builder.genre;
        this.location = builder.location;
        this.members = builder.members;
        this.socialMedia = builder.socialMedia;
        this.contact = builder.contact;
        this.image = builder.image;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public Band() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;}

    public Instant getUpdatedAt() {return updatedAt;}

    public void setUpdatedAt(Instant updatedAt) {this.updatedAt = updatedAt;}

    public static class Builder {
        private String name;
        private String description;
        private String genre;
        private String location;
        private String members;
        private String socialMedia;
        private String contact;
        private String image;
        private Instant createdAt;
        private Instant updatedAt;

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        public Builder genre(String genre) {
            this.genre = genre;
            return this;
        }
        public Builder location(String location) {
            this.location = location;
            return this;
        }
        public Builder members(String members) {
            this.members = members;
            return this;
        }
        public Builder socialMedia(String socialMedia) {
            this.socialMedia = socialMedia;
            return this;
        }
        public Builder contact(String contact) {
            this.contact = contact;
            return this;
        }
        public Builder image(String image) {
            this.image = image;
            return this;
        }
        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public Builder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }
        public Band build() { return new Band(this); }
    }

}
