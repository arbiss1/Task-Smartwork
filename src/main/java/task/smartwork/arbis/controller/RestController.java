package task.smartwork.arbis.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import task.smartwork.arbis.domain.PhoneBook;
import task.smartwork.arbis.repository.PhoneBookRepository;
import task.smartwork.arbis.service.NameService;
import org.springframework.web.bind.annotation.*;
import task.smartwork.arbis.service.PhoneBookService;

import static java.lang.String.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/phoneBook")
@Api(value="Phone Book", description="Phone Book CRUD operations")
public class RestController {

    @Autowired
    PhoneBookService phoneBookService;

    @Autowired
    NameService nameService;

    @Autowired
    PhoneBookRepository phoneBookRepository;


    @PostMapping("/createPhoneBook")
    public ResponseEntity<PhoneBook> createPhoneBook (@RequestBody PhoneBook phoneBook) throws Exception {

        if(phoneBook.getType().equals("Work") || phoneBook.getType().equals("Cellphone") || phoneBook.getType().equals("Home")){
            phoneBook.setType(phoneBook.getType());
        }else {
            return new ResponseEntity("Type must be {Work , Cellphone or Home}",HttpStatus.BAD_REQUEST);
        }
        //save name
        nameService.saveName(phoneBook);

        //save phoneBook
        PhoneBook newPhoneBook = new PhoneBook();
        newPhoneBook.setName(phoneBook.getName());
        newPhoneBook.setNumber(phoneBook.getNumber());
        newPhoneBook.setType(phoneBook.getType());
        phoneBookService.savePhoneBook(newPhoneBook);

        //return response in json format
        return new ResponseEntity(newPhoneBook, HttpStatus.OK);
    }

    @DeleteMapping("/deletePhoneBookById")
    public ResponseEntity<PhoneBook> deletePhoneBookById(@RequestBody PhoneBook phoneBook){
        PhoneBook deletePhoneBook = phoneBookRepository.findById(phoneBook.getId());
        if(deletePhoneBook == null) {
            return new ResponseEntity("Phone book is not found",HttpStatus.BAD_REQUEST);
        }
        phoneBookService.deletePhoneBook(deletePhoneBook);
        return new ResponseEntity(phoneBook, HttpStatus.OK);
    }

    @PutMapping("/editPhoneBookById")
    public ResponseEntity<PhoneBook> editPhoneBookById(@RequestBody PhoneBook phoneBook){
        if(phoneBook.getType().equals("Work") || phoneBook.getType().equals("Cellphone") || phoneBook.getType().equals("Home")){
                PhoneBook editPhoneBook = phoneBookRepository.findById(phoneBook.getId());
                if(editPhoneBook == null) {
                    return new ResponseEntity("Phone book is not found",HttpStatus.BAD_REQUEST);
                 }
                editPhoneBook.setName(phoneBook.getName());
                editPhoneBook.setType(phoneBook.getType());
                editPhoneBook.setNumber(phoneBook.getNumber());
                phoneBookService.savePhoneBook(editPhoneBook);
                return new ResponseEntity<>(editPhoneBook, HttpStatus.OK);
        }else {
            return new ResponseEntity("Type must be {Work , Cellphone or Home}",HttpStatus.BAD_REQUEST);
        }
    }

}