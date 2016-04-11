package com.example.dkovalev.photofetcher;

/**
 * Created by d.kovalev on 11.04.2016.
 */
public class FlickrPhoto {

    private CustomArrayList<Photo> photos;

    private int page;

    private Photo photo;

    public FlickrPhoto() {
    }

    public FlickrPhoto(CustomArrayList<Photo> photos) {
        this.photos = photos;
    }

    public CustomArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(CustomArrayList<Photo> photos) {
        this.photos = photos;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public class Photo {

        private String id;
        private String owner;
        private String secret;

        public Photo() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }
    }
}
