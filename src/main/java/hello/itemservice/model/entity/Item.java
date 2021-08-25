package hello.itemservice.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="itemlist")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 기본키 자동 생성 하기
    // IDENTITY : 기본키 생성을 데이터베이스에 위임, AUTO_INCREMENT를 이용해 기본키 생성
    private Long id;

    @Column
    private String name;

    @Column
    private Integer price;

    @Column
    private Integer quantity;

    public Item() {

    }

    public Item(String name, Integer price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


}
