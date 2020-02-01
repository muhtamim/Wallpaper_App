package bluemwall.mhussain96.com.bluemwall.Model.AnalyzeModel;

/**
 * Created by Muhtamim Fuwad Nahid on 3/16/2018.
 */

public class ComputerVision {
    private Adult adult;
    private String requestId;
    private Metadata metadata;

    public ComputerVision() {
    }

    public ComputerVision(Adult adult, String requestId, Metadata metadata) {
        this.adult = adult;
        this.requestId = requestId;
        this.metadata = metadata;
    }

    public Adult getAdult() {
        return adult;
    }

    public void setAdult(Adult adult) {
        this.adult = adult;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
