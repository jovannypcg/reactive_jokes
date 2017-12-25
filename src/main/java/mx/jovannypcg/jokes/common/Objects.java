package mx.jovannypcg.jokes.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Provides useful methods to deal with objects.
 *
 * @author Jovanny Cruz
 */
public class Objects {
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * JSON representation of the given object.
     *
     * @param o Object to convert.
     * @return JSON string representing the object.
     */
    public static String toString(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return String.valueOf(o);
        }
    }
}
