package pl.as.zarzadzaniezadaniami;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Category category;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate deadLine;

    public Task() {
    }

    public Task(String description, Status status, Category category, LocalDate deadLine) {
        this.description = description;
        this.status = status;
        this.category = category;
        this.deadLine = deadLine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return "Zadanie {" +
                "identyfikator =" + id +
                ", opis ='" + description + '\'' +
                ", status =" + status +
                ", kategoria =" + category +
                ", termin do wykonania =" + deadLine +
                '}';
    }
}
