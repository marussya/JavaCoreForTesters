package lesson1.ObstacleCourse;

import lesson1.TeamMember;

public class Swimming extends Obstacle {
    public Swimming(int difficulty){
        super(difficulty);
    }
    @Override
    public void goChallenge(TeamMember member) {
        member.swim(super.getDifficulty());
    }
}
