package com.wiredbrain.friends.controller;

import com.wiredbrain.friends.model.Friend;
import com.wiredbrain.friends.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

//import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import com.wiredbrain.friends.util.ErrorMessage;
import com.wiredbrain.friends.util.FieldErrorMessage;

@RestController
public class FriendController {

    @Autowired
    FriendService friendService;

    @GetMapping("/friend")
    Iterable<Friend> getAllFriends() {
        return friendService.findAll();
    }

//    @PostMapping( "/friend")
//    Friend create(@RequestBody Friend friend) throws ValidationException {
//        if(friend.getFirstName() != null && friend.getLastName() != null)
//            return friendService.save(friend);
//        else throw new ValidationException(("friend cannot be created"));
//    }
//        This validation exception is handled in ControllerExceptionHandler in controller and ErrorMessage in util


//    Can use validation API

    @PostMapping("/friend")
    Friend create(@RequestBody Friend friend){
        return friendService.save(friend);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
        return fieldErrorMessages;
    }

    @PutMapping("/friend")
    ResponseEntity update(@RequestBody Friend friend) {
        if(friendService.findById(friend.getId()).isPresent())
            return new ResponseEntity(friendService.save(friend), HttpStatus.OK);
        else
            return new ResponseEntity(friend, HttpStatus.BAD_REQUEST);
//        if(friend.getId()== NullValue) {
//            return "Please enter an id";
//        }
//        else if(existsById(friend.existsById())){
//            friendService.save(friend);
//            return  "updated friend list";
//        }
//        else
//            return "Id does not exist";
    }

    @DeleteMapping("/friend/{id}")
    void delete(@PathVariable Integer id) {
        friendService.deleteById(id);
    }

    // Search URLs

    // Optional is used where only 1 or none value is returned like findById will just return 1 value or none.
    @GetMapping("/friend/{id}")
    Optional<Friend> findById(@PathVariable Integer id) {
        return friendService.findById(id);
    }

    // Iterable is used where there can be more than 1 value returned like findAllById then many Ids can be returned.
    // List can also be returned here
    @GetMapping("/friend/search")
    Iterable<Friend> findByQuery(@RequestParam(value = "first", required = false) String firstName,
                                 @RequestParam(value = "last", required = false) String lastName) {
//        if(firstName!=null && lastName!=null)
//            return friendService.findByFirstNameAndLastName(firstName, lastName);
        if(firstName!=null || lastName!=null)
            return friendService.findByFirstNameOrLastName(firstName, lastName);
//        else if(firstName!=null)
//            return friendService.findByFirstName(firstName);
//        else if(lastName!=null)
//            return friendService.findByLastName(lastName);
        else
            return friendService.findAll();
    }


}
