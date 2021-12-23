package com.example;

import java.util.ArrayList;

public class Admin {
    private Long id;
    private String email;
    private String password;

    private ArrayList<Student> students;

    public Admin() {
    }

    public Admin(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void register(String email, String password) {
        // Comprobaríamos si el email ya esta registrado
        // En caso de no estarlo registramos al usuario
        // Si está registrado devolvemos error
    }

    public void login(String email, String plainPassword) {
        // Comprobamos que el email existe. Si no existe, devolvemos error
        // Si existe, encriptamos la contraseña introducida
        // Comparamos la contraseña introducida con la registrada en la BBDD
        // Si es correcta hacemos login. En caso de que no devolvemos error
    }
}
