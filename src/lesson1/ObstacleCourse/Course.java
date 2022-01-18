package lesson1.ObstacleCourse;

import lesson1.Status;
import lesson1.myTeam.Team;
import lesson1.myTeam.TeamMember;

public class Course {
    private Obstacle[] obstacles;

    public Course(Obstacle... obstacles) {
        this.obstacles = obstacles;
    }

    public void doIt(Team team) {
        for (TeamMember member: team.getMembers()) {
            for (Obstacle obs :obstacles) {
                obs.goChallenge(member);
                if (member.getStatus() == Status.NoPassedDistance) {
                    break;
                }
            }
        }
    }

}
