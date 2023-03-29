package com.procesos.inventario.controllers;

import com.procesos.inventario.models.User;
import com.procesos.inventario.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserServiceImp userService;

    @GetMapping(value = "/user/{id}")
    public ResponseEntity findUserById(@PathVariable Long id){
        Map response = new HashMap();
        try{
            return new ResponseEntity(userService.getUser(id), HttpStatus.OK);
           }catch (Exception e){
            response.put("status","404");
            response.put("message","No se encontro el usuario");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping(value = "/user")
    public ResponseEntity saveUser(@RequestBody User user){
        Map response = new HashMap();
        Boolean userRes = userService.createUser(user);
        if(userRes == true){
            response.put("status","201");
            response.put("message","Se creo el usuario ");
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        response.put("status","400");
        response.put("message","Hubo un error al crear el usuario");
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/users")
    public ResponseEntity findUsers() {
        Map response = new HashMap();
        try {
            return new ResponseEntity(userService.allUsers(), HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", "404");
            response.put("message", "No se encontro el usuario");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping (value = "/user/{id}")
    public ResponseEntity updateUser(@PathVariable long id, @RequestBody User user){
        Map response = new HashMap();
        Boolean userResp = userService.updateUser(id, user);
        if (userResp){
            response.put("status","400");
            response.put("message","Error en los datos enviados");
            return new ResponseEntity(response, HttpStatus.PAYMENT_REQUIRED);
        }
        response.put("status","200");
        response.put("message","Usuario actualizado exitosamente");
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}

