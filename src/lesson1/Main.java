package lesson1;

import lesson1.ObstacleCourse.Course;
import lesson1.ObstacleCourse.Cross;
import lesson1.ObstacleCourse.Jumping;
import lesson1.ObstacleCourse.Swimming;

public class Main {

    public static void main(String[] args) {
        Team myTeam = new Team("MyTeam",
                new TeamMember("player1", 6),
                new TeamMember("player2", 8),
                new TeamMember("player2", 10),
                new TeamMember("player2", 5));

        Course course = new Course(new Cross(8),
                new Jumping(5),
                new Swimming(7));
        course.doIt(myTeam);
    }
}





/*
* public static void main(String[] args) {
Course c = new Course(...); // Создаем полосу препятствий
Team team = new Team(...); // Создаем команду
c.doIt(team); // Просим команду пройти полосу
team.showResults(); // Показываем результаты
}
* */
