package lesson1;

import lesson1.ObstacleCourse.Course;
import lesson1.ObstacleCourse.Cross;
import lesson1.ObstacleCourse.Jumping;
import lesson1.ObstacleCourse.Swimming;
import lesson1.myTeam.Team;
import lesson1.myTeam.TeamMember;

public class Main {

    public static void main(String[] args) {
        Team dreamTeam = new Team("MyTeam",
                new TeamMember("player1", 8),
                new TeamMember("player2", 8),
                new TeamMember("player3", 7),
                new TeamMember("player4", 5));

        Course course = new Course(
                new Cross(6),
                new Jumping(6),
                new Swimming(6));

        course.doIt(dreamTeam);
        dreamTeam.showResults();
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
