package com.agm.boatme;


import com.agm.boatme.data.MessageManager;

public class RecognitionManager {
    private static RecognitionManager recognitionManager;
    private String lastRecognizedMsg = "-";
    private boolean enableTalkback = true;

    private RecognitionManager() {}

    public static RecognitionManager getInstance() {
        if (recognitionManager == null) {
            recognitionManager = new RecognitionManager();
        }

        return recognitionManager;
    }

    public void setEnableTalkback(boolean enableTalkback) {
        this.enableTalkback = enableTalkback;
    }

    public boolean isEnableTalkback() {
        return enableTalkback;
    }

    public void print(String s) {
        System.out.println(s);
    }

    private RecognitionResponse getResponse(String operation, String result, String placeholder) {
        switch (operation) {
            case "greeting":
            case "create-route":
                return new RecognitionResponse(
                        true,
                        MessageManager.getInstance().getByName(operation).random(),
                        true
                );

            case "remove-route":
            case "error":
                return new RecognitionResponse(
                    true,
                    MessageManager.getInstance().getByName(operation).random(),
                    false
                );

            case "waypoint":
            case "error-message":
            case "not-found":
                return new RecognitionResponse(
                        true,
                        MessageManager.getInstance().getByName(operation).random().replaceAll("placeholder", placeholder),
                        false
                );

            case "to-port":
            case "from-port":
                return new RecognitionResponse(
                        true,
                        MessageManager.getInstance().getByName(operation).random().replaceAll("placeholder", placeholder),
                        true
                );
        }

        return new RecognitionResponse(
                false,
                "",
                false
        );
    }

    public RecognitionResponse getAnswer(String result) {
        if (lastRecognizedMsg != result) {
            print("RECOGNIZING: " + result); // Testing purposes

            if (result.contains("hola")) {
                return this.getResponse("greeting", result, "");
            } else if (result.contains("nueva") && result.contains("ruta")) {
                return this.getResponse("create-route", result, "");
            } else if (result.contains("desde") && result.contains("puerto")) {
                int index = result.indexOf("puerto");
                String placeholder = result.substring(index);
                return this.getResponse("from-port", result, placeholder);
            } else if ((result.contains(" al ") || result.contains(" hacia ")) && result.contains("puerto")) {
                int index = result.indexOf("puerto");
                String placeholder = result.substring(index);
                return this.getResponse("to-port", result, placeholder);
            } else if ((result.contains(" borrar ") || result.contains(" cancelar ") || result.contains(" eliminar ")) && result.contains("ruta")) {
                return this.getResponse("remove-route", result, "");
            } else if (result.contains(" ir a ") || result.contains(" nuevo punto ")) {
                int index = result.indexOf(" ir a ");
                String placeholder = result.substring(index + 6);
                return this.getResponse("waypoint", result, placeholder);
            }

            lastRecognizedMsg = result;
            return this.getResponse("error-message", result, result);
        }

        return this.getResponse("", "", "");
    }
}
