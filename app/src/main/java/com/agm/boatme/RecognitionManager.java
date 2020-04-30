package com.agm.boatme;

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

    public RecognitionResponse getAnswer(String result) {
        if (lastRecognizedMsg != result) {
            print("RECOGNIZING: " + result); // Testing purposes

            if (result.contains("nueva") && result.contains("ruta")) {
                return new RecognitionResponse(
                        true,
                        "Muy bien, ¿a donde vamos hoy?",
                        enableTalkback && true
                );
            } else if (result.contains("puerto")) {
                int index = result.indexOf("puerto");
                return new RecognitionResponse(
                        true,
                        "Genial, nos vamos al " + result.substring(index) + ". ¿Desde dónde salimos?",
                        enableTalkback && true
                );
            }

            lastRecognizedMsg = result;
            return new RecognitionResponse(
                    false,
                    "Lo siento, no te entendí. Dijiste: " + result,
                    enableTalkback && false
            );
        }

        return new RecognitionResponse(
                false,
                "",
                false
        );
    }
}
