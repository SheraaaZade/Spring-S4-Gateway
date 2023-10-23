package be.vinci.products;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "products")
public class Product {
    /*
    create table product(
        id int primary key not null,
        name varchar,
        ..
        dont_say numeric,
        )
     */
    @Id
    private int id;
    private String name;
    private String category;
    private double price;
//    @Column(name = "dont_say")
//    private double sleeptime;
}
