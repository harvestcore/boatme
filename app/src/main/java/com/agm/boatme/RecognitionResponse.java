package com.agm.boatme;

public class RecognitionResponse {
    boolean success;
    String response;
    boolean continueTalking;

    public RecognitionResponse(boolean success, String response, boolean continueTalking) {
        this.success = success;
        this.response = response;
        this.continueTalking = continueTalking;
    }
}
