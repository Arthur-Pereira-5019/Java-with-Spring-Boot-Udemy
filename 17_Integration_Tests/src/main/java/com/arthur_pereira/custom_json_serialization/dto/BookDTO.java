package com.arthur_pereira.custom_json_serialization.dto;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BookDTO extends RepresentationModel<BookDTO> implements Serializable {
        Long id;

        String author;

        Date launchDate;

        Float price;

        String title;

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            BookDTO book = (BookDTO) o;
            return Objects.equals(getId(), book.getId()) && Objects.equals(getAuthor(), book.getAuthor()) && Objects.equals(getLaunchDate(), book.getLaunchDate()) && Objects.equals(getPrice(), book.getPrice()) && Objects.equals(getTitle(), book.getTitle());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getAuthor(), getLaunchDate(), getPrice(), getTitle());
        }

        public BookDTO() {
        }

        public BookDTO(Long id, String author, Date launchDate, Float price, String title) {
            this.id = id;
            this.author = author;
            this.launchDate = launchDate;
            this.price = price;
            this.title = title;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public Date getLaunchDate() {
            return launchDate;
        }

        public void setLaunchDate(Date launchDate) {
            this.launchDate = launchDate;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

