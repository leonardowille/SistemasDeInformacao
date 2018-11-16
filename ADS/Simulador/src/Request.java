import java.util.Date;

public class Request {

    private Long startedTime;
    private Long finishedTime;
    private Long totalTime;

    public Request() {
        this.startedTime = new Date().getTime();
    }

    public void finishRequest() {
        this.finishedTime = new Date().getTime();
        this.totalTime = finishedTime - startedTime;
    }

    public Long getStartedTime() {
        return startedTime;
    }

    public Long getFinishedTime() {
        return finishedTime;
    }

    public Long getTotalTime() {
        return totalTime;
    }
}
