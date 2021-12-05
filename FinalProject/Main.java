package FinalProject;

public class Main {
    public static void main(String[] args) {
        AnimationPlayer animationPlayer = new AnimationPlayer();
        animationPlayer.loadAnimationFromFile("data.txt");
        animationPlayer.run();
    }
}
