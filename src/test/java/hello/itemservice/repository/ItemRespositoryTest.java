package hello.itemservice.repository;

import hello.itemservice.model.entity.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRespositoryTest {

    @Autowired
    ItemRespository itemRespository;

    @Test
    void save() {
        //given
        Item item = new Item("itemD", 10000, 10);
        //when
        Item saveditem = itemRespository.save(item);
        //then
        Optional<Item> optional = itemRespository.findById(item.getId());
        Item finditem = optional.get();

        Assertions.assertThat(finditem).isEqualTo(saveditem);
    }
}