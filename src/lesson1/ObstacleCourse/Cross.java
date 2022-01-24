package lesson1.ObstacleCourse;

import lesson1.myTeam.TeamMember;

public class Cross extends Obstacle {
    public Cross(int difficulty) {
        super(difficulty);
    }

    @Override
    public void goChallenge(TeamMember member) {
        member.run(super.getDifficulty());
    }
}
