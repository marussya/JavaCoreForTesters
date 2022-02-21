package lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp9 {
    /*
    1. Написать функцию, принимающую список Student и возвращающую список
     уникальных курсов, на которые подписаны студенты.

    2. Написать функцию, принимающую на вход список Student и возвращающую список
    из трех самых любознательных (любознательность определяется количеством курсов).

    3. Написать функцию, принимающую на вход список Student и экземпляр Course,
    возвращающую список студентов, которые посещают этот курс.
     */
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Олег", Arrays.asList(new Course("Математика"),
                new Course("История"),
                new Course("Философия"),
                new Course("Психология"))));
        students.add(new Student("Антон", Arrays.asList(new Course("Математика"),
                new Course("История"),
                new Course("Философия"),
                new Course("Психология"))));
        students.add(new Student("Ирина", Arrays.asList(new Course("Математика"),
                new Course("История"),
                new Course("Биология"),
                new Course("Философия"),
                new Course("Физика"),
                new Course("Психология"))));
        students.add(new Student("Елена", Arrays.asList(new Course("Математика"),
                new Course("История"),
                new Course("Биология"),
                new Course("Философия"),
                new Course("Физика"),
                new Course("Рисование"),
                new Course("Психология"))));
        students.add(new Student("Максим", Arrays.asList(new Course("Математика"),
                new Course("История"),
                new Course("Биология"),
                new Course("Психология"))));
        students.add(new Student("Илья", Arrays.asList(new Course("Математика"),
                new Course("Биология"),
                new Course("Психология"))));

        //1
        System.out.println(students.stream()
                .map(s -> s.getCourses())
                .flatMap(f -> f.stream())
                .collect(Collectors.toSet()));

        //2
        System.out.println(students.stream()
                .sorted((s1, s2) -> s2.getCourses().size() - s1.getCourses().size())
                        .limit(3)
                .collect(Collectors.toList()));

        //3
        Course course = new Course("Рисование");
        System.out.println(students.stream()
                .filter(s -> s.getCourses().contains(course))
                .collect(Collectors.toList()));
    }

}
