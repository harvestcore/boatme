package com.agm.boatme.data;

import java.util.ArrayList;

public class MessageManager {
    private static MessageManager messages;
    private ArrayList<Msg> data;

    public static MessageManager getInstance() {
        if (messages == null) {
            messages = new MessageManager();
        }

        return messages;
    }

    public Msg getByName(String name) {
        for (Msg m: data) {
            if (m.operation == name) {
                return m;
            }
        }

        return null;
    }

    private MessageManager() {
        // Add messages here
        data.add(new Msg("greeting", new String[]{
                "¡Hola! ¿Qué quieres hacer hoy?",
                "¡Cuanto tiempo!, ¿qué rumbo tomamos hoy?",
                "¡Bienvenido marinero!"
        }));

        data.add(new Msg("create-route", new String[]{
                "Muy bien, ¿a dónde vamos hoy?",
                "Estupendo, ¿dónde vamos?",
                "¡Nueva ruta! Suena bien, ¿próximo destino?"
        }));

        data.add(new Msg("to-port", new String[]{
                "Genial, nos vamos a placeholder, ¿desde dónde salimos?",
                "placeholder suena genial. ¿Cual es el destino?",
                "¡Gran elección! ¿Desde dónde zarpamos?"
        }));

        data.add(new Msg("from-port", new String[]{
                "Guay, me encanta el placeholder, ¿hacia dónde navegamos?",
                "Muy bien, ¿cuál es el siguiente punto en la ruta?",
                "Excelente elección, ¿siguiente parada?"
        }));

        data.add(new Msg("error", new String[]{
                "Lo siento, no te entendí. ¿Podrías repetirlo?",
                "No comprendo tu lo que me quieres decir, prueba de nuevo.",
                "¿Podrías repetir eso?"
        }));

        data.add(new Msg("error-message", new String[]{
                "Lo siento, no te entendí. ¿Podrías repetirlo?. Dijiste: placeholder",
                "No comprendo tu lo que me quieres decir, prueba de nuevo. Dijiste: placeholder",
                "¿Podrías repetir eso? Dijiste: placeholder"
        }));

        data.add(new Msg("not-found", new String[]{
                "Lo siento, no encuentro placeholder. Prueba de nuevo.",
                "No pude encontrar placeholder. ¿Podrías repetirlo?"
        }));

        data.add(new Msg("waypoint", new String[]{
                "Waypoint agregado correctamente.",
                "Muy bien, ¡tomamos rumbo a placeholder!",
                "Vale, agrego waypoint a la ruta."
        }));

        data.add(new Msg("remove-route", new String[]{
                "Ruta borrada.",
                "Limpiando ruta."
        }));
    }
}
