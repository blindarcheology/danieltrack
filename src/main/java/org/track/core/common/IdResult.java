package org.track.core.common;

/**
 * @Descriptor ip toolKit
 * @author blindarcheology@hotmail.com
 * @time 2020/10/2 13:54:50
 */
public class IdResult {

    /**
     * system distributed id
     */
    private long id;

    /**
     * Obtain the id status.
     * this result <code>status</code> contain "success" and "exception"
     */
    private Status status;

    public IdResult() {
    }

    public IdResult(long id, Status status) {
        this.id = id;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "IdResult{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
