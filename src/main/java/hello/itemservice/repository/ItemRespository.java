package hello.itemservice.repository;


import hello.itemservice.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRespository extends JpaRepository<Item, Long> {


}
