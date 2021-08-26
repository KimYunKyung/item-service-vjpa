package hello.itemservice.controller;

import hello.itemservice.model.entity.Item;
import hello.itemservice.repository.ItemRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemRespository itemRespository;

    // 상품 목록 페이지
    // 요청 url : http://localhost:8080/items
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRespository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    // 상품 상세 정보 페이지
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) throws Exception {
        Optional<Item> optional = itemRespository.findById(itemId);
        Item item;
        // Optional에서 객체 꺼내기
        if (optional.isPresent()) {
            item = optional.get();
        } else {
            throw new Exception();
        }

        model.addAttribute("item", item);

        log.info("item={}",item);

        return "basic/item";
    }

    // 상품 등록 폼 요청
    @GetMapping("/add")
    public String add() {

        return "basic/addForm";
    }

    // 등록 폼을 모두 작성하고 등록 요청
    @PostMapping("/add")
    public String add(@ModelAttribute Item item, Model model) {

        Item saveItem = itemRespository.save(item);
        log.info("saveItem={}", saveItem);
        model.addAttribute("saveItem",saveItem);

        return "basic/item";
    }

    // 수정 폼 요청
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) throws Exception {
        Optional<Item> optional = itemRespository.findById(itemId);

        // Optional에서 isPresent()메서드 사용해서 객체 꺼내기
        Item item;
        if (optional.isPresent()) {
            item = optional.get();
        } else {
            throw new Exception();
        }

        Item finditem = optional.get();
        model.addAttribute("item", finditem);

        return "basic/editForm";
    }

    // 수정폼 작성해서 수정 해달라는 요청
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        Optional<Item> optional = itemRespository.findById(itemId);
        Item changeItem = optional.get();
        changeItem.setName(item.getName());
        changeItem.setPrice(item.getPrice());
        changeItem.setQuantity(item.getQuantity());

        itemRespository.save(changeItem);
        // 왜????
        // 아 내가 수정만 했지 db에는 이거 수정했다고 안알려줬으니깐 알려줘야지
        // 오.. 그럼 왜 저장할 때 다시 새롭게 저장이 안되는 거지?
        // 기존의 데이터가 있다면 update가 일어난다래..

        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/delete")
    public String delete(@PathVariable Long itemId) {
        Optional<Item> optional = itemRespository.findById(itemId);

        Item deleteItem = optional.get();
        itemRespository.delete(deleteItem);

        return "redirect:/basic/items";
    }









}
