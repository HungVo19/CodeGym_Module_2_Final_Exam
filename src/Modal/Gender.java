package Modal;

import java.io.Serializable;

public class Gender implements Serializable {
    private String status;

    public Gender(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
