package com.instabot.models;

import org.json.JSONException;
import org.json.JSONObject;


public class Relationship {

    public enum OutgoingStatus {
        FOLLOWS, REQUESTED, NONE
    }

    public static enum IncomingStatus {
        FOLLOWED_BY, REQUESTED_BY, BLOCKED_BY_YOU, NONE
    }

    public static enum Action {
        FOLLOW, UNFOLLOW, BLOCK, UNBLOCK, APPROVE, DENY
    }

    OutgoingStatus outgoingStatus;
    IncomingStatus incomingStatus;
    boolean targetUserIsPrivate;

    public Relationship(JSONObject obj) throws JSONException {
        setOutgoingStatus(obj.getString("outgoing_status"));
        setIncomingStatus(obj.getString("incoming_status"));
        this.targetUserIsPrivate = obj.getBoolean("target_user_is_private");
    }

    public OutgoingStatus getOutgoingStatus() {
        return outgoingStatus;
    }

    private void setOutgoingStatus(String outgoingStatus) {

        switch (outgoingStatus) {
            case "follows":
                this.outgoingStatus = OutgoingStatus.FOLLOWS;
                break;
            case "requested":
                this.outgoingStatus = OutgoingStatus.REQUESTED;
                break;
            case "none":
                this.outgoingStatus = OutgoingStatus.NONE;
                break;
        }
    }

    public IncomingStatus getIncomingStatus() {
        return incomingStatus;
    }

    public boolean targetUserIsPrivate() {
        return this.targetUserIsPrivate;
    }

    private void setIncomingStatus(String incomingStatus) {
        if (incomingStatus.equals("followed_by")) {
            this.incomingStatus = IncomingStatus.FOLLOWED_BY;
        } else if (incomingStatus.equals("requested_by")) {
            this.incomingStatus = IncomingStatus.REQUESTED_BY;
        } else if (incomingStatus.equals("blocked_by_you")) {
            this.incomingStatus = IncomingStatus.BLOCKED_BY_YOU;
        } else if (incomingStatus.equals("none")) {
            this.incomingStatus = IncomingStatus.NONE;
        }
    }

    /**
     * Checks if two relationship objects are equal. Relationships
     * are equal if their incoming and outgoing statuses are the same.
     *
     * @param o The object to be compared
     * @return True of the two objects are equal, false otherwise
     */
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;
        return ((Relationship) o).getIncomingStatus() == getIncomingStatus() &&
                ((Relationship) o).getOutgoingStatus() == getOutgoingStatus();
    }
}

