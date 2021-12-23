package com.example;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    // Creamos una lista de admins, estudiantes y tags (Emulando la BBDD)
    static ArrayList<Admin> admins = new ArrayList<>();
    static ArrayList<Student> students = new ArrayList<>();
    static HashMap<Long, Tag> tags = new HashMap<>();

    public static void main(String[] args) {

        // Creamos un administrador
        Admin alex = new Admin(1L, "alex@open-bootcamp.com", "supersecreta");
        admins.add(alex);

        // Creamos algunos usuarios
        Student dario = new Student(1L,
                1L,
                "Darío Aboy Hidalgo",
                "dario@gmail.com",
                "612345678",
                "ES",
                "Huelva",
                false,
                WorkType.REMOTE,
                "4e8f01daa7eda1aab3ce174903b465d0.jpg",
                "4e8f01daa7eda1aab3ce174903b465d0.pdf",
                "");
        Student luis = new Student(2L,
                1L,
                "Luis Pérez García",
                "luis@gmail.com",
                "612345678",
                "ES",
                "Madrid",
                true,
                WorkType.REMOTE,
                "52ca4584c065084773ff4206892ec237.jpg",
                "52ca4584c065084773ff4206892ec237.pdf",
                "");
        students.add(dario);
        students.add(luis);

        // Creamos algunos tags
        Tag HTML = new Tag(1L, "HTML");
        Tag JS = new Tag(2L, "JS");
        Tag Java = new Tag(3L, "Java");
        Tag CSharp = new Tag(4L, "C#");
        Tag React = new Tag(5L, "React");

        tags.put(HTML.getId(), HTML);
        tags.put(JS.getId(), JS);
        tags.put(Java.getId(), Java);
        tags.put(CSharp.getId(), CSharp);
        tags.put(React.getId(), React);

        // Asignamos los estudiantes de Álex
        ArrayList<Student> tempStudents = new ArrayList<>();
        tempStudents.add(dario);
        tempStudents.add(luis);
        alex.setStudents(tempStudents);
        tempStudents.clear();

        // Asignamos a los estudiantes su administrador
        dario.setAdmin(alex);
        luis.setAdmin(alex);

        // Asignamos a los estudiantes algunos tags
        dario.setTags("3;4;5"); // Tags de Java, C# y React a Darío
        luis.setTags("1;2;5"); // Tags de HTML, JS y React a Luis

        GeneratePDF(dario);
        GeneratePDF(luis);
    }

    public static void GeneratePDF(Student student) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(student.getId() + "_" + System.currentTimeMillis() + ".pdf"));
            document.open();

            String[] splitTags = student.getTags().split(";");
            ArrayList<String> studentTags = new ArrayList<>();
            for(String id : splitTags) {
                if(tags.containsKey(Long.valueOf(id)))
                    studentTags.add(tags.get(Long.valueOf(id)).getTitle());
            }

            document.add(new Paragraph("Nombre: " + student.getFullname()));
            document.add(new Paragraph("Email: " + student.getEmail()));
            document.add(new Paragraph("Teléfono: " + student.getPhone()));
            document.add(new Paragraph("País: " + student.getCountry()));
            document.add(new Paragraph("Ciudad: " + student.getCity()));
            document.add(new Paragraph("Traslado: " + (student.getCanMove() ? "SÍ" : "NO")));
            document.add(new Paragraph("Presencialidad: " + (student.getWorkType() == WorkType.REMOTE ? "REMOTO" : "PRESENCIAL")));
            document.add(new Paragraph("Etiquetas: " + String.join(", ", studentTags)));

            Image photo = Image.getInstance(student.getPhoto());
            photo.scaleToFit(150, 150);
            photo.setAlignment(Chunk.ALIGN_RIGHT);
            document.add(photo);

            document.close();
        } catch (DocumentException e) {
            System.out.println("Error while trying to create the document: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println("Can't find image: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing PDF: " + e.getMessage());
        }
    }
}
