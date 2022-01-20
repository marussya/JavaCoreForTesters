package lesson1.ObstacleCourse;

import lesson1.TeamMember;

public class Jumping extends Obstacle{
    public Jumping(int difficulty) {
        super(difficulty);
    }

    @Override
    public void goChallenge(TeamMember member) {
        member.jump(super.getDifficulty());
    }
}
