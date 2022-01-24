package lesson1.ObstacleCourse;


import lesson1.myTeam.TeamMember;

public abstract class Obstacle {
    private int difficulty;

    public  Obstacle(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void goChallenge (TeamMember member) {

    }

}
