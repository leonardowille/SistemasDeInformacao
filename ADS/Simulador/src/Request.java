import java.util.Date;

public class Request {

    private Long startedTime;
    private Long totalTime;

    public Request() {
        this.startedTime = new Date().getTime();
    }

    public void finishRequest() {
        this.totalTime = new Date().getTime() - startedTime;
    }

    public Long getTotalTime() {
        return totalTime;
    }
}
